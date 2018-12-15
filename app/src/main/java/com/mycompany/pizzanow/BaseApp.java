package com.mycompany.pizzanow;

import android.app.Application;

import com.mycompany.pizzanow.database.AppDatabase;
import com.mycompany.pizzanow.database.repository.CollaborateurRepository;
import com.mycompany.pizzanow.database.repository.MenuPizzaRepository;
import com.mycompany.pizzanow.database.repository.MenuRepository;
import com.mycompany.pizzanow.database.repository.PizzaRepository;
import com.mycompany.pizzanow.database.repository.PosRepository;

public class BaseApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }

    public PizzaRepository getPizzaRepository() {
        return PizzaRepository.getInstance();
    }

    public CollaborateurRepository getCollaborateurRepository() {
        return CollaborateurRepository.getInstance(getDatabase());
    }

    public PosRepository getPosRepository(){
        return PosRepository.getInstance(getDatabase());
    }

    public MenuRepository getMenuRepository() {
        return MenuRepository.getInstance(getDatabase());
    }

   /* public MenuPizzaRepository getMenuPizzaRepository() {
        return MenuPizzaRepository.getInstance(getDatabase());
    }
*/
}
