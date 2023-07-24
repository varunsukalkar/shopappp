package com.example.shopappp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.navigation.NavigationView;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import retrofit2.Call;
import retrofit2.Callback;

public class dashboard extends AppCompatActivity implements PaymentResultListener {
    int sum;
    RecyclerView recview;
    TextView rateview;

    MeowBottomNavigation bottomNavigation;
    RelativeLayout home, category, account, cart;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle Toggle;
    NavigationView nav;
    Button button;
    Toolbar toolbar;
    private static final String URL_PRODUCTS = "https://varun71.000webhostapp.com/sview.php";
    List<sview> productList1;
    RecyclerView recyclerView, rebanner;
    RecyclerView rebox,cardre;
    List<sview> boxlist, bannerlist,cardlist;
    private static final String box = "https://varun71.000webhostapp.com/sidebar.php";
    private static final String prasad = "https://varun71.000webhostapp.com/banner.php";
    private static final String card = "https://varun71.000webhostapp.com/card.php";


    LinearLayoutManager HorizontalLayout, HorizontalLayout1, HorizontalLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        category = findViewById(R.id.category);
        account = findViewById(R.id.account);
        cart = findViewById(R.id.cart);
        home = findViewById(R.id.home);


        bottomNavigation = findViewById(R.id.bottomNavigation);


        bottomNavigation.show(1, true);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_home_24));

        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_category_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_person_24));

        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.baseline_shopping_cart_24));


        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()) {
                    case 1:
                        home.setVisibility(View.VISIBLE);
                        category.setVisibility(View.GONE);
                        account.setVisibility(View.GONE);
                        cart.setVisibility(View.GONE);
                        break;

                    case 2:
                        home.setVisibility(View.GONE);
                        category.setVisibility(View.VISIBLE);
                        account.setVisibility(View.GONE);
                        cart.setVisibility(View.GONE);
                        break;
                    case 3:
                        home.setVisibility(View.GONE);
                        category.setVisibility(View.GONE);
                        account.setVisibility(View.VISIBLE);
                        cart.setVisibility(View.GONE);
                        break;

                    case 4:
                        home.setVisibility(View.GONE);
                        category.setVisibility(View.GONE);
                        account.setVisibility(View.GONE);
                        cart.setVisibility(View.VISIBLE);
                        break;

                }
                // your codes
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // your codes
                switch (item.getId()) {
                    case 2:
                        home.setVisibility(View.GONE);
                        category.setVisibility(View.VISIBLE);
                        account.setVisibility(View.GONE);
                        cart.setVisibility(View.GONE);
                }
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // your codes
                switch (item.getId()) {
                    case 3:
                        home.setVisibility(View.GONE);
                        category.setVisibility(View.GONE);
                        account.setVisibility(View.VISIBLE);
                        cart.setVisibility(View.GONE);
                }
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // your codes
                switch (item.getId()) {
                    case 4:
                        home.setVisibility(View.GONE);
                        category.setVisibility(View.GONE);
                        account.setVisibility(View.GONE);
                        cart.setVisibility(View.VISIBLE);
                        button=findViewById(R.id.pay);



                        rateview=findViewById(R.id.rateview);



                        getroomdata();

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // initialize Razorpay account.
                                Checkout checkout = new Checkout();

                                // set your id as below
                                checkout.setKeyID("rzp_test_pV6zBXJZXwVos4");

                                // set image
                                checkout.setImage(R.drawable.v);

                                // initialize json object
                                JSONObject object = new JSONObject();
                                try {
                                    // to put name
                                    object.put("name", "H mart");

                                    // put description
                                    object.put("description", "Test payment");

                                    // to set theme color
                                    object.put("theme.color", "");

                                    // put the currency
                                    object.put("currency", "INR");

                                    // put amount
                                    object.put("amount", sum);

                                    // put mobile number
                                    object.put("prefill.contact", "7387310647");

                                    // put email
                                    object.put("prefill.email", "varunsukalkar31@gmail.com");

                                    // open razorpay to checkout activity
                                    checkout.open(dashboard.this, object);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                }


                }
        });


        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                // your codes
                switch (item.getId()) {
                    case 1:
                        home.setVisibility(View.VISIBLE);
                        category.setVisibility(View.GONE);
                        account.setVisibility(View.GONE);
                        cart.setVisibility(View.GONE);
                        break;

                    case 2:
                        home.setVisibility(View.GONE);
                        category.setVisibility(View.VISIBLE);
                        account.setVisibility(View.GONE);
                        cart.setVisibility(View.GONE);
                        break;
                    case 3:
                        home.setVisibility(View.GONE);
                        category.setVisibility(View.GONE);
                        account.setVisibility(View.VISIBLE);
                        cart.setVisibility(View.GONE);
                        break;

                    case 4:
                        home.setVisibility(View.GONE);
                        category.setVisibility(View.GONE);
                        account.setVisibility(View.GONE);

                        cart.setVisibility(View.VISIBLE);
                        button=findViewById(R.id.pay);



                        rateview=findViewById(R.id.rateview);



                        getroomdata();

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // initialize Razorpay account.
                                Checkout checkout = new Checkout();

                                // set your id as below
                                checkout.setKeyID("rzp_test_pV6zBXJZXwVos4");

                                // set image
                                checkout.setImage(R.drawable.v);

                                // initialize json object
                                JSONObject object = new JSONObject();
                                try {
                                    // to put name
                                    object.put("name", "H mart");

                                    // put description
                                    object.put("description", "Test payment");

                                    // to set theme color
                                    object.put("theme.color", "");

                                    // put the currency
                                    object.put("currency", "INR");

                                    // put amount
                                    object.put("amount", sum);

                                    // put mobile number
                                    object.put("prefill.contact", "7387310647");

                                    // put email
                                    object.put("prefill.email", "varunsukalkar31@gmail.com");

                                    // open razorpay to checkout activity
                                    checkout.open(dashboard.this, object);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });























                        break;

                }
            }
        });

        toolbar = (Toolbar) findViewById(R.id.a);
        toolbar.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_menu_24);


        nav = (NavigationView) findViewById(R.id.navimumbai);
        drawerLayout = (DrawerLayout) findViewById(R.id.my_drawer_layout);
        Toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.menu_open, R.string.menu_close);
        drawerLayout.addDrawerListener(Toggle);
        Toggle.syncState();
