package com.example.shopappp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {

    EditText regemail,regmobile,regpassord,regaddress;
    Button regsubmit;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        regemail=(EditText)findViewById(R.id.reg_email);
        regmobile=(EditText)findViewById(R.id.reg_mobile);
        regpassord=(EditText)findViewById(R.id.reg_password);
        regaddress=(EditText)findViewById(R.id.reg_address);

        regsubmit=(Button)findViewById(R.id.reg_submit);
        regsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userregister(regemail.getText().toString(),regmobile.getText().toString(),regpassord.getText().toString(),regaddress.getText().toString());
            }
        });
    }

    public void userregister(String email, String mobile, String password,String address)
    {
        String name="not applicable";

        Call<signup_response_model> call=apicontroller.getInstance()
                .getapi()
                .getregister(name,email,password,mobile,address);

        call.enqueue(new Callback<signup_response_model>() {


            

            @Override
            public void onResponse(Call<signup_response_model> call, Response<signup_response_model> response) {
                signup_response_model obj=response.body();
                String result=obj.getMessage().trim();
                if(result.equals("inserted"))

                {

                    Toast.makeText(register.this, "account created", Toast.LENGTH_SHORT).show();
                    regemail.setText("");
                    regmobile.setText("");
                    regpassord.setText("");
                    startActivity(new Intent(register.this,signup.class));
                    finish();

                }
                if(result.equals("exist"))
                {
                    regemail.setText("");
                    regmobile.setText("");
                    regpassord.setText("");
                }
            }

            @Override
            public void onFailure(Call<signup_response_model> call, Throwable t) {
                regemail.setText("");
                regmobile.setText("");
                regpassord.setText("");
                Toast.makeText(register.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,signup.class));
        finish();

    }

}