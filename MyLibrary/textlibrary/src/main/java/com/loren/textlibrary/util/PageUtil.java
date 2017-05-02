package com.loren.textlibrary.util;

public class PageUtil {

	public static int getPages(int totalSize, int pageSize) {
		return (totalSize  +  pageSize  - 1) / pageSize;
	}
	public static int getRemainderPage(int totalSize,int pageSize){
		return totalSize%pageSize;
	}
}
