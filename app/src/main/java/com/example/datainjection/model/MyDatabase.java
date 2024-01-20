package com.example.datainjection.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Category.class, Karlsruhe.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract CategoryDAO categoryDAO();
    public abstract KarlsruheDAO karlsruheDAO();

    // Singleton Pattern
    private static MyDatabase instance;

    public static synchronized MyDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MyDatabase.class, "my_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    // Callback
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // Insert data when db is created...
            InitializeData();


        }
    };

    private static void InitializeData() {

        KarlsruheDAO karlsruheDAO = instance.karlsruheDAO();
        CategoryDAO categoryDAO= instance.categoryDAO();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {

                // Categories
                Category category1 = new Category();
                category1.setCategoryName("Front End");
                category1.setCategoryDescription("Web Development Interface");

                Category category2 = new Category();
                category2.setCategoryName("Back End");
                category2.setCategoryDescription("Web Development Database");

                categoryDAO.insert(category1);
                categoryDAO.insert(category2);


                // Add Data
                Karlsruhe karlsruhe1 = new Karlsruhe();
                karlsruhe1.setKarlName("HTML");
                karlsruhe1.setUnitPrice("100$");
                karlsruhe1.setCategoryId(1);

                Karlsruhe karlsruhe2 = new Karlsruhe();
                karlsruhe2.setKarlName("CSS");
                karlsruhe2.setUnitPrice("100$");
                karlsruhe2.setCategoryId(1);

                Karlsruhe karlsruhe3 = new Karlsruhe();
                karlsruhe3.setKarlName("PHP");
                karlsruhe3.setUnitPrice("400$");
                karlsruhe3.setCategoryId(2);

                Karlsruhe karlsruhe4 = new Karlsruhe();
                karlsruhe4.setKarlName("AJAX");
                karlsruhe4.setUnitPrice("200$");
                karlsruhe4.setCategoryId(2);

                karlsruheDAO.insert(karlsruhe1);
                karlsruheDAO.insert(karlsruhe2);
                karlsruheDAO.insert(karlsruhe3);
                karlsruheDAO.insert(karlsruhe4);

            }
        });




    }


}
