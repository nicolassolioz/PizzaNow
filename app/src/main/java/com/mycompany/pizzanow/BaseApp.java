package com.mycompany.pizzanow;

import android.app.Application;

import com.mycompany.pizzanow.database.Database;
import com.mycompany.pizzanow.database.repository.PizzaRepository;

public class BaseApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Database getDatabase() {
        return Database.getInstance(this);
    }

    public PizzaRepository getPizzaRepository() {
        return PizzaRepository.getInstance(getDatabase());
    }

}
