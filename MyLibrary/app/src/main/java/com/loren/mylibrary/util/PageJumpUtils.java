package com.loren.mylibrary.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.loren.mylibrary.R;


/**
 * @author: Loren
 * @create: 2016-7-1上午10:33:42
 * @desc: Activity 跳转工具
 * 
 */
public class PageJumpUtils {
	private static final String TAG = PageJumpUtils.class.getSimpleName();
	
	public static void pageJump(Context context, Class<?> toClass) {
		((Activity)context).startActivity(new Intent(context, toClass));
		((Activity)context).overridePendingTransition(R.anim.in_anim,
				R.anim.out_anim);
	}
	
	public static void pageJumpAddString(Context context, Class<?> toClass,
			String key, String value){
		Intent intent = new Intent(context, toClass);
		Bundle bundle = new Bundle();
		bundle.putString(key, value);
		intent.putExtras(bundle);
		((Activity)context).startActivity(intent);
		((Activity)context).overridePendingTransition(R.anim.in_anim,
				R.anim.out_anim);
	}
	
	public static String pageJumpGetString(Context context, String key) {
		if(context != null){
			Intent intent = ((Activity)context).getIntent();
			if(intent != null){
				Bundle bundle = intent.getExtras();
				if(bundle != null){
					return bundle.getString(key, "");
				}
			}
		}
		return "";
	}
	
	public static void pageJumpAddBoolean(Context context, Class<?> toClass,
			String key, boolean value){
		Intent intent = new Intent(context, toClass);
		Bundle bundle = new Bundle();
		bundle.putBoolean(key, value);
		intent.putExtras(bundle);
		((Activity)context).startActivity(intent);
		((Activity)context).overridePendingTransition(R.anim.in_anim,
				R.anim.out_anim);
	}
	
	public static boolean pageJumpGetBoolean(Context context, String key) {
		if(context != null){
			Intent intent = ((Activity)context).getIntent();
			if(intent != null){
				Bundle bundle = intent.getExtras();
				if(bundle != null){
					return bundle.getBoolean(key, false);
				}
			}
		}
		return false;
	}

	public static void pageJumpAddInt(Context context, Class<?> toClass,
			String key, int value) {
		Intent intent = new Intent(context, toClass);
		Bundle bundle = new Bundle();
		bundle.putInt(key, value);
		intent.putExtras(bundle);
		((Activity)context).startActivity(intent);
		((Activity)context).overridePendingTransition(R.anim.in_anim,
				R.anim.out_anim);
	}

	public static int pageJumpGetInt(Context context, String key) {
		return ((Activity)context).getIntent().getExtras().getInt(key, -1);
	}

	/**
	 * 根据包名打开指定已安装APP
	 * @param context
	 * @param packageName
	 * @return
	 */
	public static boolean startSpecifyActivity(Context context,
			String packageName) {
		Intent intent = context.getPackageManager().getLaunchIntentForPackage(
				packageName);
		if (intent != null) {
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			try {
				context.startActivity(intent);
				return true;
			} catch (ActivityNotFoundException e) {
				LogUtils.e(TAG, "package not found.");
				return false;
			}
		}
		return false;
	}
	
	public static void pageJumpHome(Activity activity){
		 Intent intent = new Intent(Intent.ACTION_MAIN);
		 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 注意
	     intent.addCategory(Intent.CATEGORY_HOME);
	     activity.startActivity(intent);
	}
}
