package com.loren.textlibrary.util;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class ImageCache {
	private String TAG=this.getClass().getSimpleName();
	private LruCache<String, Bitmap> mMemoryCache;
	
	static private ImageCache instants;
	
	static public ImageCache getInstants(){
		if (instants == null){
			instants = new ImageCache();
		}
		return instants;
	}
	
	
	public ImageCache(){
        //获取系统分配给每个应用程序的最大内存，每个应用系统分配32M
        int maxMemory = (int) Runtime.getRuntime().maxMemory();    
        int mCacheSize = maxMemory / 8; 
        //MyLog.e(TAG, "app maxMemory= "+maxMemory);
        //给LruCache分配1/8 4M  
        mMemoryCache = new LruCache<String, Bitmap>(mCacheSize){  
  
            //必须重写此方法，来测量Bitmap的大小  
            @Override  
            protected int sizeOf(String key, Bitmap value) {  
                return value.getRowBytes() * value.getHeight();  
            }  
              
        };
	}
	/**
     * @description 将bitmap添加到内存中去
     *
     * @param key
     * @param bitmap
     */
    public void put(String key, Bitmap bitmap) {
        if (get(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }
    /**
     * @description 通过key来从内存缓存中获得bitmap对象
     *
     * @param key
     * @return
     */
    public Bitmap get(String key) {
        return mMemoryCache.get(key);
    }
}
