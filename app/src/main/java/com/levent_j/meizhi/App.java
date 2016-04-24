package com.levent_j.meizhi;

import android.app.Application;
import android.util.Log;

import cn.bmob.v3.Bmob;

/**
 * Created by levent_j on 16-4-24.
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "fc5d7abeb322e8bdafd944a8dfdbd55b");

    }
}
