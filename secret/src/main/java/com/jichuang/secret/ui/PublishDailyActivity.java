package com.jichuang.secret.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.alibaba.fastjson.JSONObject;
import com.jichuang.secret.R;
import com.jichuang.secret.utils.FilesUtil;
import com.jichuang.secret.utils.TimeUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 日记发表功能
 * Created by KIM on 2015/5/17.
 */
public class PublishDailyActivity extends Activity {
    private Spinner weather;
    private Spinner city;
    private EditText daily;
    private Button submit;
    private List<String> weatherList;
    private List<String> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_publishdaily);

        weather = (Spinner) findViewById(R.id.weather);
        city = (Spinner) findViewById(R.id.city);
        daily = (EditText) findViewById(R.id.et_daily);
        submit = (Button) findViewById(R.id.submit);

        weatherList.addAll(Arrays.asList(getResources().getStringArray(R.array.weather)));
        cityList.addAll(Arrays.asList(getResources().getStringArray(R.array.city)));

//        for (String w : getResources().getStringArray(R.array.weather))
//            weatherList.add(w);
//
//        for (String c : getResources().getStringArray(R.array.city))
//            cityList.add(c);

        ArrayAdapter<String> weatherAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, weatherList);
        weather.setAdapter(weatherAdapter);
        weather.setSelection(weatherAdapter.getPosition("晴"));
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityList);
        city.setAdapter(cityAdapter);
        weather.setSelection(cityAdapter.getPosition("长春"));

        submit.setOnClickListener(new submitAction());
    }

    class submitAction implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String w = weather.getSelectedItem().toString().trim();
            String c = city.getSelectedItem().toString().trim();
            String d = daily.getText().toString().trim();
            String time = TimeUtil.getStringDate();

            if (!d.isEmpty()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("weather", w);
                jsonObject.put("city", c);
                jsonObject.put("daily", d);
                jsonObject.put("publishTime", time);

                try {
                    FilesUtil filesUtil = new FilesUtil(getApplicationContext());
                    filesUtil.appendSave("daily.txt", jsonObject.toJSONString());

                    Toast.makeText(PublishDailyActivity.this,
                            getString(R.string.publish_success), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.setClass(PublishDailyActivity.this, DailyIndexActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(PublishDailyActivity.this,
                            getString(R.string.publish_fail), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(PublishDailyActivity.this,
                        getString(R.string.content_should_not_empty), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
