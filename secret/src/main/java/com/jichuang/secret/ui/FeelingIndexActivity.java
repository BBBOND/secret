package com.jichuang.secret.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.jichuang.secret.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 心情主页功能
 * Created by KIM on 2015/5/14.
 */
public class FeelingIndexActivity extends Activity {
    private ListView feelinglist;
    private TextView nofeeling;
    List<String> feeling = new ArrayList<String>();

    private int backKeyPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_index);


        feelinglist = (ListView) findViewById(R.id.feelinglist);
        nofeeling = (TextView) findViewById(R.id.nofeeling);

        feeling.add("hahhahhah");
        feeling.add("hahhahhah");
        feeling.add("hahhahhah");
        feeling.add("hahhahhah");
        feeling.add("hahhahhah");
        feeling.add("hahhahhah");
        feeling.add("hahhahhah");
        feeling.add("hahhahhah");
        feeling.add("hahhahhah");
        feeling.add("hahhahhah");
        feeling.add("hahhahhah");
        feeling.add("hahhahhah");
        feeling.add("hahhahhah");
        feeling.add("hahhahhah");
        feeling.add("hahhahhah");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_card, R.id.list_item, feeling);
        feelinglist.setAdapter(adapter);

//            nofeeling.setText("Nothing~~");

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
        }

//        initSystemBar();
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
