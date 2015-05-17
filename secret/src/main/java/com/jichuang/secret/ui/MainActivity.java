package com.jichuang.secret.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.io.File;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            File file = new File(String.valueOf(getFilesDir()) + "/user.dat");

            //判断注册文件是否存在，确定是否需要注册
            if (file.exists()) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ReloginActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            } else {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RegistActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
