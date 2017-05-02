package com.loren.textlibrary.util;

import android.text.Editable;
import android.widget.EditText;

/**
 *
 * @author yuegy
 *
 */
public class EditTextUtils {

    /**
     * 获取EditText 的值
     *
     * @param et
     * @return
     */
    public static String getText(EditText et) {
        if (null == et) {
            return "";
        }
        Editable editable = et.getText();
        if (null == editable) {
            return "";
        }
        return editable.toString();
    }

    /**
     * 设置焦点
     *
     * @param et
     */
    public static void setFocusable(EditText et) {
        et.setFocusable(true);
        et.setFocusableInTouchMode(true);
        et.requestFocus();
    }

    /**
     * 设置EditText 的值
     *
     * @param et
     * @return
     */
    public static void setText(EditText et, String val) {
        if (null == et || StringUtils.isBlank(val)) {
            return;
        }
        et.setText(val);
    }

}
