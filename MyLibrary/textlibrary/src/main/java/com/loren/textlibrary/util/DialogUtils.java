/*
 * Copyright (C) 2013 Peng fei Pan <sky@xiaopan.me>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.loren.textlibrary.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * 对话框工具箱
 *
 * @author xiaopan
 */
public class DialogUtils {
    /**
     * 显示一个对话框
     *
     * @param context 上下文对象，最好给Activity，否则需要android.permission.SYSTEM_ALERT_WINDOW
     * @param title 标题
     * @param message 消息
     * @param confirmButton 确认按钮
     * @param confirmButtonClickListener 确认按钮点击监听器
     * @param centerButton 中间按钮
     * @param centerButtonClickListener 中间按钮点击监听器
     * @param cancelButton 取消按钮
     * @param cancelButtonClickListener 取消按钮点击监听器
     * @param onShowListener 显示监听器
     * @param cancelable 是否允许通过点击返回按钮或者点击对话框之外的位置关闭对话框
     * @param onCancelListener 取消监听器
     * @param onDismissListener 销毁监听器
     * @return 对话框
     */
    public static AlertDialog showAlert(Context context, String title, String message, String confirmButton, DialogInterface.OnClickListener confirmButtonClickListener, String centerButton, DialogInterface.OnClickListener centerButtonClickListener, String cancelButton, DialogInterface.OnClickListener cancelButtonClickListener, DialogInterface.OnShowListener onShowListener, boolean cancelable, DialogInterface.OnCancelListener onCancelListener, DialogInterface.OnDismissListener onDismissListener) {
        AlertDialog.Builder promptBuilder = new AlertDialog.Builder(context);
        if (title != null) {
            promptBuilder.setTitle(title);
        }
        if (message != null) {
            promptBuilder.setMessage(message);
        }
        if (confirmButton != null) {
            promptBuilder.setPositiveButton(confirmButton,
                    confirmButtonClickListener);
        }
        if (centerButton != null) {
            promptBuilder.setNeutralButton(centerButton,
                    centerButtonClickListener);
        }
        if (cancelButton != null) {
            promptBuilder.setNegativeButton(cancelButton,
                    cancelButtonClickListener);
        }
        promptBuilder.setCancelable(true);
        if (cancelable) {
            promptBuilder.setOnCancelListener(onCancelListener);
        }
        AlertDialog alertDialog = promptBuilder.create();
        if (!(context instanceof Activity)) {
            alertDialog.getWindow()
                       .setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        alertDialog.setOnDismissListener(onDismissListener);
        alertDialog.setOnShowListener(onShowListener);
        alertDialog.show();
        return alertDialog;
    }


    /**
     * 显示一个对话框
     *
     * @param context 上下文对象，最好给Activity，否则需要android.permission.SYSTEM_ALERT_WINDOW
     * @param title 标题
     * @param message 消息
     * @param confirmButton 确认按钮
     * @param confirmButtonClickListener 确认按钮点击监听器
     * @param cancelButton 取消按钮
     * @param cancelButtonClickListener 取消按钮点击监听器
     * @return 对话框
     */
    public static AlertDialog showAlert(Context context, String title, String message, String confirmButton, DialogInterface.OnClickListener confirmButtonClickListener, String cancelButton, DialogInterface.OnClickListener cancelButtonClickListener) {
        return showAlert(context, title, message, confirmButton,
                confirmButtonClickListener, null, null, cancelButton,
                cancelButtonClickListener, null, true, null, null);
    }

    /**
     * 带输入框对话框
     *
     * @param context
     * @param title
     *            标题
     * @param message
     *            内容
     * @param confirmClick
     *            确定
     */
    public static void showDialogEditText(Context context, String title,
                                          String message,  String confirmButton,DialogInterface.OnClickListener confirmClick, String cancelButton,
                                          DialogInterface.OnClickListener cancelClick) {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        adb.setTitle(title);
        adb.setMessage(message);
        adb.setView(new EditText(context));
        //adb.setIcon(android.R.drawable.ic_dialog_info);
        adb.setPositiveButton(confirmButton, confirmClick);
        adb.setNegativeButton(cancelButton, cancelClick);
        adb.setCancelable(false);
        adb.show();
    }

    /**
     * 显示一个提示框
     *
     * @param context 上下文对象，最好给Activity，否则需要android.permission.SYSTEM_ALERT_WINDOW
     * @param message 提示的消息
     * @param confirmButton 确定按钮的名字
     */
    public static AlertDialog showPrompt(Context context, String message, String confirmButton) {
        return showAlert(context, null, message, confirmButton, null, null,
                null, null, null, null, true, null, null);
    }

    /**
     * 提示信息 选择-单选
     *
     * @param clickListener
     *            确定事件
     */
    public static void showDialogSelect(Context context, String items[],String cancelButton,
                                        DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        //adb.setTitle(R.string.dialog_select);
        adb.setIcon(android.R.drawable.ic_dialog_info);
        adb.setSingleChoiceItems(items, 0, clickListener);
        adb.setNegativeButton(cancelButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        adb.setCancelable(false);
        adb.show();
    }

    /**
     * 提示信息 选择-单选
     *
     * @param clickListener
     *            确定事件
     */
    public static void showDialogSelect2(Context context, String items[],String cancelButton,
                                         DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        //adb.setTitle(R.string.dialog_select);
        adb.setIcon(android.R.drawable.ic_dialog_info);
        adb.setItems(items, clickListener);
        adb.setNegativeButton(cancelButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        adb.setCancelable(false);
        adb.show();
    }

    /**
     * 提示信息 列表对话框
     *
     * @param items
     *            确定事件
     */
    public static void showDialog(Context context, String confirmButton,String items[]) {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        //adb.setTitle(R.string.dialog_list_box);
        adb.setIcon(android.R.drawable.ic_dialog_info);
        adb.setItems(items, null);
        adb.setNegativeButton(confirmButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        adb.setCancelable(false);
        adb.show();
    }

    /**
     * 提示信息 选择-多选
     *
     * @param clickListener
     *            确定事件
     */
    public static void showDialogs(Context context, String items[],String confirmButton,String cancelButton,
                                   DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        //adb.setTitle(R.string.dialog_select);
        adb.setIcon(android.R.drawable.ic_dialog_info);
        adb.setMultiChoiceItems(items, null, null);
        adb.setPositiveButton(confirmButton, clickListener);
        adb.setNegativeButton(cancelButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        adb.setCancelable(false);
        adb.show();
    }

    /**
     * 提示信息 显示图片
     *
     * @param bitmap
     *  确定事件
     */
    public static void showDialogImage(Context context, String items[],String confirmButton,
                                       Bitmap bitmap) {
        ImageView img = new ImageView(context);
        img.setImageBitmap(bitmap);
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        //adb.setTitle(R.string.dialog_image_box);
        adb.setView(img);
        adb.setNegativeButton(confirmButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        adb.setCancelable(false);
        adb.show();
    }

}
