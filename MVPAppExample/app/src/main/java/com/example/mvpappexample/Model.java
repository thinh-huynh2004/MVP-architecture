package com.example.mvpappexample;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model implements Contract.Model {
    private final ExecutorService service = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());


    @Override
    public HashMap<String, String> findAccount() {
        HashMap<String, String> password = new HashMap<>();
        password.put("alice@gmail.com", "alice123");
        password.put("bob@gmail.com", "bob123");
        password.put("charlie@gmail.com", "charlie123");
        return password;
    }
    @Override
    public void fetchImageFromUrl(String url, ImageCallBack callBack) {
        service.execute(() -> {
            try {
                InputStream inputStream = new URL(url).openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(bitmap);
                    }
                });
            } catch (Exception e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e);
                    }
                });
            }
        });
    }
}
