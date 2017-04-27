package com.loren.textlibrary.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.File;

/**
 * Author: Loren on 2017/4/27 11:32.
 */

public class SDCardUtils {

    /**
     * 获取SD卡的状态
     */
    public static String getState() {
        return Environment.getExternalStorageState();
    }


    /**
     * SD卡是否可用
     *
     * @return 只有当SD卡已经安装并且准备好了才返回true
     */
    public static boolean isAvailable() {
        return getState().equals(Environment.MEDIA_MOUNTED);
    }


    /**
     * 获取SD卡的根目录File
     *
     * @return null：不存在SD卡
     */
    public static File getRootDirectory() {
        return isAvailable() ? Environment.getExternalStorageDirectory() : null;
    }
    

    /**
     * 获取SD卡的相对根路径
     *
     * @return null：不存在SD卡
     */
    public static String getRootPath() {
        File rootDirectory = getRootDirectory();
        return rootDirectory != null ? rootDirectory.getPath() : null;
    }

    /**
     * 获取SD卡的绝对根路径
     *
     * @return null：不存在SD卡
     */
    public static String getAbsRootPath() {
        File rootDirectory = getRootDirectory();
        return rootDirectory != null ? rootDirectory.getAbsolutePath() : null;
    }
    
    /**
     * 获取机身内存的根目录File
     *
     * @return null：不存在SD卡
     */
    public static File getDataDirectory() {
        return Environment.getDataDirectory();
    }
    
    /**
     * 获取机身内存的根目录
     * @return
     */
    public static String getAbsDataPath(){
    	return Environment.getDataDirectory().getAbsolutePath();
    }

    /**
     * 获得SD卡总大小
     *
     * @return byte
     */
	public static long getSDTotalSizeByte() {
        File path = getRootDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return blockSize * totalBlocks;
    }

    
    /**
     * 获得SD卡总大小
     *
     * @return Byte KB MB GB
     */
    public static String getSDTotalSizeFormart(Context context) {
        return Formatter.formatFileSize(context, getSDTotalSizeByte());
    }
    
    /**
     * 获得sd卡剩余容量，即可用大小
     *
     * @return Byte
     */
    public static long getSDAvailableSizeByte() {
    	File path = getRootDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return blockSize * availableBlocks;
    }

    /**
     * 获得sd卡剩余容量，即可用大小
     *
     * @return Byte KB MB GB
     */
    public static String getSDAvailableSizeFormart(Context context) {
        return Formatter.formatFileSize(context, getSDAvailableSizeByte());
    }

    /**
     * 获得机身内存总大小
     *
     * @return Byte
     */
    public static long getRomTotalSizeByte() {
        File path = getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return blockSize * totalBlocks;
    }
    
    /**
     * 获得机身内存总大小
     *
     * @return Byte KB MB GB
     */
    public static String getRomTotalSizeFormat(Context context) {
        return Formatter.formatFileSize(context, getRomTotalSizeByte());
    }

    /**
     * 获得机身可用内存
     *
     * @return Byte
     */
    public static long getRomAvailableSizeByte() {
        File path = getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return blockSize * availableBlocks;
    }
    
    /**
     * 获得机身可用内存
     *
     * @return Byte KB MB GB
     */
    public static String getRomAvailableSizeFormat(Context context) {
        return Formatter.formatFileSize(context, getRomAvailableSizeByte());
    }
    
   /**
    * 获取指定路径所在空间的剩余可用容量(包含sdcard或者内置存储)
    * @param filePath
    * @return Byte
    */
    public static long getPathAvailableZizeByte(String filePath)  
    {  
        // 如果是sd卡的下的路径，则获取sd卡可用容量  
        if (filePath.startsWith(getAbsRootPath()))  
        {  
            filePath = getAbsRootPath();  
        } else  
        {// 如果是内部存储的路径，则获取内存存储的可用容量  
            filePath = getAbsDataPath();
        }  
        StatFs stat = new StatFs(filePath);  
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;  
        return stat.getBlockSize() * availableBlocks;  
    }
    
   /**  
    * 获取指定路径所在空间的剩余可用容量(包含sdcard或者内置存储)
    * @param filePath
    * @param context
    * @return Byte KB MB GB
    */
    public static String getPathAvailableZizeFormat(String filePath,Context context)  
    {  
        return Formatter.formatFileSize(context, getPathAvailableZizeByte(filePath));  
    } 
  

}
