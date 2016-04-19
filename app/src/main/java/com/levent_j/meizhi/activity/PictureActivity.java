package com.levent_j.meizhi.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.levent_j.meizhi.R;
import com.levent_j.meizhi.base.BaseActivity;
import com.levent_j.meizhi.bean.GalleryResult;
import com.levent_j.meizhi.bean.PictureResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by levent_j on 16-4-19.
 */
public class PictureActivity extends BaseActivity{

    private GalleryResult galleryResult;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture;
    }

    @Override
    protected void init() {
        galleryResult = getIntent().getParcelableExtra("pictures");
        Toa("size is--"+galleryResult.getSize());
    }

    @Override
    protected void setListener() {

    }

    private class PictureFragmentAdapter extends FragmentPagerAdapter {
        private Context context;
        private List<Fragment> fragmentList;

        public PictureFragmentAdapter(FragmentManager fm,Context context) {
            super(fm);
            this.context = context;
            fragmentList = new ArrayList<>();
        }

        public void addFragment(Fragment fragment){
            fragmentList.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
