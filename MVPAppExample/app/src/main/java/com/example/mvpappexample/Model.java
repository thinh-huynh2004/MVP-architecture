package com.example.mvpappexample;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model implements Contract.Model {
    private ExecutorService service = null;
    private Handler handler = null;

    private HashMap<String, String> accounts = null;

    public Model() {
        handler = new Handler(Looper.getMainLooper());
        service = Executors.newSingleThreadExecutor();
        accounts = new HashMap<String, String>();
        accounts.put("alice@gmail.com", "alice123");
        accounts.put("bob@gmail.com", "bob123");
        accounts.put("charlie@gmail.com", "charlie123");
    }

    @Override
    public boolean checkAccount(String username, String password) {
        String pass = accounts.get(username);
        return Objects.equals(pass, password);
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
