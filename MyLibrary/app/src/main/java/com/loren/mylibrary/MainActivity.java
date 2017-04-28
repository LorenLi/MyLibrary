package com.loren.mylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.loren.textlibrary.util.LogUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtils.d("message");
        MyLogUtils.d("MainActivity","messageH");

    }
}
