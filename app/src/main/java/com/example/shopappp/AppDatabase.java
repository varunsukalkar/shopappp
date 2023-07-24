package com.example.shopappp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract ProductDao ProductDao();
}
