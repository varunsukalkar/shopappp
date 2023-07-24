package com.example.shopappp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;

import java.util.List;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

String img;
    private Context mCtx;
    private List<Product11> productList;

    public ProductsAdapter(Context mCtx, List<Product11> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.mobile, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product11 product = productList.get(position);

img = "https://varun71.000webhostapp.com"+product.getImage();
        //loading the image
        Glide.with(mCtx)
                .load(img)
                .into(holder.imageView);

        holder.title.setText(product.getTitle());
        holder.price.setText(String.valueOf(product.getPrice()));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    AppDatabase db= Room.databaseBuilder(mCtx.getApplicationContext(),AppDatabase.class,"cart_db").allowMainThreadQueries().build();
                    ProductDao productDao=db.ProductDao();
                    Boolean check=productDao.is_exist((product.getId()));

                    if(check==false)
                    {
                        int pid=(product.getId());
                        String pname=product.getTitle();
                        int price=(product.getPrice());

                        productDao.insertrecord(new Product(pid,pname,price,1,img));
                        Toast.makeText(mCtx, " product added to cart", Toast.LENGTH_SHORT).show();


                    }
                    else
                    {
                        Toast.makeText(mCtx, "product already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            });












        }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView title,price;
        ImageView imageView;
        Button button;
        int id ;

        public ProductViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            price= itemView.findViewById(R.id.price);
            button = itemView.findViewById(R.id.Button);
            imageView = itemView.findViewById(R.id.ImageView);
        }
    }
}