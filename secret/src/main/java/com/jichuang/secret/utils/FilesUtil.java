package com.jichuang.secret.utils;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by KIM on 2015/5/15.
 */
public class FilesUtil {
    Context context;

    public FilesUtil(Context context) {
        this.context = context;
    }

    public void privateSave(String fileName, String content) throws Exception {
        FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        outputStream.write(content.getBytes());
        outputStream.close();
    }

    public void appendSave(String filename, String content) throws Exception {
        FileOutputStream outputStream = context.openFileOutput(filename, Context.MODE_APPEND);
        outputStream.write(content.getBytes());
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
        return new String(date);
    }
}