package com.levent_j.meizhi.activity;

import android.content.Context;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.levent_j.meizhi.R;
import com.levent_j.meizhi.base.BaseActivity;
import com.levent_j.meizhi.fragment.TypeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;

    private TypeFragmentAdapter typeFragmentAdapter;
    private FragmentManager fragmentManager;
    private long lastExitTime = 0;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        disableNavigationViewScrollbars(navigationView);
        toggle.syncState();

        typeFragmentAdapter = new TypeFragmentAdapter(getSupportFragmentManager(),this);

        for (int i=0;i<7;i++){
            typeFragmentAdapter.addFragment(TypeFragment.newInstance(i+1),i);
        }


        typeFragmentAdapter.replaceFragmrnt(0);

    }

    @Override
    protected void setListener() {
        fab.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - lastExitTime) > 3000) {
            Toast.makeText(this, "重复操作退出应用", Toast.LENGTH_SHORT).show();
            lastExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_1) {
            typeFragmentAdapter.replaceFragmrnt(0);
        } else if (id == R.id.nav_2) {
            typeFragmentAdapter.replaceFragmrnt(1);
        } else if (id == R.id.nav_3) {
            typeFragmentAdapter.replaceFragmrnt(2);
        } else if (id == R.id.nav_4) {
            typeFragmentAdapter.replaceFragmrnt(3);
        } else if (id == R.id.nav_5) {
            typeFragmentAdapter.replaceFragmrnt(4);
        } else if (id == R.id.nav_6) {
            typeFragmentAdapter.replaceFragmrnt(5);
        } else if (id == R.id.nav_7) {
            typeFragmentAdapter.replaceFragmrnt(6);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }

    public class TypeFragmentAdapter extends FragmentPagerAdapter{

        private final List<Fragment> typeFragmentList = new ArrayList<>();
        private final List<String> fragmentTitles = new ArrayList<>();
        private Context context;
        private FragmentManager fragmentManager;

        public TypeFragmentAdapter(FragmentManager fm,Context context) {
            super(fm);
            this.fragmentManager = fm;
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            return typeFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return typeFragmentList.size();
        }

        public void replaceFragmrnt(int position){
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,typeFragmentList.get(position));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            toolbar.setTitle(getPageTitle(position));
        }

        public void addFragment(Fragment fragment,int type){
            typeFragmentList.add(fragment);
            fragmentTitles.add(getTitle(type));
        }

        private String getTitle(int type) {
            switch (type){
                case 0:
                    return "性感美女";
                case 1:
                    return "日韩美女";
                case 2:
                    return "丝袜美腿";
                case 3:
                    return "美女照片";
                case 4:
                    return "美女写真";
                case 5:
                    return "清纯美女";
                default:
                    return "性感车模";
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }
    }

    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }


}
