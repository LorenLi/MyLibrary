/**
 *
 *项目名：
 *包名：com.zhibaicc.android
 *文件名：Sphelper.java
 *版本信息：1.0.0
 *创建日期：2014年5月30日-下午3:14:27
 *创建人： 单
 *上海智百文化传播有限公司
 */
package com.loren.mylibrary.util;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * @author: Loren
 * @create: 2016-7-4下午8:06:18
 * @desc: 保存数据
 *
 */
public class SharedPreferencesUtils {
    private static android.content.SharedPreferences sp;
    //有时候需要清除
    private static final String NAME_CLEAR = "MyShare_clear";
    //不需要清除的数据,供外部类调用（例如用户名，手势密码等信息）
    public static final String NAME_NOT_CLEAR = "MyShare_not_clear";
    /**
     * 第一种方式（固定Sharedpreferences名称）
     * 保存数据
     * 
     * @param mContext
     * @param key
     * @param value
     */
    public static void save(Context mContext, String key, Object value) {
        initial(mContext);
        if (value instanceof String) {
            sp.edit().putString(key, (String) value).commit();
        } else if (value instanceof Boolean) {
            sp.edit().putBoolean(key, (Boolean) value).commit();
        } else if (value instanceof Integer) {
            sp.edit().putInt(key, (Integer) value).commit();
        } else if (value instanceof Float) {
            sp.edit().putFloat(key, (Float) value).commit();
        } else if (value instanceof Long) {
            sp.edit().putLong(key, (Long) value).commit();
        }
    }
    
    /**
     * 第一种方式（固定Sharedpreferences名称）
     * 获取数据
     * 
     * @param mContext
     * @param key
     * @return
     */
    public static int getInteger(Context mContext, String key) {
        initial(mContext);
        return sp.getInt(key, 0);
    }

    public static Float getFloat(Context mContext, String key) {
        initial(mContext);
        return sp.getFloat(key, 0F);
    }

    public static Long getLong(Context mContext, String key) {
        initial(mContext);
        return sp.getLong(key, 0L);
    }

    public static Boolean getBoolean(Context mContext, String key) {
        initial(mContext);
        return sp.getBoolean(key, false);
    }

    public static String getString(Context mContext, String key) {
        initial(mContext);
        return sp.getString(key, "");
    }

    private static void initial(Context mContext) {
        sp = mContext.getSharedPreferences(NAME_CLEAR, 0);
    }
    
  
    
	/**
	 * 第二种方式（自定义Sharedpreferences名称）
	 * 保存数据
	 * 
	 * @param mContext 传回comtext
	 * @param fileName 存储的文件名
	 * @param key 所要存储的key值
	 * @param value 所要存储的key值得value
	 *            
	 */
    public static void save2(Context mContext, String fileName, String key,
            Object value) {
        initial2(mContext, fileName);
        if (value instanceof String) {
            sp.edit().putString(key, (String) value).commit();
        } else if (value instanceof Boolean) {
            sp.edit().putBoolean(key, (Boolean) value).commit();
        } else if (value instanceof Integer) {
            sp.edit().putInt(key, (Integer) value).commit();
        } else if (value instanceof Float) {
            sp.edit().putFloat(key, (Float) value).commit();
        } else if (value instanceof Long) {
            sp.edit().putLong(key, (Long) value).commit();
        }
    }

	/**
	 * 第一种方式（固定Sharedpreferences名称） 
	 * 获取数据
	 * 
	 * @param mContext
	 * @param fileName
	 * @param key
	 * @return
	 */
    public static int getInteger2(Context mContext,String fileName, String key) {
        initial2(mContext,fileName);
        return sp.getInt(key, 0);
    }
    
    public static Float getFloat2(Context mContext,String fileName, String key) {
        initial2(mContext,fileName);
        return sp.getFloat(key, 0F);
    }

    public static Long getLong2(Context mContext, String fileName,String key) {
        initial2(mContext,fileName);
        return sp.getLong(key, 0L);
    }
    
    public static Boolean getBoolean2(Context mContext, String fileName,
            String key) {
        initial2(mContext, fileName);
        return sp.getBoolean(key, false);
    }

    public static String getString2(Context mContext, String fileName,
            String key) {
        initial2(mContext, fileName);
        return sp.getString(key, "");
    }
    
    public static void initial2(Context mContext, String fileName) {
        sp = mContext.getSharedPreferences(fileName, 0);
    }

