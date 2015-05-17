package com.jichuang.secret.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.jichuang.secret.R;

import java.util.List;

/**
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

        for (String w : getResources().getStringArray(R.array.weather))
            weatherList.add(w);

        for (String c : getResources().getStringArray(R.array.city))
            cityList.add(c);

        ArrayAdapter<String> weatherAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,weatherList);
        weather.setAdapter(weatherAdapter);
        weather.setSelection(weatherAdapter.getPosition("晴"));
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cityList);
        city.setAdapter(cityAdapter);
        weather.setSelection(cityAdapter.getPosition("长春"));

        submit.setOnClickListener(new submitAction());
    }

    class submitAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String w = weather.getSelectedItem().toString().trim();
            String c = city.getSelectedItem().toString().trim();
            String d = daily.getText().toString().trim();
            if (!d.isEmpty()){
                
            }
        }
    }
}
