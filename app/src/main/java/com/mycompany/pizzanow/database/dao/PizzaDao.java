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
import android.widget.ListAdapter;

import com.mycompany.pizzanow.database.entity.PizzaEntity;
import java.util.List;

@Dao
public interface PizzaDao {/*
    @Query("SELECT * FROM Pizza")
    LiveData<List<PizzaEntity>> getAll();

    @Insert
    void insert(PizzaEntity pizzaEntity);

    @Query("SELECT * FROM Pizza WHERE idPizza=1")
    List<PizzaEntity> getOnePizza();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PizzaEntity> pizzas);

    @Update
    void update(PizzaEntity pizza);

    @Delete
    void delete(PizzaEntity pizza);

    @Query("SELECT * FROM Pizza WHERE idPizza = :id")
    LiveData<PizzaEntity> getById(int id);

    @Query("SELECT P.idPizza, P.Nom, P.Description, P.Prix FROM Pizza AS P INNER JOIN MenuPizza AS MP ON P.idPizza = MP.idPizza INNER JOIN Menu AS M ON M.idMenu = :id")
    LiveData<List<PizzaEntity>> getPizzasByIdMenu(int id);

    @Query("SELECT * FROM Pizza WHERE idPizza IN (:ids) ")
    LiveData<List<PizzaEntity>> getAllByIdMenu(List<Integer> ids);
    */
}
