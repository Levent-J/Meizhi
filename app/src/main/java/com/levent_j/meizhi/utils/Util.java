package com.levent_j.meizhi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by levent_j on 16-4-20.
 */
public class Util {
    public static String getDate(long d){
        Date date = new Date(d);
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy.MM.dd");
        return dataFormat.format(date);
    }
}
