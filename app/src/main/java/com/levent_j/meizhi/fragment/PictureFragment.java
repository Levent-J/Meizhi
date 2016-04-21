package com.levent_j.meizhi.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    @Bind(R.id.ll_gallery_detail_fail)
    LinearLayout mFailed;
    @Bind(R.id.btn_gallery_retry)
    Button mRetry;

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
                mFailed.setVisibility(View.VISIBLE);
                mPicture.setVisibility(View.GONE);
                mRetry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadImage(activity);
                    }
                });
                avLoadingIndicatorView.setVisibility(View.GONE);

                Snackbar.make(getView(), "网络出问题啦～", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected int setRootViewId() {
        return R.layout.fragment_picture;
    }
}
