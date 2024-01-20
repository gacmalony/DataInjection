package com.example.datainjection.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface KarlsruheDAO {


    @Insert
    void insert(Karlsruhe karlsruhe);

    @Update
    void update(Karlsruhe karlsruhe);

    @Delete
    void delete(Karlsruhe karlsruhe);


    @Query("SELECT * FROM my_table")
    LiveData<List<Karlsruhe>> getAllKarlsruhe();

    @Query("SELECT * FROM my_table WHERE category_id==:categoryId")
    LiveData<List<Karlsruhe>> getKarlsruhe(int categoryId);





}
