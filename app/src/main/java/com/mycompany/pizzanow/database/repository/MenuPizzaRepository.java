package com.mycompany.pizzanow.database.repository;

import android.arch.lifecycle.LiveData;

import com.mycompany.pizzanow.database.AppDatabase;
import com.mycompany.pizzanow.database.entity.MenuPizzaEntity;

import java.util.List;

public class MenuPizzaRepository {

    private static MenuPizzaRepository sInstance;

    private final AppDatabase mDatabase;

    private MenuPizzaRepository(final AppDatabase database) {
        mDatabase = database;
    }

    public static MenuPizzaRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            if (sInstance == null) {
                sInstance = new MenuPizzaRepository(database);
            }
        }
        return sInstance;
    }

    /*
    public LiveData<List<Integer>> getPizzasBasedMenu(final int idMenu) {
        return mDatabase.menuPizzaDao().getIdPizzasByIdMenu(idMenu);
    }


    public void insert(final MenuPizzaEntity menuPizza) {
        mDatabase.menuPizzaDao().insert(menuPizza);
    }

    public void update(final MenuPizzaEntity menuPizza) {
        mDatabase.menuPizzaDao().update(menuPizza);
    }

    public void delete(final MenuPizzaEntity menuPizza) {
        mDatabase.menuPizzaDao().delete(menuPizza);
    }
    */
}
