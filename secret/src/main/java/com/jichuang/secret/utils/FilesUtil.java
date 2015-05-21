package com.jichuang.secret.utils;

import android.content.Context;
import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件相关功能
 * Created by KIM on 2015/5/15.
 */
public class FilesUtil {
    Context context;

    public FilesUtil(Context context) {
        this.context = context;
    }

    public void privateSave(String fileName, String content) throws Exception {
        FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        outputStream.write(content.getBytes("UTF-8"));
        outputStream.close();
    }

    public void appendSave(String filename, String content) throws Exception {
        FileOutputStream outputStream = context.openFileOutput(filename, Context.MODE_APPEND);
        content += "\n\r";
        outputStream.write(content.getBytes("UTF-8"));
        outputStream.close();
    }

    public String read(String filename) throws Exception {
        FileInputStream inputStream = context.openFileInput(filename);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        byte[] date = byteArrayOutputStream.toByteArray();
        inputStream.close();
        byteArrayOutputStream.close();
        return new String(date);
    }

    public List<JSONObject> readAll(String filename) throws Exception {
        List<JSONObject> list = new ArrayList<JSONObject>();
        FileInputStream inputStream = context.openFileInput(filename);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        byte[] date = byteArrayOutputStream.toByteArray();

        String d = new String(date);

        String[] content = d.split("\n\r");

        for (String c : content) {
            list.add(JSONObject.parseObject(c));
        }

        byteArrayOutputStream.close();
        inputStream.close();
        return list;
    }
}