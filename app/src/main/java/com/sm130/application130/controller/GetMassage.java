package com.sm130.application130.controller;

import com.sm130.application130.global.GlobalConstants;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetMassage {


    private HttpURLConnection connection = null;
    BufferedReader reader = null;

    public void getHome(){
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   URL url = new URL(GlobalConstants.CATEGORY_URL);
                   connection = (HttpURLConnection) url.openConnection();
//            设置请求的方法
                   connection.setRequestMethod("GET");
                   connection.setConnectTimeout(5000);
                   connection.setReadTimeout(5000);
//            返回输入流
                   InputStream in = connection.getInputStream();

                   reader = new BufferedReader(new InputStreamReader(in));
                   StringBuilder result = new StringBuilder();
                   String line;
                   while (((line = reader.readLine())) != null){
                       result.append(line);
                   }
                   System.out.println(result);

               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }).start();
    }
}
