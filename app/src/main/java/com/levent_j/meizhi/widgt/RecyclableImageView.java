package com.levent_j.meizhi.widgt;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by levent_j on 16-4-20.
 */
public class RecyclableImageView extends ImageView{
    public RecyclableImageView(Context context) {
        super(context);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setImageDrawable(null);
    }
}
