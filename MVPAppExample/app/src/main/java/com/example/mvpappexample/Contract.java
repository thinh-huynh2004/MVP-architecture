package com.example.mvpappexample;
import android.graphics.Bitmap;
import android.util.Pair;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.List;

public interface Contract {
    interface View {
        void onSuccess(String message);
        void onError(String message);

        void showImage(Bitmap bitmap);
        void showError(String message);
    }

    interface Presenter {
        void checkAccount(String username, String password);

        void loadImage(String url);
    }

    interface Model {
        HashMap<String, String> findAccount();

        void fetchImageFromUrl(String url, ImageCallBack callBack);

        interface ImageCallBack {
            void onSuccess(Bitmap bitmap);

            void onError(Exception e);
        }
    }
}
