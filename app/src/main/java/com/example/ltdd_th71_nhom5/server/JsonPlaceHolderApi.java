package com.example.ltdd_th71_nhom5.server;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("products")
    Call<List<Post>> getPosts();
}
