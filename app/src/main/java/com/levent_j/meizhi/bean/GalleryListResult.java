package com.levent_j.meizhi.bean;

import java.util.List;

/**
 * Created by levent_j on 16-4-18.
 */
public class GalleryListResult {

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Gallery[] getTngou() {
        return tngou;
    }

    public void setTngou(Gallery[] tngou) {
        this.tngou = tngou;
    }

    private boolean status;
    private int total;
    private Gallery[] tngou;
}
