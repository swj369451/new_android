package com.sm130.application130.controller;

import android.os.StrictMode;

import com.sm130.application130.global.GlobalConstants;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetMassage {


    private static HttpURLConnection connection = null;
    private static BufferedReader reader = null;
    private static StringBuilder result;
    public static String getHome(){
//       new Thread(new Runnable() {
//           @Override
//           public void run() {
               try {
                   URL url = new URL(GlobalConstants.CATEGORY_URL);
                   connection = (HttpURLConnection) url.openConnection();
//            设置请求的方法
                   connection.setRequestMethod("GET");
                   connection.setConnectTimeout(5000);
                   connection.setReadTimeout(5000);
//            返回输入流
                   StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                           .detectDiskReads()
                           .detectDiskWrites()
                           .detectNetwork()   // or .detectAll() for all detectable problems
                           .penaltyLog()
                           .build());
                   StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                           .detectLeakedSqlLiteObjects()
                           .detectLeakedClosableObjects()
                           .penaltyLog()
                           .penaltyDeath()
                           .build());
                   InputStream in = connection.getInputStream();

                   reader = new BufferedReader(new InputStreamReader(in));
                   result = new StringBuilder();
                   String line;
                   while (((line = reader.readLine())) != null){
                       result.append(line);
                   }

               } catch (Exception e) {
                   e.printStackTrace();
               }
//           }
//       }).start();
       return result.toString();
    }
}
