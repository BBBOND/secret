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
 * 注册界面功能
 * Created by KIM on 2015/5/14.
 */
public class RegistActivity extends Activity {
    private TextView username;
    private EditText password;
    private EditText repassword;
    private Button regist;

    private int backKeyPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_regist);
//        initSystemBar();

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        regist = (Button) findViewById(R.id.regist);
        regist.setOnClickListener(new regist());

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
        }
    }

    /**
     * 注册操作
     */
    class regist implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if ((!username.getText().toString().trim().isEmpty())
                    && (!password.getText().toString().trim().isEmpty())) {
                if (password.getText().toString().trim()
                        .equals(repassword.getText().toString().trim())) {

                    JSONObject json = new JSONObject();

                    try {
                        json.put("username", username.getText().toString());
                        json.put("password", password.getText().toString());

                        FilesUtil filesUtil = new FilesUtil(getApplicationContext());
                        filesUtil.privateSave("user.dat", json.toJSONString());

                        Toast.makeText(RegistActivity.this,
                                getString(R.string.regist_success), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        intent.setClass(RegistActivity.this, IndexActivity.class);
                        startActivity(intent);

                        RegistActivity.this.finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(RegistActivity.this,
                                getString(R.string.regist_fail), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegistActivity.this,
                            getString(R.string.difference_passwrod), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(RegistActivity.this,
                        getString(R.string.username_password_shouldNot_empty), Toast.LENGTH_SHORT).show();
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
