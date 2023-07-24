package com.example.shopappp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class productdisplay extends AppCompatActivity {
    static String u = null;

    List<Product11> productList;

    //the recyclerview
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_productdisplay);
    String    a=getIntent().getStringExtra("mobile");
    switch (a)
    {
        case "oil":
            u=  "https://varun71.000webhostapp.com/oil.php";
         break;
        case "mobile":
            u="https://varun71.000webhostapp.com/mobile.php";
            break;

        case "furniture":
       u=         "https://varun71.000webhostapp.com/furniture.php";
       break;
        case "fashino":
            u="https://varun71.000webhostapp.com/fashino.php";
            break;
        case "etv":
            u="https://varun71.000webhostapp.com/etv.php";
            break;
        case "cook":
            u="https://varun71.000webhostapp.com/cook.php";

    }
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        System.out.println(getIntent().getStringExtra("mobile"));
        productList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();








    }


    private void loadProducts() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, u,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Product11(
                                        product.getInt("id"),
                                        product.getString("title"),
                                        product.getInt("price"),
                                        product.getString("image")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            ProductsAdapter adapter = new ProductsAdapter(productdisplay.this, productList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Handle the back button event
        finish(); // Finish the current activity
        return true;
    }


}




