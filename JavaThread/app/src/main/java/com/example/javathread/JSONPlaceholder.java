package com.example.javathread;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface JSONPlaceholder {
    @GET("comments")
    Call<List<Comment>> getComment();
}
