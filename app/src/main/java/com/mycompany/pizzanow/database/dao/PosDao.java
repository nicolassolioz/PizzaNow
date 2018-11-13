package com.mycompany.pizzanow.database.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.mycompany.pizzanow.database.entity.PosEntity;

import java.util.List;

@Dao
public interface PosDao {
    @Query("SELECT * FROM filiale")
    LiveData<List<PosEntity>> getAll();

    @Insert
    void insert(PosEntity posEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PosEntity> posEntities);

    @Update
    void update(PosEntity posEntity);

    @Delete
    void delete(PosEntity posEntity);


    @Query("SELECT * FROM filiale WHERE IdFiliale = :id")
    LiveData<PosEntity> getById(int id);

    @Query("SELECT * FROM filiale WHERE IdFiliale= :id")
    PosEntity getOnePos(int id);
}
