package com.levent_j.meizhi.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.levent_j.meizhi.R;
import com.levent_j.meizhi.adapter.GalleryAdapter;
import com.levent_j.meizhi.base.BaseFragment;
import com.levent_j.meizhi.bean.Gallery;
import com.levent_j.meizhi.bean.GalleryListResult;
import com.levent_j.meizhi.net.Api;
import com.levent_j.meizhi.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import rx.Subscriber;

/**
 * Created by levent_j on 16-4-18.
 */
public class TypeFragment extends BaseFragment{
    @Bind(R.id.rv_gallery)
    RecyclerView mGalleryRecycler;

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
        rows = 20;
        galleryList = new ArrayList<>();
        galleryAdapter = new GalleryAdapter(getActivity());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
        mGalleryRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        mGalleryRecycler.setLayoutManager(new LinearLayoutManager(mGalleryRecycler.getContext()));
        mGalleryRecycler.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        mGalleryRecycler.setHasFixedSize(true);
        mGalleryRecycler.setAdapter(galleryAdapter);
    }

    private void loadData() {
        msg("第"+type+"种");
        //发起网络请求
        Api.getInstance().getGalleryList(page,rows,type, galleryListResultSubscriber);
    }

    private Subscriber<GalleryListResult> galleryListResultSubscriber = new Subscriber<GalleryListResult>() {
        @Override
        public void onCompleted() {
            for (Gallery gallery:galleryList){
                msg("completed-->url is"+gallery.getImg());
            }
            galleryAdapter.updateGalleryAdapter((ArrayList<Gallery>) galleryList);
        }

        @Override
        public void onError(Throwable e) {
            msg("error-->message is"+e.getMessage());
        }

        @Override
        public void onNext(GalleryListResult galleryListResult) {
            if (!galleryListResult.isStatus()){
                msg("next-->status is false");
                this.unsubscribe();
            }else {
                Collections.addAll(galleryList, galleryListResult.getTngou());
            }
        }

        @Override
        public void onStart() {
            super.onStart();
            msg("net-->start()");
            galleryList.clear();
        }
    };
}
