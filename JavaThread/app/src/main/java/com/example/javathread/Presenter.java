package com.example.javathread;


import java.io.File;
import java.util.List;

public class Presenter implements Contract.Presenter {
    private final Model model;
    private final Contract.View view;

    public Presenter(Contract.View view) {
        this.view = view;
        this.model = new Model();
    }

    @Override
    public void writeFile(String name, String content, File path) {
        model.writeFile(name, content, path, new Contract.Model.WriteFileCallBack() {
            @Override
            public void onSuccess() {
                view.showWriteSuccess("Write file success");
            }
            @Override
            public void onError(Exception e) {
                view.showWriteError(e.getMessage());
            }
        });
    }

    @Override
    public void readFile(String name, File path) {
        model.readFile(name, path, new Contract.Model.ReadFileCallBack() {
            @Override
            public void onSuccess(String content) {
                view.showReadSuccess(content);
            }

            @Override
            public void onError(String message) {
                view.showReadError(message);
            }
        });
    }

    @Override
    public void fetchComment() {
        model.fetchComment(new Contract.Model.CallBack() {
            @Override
            public void onSuccess(List<Comment> comments) {
                view.showComments(comments);
            }

            @Override
            public void onError(Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }
}
