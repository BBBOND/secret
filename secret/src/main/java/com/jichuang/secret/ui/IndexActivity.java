package com.jichuang.secret.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.jichuang.secret.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 心情主页功能
 * Created by KIM on 2015/5/14.
 */
public class IndexActivity extends Activity implements AdapterView.OnItemClickListener {
    private DrawerLayout drawerLayout;
    private ListView left_drawer;
    private List<String> appItem = new ArrayList<String>();

    private int backKeyPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_feeling);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        left_drawer = (ListView) findViewById(R.id.left_drawer);

        appItem.addAll(Arrays.asList(getResources().getStringArray(R.array.appItem)));
        ArrayAdapter<String> aItem = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, appItem);
        left_drawer.setAdapter(aItem);

        left_drawer.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //动态插入一个fragment到framelayout中
        FragmentManager fragmentManager = getFragmentManager();
        switch (position) {
            case 0:
                Fragment feelingFragment = new FeelingFragment();
                fragmentManager.beginTransaction().replace(R.id.index, feelingFragment).commit();
                break;
            case 1:
                Fragment dailyFaFragment = new DailyFragment();
                fragmentManager.beginTransaction().replace(R.id.index, dailyFaFragment).commit();
                break;
            case 2:
                Fragment darkFragment = new DarkFragment();
                fragmentManager.beginTransaction().replace(R.id.index, darkFragment).commit();
                break;
            case 3:
                Toast.makeText(this, "暂无次功能", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(appItem.get(position));
        }

        drawerLayout.closeDrawer(left_drawer);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (drawerLayout.isDrawerVisible(left_drawer)) {
            drawerLayout.closeDrawer(left_drawer);
            return false;
        } else {
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
    }
}
