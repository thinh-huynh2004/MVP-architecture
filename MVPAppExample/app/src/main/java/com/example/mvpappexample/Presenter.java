package com.example.mvpappexample;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import java.util.HashMap;
import java.util.Objects;

public class Presenter implements Contract.Presenter{
    private final Contract.View view;
    private final Contract.Model model;

    public Presenter(Contract.View view, Contract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void checkAccount(String username, String password) {
        boolean finded = model.checkAccount(username, password);
        if (finded) {
            view.onSuccess("Login success");
        }
        else {
            view.onError("Login failed");
        }
    }

    @Override
    public void loadImage(String url) {
        model.fetchImageFromUrl(url, new Model.ImageCallBack() {
            @Override
            public  void onSuccess(Bitmap bitmap) {
                view.showImage(bitmap);
            }

            @Override
            public void onError(Exception e) {
                view.showError(e.getMessage());
            }
        });
    }

}
