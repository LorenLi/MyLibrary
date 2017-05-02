package com.loren.textlibrary.util;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

public class MemoryInfoUtil {
	public static final long BYTE_CONVERSION_MB = (1024*1024);

	public static long getAvailMemory(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
		am.getMemoryInfo(mi);
		return mi.availMem /BYTE_CONVERSION_MB;
	}
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static long getTotalMemory(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
		am.getMemoryInfo(mi);
		return mi.totalMem / BYTE_CONVERSION_MB;
	}
}
