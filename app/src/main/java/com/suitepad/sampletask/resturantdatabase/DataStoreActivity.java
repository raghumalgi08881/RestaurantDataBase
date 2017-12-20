package com.suitepad.sampletask.resturantdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataStoreActivity extends AppCompatActivity {
    private static String CLASS_NAME = "com.restaurantproxyserver.reciever.IncomingReceiver";
    private static String PACKAGE_NAME = "com.restaurantproxyserver";
    private static String ACTION = "com.resturantserver.android.intent.action";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String contents = readFile();
        final Intent intent=new Intent();
        intent.setClassName(PACKAGE_NAME, CLASS_NAME);
        intent.setAction(ACTION);
        intent.putExtra("fileContent",contents);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
        finish();
    }
    private String readFile(){
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("menu.json"), "UTF-8"));

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }
}
