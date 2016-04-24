package com.levent_j.meizhi.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by levent_j on 16-4-24.
 */
public class Test extends BmobObject{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
