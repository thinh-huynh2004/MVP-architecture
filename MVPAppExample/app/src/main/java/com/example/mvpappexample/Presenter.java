package com.example.mvpappexample;

import android.graphics.Bitmap;

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
        HashMap<String, String> accounts = model.findAccount();
        if (!Objects.equals(accounts.get(username), password)) {
            view.onError("Login failed");
            return;
        }
        view.onSuccess("Login success");
        return;
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