//


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {

                switch (menuitem.getItemId()) {
                    case R.id.menu_home:
//                  Toast.makeText(MainActivity.this, "home ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(dashboard.this,cartdata.class));
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;


                    case R.id.menu_call:


                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"questooteam@gmail.com"});
                        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                        i.putExtra(Intent.EXTRA_TEXT, "body of email");
                        try {
                            startActivity(Intent.createChooser(i, "Send mail..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(dashboard.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }


                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_support:


                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_logout:{


                        apiset apiService = MyApplication.getApiService();
                        Call<Void> call = apiService.logout();
                        call.enqueue(new Callback<Void>() {

                            @Override
                            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                                if (response.isSuccessful()) {
                                    startActivity(new Intent(dashboard.this,signup.class));
                                    // Handle the response or any necessary UI updates after logout
                                } else {
                                    Toast.makeText(dashboard.this, "failed logout", Toast.LENGTH_SHORT).show();
                                    // Handle unsuccessful response
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                // Handle failure cases
                            }
                        });
                }




                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;}


                return true;
            }
        });


        //the recyclerview

        recyclerView = findViewById(R.id.avvena);
        recyclerView.setHasFixedSize(true);
        HorizontalLayout
                = new LinearLayoutManager(
                dashboard.this,
                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView.setLayoutManager(HorizontalLayout);


        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts((ArrayList) productList1, URL_PRODUCTS);


        rebox = findViewById(R.id.varun);
        rebox.setHasFixedSize(true);
        HorizontalLayout1
                = new LinearLayoutManager(
                dashboard.this,
                LinearLayoutManager.HORIZONTAL,
                false);
        rebox.setLayoutManager(HorizontalLayout1);
        boxlist = new ArrayList<>();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, box,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product1 = array.getJSONObject(i);

                                //adding the product to product list
                                boxlist.add(new sview(
                                        product1.getInt("id"),
                                        product1.getString("title"),
                                        product1.getString("image")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            boxadapter adapter = new boxadapter(dashboard.this, boxlist);
                            rebox.setAdapter(adapter);
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


        rebanner = findViewById(R.id.banner);
        rebanner.setHasFixedSize(true);
        HorizontalLayout2
                = new

                LinearLayoutManager(
                dashboard.this,
                LinearLayoutManager.HORIZONTAL,
                true);
        rebanner.setLayoutManager(HorizontalLayout2);
        bannerlist = new ArrayList<>();


        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, prasad,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product1 = array.getJSONObject(i);

                                //adding the product to product list
                                bannerlist.add(new sview(
                                        product1.getInt("id"),
                                        product1.getString("title"),
                                        product1.getString("image")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            banneradapter adapter = new banneradapter(dashboard.this, bannerlist);
                            rebanner.setAdapter(adapter);
                            startAutoScroll();
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
        Volley.newRequestQueue(this).add(stringRequest2);








        cardre = findViewById(R.id.card);
        cardre.setHasFixedSize(true);
        GridLayoutManager layoutManager=new GridLayoutManager(this,3);


        cardre.setLayoutManager(layoutManager);
        cardlist = new ArrayList<>();


        StringRequest stringRequest3 = new StringRequest(Request.Method.GET, card,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product1 = array.getJSONObject(i);

                                //adding the product to product list
                                cardlist.add(new sview(
                                        product1.getInt("id"),
                                        product1.getString("title"),
                                        product1.getString("image")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            cardadapter adapter = new cardadapter(dashboard.this, cardlist);
                            cardre.setAdapter(adapter);
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
        Volley.newRequestQueue(this).add(stringRequest3);




















    }


    private Timer autoScrollTimer;
    private int currentPosition = 0;

    public void startAutoScroll() {
        autoScrollTimer = new Timer();
        autoScrollTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                currentPosition++;
                rebanner.smoothScrollToPosition(currentPosition);
            }
        }, 0, 2000); // 3000 milliseconds (3 seconds) interval
    }

    public void stopAutoScroll() {
        if (autoScrollTimer != null) {
            autoScrollTimer.cancel();
            autoScrollTimer = null;
        }
    }


    private void loadProducts(ArrayList productList, String s) {
        productList = new ArrayList<>();


        ArrayList finalProductList = productList;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, s,
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
                                finalProductList.add(new sview(
                                        product.getInt("id"),
                                        product.getString("title"),
                                        product.getString("image")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            sviewadapter adapter = new sviewadapter(dashboard.this, finalProductList);
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
    protected void onPause() {
        super.onPause();
        stopAutoScroll();
    }

    public void getroomdata()
    {


        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "cart_db").addMigrations(new Migration1to2(1,2)).allowMainThreadQueries().build();
        ProductDao productDao = db.ProductDao();

        recview=findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        List<Product> products=productDao.getallproduct();

        myadapter adapter=new myadapter(dashboard.this,products, rateview);
        recview.setAdapter(adapter);

        int sum=0,i;
        for(i=0;i< products.size();i++)
            sum=sum+(products.get(i).getPrice()*products.get(i).getQnt());

        rateview.setText("Total Amount : INR "+sum);
    }






    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment is successful : " + s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed due to error : " , Toast.LENGTH_SHORT).show();

    }



}



















