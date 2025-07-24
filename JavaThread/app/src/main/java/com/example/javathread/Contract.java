package com.example.javathread;

import java.io.File;
import java.util.List;

public interface Contract {
    interface View {
        void showWriteSuccess(String message);
        void showWriteError(String message);
        void showReadSuccess(String content);
        void showReadError(String message);
        void showComments(List<Comment> comments);
        void showError(String message);
    }

    interface Model {
        void writeFile(String filename, String content, File path, WriteFileCallBack callBack);
        void readFile(String filename, File path, ReadFileCallBack callBack);

        interface WriteFileCallBack {
            void onSuccess();
            void onError(Exception e);
        }

        interface ReadFileCallBack {
            void onSuccess(String content);
            void onError(String message);
        }

        void fetchComment(CallBack callBack);

        interface CallBack {
            void onSuccess(List<Comment> comments);
            void onError(Throwable t);
        }
    }

    interface Presenter {
        void writeFile(String name, String content, File path);
        void readFile(String name, File path);

        void fetchComment();
    }
}
