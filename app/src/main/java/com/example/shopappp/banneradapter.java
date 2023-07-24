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





public class banneradapter extends RecyclerView.Adapter<banneradapter.sviewViewHolder> {

    private Context mCtx;
    private List<sview> sview;
    String h;

    public banneradapter(Context mCtx, List<sview> productList) {
        this.mCtx = mCtx;
        this.sview= productList;
    }

    @Override
    public banneradapter.sviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.banner, null);
        return new banneradapter.sviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull banneradapter.sviewViewHolder holder, int position) {


        int actualPosition = position % sview.size();
        sview product = sview.get(actualPosition);
        h= product.getTitle();
//        holder.title.setText(product.getTitle());


        //loading the image
        Glide.with(mCtx)
                .load(("https://varun71.000webhostapp.com"+product.getImage()))
                .into(holder.imageView);


    }



    @Override
    public int getItemCount() {
             return Integer.MAX_VALUE;

    }

    class sviewViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        TextView title;

        ImageView imageView;
        long  itemId;
        private String itemTitle;



        public sviewViewHolder(View itemView) {
            super(itemView);

               itemView.setOnClickListener(this);
               title=itemView.findViewById(R.id.textViewTitle);
            itemView.setOnClickListener(this);

            imageView = itemView.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View view) {
            itemId = (int) getItemId();
//            itemTitle = title.getText().toString();
            Intent intent = new Intent(itemView.getContext(), productdisplay.class);
            intent.putExtra("mobile", h);
            itemView.getContext().startActivity(intent);
        }
    }
}
