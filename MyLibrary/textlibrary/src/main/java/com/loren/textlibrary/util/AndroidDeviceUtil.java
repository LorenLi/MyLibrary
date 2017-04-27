package com.loren.textlibrary.util;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * 获取手机一些参数信息
 * @author: Loren
 * @create: 2017-4-27下午1:52:52
 * @desc: 
 *
 */
public class AndroidDeviceUtil {

	public static String getDeviceIMEI(Context context){
		TelephonyManager mTelephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = mTelephonyMgr.getDeviceId();
		return imei;
	}
	
	public static String getDeviceIMSI(Context context){
		TelephonyManager mTelephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		String imsi = mTelephonyMgr.getSubscriberId();
		return imsi;
	}
}
