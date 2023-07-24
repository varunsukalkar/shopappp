package com.example.shopappp;


public class Product11{
    private int id;
    private String title;
    private int price;
    private String image;

    public Product11(int id, String title, int price, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
    }
    public Product11(int id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }



    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}