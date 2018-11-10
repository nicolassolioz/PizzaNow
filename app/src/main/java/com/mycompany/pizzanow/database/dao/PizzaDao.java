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
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import java.util.List;

@Dao
public interface PizzaDao {
    @Query("SELECT * FROM Pizza")
    List<PizzaEntity> getAll();

    @Insert
    void insert(PizzaEntity pizzaEntity);

    @Query("SELECT * FROM Pizza WHERE idPizza=1")
    List<PizzaEntity> getOnePizza();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PizzaEntity> pizzas);
}
