package com.example.shopappp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiset
{
     @FormUrlEncoded
     @POST("signup.php")
    Call<signup_response_model> getregister(
          @Field("name") String name,
          @Field("email") String email,
          @Field("password") String password,
          @Field("mobile") String mobile,
          @Field("address") String address
     );

    @FormUrlEncoded
    @POST("lo.php")
    Call<login_resopnse_modal> getlogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("logout.php")
    Call<Void> logout();
}
