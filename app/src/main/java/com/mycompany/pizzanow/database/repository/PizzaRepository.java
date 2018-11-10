package com.mycompany.pizzanow.database.repository;

import android.arch.lifecycle.LiveData;

import com.mycompany.pizzanow.database.Database;
import com.mycompany.pizzanow.database.entity.PizzaEntity;

import java.util.List;

public class PizzaRepository {

    private static PizzaRepository sInstance;

    private final Database mDatabase;

    private PizzaRepository(final Database database) {
        mDatabase = database;
    }

    public static PizzaRepository getInstance(final Database database) {
        if (sInstance == null) {
                if (sInstance == null) {
                    sInstance = new PizzaRepository(database);
                }
        }
        return sInstance;
    }

    public LiveData<PizzaEntity> getPizza(final int pizzaId) {
        return mDatabase.pizzaDao().getById(pizzaId);
    }

   /* public LiveData<List<ClientWithAccounts>> getOtherClientsWithAccounts(final String owner) {
        return mDatabase.pizzaDao().getOtherClientsWithAccounts(owner);
    }*/

    public void insert(final PizzaEntity pizza) {
        mDatabase.pizzaDao().insert(pizza);
    }

    public void update(final PizzaEntity pizza) {
        mDatabase.pizzaDao().update(pizza);
    }

    public void delete(final PizzaEntity pizza) {
        mDatabase.pizzaDao().delete(pizza);
    }
}
