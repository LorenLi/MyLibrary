package com.loren.mylibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.loren.textlibrary.util.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkUtils.isNetworkAvailable(this);

    }
}
