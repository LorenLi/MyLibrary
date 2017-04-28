package com.loren.mylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.loren.mylibrary.text.MyLogUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyLogUtils.d("message");
        MyLogUtils.d("MainActivity","messageH");

    }
}
