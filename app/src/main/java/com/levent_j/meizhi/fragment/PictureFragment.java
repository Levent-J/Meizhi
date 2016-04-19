package com.levent_j.meizhi.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.levent_j.meizhi.R;
import com.levent_j.meizhi.base.BaseFragment;
import com.levent_j.meizhi.utils.PicassoTransformation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.Bind;

/**
 * Created by levent_j on 16-4-19.
 */
public class PictureFragment extends BaseFragment{
    @Bind(R.id.iv_picture)
    ImageView mPicture;
    @Bind(R.id.loading_picture)
    AVLoadingIndicatorView avLoadingIndicatorView;

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
        avLoadingIndicatorView.setVisibility(View.VISIBLE);
        //用transform()变换
        loadImage(getActivity());


        //耗流量变换
//        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
//        float sw =   windowManager.getDefaultDisplay().getWidth();
//        float sh = windowManager.getDefaultDisplay().getHeight();
//        Picasso.with(getActivity()).load(URL + url).resize((int)sw,(int)sh).centerCrop().into(mPicture);
//        if (mPicture.getWidth()>mPicture.getHeight()){
//            Picasso.with(getActivity()).load(URL + url).rotate(90f).resize((int)sw,(int)sh).centerCrop().into(mPicture);
//        }


    }

    private void loadImage(final FragmentActivity activity) {
        Picasso picasso = Picasso.with(activity);
        PicassoTransformation picassoTransformation = new PicassoTransformation(getActivity(),true);
        picasso.load(URL + url).transform(picassoTransformation).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE).into(mPicture, new Callback() {
            @Override
            public void onSuccess() {
                avLoadingIndicatorView.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                avLoadingIndicatorView.setVisibility(View.GONE);

                Snackbar.make(getView(), "网络出问题啦～", Snackbar.LENGTH_LONG).setAction("重新加载", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadImage(activity);
                    }
                }).show();
            }
        });
    }

    @Override
    protected int setRootViewId() {
        return R.layout.fragment_picture;
    }
}
