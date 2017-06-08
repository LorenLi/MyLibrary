package com.loren.mylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.loren.mylibrary.util.LogUtils;
import com.loren.textlibrary.util.DensityUtil;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);


        int screenWidth = DensityUtil.getScreenWidth(this);
        int screenHeight = DensityUtil.getScreenHeight(this);
        int navagationHeight = DensityUtil.getNavigationBarHeight(this);


        LogUtils.d("screenWidth : " + screenWidth);
        LogUtils.d("screenHeight : " + screenHeight);
        LogUtils.d("navagationHeight : " + navagationHeight);

    }
}
