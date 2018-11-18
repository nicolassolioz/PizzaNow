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
import com.mycompany.pizzanow.database.entity.MenuPizzaEntity;

import java.util.List;

@Dao
public interface MenuPizzaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MenuPizzaEntity> menuPizzas);

    @Insert
    void insert(MenuPizzaEntity menuPizza);

    @Update
    void update(MenuPizzaEntity menuPizza);

    @Delete
    void delete(MenuPizzaEntity menuPizza);

    @Query("SELECT * FROM MenuPizza WHERE idMenu = :id")
    LiveData<List<MenuPizzaEntity>> getByIdMenu(int id);

    @Query("SELECT idPizza FROM MenuPizza WHERE idMenu = :id")
    LiveData<List<Integer>> getIdPizzasByIdMenu(int id);


}
