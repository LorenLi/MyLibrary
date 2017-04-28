package com.loren.mylibrary.text;


import com.loren.textlibrary.util.LogUtils;

public class MyLogUtils {
    public static final boolean DEBUG = true;

    public static void v(String msg) {
        if (DEBUG)
            LogUtils.v(msg, DEBUG);

    }

    public static void v(String tag, String msg) {
        if (DEBUG)
            LogUtils.v(tag, msg, DEBUG);
    }

    public static void d(String msg) {
        if (DEBUG)
            LogUtils.d(msg, DEBUG);

    }

    public static void d(String tag, String msg) {
        if (DEBUG)
            LogUtils.d(tag, msg, DEBUG);
    }

    public static void i(String msg) {
        if (DEBUG)
            LogUtils.i(msg, DEBUG);

    }

    public static void i(String tag, String msg) {
        if (DEBUG)
            LogUtils.i(tag, msg, DEBUG);
    }

    public static void w(String msg) {
        if (DEBUG)
            LogUtils.w(msg, DEBUG);

    }

    public static void w(String tag, String msg) {
        if (DEBUG)
            LogUtils.w(tag, msg, DEBUG);
    }

    public static void e(String msg) {
        if (DEBUG)
            LogUtils.e(msg, DEBUG);

    }

    public static void e(String tag, String msg) {
        if (DEBUG)
            LogUtils.e(tag, msg, DEBUG);
    }

}
 