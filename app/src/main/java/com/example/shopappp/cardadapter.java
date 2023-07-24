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



public class cardadapter extends RecyclerView.Adapter<cardadapter.sviewViewHolder> {

    private Context mCtx;
    private List<sview> sview;
String j ;
    public cardadapter(Context mCtx, List<sview> productList) {
        this.mCtx = mCtx;
        this.sview= productList;
    }

    @Override
    public cardadapter.sviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card, null);
        return new cardadapter.sviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cardadapter.sviewViewHolder holder, int position) {
        sview product = sview.get(position);
//        holder.textViewTitle.setText(product.getTitle());
        System.out.println(product.getTitle());
        j=product.getTitle();

        //loading the image
        Glide.with(mCtx)
                .load(("https://varun71.000webhostapp.com"+product.getImage()))
                .into(holder.imageView);


    }



    @Override
    public int getItemCount() {
        return sview.size();

    }

    class sviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewTitle;
        ImageView imageView;

        int itemId;
        String itemTitle;

        public sviewViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            imageView = itemView.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View view) {
            itemId = (int) getItemId();
//            itemTitle = textViewTitle.getText().toString();
            Intent intent = new Intent(itemView.getContext(), productdisplay.class);
            intent.putExtra("mobile", "tv");
            itemView.getContext().startActivity(intent);
        }
    }
}
