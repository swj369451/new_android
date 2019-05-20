package com.sm130.application130.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;

import com.google.gson.Gson;
import com.sm130.application130.controller.GetMassage;
import com.sm130.application130.domain.JsonRootBean;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;

public class URLUtils{
    /**
     * 从服务器取图片
     * @param url
     * @return
     */
    public static Bitmap getHttpBitmap(String url) {
            try {
//                URL myFileUrl = new URL(url);
//                final Bitmap bitmap = null;
//                HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
//                conn.setConnectTimeout(0);
//                conn.setDoInput(true);
//                conn.connect();
//                final InputStream is = conn.getInputStream();
//
//                Callable<Bitmap> callable = new Callable<Bitmap>() {
//                    @Override
//                    public Bitmap call() throws Exception {
//                        Bitmap bitmap = BitmapFactory.decodeStream(is);
//                        return bitmap;
//                    }
//                };
//            return bitmap;

                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                Bitmap bitmap;
                final Call call = okHttpClient.newCall(request);



                Callable<Bitmap> callable = new Callable<Bitmap>() {
                    @Override
                    public Bitmap call() throws Exception {
                        Response response = call.execute();
                        return BitmapFactory.decodeStream( response.body().byteStream());
                    }
                };

                ExecutorService es = Executors.newSingleThreadExecutor();
                Future<Bitmap> submit = es.submit(callable);

                return submit.get();

            }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public static String GetMassageByOkhttp(String url){
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectDiskReads()
//                .detectDiskWrites()
//                .detectNetwork()   // or .detectAll() for all detectable problems
//                .penaltyLog()
//                .build());
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                .detectLeakedSqlLiteObjects()
//                .detectLeakedClosableObjects()
//                .penaltyLog()
//                .penaltyDeath()
//                .build());
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            final Request request = new Request.Builder().url(url).build();
            final Call call = okHttpClient.newCall(request);
            String s =null;


            Callable<String> callable = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Response response = call.execute();
                    return response.body().string();
                }
            };
            ExecutorService es = Executors.newSingleThreadExecutor();
            Future<String> future =es.submit(callable);
            String s1 = future.get();
            return s1;



        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static Object getInstentForUrl(String url,Class clazz){
        Gson gson = new Gson();
        String result = null;

        result = GetMassageByOkhttp(url);

        return gson.fromJson(result, clazz);
    }
}
