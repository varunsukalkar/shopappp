package com.example.shopappp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product
{
    @PrimaryKey(autoGenerate = true)
    public int pid;

    @ColumnInfo(name = "pname")
    public String pname;

    @ColumnInfo(name = "price")
    public int price;

    @ColumnInfo(name = "qnt")
    public int qnt;

    @ColumnInfo(name = "image")
    public String image;

    public Product(int pid, String pname, int price, int qnt,String image) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.qnt = qnt;
        this.image=image;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
