package com.example.shopappp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class cartdata extends AppCompatActivity implements PaymentResultListener {

    RecyclerView recview;
    TextView rateview;
    Button button;
    int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartdata);

        rateview=findViewById(R.id.rateview);
        button=findViewById(R.id.pay);



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
                    checkout.open(cartdata.this, object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }








    public void getroomdata()
    {


        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "cart_db").addMigrations(new Migration1to2(1,2)).allowMainThreadQueries().build();
        ProductDao productDao = db.ProductDao();

        recview=findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        List<Product> products=productDao.getallproduct();

        myadapter adapter=new myadapter(cartdata.this,products, rateview);
        recview.setAdapter(adapter);

         sum=0;int i;
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