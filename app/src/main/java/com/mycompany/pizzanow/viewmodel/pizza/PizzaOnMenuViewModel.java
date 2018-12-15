package com.mycompany.pizzanow.viewmodel.pizza;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.mycompany.pizzanow.BaseApp;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.repository.MenuPizzaRepository;
import com.mycompany.pizzanow.database.repository.PizzaRepository;

import java.util.List;

public class PizzaOnMenuViewModel {
    /*extends
} AndroidViewModel {

    private static final String TAG = "PizzaOnMenuViewModel";

    private PizzaRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<PizzaEntity>> mObservablePizzas;

    public PizzaOnMenuViewModel(@NonNull Application application, final int menuId,
                                PizzaRepository pizzaRepository) {
        super(application);

        mRepository = pizzaRepository;

        mObservablePizzas = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservablePizzas.setValue(null);

        //LiveData<List<PizzaEntity>> pizzas = mRepository.getAllByIdMenu(menuId);

        LiveData<List<PizzaEntity>> pizzas = mRepository.getPizzasByIdMenu(menuId);

        // observe the changes of the pizza entity from the database and forward them
        mObservablePizzas.addSource(pizzas, mObservablePizzas::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;
        private final int mMenuId;
        private final PizzaRepository mRepository;

        public Factory(@NonNull Application application, int menuId) {
            Log.d(TAG, "PizzaViewModel enter factory");
            mApplication = application;
            Log.d(TAG, "PizzaViewModel enter factory, app set");
            mMenuId = menuId;
            mRepository = ((BaseApp) application).getPizzaRepository();
            Log.d(TAG, "PizzaViewModel enter factory, Repository set");

        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new PizzaOnMenuViewModel(mApplication, mMenuId, mRepository);
        }
    }

    /**
     * Expose the LiveData Pizza query so the UI can observe it.

    public LiveData<List<PizzaEntity>> getPizzasOnmenu() {
        return mObservablePizzas;
    }
    */
}