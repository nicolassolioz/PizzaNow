package com.mycompany.pizzanow.database;

import android.arch.persistence.room.RoomDatabase;

import com.mycompany.pizzanow.database.dao.PizzaDao;
import com.mycompany.pizzanow.database.entity.PizzaEntity;

@android.arch.persistence.room.Database(entities = {PizzaEntity.class}, version = 1)
public abstract class Database extends RoomDatabase{
    public abstract PizzaDao pizzaDao();

}
