package com.jichuang.secret.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.jichuang.secret.R;
import com.jichuang.secret.utils.FilesUtil;
import com.jichuang.secret.utils.TimeUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * 发布心情功能
 * Created by KIM on 2015/5/17.
 */
public class PublishFeelingActivity extends Activity {
    private EditText ed_feeling;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_publishfeeling);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
        }

        ed_feeling = (EditText) findViewById(R.id.et_feeling);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new submitAction());
    }

    class submitAction implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String feeling = ed_feeling.getText().toString().trim();
            String time = TimeUtil.getStringDate();
            if (!feeling.isEmpty()) {
                try {
                    JSONObject feelings = new JSONObject();
                    feelings.put("feeling", feeling);
                    feelings.put("time", time);

                    FilesUtil filesUtil = new FilesUtil(getApplicationContext());
                    filesUtil.appendSave("feelings.txt", feelings.toJSONString());

                    Toast.makeText(PublishFeelingActivity.this,
                            getString(R.string.publish_success), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.setClass(PublishFeelingActivity.this, IndexActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(PublishFeelingActivity.this,
                            getString(R.string.publish_fail), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(PublishFeelingActivity.this,
                        getString(R.string.content_should_not_empty), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.setClass(PublishFeelingActivity.this, IndexActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
