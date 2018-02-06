package com.example.jsonacdat.utils;


import com.example.jsonacdat.pojo.GSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface APIService {
    @GET("users/{user}/repos")
    Call<List<GSONObject.Repositorio>> listRepos(@Path("user") String user);
}
