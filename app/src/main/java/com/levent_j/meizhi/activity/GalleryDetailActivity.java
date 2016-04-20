package com.levent_j.meizhi.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.levent_j.meizhi.R;
import com.levent_j.meizhi.base.BaseActivity;
import com.levent_j.meizhi.bean.GalleryResult;
import com.levent_j.meizhi.bean.Picture;
import com.levent_j.meizhi.net.Api;
import com.levent_j.meizhi.utils.PicassoTransformation;
import com.levent_j.meizhi.utils.Util;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import rx.Subscriber;

/**
 * Created by levent_j on 16-4-19.
 */
public class GalleryDetailActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.iv_gallery_detail_img)
    ImageView mGalleryImg;
    @Bind(R.id.tv_gallery_detail_title)
    TextView mGalleryTitle;
    @Bind(R.id.tv_gallery_detail_count)
    TextView mGalleryCount;
    @Bind(R.id.tv_gallery_detail_rcount)
    TextView mGalleryRcount;
    @Bind(R.id.tv_gallery_detail_fcount)
    TextView mGalleryFcount;
    @Bind(R.id.detail_toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_gallery_detail_time)
    TextView mGalleryTime;
    @Bind(R.id.rl_detail)
    RelativeLayout mRelativeLayout;
    @Bind(R.id.loading_detail)
    AVLoadingIndicatorView avLoadingIndicatorView;

    private int id;
    private GalleryResult galleryResult;
    private Context context;
    private static final String IMAGE_URL = "http://tnfs.tngou.net/image";
    private ArrayList<Picture> pictureResultArrayList;
    private ArrayList<String> urlList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gallery_detail;
    }

    @Override
    protected void init() {
        context = this;
        id = getIntent().getIntExtra("id",1);
        setSupportActionBar(toolbar);
        toolbar.setTitle("图片详情");
        pictureResultArrayList = new ArrayList<>();
        urlList = new ArrayList<>();
        loadData();
        avLoadingIndicatorView.setVisibility(View.VISIBLE);
        mRelativeLayout.setVisibility(View.GONE);
    }

    private void loadData() {
        //发起网络请求
        Api.getInstance().getGalleryDetail(id,galleryResultSubscriber);

    }

    @Override
    protected void setListener() {
        mGalleryImg.setOnClickListener(this);
    }

    private Subscriber<GalleryResult> galleryResultSubscriber = new Subscriber<GalleryResult>() {
        @Override
        public void onCompleted() {
            Picasso picasso = Picasso.with(getApplicationContext());
            PicassoTransformation picassoTransformation = new PicassoTransformation(getApplicationContext(),false);
            picasso.load(IMAGE_URL+galleryResult.getImg()).transform(picassoTransformation).into(mGalleryImg, new Callback() {
                @Override
                public void onSuccess() {
                    avLoadingIndicatorView.setVisibility(View.GONE);
                    mRelativeLayout.setVisibility(View.VISIBLE);
                }

                @Override
                public void onError() {

                }
            });
//
            mGalleryTitle.setText(galleryResult.getTitle() + "(" + galleryResult.getSize() + "张)");
            mGalleryCount.setText("访问数:"+galleryResult.getCount());
            mGalleryRcount.setText("回复数："+galleryResult.getRcount());
            mGalleryFcount.setText("收藏数:" + galleryResult.getFcount());
            mGalleryTime.setText(Util.getDate(galleryResult.getTime()));

            for (int i=0;i<galleryResult.getList().length;i++){
                urlList.add(galleryResult.getList()[i].getSrc());
            }
        }

        @Override
        public void onError(Throwable e) {
            finish();
            msg("error-->message is" + e.getLocalizedMessage());
        }

        @Override
        public void onNext(GalleryResult result) {
            if (result.isStatus()){
                galleryResult = result;
            }else {
                msg("error-->false");
            }

        }

        @Override
        public void onStart() {
            super.onStart();
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_gallery_detail_img:
                Intent intent = new Intent(this,PictureActivity.class);
                intent.putStringArrayListExtra("urls",urlList);
                msg("urls---<<<"+urlList.size());
                startActivity(intent);
                break;
        }
    }
}
