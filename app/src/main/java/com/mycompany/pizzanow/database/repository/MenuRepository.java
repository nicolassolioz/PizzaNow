package com.mycompany.pizzanow.database.repository;

import android.arch.lifecycle.LiveData;

import com.mycompany.pizzanow.database.AppDatabase;
import com.mycompany.pizzanow.database.entity.MenuEntity;

import java.util.List;

public class MenuRepository {

    private static MenuRepository sInstance;

    private final AppDatabase mDatabase;

    private MenuRepository(final AppDatabase database) {
        mDatabase = database;
    }

    public static MenuRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            if (sInstance == null) {
                sInstance = new MenuRepository(database);
            }
        }
        return sInstance;
    }

    /*public LiveData<MenuEntity> getMenu(final int idMenu) {
        return mDatabase.menuDao().getById(idMenu);
    }

    public LiveData<List<MenuEntity>> getAllMenu() {
        return mDatabase.menuDao().getAll();
    }*/

   /* public LiveData<List<ClientWithAccounts>> getOtherClientsWithAccounts(final String owner) {
        return mDatabase.pizzaDao().getOtherClientsWithAccounts(owner);
    }*/

    /*public void insert(final MenuEntity menu) {
        mDatabase.menuDao().insert(menu);
    }

    public void update(final MenuEntity menu) {
        mDatabase.menuDao().update(menu);
    }

    public void delete(final MenuEntity menu) {
        mDatabase.menuDao().delete(menu);
    }*/
}