    /**
     * 第一种情况下清空某个文件下的所有的数据
     * 
     * @param mContext
     */
    public static void removeFile(Context mContext) {
        initial(mContext);
        Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 第二种情况下清空某个文件下的所有的数据
     * 
     * @param mContext
     * @param fileName
     */
    public static void removeFile2(Context mContext, String fileName) {
        initial2(mContext, fileName);
        Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    /**
  	 * desc:保存对象
     
  	 * @param context
  	 * @param key 
  	 * @param obj 要保存的对象，只能保存实现了serializable的对象
  	 * modified:	
  	 */
  	public static void saveObject(Context context,String key ,Object obj){
  		try {
  			// 保存对象
  			Editor sharedata = context.getSharedPreferences(NAME_CLEAR, 0).edit();
  			//先将序列化结果写到byte缓存中，其实就分配一个内存空间
  			ByteArrayOutputStream bos=new ByteArrayOutputStream();
  			ObjectOutputStream os=new ObjectOutputStream(bos);
  			//将对象序列化写入byte缓存
  			os.writeObject(obj);
  			//将序列化的数据转为16进制保存
  			String bytesToHexString = bytesToHexString(bos.toByteArray());
  			//保存该16进制数组
  			sharedata.putString(key, bytesToHexString);
  			sharedata.commit();
  		} catch (IOException e) {
  			e.printStackTrace();
  			LogUtils.e("", "保存obj失败");
  		}
  	}

	/**
	 * desc:获取保存的Object对象
	 * @param context
	 * @param key
	 * @return
	 * modified:
	 */
	public static Object getObject(Context context,String key ){
		try {
			android.content.SharedPreferences sharedata = context.getSharedPreferences(NAME_CLEAR, 0);
			if (sharedata.contains(key)) {
				String string = sharedata.getString(key, "");
				if(TextUtils.isEmpty(string)){
					return null;
				}else{
					//将16进制的数据转为数组，准备反序列化
					byte[] stringToBytes = StringToBytes(string);
					ByteArrayInputStream bis=new ByteArrayInputStream(stringToBytes);
					ObjectInputStream is=new ObjectInputStream(bis);
					//返回反序列化得到的对象
					Object readObject = is.readObject();
					return readObject;
				}
			}
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//所有异常返回null
		return null;

	}
  	/**
  	 * desc:将数组转为16进制
  	 * @param bArray
  	 * @return
  	 * modified:	
  	 */
  	public static String bytesToHexString(byte[] bArray) {
  		if(bArray == null){
  			return null;
  		}
  		if(bArray.length == 0){
  			return "";
  		}
  		StringBuffer sb = new StringBuffer(bArray.length);
  		String sTemp;
  		for (int i = 0; i < bArray.length; i++) {
  			sTemp = Integer.toHexString(0xFF & bArray[i]);
  			if (sTemp.length() < 2)
  				sb.append(0);
  			sb.append(sTemp.toUpperCase());
  		}
  		return sb.toString();
  	}
  	
      /**
  	 * desc:将16进制的数据转为数组
  	 * @param data
  	 * @return
  	 * modified:	
  	 */
  	public static byte[] StringToBytes(String data){
  		String hexString=data.toUpperCase().trim();
  		if (hexString.length()%2!=0) {
  			return null;
  		}
  		byte[] retData=new byte[hexString.length()/2];
  		for(int i=0;i<hexString.length();i++)
  		{
  			int int_ch;  // 两位16进制数转化后的10进制数
  			char hex_char1 = hexString.charAt(i); ////两位16进制数中的第一位(高位*16)
  			int int_ch1;
  			if(hex_char1 >= '0' && hex_char1 <='9')
  				int_ch1 = (hex_char1-48)*16;   //// 0 的Ascll - 48
  			else if(hex_char1 >= 'A' && hex_char1 <='F')
  				int_ch1 = (hex_char1-55)*16; //// A 的Ascll - 65
  			else
  				return null;
  			i++;
  			char hex_char2 = hexString.charAt(i); ///两位16进制数中的第二位(低位)
  			int int_ch2;
  			if(hex_char2 >= '0' && hex_char2 <='9')
  				int_ch2 = (hex_char2-48); //// 0 的Ascll - 48
  			else if(hex_char2 >= 'A' && hex_char2 <='F')
  				int_ch2 = hex_char2-55; //// A 的Ascll - 65
  			else
  				return null;
  			int_ch = int_ch1+int_ch2;
  			retData[i/2]=(byte) int_ch;//将转化后的数放入Byte里
  		}
  		return retData;
    }

}