package com.example.javathread;

import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Model implements Contract.Model {
    private final ExecutorService service = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final JSONPlaceholder api;

    public Model() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(JSONPlaceholder.class);
    }

    @Override
    public void writeFile(String filename, String content, File path, Contract.Model.WriteFileCallBack callBack) {
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    FileOutputStream fos = new FileOutputStream(new File(path, filename));
                    fos.write(content.getBytes());
                    fos.close();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess();
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
            }
        });
    }

    @Override
    public void readFile(String filename, File path, Contract.Model.ReadFileCallBack callBack) {
        service.execute(new Runnable() {
            @Override
            public void run() {
                File readFrom = new File(path, filename);
                byte[] content = new byte[(int) readFrom.length()];
                try {
                    FileInputStream stream = new FileInputStream(readFrom);
                    stream.read(content);
                    stream.close();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(new String(content));
                        }
                    });
                } catch (Exception e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onError(e.getMessage());
                        }
                    });
                }
            }
        });
    }

    @Override
    public void fetchComment(CallBack callBack) {
        api.getComment().enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callBack.onSuccess(response.body());
                }
                else {
                    callBack.onError(new Exception(response.message()));
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                callBack.onError(t);
            }
        });
    }
}
