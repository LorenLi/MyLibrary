package com.loren.textlibrary.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

/**
 * 
 * @author: Loren
 * @create: 2016-7-1上午10:33:18
 * @desc: Toast消息封装
 *
 */
public class MyToast {
	private static Handler handler = new Handler(Looper.getMainLooper());
	private static Toast toast = null;
	private static Object synObj = new Object();
	private static final int SHOW_TIME = 3 *1000;
	
	//String 消息
	/**
	 * 
	 * @param act
	 * @param msg
	 * @param isCover true 消息覆盖,false 消息不覆盖
	 */
	public static void showMessage(final Context act, final String msg,boolean isCover) {
		if(isCover)
		showMessage(act, msg, Toast.LENGTH_SHORT);
		else
		showMessageNotCover(act, msg, Toast.LENGTH_SHORT);
			
	}
	/**
	 * Toast发送String消息，默认Toast.LENGTH_SHORT,多个消息不覆盖
	 * @param act
	 * @param msg
	 */
	public static void showMessage(Context act,String msg){
		//showMessageNotCover(act,msg,Toast.LENGTH_SHORT);
		showMessage(act, msg, SHOW_TIME, true);
	}
	
	/**
	 * Toast发送String消息，默认Toast.LENGTH_LONG,true 消息覆盖,false 消息不覆盖
	 */
	public static void showMessageLong(final Context act, final String msg,boolean isCover) {
		if(isCover)
		showMessage(act, msg, Toast.LENGTH_LONG);
		else
		showMessageNotCover(act, msg, Toast.LENGTH_LONG);
	}

	/**
	 * Toast发送String消息，默认Toast.LENGTH_LONG,多个消息不覆盖
	 * @param act
	 * @param msg
	 */
	public static void showMessageLong(Context act,String msg){
		showMessageNotCover(act, msg, Toast.LENGTH_LONG);
	}
	
	/**
	 * Toast发送String消息,多个消息覆盖
	 */
	private static void showMessage(final Context act, final String msg,
			final int len) {
		new Thread(new Runnable() {
			public void run() {
				handler.post(new Runnable() {

					@Override
					public void run() {
						synchronized (synObj) {
							if (toast != null) {
								toast.cancel();
								toast.setText(msg);
								toast.setDuration(len);
							} else {
								toast = Toast.makeText(act, msg, len);
							}
							toast.show();
						}
					}
				});
			}
		}).start();
	}
	/**
	 * Toast发送String消息,多个消息不覆盖
	 * @param act
	 * @param msg
	 * @param len
	 */
	private static void showMessageNotCover(Context act, String msg,
			int len){
		toast = Toast.makeText(act, msg, len);
		toast.show();
	}
	
	//int 消息
	
	/**
	 * Toast发送int消息，默认Toast.LENGTH_SHORT,true 消息覆盖,false 消息不覆盖
	 */
	public static void showMessage(final Context act, final int msg,boolean isCover) {
		if(isCover)
		showMessage(act, msg, Toast.LENGTH_SHORT);
		else
		showMessageNotCover(act,msg,Toast.LENGTH_SHORT);
	}
	/**
	 * Toast发送int消息，默认Toast.LENGTH_SHORT,多个消息不覆盖
	 * @param act
	 * @param msg
	 */
	public static void showMessage(Context act,int msg){
		//showMessageNotCover(act,msg,Toast.LENGTH_SHORT);
		showMessage(act, msg, SHOW_TIME, true);
	}
	
	/**
	 * Toast发送int消息，默认Toast.LENGTH_LONG,多个消息不覆盖
	 * @param act
	 * @param msg
	 */
	public static void showMessageLong(Context act,int msg){
		showMessageNotCover(act, msg, Toast.LENGTH_LONG);
	}
	
	/**
	 * Toast发送int消息，默认Toast.LENGTH_LONG,true 消息覆盖,false 消息不覆盖
	 */
	public static void showMessageLong(final Context act, final int msg,boolean isCover) {
		if(isCover)
		showMessage(act, msg, Toast.LENGTH_LONG);
		else
		showMessageNotCover(act, msg, Toast.LENGTH_LONG);
	}
	
	/**
	 *  Toast发送int消息,多个消息不覆盖
	 * @param act
	 * @param msg
	 * @param len
	 */
	private static void showMessageNotCover(Context act, int msg, int len) {
		toast = Toast.makeText(act, msg, len);
		toast.show();
	}


	
	/**
	 * Toast发送int消息,多个消息覆盖
	 */
	private static void showMessage(final Context act, final int msg,
			final int len) {
		new Thread(new Runnable() {
			public void run() {
				handler.post(new Runnable() {

					@Override
					public void run() {
						synchronized (synObj) {
							if (toast != null) {
								toast.cancel();
								toast.setText(msg);
								toast.setDuration(len);
							} else {
								toast = Toast.makeText(act, msg, len);
							}
							toast.show();
						}
					}
				});
			}
		}).start();
	}

	/**
	 * 关闭当前Toast
	 */
	public static void cancelCurrentToast() {
		if (toast != null) {
			toast.cancel();
		}
	}

	private final static int DEFAULT = 2000;
	private static int duration = 0;
	private static int currDuration = 0;
	
    private static Runnable mToastThread = new Runnable() {
    public void run() {
    	toast.show();
    	handler.postDelayed(mToastThread, DEFAULT);// 每隔2秒显示一次
        if (duration != 0) {
            if (currDuration <= duration) {
                currDuration += DEFAULT;
            } 
            else {
                cancel();
            }
        }
        
        }
    };
    
    public static void showMessageTime(Context context,int msg,int len){
         currDuration=DEFAULT;
         toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
         setText(msg);
         show(len);
    }
    
    public static void showMessageTime(Context context,String msg,int len){
        currDuration=DEFAULT;
        toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        setText(msg);
        show(len);
   }
    
    public static void showMessage(Context context,String msg,int len,boolean isDismiss){
    	if(isDismiss){
    		showMessageTime(context, msg, len);
    	}else{
    		showMessageTime(context, msg, len);
    	}
    }
    
    public static void showMessage(Context context,int msg,int len,boolean isDismiss){
    	if(isDismiss){
    		showMessageTime(context, msg, len);
    	}else{
    		showMessageTime(context, msg, len);
    	}
    }
    
    public static void setText(String text) {
    	toast.setText(text);
    }
    
    public static void setText(int text) {
    	toast.setText(text);
    }
    
    public static void setView(View view){
        toast.setView(view);
    }
    public static void show(int len) {
        duration = len;
        handler.post(mToastThread);
    }
    
    public static void cancel( ) {
    	handler.removeCallbacks(mToastThread);// 先把显示线程删除
        toast.cancel();// 把最后一个线程的显示效果cancel掉，就一了百了了
        currDuration = DEFAULT;
    }
}
