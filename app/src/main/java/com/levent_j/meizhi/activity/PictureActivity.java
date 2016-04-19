package com.levent_j.meizhi.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.levent_j.meizhi.R;
import com.levent_j.meizhi.base.BaseActivity;
import com.levent_j.meizhi.fragment.PictureFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by levent_j on 16-4-19.
 */
public class PictureActivity extends BaseActivity{
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    private ArrayList<String> urlList;
    private PictureFragmentAdapter pictureFragmentAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture;
    }

    @Override
    protected void init() {
        urlList = getIntent().getStringArrayListExtra("urls");
        pictureFragmentAdapter = new PictureFragmentAdapter(getSupportFragmentManager(),this);
        for (int i=1;i<urlList.size();i++){
            pictureFragmentAdapter.addFragment(PictureFragment.newInstance(urlList.get(i)));
        }
        viewPager.setAdapter(pictureFragmentAdapter);
        viewPager.setOffscreenPageLimit(urlList.size());

    }

    @Override
    protected void setListener() {

    }

    private class PictureFragmentAdapter extends FragmentPagerAdapter{
        private Context context;
        private List<Fragment> fragmentList;

        public PictureFragmentAdapter(FragmentManager fm,Context context) {
            super(fm);
            this.context = context;
            fragmentList = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(PictureFragment pictureFragment){
            fragmentList.add(pictureFragment);
        }
    }

}
