package com.levent_j.meizhi.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.levent_j.meizhi.R;
import com.levent_j.meizhi.base.BaseActivity;
import com.levent_j.meizhi.bean.GalleryResult;
import com.levent_j.meizhi.bean.PictureResult;
import com.levent_j.meizhi.net.Api;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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

    private int id;
    private GalleryResult galleryResult;
    private Context context;
    private static final String IMAGE_URL = "http://tnfs.tngou.net/image";

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
        loadData();
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
            WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            double width =   windowManager.getDefaultDisplay().getWidth() * 2/3;
            double height = width / 0.618;
            Picasso.with(context).load(IMAGE_URL+galleryResult.getImg()).resize((int)width,(int)height).centerCrop().into(mGalleryImg);
            mGalleryTitle.setText(galleryResult.getTitle() + "(" + galleryResult.getSize() + "张)");
            mGalleryCount.setText("收藏数"+galleryResult.getCount());
            mGalleryRcount.setText("回复数："+galleryResult.getRcount());
            mGalleryFcount.setText("收藏数"+galleryResult.getFcount());
        }

        @Override
        public void onError(Throwable e) {
            msg("error-->message is"+e.getLocalizedMessage());
            galleryResultSubscriber.unsubscribe();
        }

        @Override
        public void onNext(GalleryResult result) {
            galleryResult = result;
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
                intent.putExtra("pictures", galleryResult);
                startActivity(intent);
                break;
        }
    }
}
