package com.jichuang.secret.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.jichuang.secret.R;
import com.jichuang.secret.utils.FilesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KIM on 2015/5/21.
 */
public class FeelingFragment extends Fragment {
    private ListView feelinglist;
    private TextView nofeeling;
    private Button submit;
    List<JSONObject> feelingObject = new ArrayList<JSONObject>();
    List<String> feelings = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);

        feelinglist = (ListView) view.findViewById(R.id.feelinglist);
        nofeeling = (TextView) view.findViewById(R.id.nofeeling);

        try {
            FilesUtil filesUtil = new FilesUtil(getActivity().getApplicationContext());
            feelingObject = filesUtil.readAll("feelings.txt");

            if (feelingObject.isEmpty()) {
                nofeeling.setText("Nothing~~");
            } else {
                for (JSONObject feeling : feelingObject) {
                    Log.i("~~~~", feeling.get("feeling").toString());
                    feelings.add(feeling.get("feeling").toString() + "\n\r" + feeling.get("time").toString());
                }

                nofeeling.setVisibility(View.INVISIBLE);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_card, R.id.list_item, feelings);
                feelinglist.setAdapter(adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
            nofeeling.setText("Nothing~~");
        }

        submit = (Button) view.findViewById(R.id.submit);
        submit.setText(getString(R.string.publish_feeling));
        submit.setOnClickListener(new submitAction());

        return view;
    }

    class submitAction implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), PublishFeelingActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }
}