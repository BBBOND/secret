package com.jichuang.secret.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.jichuang.secret.R;
import com.jichuang.secret.utils.FilesUtil;
import com.jichuang.secret.utils.TimeUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 发送黑暗功能
 * Created by KIM on 2015/5/17.
 */
public class PublishDarkActivity extends Activity {
    private EditText dark;
    private Spinner disappearTime;
    private Button submit;

    private List<String> disappearTimeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_publishdark);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
        }

        dark = (EditText) findViewById(R.id.et_dark);
        disappearTime = (Spinner) findViewById(R.id.disappear_time);
        submit = (Button) findViewById(R.id.submit);

        disappearTimeList.addAll(Arrays.asList(getResources().getStringArray(R.array.disappearTime)));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, disappearTimeList);
        disappearTime.setAdapter(adapter);
        disappearTime.setSelection(adapter.getPosition("一小时"));

        submit.setOnClickListener(new submitAction());
    }

    class submitAction implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Date dTime = null;
            Date time = new Date();
            String darkStr = dark.getText().toString().trim();
            String disTime = String.valueOf(disappearTime.getSelectedItem());
            try {
                if (disTime.equals("一分钟")) {
                    dTime = TimeUtil.formatStringToDate("0000/00/00 00:01:00");
                } else if (disTime.equals("一小时")) {
                    dTime = TimeUtil.formatStringToDate("0000/00/00 01:00:00");
                } else if (disTime.equals("一天")) {
                    dTime = TimeUtil.formatStringToDate("0000/00/01 00:00:00");
                } else if (disTime.equals("一个月")) {
                    dTime = TimeUtil.formatStringToDate("0000/01/00 00:00:00");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(PublishDarkActivity.this,
                        getString(R.string.publish_fail), Toast.LENGTH_SHORT).show();
                return;
            }
            if (dTime == null) {
                Toast.makeText(PublishDarkActivity.this,
                        getString(R.string.publish_fail), Toast.LENGTH_SHORT).show();
            } else {
                dTime.setSeconds(time.getSeconds() + dTime.getSeconds());
                dTime.setMinutes(time.getMinutes() + dTime.getMinutes());
                dTime.setHours(time.getHours() + dTime.getHours());
                dTime.setDate(time.getDate() + dTime.getDate());
                dTime.setMonth(time.getMonth() + dTime.getMonth());
                dTime.setYear(time.getYear() + dTime.getYear());

                if (!darkStr.isEmpty()) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("dark", darkStr);
                    jsonObject.put("disappearTime", dTime);

                    try {
                        FilesUtil filesUtil = new FilesUtil(getApplicationContext());
                        filesUtil.appendSave("dark.txt", jsonObject.toJSONString());
                        Toast.makeText(PublishDarkActivity.this,
                                getString(R.string.publish_success), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        intent.setClass(PublishDarkActivity.this, DarkFragment.class);
                        startActivity(intent);
                        PublishDarkActivity.this.finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(PublishDarkActivity.this,
                                getString(R.string.publish_fail), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
