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
import com.mycompany.pizzanow.database.repository.PizzaRepository;
import com.mycompany.pizzanow.util.OnAsyncEventListener;

public class PizzaViewModel extends AndroidViewModel {

    private static final String TAG = "PizzaViewModel";

    private PizzaRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<PizzaEntity> mObservablePizza;

    public PizzaViewModel(@NonNull Application application,
                           final String pizzaId, PizzaRepository pizzaRepository) {
        super(application);

        mRepository = pizzaRepository;

        mObservablePizza = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservablePizza.setValue(null);

            LiveData<PizzaEntity> pizza = mRepository.getPizza(pizzaId);

            // observe the changes of the pizza entity from the database and forward them
            mObservablePizza.addSource(pizza, mObservablePizza::setValue);

    }

    public void createPizza(PizzaEntity newPizza) {
        ((BaseApp) getApplication()).getPizzaRepository().insert(newPizza);
    }

    /**
     * A creator is used to inject the pizza id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final String mPizzaId;

        private final PizzaRepository mRepository;

        public Factory(@NonNull Application application, String pizzaId) {

            mApplication = application;
            Log.d(TAG,"PizzaViewModel enter factory, app set");
            mPizzaId = pizzaId;
            Log.d(TAG,"PizzaViewModel enter factory, pizza id set");
            mRepository = ((BaseApp) application).getPizzaRepository();
            Log.d(TAG,"PizzaViewModel enter factory, Repository set");

        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new PizzaViewModel(mApplication, mPizzaId, mRepository);
        }
    }

    /**
     * Expose the LiveData PizzaEntity query so the UI can observe it.
     */
    public LiveData<PizzaEntity> getPizza() {
        return mObservablePizza;
    }

    public void updatePizza(PizzaEntity pizza) {
        ((BaseApp) getApplication()).getPizzaRepository().update(pizza);
    }

    public void deletePizza(PizzaEntity pizza) {

        ((BaseApp) getApplication()).getPizzaRepository().delete(pizza);

    }
}
