package com.loren.textlibrary.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author: Loren
 * @create: 2016-7-5上午10:23:50
 */
public class ToastUtils {
    private static Toast toast = null; //Toast的对象！

    /**
     * 多个覆盖式
     * @param context
     * @param id
     */
    public static void showToast(Context context, int id) {
         if (toast == null) {
            toast = Toast.makeText(context, id, Toast.LENGTH_SHORT);
        }
        else {
            toast.setText(id);
        }
        toast.show();
    }

    /**
     * 多个覆盖式
     * @param context
     * @param msg
     */
    public static void showToast(Context context,String msg){
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        else {
            toast.setText(msg);
        }
        toast.show();
    }
}
