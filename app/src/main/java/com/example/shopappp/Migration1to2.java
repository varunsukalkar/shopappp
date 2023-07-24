package com.example.shopappp;


import android.content.Context;

import androidx.room.Room;
import androidx.room.migration.Migration;
        import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migration1to2 extends Migration {
Context context;
    public AppDatabase database;

    public Migration1to2(int startVersion, int endVersion) {
        super(startVersion, endVersion);
    }

    @Override
    public void migrate(SupportSQLiteDatabase database) {
        // Perform necessary schema changes here
        // You can use SQL statements like ALTER TABLE to modify the existing schema
        database.execSQL("ALTER TABLE Product ADD COLUMN image TEXT");

    }
}
