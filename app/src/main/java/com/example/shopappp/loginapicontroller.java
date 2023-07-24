package com.example.shopappp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class loginapicontroller {


    static final String url="https://varun71.000webhostapp.com/lo.php/";
    private static apicontroller clientobject;
    private static Retrofit retrofit;

    loginapicontroller()
    {
        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized apicontroller getInstance()
    {
        if(clientobject==null)
            clientobject=new apicontroller();
        return clientobject;
    }

    apiset getapi()
    {
        return retrofit.create(apiset.class);
    }
}
