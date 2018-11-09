package com.mycompany.pizzanow.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.provider.ContactsContract;

import com.mycompany.pizzanow.database.dao.PizzaDao;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import java.util.List;

@android.arch.persistence.room.Database(entities = {PizzaEntity.class}, version = 1)

public abstract class Database extends RoomDatabase{
    public abstract PizzaDao pizzaDao();

    private static Database sInstance;

    public static Database getInstance(Context context) {

        if (sInstance == null) {
            synchronized (Database.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context, Database.class, "test")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
                }
            }
        }
        return sInstance;
    }

    public void initializeData(final Database database) {

    //List<PizzaEntity>pizzas = database.pizzaDao().getAll();
    //database.pizzaDao().insertAll(pizzas);

    }

}
