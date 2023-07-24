package com.example.shopappp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class myadapter  extends RecyclerView.Adapter<myadapter.myviewholder>
{
    List<Product> products;
    TextView rateview;
    private Context mCtx;

    public myadapter(Context mCtx,List<Product> products, TextView rateview) {
        this.mCtx = mCtx;
        this.products = products;
        this.rateview= rateview;
    }

    @NonNull
    @NotNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull myadapter.myviewholder holder,int position) {
        int adapterPosition = holder.getAdapterPosition();
        if (adapterPosition != RecyclerView.NO_POSITION) {
            holder.recid.setText(String.valueOf(products.get(adapterPosition).getPid()));
            holder.recpname.setText(products.get(adapterPosition).getPname());
            holder.recpprice.setText(String.valueOf(products.get(adapterPosition).getPrice()));
            holder.recqnt.setText(String.valueOf(products.get(adapterPosition).getQnt()));
            Glide.with(holder.itemView.getContext())
                    .load(products.get(adapterPosition).getImage())  // Assuming you have the image URL in your Product model
                    .into(holder.imageView);

        }
       holder.delbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AppDatabase db = Room.databaseBuilder(holder.recid.getContext(),
               AppDatabase.class, "cart_db").allowMainThreadQueries().build();
               ProductDao productDao = db.ProductDao();

               productDao.deleteById(products.get(adapterPosition).getPid());
               products.remove(adapterPosition);
               notifyItemRemoved(adapterPosition);
               updateprice();
           }
       });

           holder.incr.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   int qnt=products.get(adapterPosition).getQnt();
                   qnt++;
                   products.get(adapterPosition).setQnt(qnt);
                   notifyDataSetChanged();
                   updateprice();
               }
           });

           holder.decr.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   int qnt=products.get(adapterPosition).getQnt();
                   qnt--;
                   products.get(adapterPosition).setQnt(qnt);
                   notifyDataSetChanged();
                   updateprice();
               }
           });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView recid,recpname,recqnt, recpprice;
        ImageButton delbtn;
        ImageView incr,decr,imageView;

        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            recid=itemView.findViewById(R.id.recid);
            recpname=itemView.findViewById(R.id.recpname);
            recpprice=itemView.findViewById(R.id.recpprice);
            recqnt=itemView.findViewById(R.id.recqnt);
            delbtn=itemView.findViewById(R.id.delbtn);
            imageView=itemView.findViewById(R.id.imageView);
            incr=itemView.findViewById(R.id.incbtn);
            decr=itemView.findViewById(R.id.decbtn);
        }
    }

    public void updateprice()
    {
        int sum=0,i;
        for(i=0;i< products.size();i++)
            sum=sum+(products.get(i).getPrice()*products.get(i).getQnt());

        rateview.setText("Total Amount : INR "+sum);
    }

}
