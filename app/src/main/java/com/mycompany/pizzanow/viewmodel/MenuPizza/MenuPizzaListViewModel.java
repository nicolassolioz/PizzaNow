package com.mycompany.pizzanow.viewmodel.MenuPizza;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.mycompany.pizzanow.BaseApp;
import com.mycompany.pizzanow.database.entity.MenuPizzaEntity;
import com.mycompany.pizzanow.database.repository.MenuPizzaRepository;

import java.util.List;

public class MenuPizzaListViewModel extends AndroidViewModel {

    private static final String TAG = "MenuPizzaListViewModel";

    private MenuPizzaRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<Integer>> mObservablePizzas;

    public MenuPizzaListViewModel(@NonNull Application application, final int menuId,
                                  MenuPizzaRepository pizzaRepository) {
        super(application);

        mRepository = pizzaRepository;

        mObservablePizzas = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservablePizzas.setValue(null);

        LiveData<List<Integer>> pizzas = mRepository.getPizzasBasedMenu(menuId);

        // observe the changes of the pizza entity from the database and forward them
        mObservablePizzas.addSource(pizzas, mObservablePizzas::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;
        private final int mMenuId;
        private final MenuPizzaRepository mRepository;

        public Factory(@NonNull Application application, int menuId) {
            Log.d(TAG, "PizzaViewModel enter factory");
            mApplication = application;
            Log.d(TAG, "PizzaViewModel enter factory, app set");
            mMenuId=menuId;
            mRepository = ((BaseApp) application).getMenuPizzaRepository();
            Log.d(TAG, "PizzaViewModel enter factory, Repository set");

        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new MenuPizzaListViewModel(mApplication,mMenuId, mRepository);
        }
    }

    /**
     * Expose the LiveData Pizza query so the UI can observe it.
     */
    public LiveData<List<Integer>> getAllMenuIDPizzas() {
        return mObservablePizzas;
    }
}