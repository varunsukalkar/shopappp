package com.example.shopappp;



import okhttp3.OkHttpClient;
        import okhttp3.logging.HttpLoggingInterceptor;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://varun71.000webhostapp.com/lo.php/")  //Change server URL
                .client(okHttpClient)
                .build();

        return retrofit;
    }


    public static apiset getUserService(){
        apiset userService = getRetrofit().create(apiset.class);

        return userService;
    }

}
