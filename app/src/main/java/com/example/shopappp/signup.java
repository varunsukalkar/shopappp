package com.example.shopappp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signup extends AppCompatActivity {
    TextView tv;
    EditText loginemail, loginpassword;
    TextView loginreport;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tv = (TextView) findViewById(R.id.login_tv);
        loginemail = findViewById(R.id.login_email);
        loginpassword = findViewById(R.id.login_password);
        loginbtn = findViewById(R.id.login_submit);
       checkuserexistence();
String e= String.valueOf(loginemail.getText());

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
                    loginemail.requestFocus();
                    loginemail.setError("please enter valid email");
                }
                if(loginemail.getText().toString().isEmpty()){
                    loginemail.requestFocus();
                    loginemail.setError("please enter email");
                }
                if(loginpassword.getText().length()<5){
                    loginpassword.requestFocus();
                    loginpassword.setError("password must be atleast 5 words");
                }






                processlogin(loginemail.getText().toString(), loginpassword.getText().toString());


            }
        });


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), register.class));
                finish();
            }
        });
    }

    private void processlogin(String email, String password) {



        Call<login_resopnse_modal> loginResponseCall = ApiClient.getUserService().getlogin(email,password);
        loginResponseCall.enqueue(new Callback<login_resopnse_modal>() {
            @Override
            public void onResponse(Call<login_resopnse_modal> call, Response<login_resopnse_modal> response) {

                if (response.isSuccessful()) {


                    login_resopnse_modal loginResponse = response.body();

                    if (loginResponse.getMessage().equals("varun")) {
                        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
                 SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username", loginemail.getText().toString());
                  editor.putString("password", loginpassword.getText().toString());
                         editor.commit();
              editor.apply();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(signup.this, "Login Successful", Toast.LENGTH_LONG).show();


                                startActivity(new Intent(signup.this, dashboard.class));
                            }
                        }, 100);


                    }
                    if (loginResponse.getMessage().equals("aveena")) {
                        Toast.makeText(signup.this, "invalid username password", Toast.LENGTH_LONG).show();

                    }

                }else {

                        Toast.makeText(signup.this, "Login Failed", Toast.LENGTH_LONG).show();

                    }


            }
            @Override
            public void onFailure(Call<login_resopnse_modal> call, Throwable t) {
                Toast.makeText(signup.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }





















    public void onLoginClick(View View){
        startActivity(new Intent(this,register.class));

    }




        void checkuserexistence()
        {
            SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
            if(sp.contains("username")) {
                startActivity(new Intent(getApplicationContext(), dashboard.class));
                finish();
            }
            else{

            }
        }}
