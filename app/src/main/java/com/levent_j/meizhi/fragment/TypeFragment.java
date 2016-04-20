package com.levent_j.meizhi.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.levent_j.meizhi.R;
import com.levent_j.meizhi.adapter.GalleryAdapter;
import com.levent_j.meizhi.base.BaseFragment;
import com.levent_j.meizhi.bean.Gallery;
import com.levent_j.meizhi.bean.GalleryListResult;
import com.levent_j.meizhi.net.Api;
import com.levent_j.meizhi.utils.SpaceItemDecoration;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by levent_j on 16-4-18.
 */
public class TypeFragment extends BaseFragment{
    @Bind(R.id.rv_gallery)
    RecyclerView mGalleryRecycler;
    @Bind(R.id.loading_type)
    public AVLoadingIndicatorView avLoadingIndicatorView;
//    @Bind(R.id.btn_more)
//    Button more;
//    @Bind(R.id.srl_type)
//    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.mrl_type)
    MaterialRefreshLayout refreshLayout;

    private static final String TYPE = "TYPE";
    private int type;
    private int page;
    private int rows;
    private int spacingInPixels;
    private List<Gallery> galleryList;
    private GalleryAdapter galleryAdapter;

    public static TypeFragment newInstance(int type){
        TypeFragment typeFragment = new TypeFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        typeFragment.setArguments(args);
        return typeFragment;
    }

    @Override
    protected int setRootViewId() {
        return R.layout.fragment_type;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            type = getArguments().getInt(TYPE);
        }
        spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space);
        page = 1;
        rows = 10;
        galleryList = new ArrayList<>();
        galleryAdapter = new GalleryAdapter(getActivity());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGalleryRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mGalleryRecycler.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        mGalleryRecycler.setHasFixedSize(true);
        mGalleryRecycler.setAdapter(galleryAdapter);

        loadData();
//        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mGalleryRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//                mGalleryRecycler.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
//                mGalleryRecycler.setHasFixedSize(true);
//                mGalleryRecycler.setAdapter(galleryAdapter);
//                Api.getInstance().getGalleryList(page, rows, type, galleryListResultSubscriber);
//            }
//        });
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                loadData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                rows += 10;
                loadData();
            }
        });
    }

    private void loadData() {
        msg("第"+type+"种");
        //发起网络请求
        galleryList.clear();
        Api.getInstance().getGalleryList(page,rows,type, galleryListResultObserver);
    }

    private Observer<GalleryListResult> galleryListResultObserver = new Observer<GalleryListResult>() {
        @Override
        public void onCompleted() {
            avLoadingIndicatorView.setVisibility(View.GONE);
            refreshLayout.finishRefresh();
            refreshLayout.finishRefreshLoadMore();
            for (Gallery gallery:galleryList){
                msg("completed-->url is"+gallery.getImg());
            }
            galleryAdapter.updateGalleryAdapter((ArrayList<Gallery>) galleryList);
        }

        @Override
        public void onError(Throwable e) {
            avLoadingIndicatorView.setVisibility(View.GONE);
            msg("error-->message is"+e.getMessage());
        }

        @Override
        public void onNext(GalleryListResult galleryListResult) {
            if (!galleryListResult.isStatus()){
                msg("next-->status is false");
            }else {
                Collections.addAll(galleryList, galleryListResult.getTngou());
            }
        }

    };


}
