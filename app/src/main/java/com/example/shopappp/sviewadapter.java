package com.example.shopappp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;



import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.recyclerview.widget.RecyclerView;

        import com.bumptech.glide.Glide;

        import java.util.List;



public class sviewadapter extends RecyclerView.Adapter<sviewadapter.sviewViewHolder> {

    private Context mCtx;
    private List<sview> sview;
 String j;
    public sviewadapter(Context mCtx, List<sview> productList) {
        this.mCtx = mCtx;
        this.sview= productList;
    }

    @Override
    public sviewadapter.sviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.sdisplay, null);
        return new sviewadapter.sviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sviewadapter.sviewViewHolder holder, int position) {

        int actualPosition = position % sview.size();
        sview product = sview.get(actualPosition);
j = product.getTitle();

        //loading the image
        Glide.with(mCtx)
                .load(("https://varun71.000webhostapp.com"+product.getImage()))
                .into(holder.imageView);

        holder.textViewTitle.setText(product.getTitle());

    }



    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;

    }

    class sviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
        {

            TextView textViewTitle;
            ImageView imageView;
            int itemId;
            String itemTitle;



        public sviewViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);

            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);

        }

        @Override
            public void onClick (View view){
            itemId = (int) getItemId();
            itemTitle = textViewTitle.getText().toString();
            Intent intent = new Intent(itemView.getContext(), productdisplay.class);
            intent.putExtra("mobile", j);
            itemView.getContext().startActivity(intent);
        }
        }


    }