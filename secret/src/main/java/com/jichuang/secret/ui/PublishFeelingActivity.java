package com.jichuang.secret.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.jichuang.secret.R;
import com.jichuang.secret.utils.FilesUtil;

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

        ed_feeling = (EditText) findViewById(R.id.et_feeling);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new submitAction());
    }

    class submitAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String feeling = ed_feeling.getText().toString().trim();
            if (!feeling.isEmpty()){
                try {
                    FilesUtil filesUtil = new FilesUtil(getApplicationContext());
                    filesUtil.appendSave("feelings.txt",feeling);

                    Toast.makeText(PublishFeelingActivity.this,
                            getString(R.string.publish_success),Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.setClass(PublishFeelingActivity.this,FeelingIndexActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(PublishFeelingActivity.this,
                            getString(R.string.publish_fail),Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(PublishFeelingActivity.this,
                        getString(R.string.content_should_not_empty),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
