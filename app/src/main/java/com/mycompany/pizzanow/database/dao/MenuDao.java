package com.mycompany.pizzanow.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.MenuEntity;
import com.mycompany.pizzanow.database.entity.PizzaEntity;

import java.util.List;

@Dao
public interface MenuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MenuEntity> menus);

    @Insert
    void insert(MenuEntity menuEntity);

    @Update
    void update(MenuEntity menuEntity);

    @Delete
    void delete(MenuEntity menuEntity);

    @Query("SELECT * FROM Menu WHERE idMenu = :id")
    LiveData<MenuEntity> getById(int id);

    @Query("SELECT * FROM Menu")
    LiveData<List<MenuEntity>> getAll();
}
