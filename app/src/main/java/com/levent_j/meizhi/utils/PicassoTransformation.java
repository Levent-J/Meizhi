package com.levent_j.meizhi.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.WindowManager;

import com.squareup.picasso.Transformation;

/**
 * Created by levent_j on 16-4-19.
 */
public class PicassoTransformation implements Transformation{
    private Context context;
    private boolean isScale;

    public PicassoTransformation(Context context,boolean scale){
        this.context = context;
        this.isScale = scale;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        synchronized (PicassoTransformation.class){
            if (source==null){
                return null;
            }
            Bitmap resultBitmap = source.copy(source.getConfig(),true);
            Log.d("TRANS","h="+resultBitmap.getHeight()+"  w="+resultBitmap.getWidth());
            Canvas canvas = new Canvas(resultBitmap);
            Matrix matrix = new Matrix();

            int width = resultBitmap.getWidth();
            int height = resultBitmap.getHeight();

            //旋转
            if (resultBitmap.getHeight()<resultBitmap.getWidth()){
                matrix.postRotate(90);
                Log.d("TRANS", "Tran!");
            }else {
                matrix.postRotate(0);
                Log.d("TRANS", "No Tran!");
            }

            if (isScale){
                //缩放
                WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                float sw =   windowManager.getDefaultDisplay().getWidth();
                float sh = windowManager.getDefaultDisplay().getHeight();

                float scaleWidth = ((float) sw) / width;
                float scaleHeight = ((float) sh) / height;

                float scale = min(scaleHeight,scaleWidth);

                matrix.postScale(scaleHeight, scaleHeight);
            }


            Bitmap dstbmp = Bitmap.createBitmap(resultBitmap, 0, 0, width, height, matrix, true);
            canvas.drawBitmap(dstbmp, 0, 0, null);
            source.recycle();
            return dstbmp;
        }
    }

    private float min(float scaleHeight, float scaleWidth) {
        if (scaleHeight<scaleWidth)
            return scaleHeight;
        else
            return scaleWidth;
    }

    @Override
    public String key() {
        return "rotate";
    }
}
