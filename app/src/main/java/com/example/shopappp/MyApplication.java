package com.example.shopappp;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {
    private static final String BASE_URL = "https://varun71.000webhostapp.com/logout.php/";

    private static apiset apiService;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(apiset.class);
    }

    public static apiset getApiService() {
        return apiService;
    }
}