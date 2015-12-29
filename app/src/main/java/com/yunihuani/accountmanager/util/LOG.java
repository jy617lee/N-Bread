package com.yunihuani.accountmanager.util;

import android.util.Log;

/**
 * Created by Seunghwan on 2015-12-28.
 */
public class LOG {
    private final static String APP_NAME = "ACCOUNT_MANAGER";

    public static int d(int i) {
        return d(i+"");
    }
    public static int d(String s) {
        return Log.d(APP_NAME, s);
    }
    public static int d(String tag, int i) {
        return d(tag, i+"");
    }
    public static int d(String tag, String s) {
        return d("["+tag+"] " + s);
    }

}
