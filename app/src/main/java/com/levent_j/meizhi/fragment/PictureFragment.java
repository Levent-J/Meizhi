package com.levent_j.meizhi.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.levent_j.meizhi.R;
import com.levent_j.meizhi.base.BaseFragment;
import com.levent_j.meizhi.utils.PicassoTransformation;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.Bind;

/**
 * Created by levent_j on 16-4-19.
 */
public class PictureFragment extends BaseFragment{
    @Bind(R.id.iv_picture)
    ImageView mPicture;

    private static final String KEY = "url";
    private static final String URL = "http://tnfs.tngou.net/image";
    private String url;

    public static PictureFragment newInstance(String url){
        PictureFragment pictureFragment = new PictureFragment();
        Bundle args = new Bundle();
        args.putString(KEY,url);
        pictureFragment.setArguments(args);
        return pictureFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            url = getArguments().getString(KEY);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //用transform()变换
        Picasso picasso = Picasso.with(getActivity());
        PicassoTransformation picassoTransformation = new PicassoTransformation(getActivity(),true);
        picasso.load(URL + url).transform(picassoTransformation).into(mPicture);

        //耗流量变换
//        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
//        float sw =   windowManager.getDefaultDisplay().getWidth();
//        float sh = windowManager.getDefaultDisplay().getHeight();
//        Picasso.with(getActivity()).load(URL + url).resize((int)sw,(int)sh).centerCrop().into(mPicture);
//        if (mPicture.getWidth()>mPicture.getHeight()){
//            Picasso.with(getActivity()).load(URL + url).rotate(90f).resize((int)sw,(int)sh).centerCrop().into(mPicture);
//        }


    }

    @Override
    protected int setRootViewId() {
        return R.layout.fragment_picture;
    }
}
