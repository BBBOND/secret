package com.jichuang.secret.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSONObject;
import com.jichuang.secret.R;
import com.jichuang.secret.utils.FilesUtil;

/**
 * 登录界面功能
 * Created by KIM on 2015/5/14.
 */
public class ReloginActivity extends Activity{
    private TextView username;
    private EditText password;
    private Button login;
    private JSONObject jsonObject = null;
    private String s;

    private int backKeyPressedTime = 0;
    /**
     * 登录操作
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_relogin);
//        initSystemBar();

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
        }

        try {
            FilesUtil filesService = new FilesUtil(getApplicationContext());
            s =filesService.read("user.dat");
            jsonObject = JSONObject.parseObject(s);
            username = (TextView) findViewById(R.id.username);
            username.setText(jsonObject.get("username").toString());
            login = (Button) findViewById(R.id.login);
            login.setOnClickListener(new relogin());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class relogin implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            password = (EditText) findViewById(R.id.password);
            String password1 = jsonObject.get("password").toString();
            if (password1.equals(password.getText().toString().trim())) {

                Intent intent = new Intent();
                intent.setClass(ReloginActivity.this,IndexActivity.class);
                startActivity(intent);

                Toast.makeText(ReloginActivity.this, "登录成功！", Toast.LENGTH_LONG).show();

                ReloginActivity.this.finish();
            } else {
                Toast.makeText(ReloginActivity.this, "密码不正确！闲人勿动！", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && backKeyPressedTime == 0) {
            Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
            backKeyPressedTime = 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        backKeyPressedTime = 0;
                    }
                }
            }.start();
            return false;
        } else if (keyCode == KeyEvent.KEYCODE_BACK && backKeyPressedTime == 1) {
            this.finish();
            System.exit(0);
        }
        return true;
    }

//    private void initSystemBar() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getWindow();
//            // Translucent status bar
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            // Translucent navigation bar
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
//    }
}
