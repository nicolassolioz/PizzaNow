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
import com.mycompany.pizzanow.database.async.pizza.CreatePizza;
import com.mycompany.pizzanow.database.async.pizza.DeletePizza;
import com.mycompany.pizzanow.database.async.pizza.UpdatePizza;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.repository.PizzaRepository;
import com.mycompany.pizzanow.util.OnAsyncEventListener;

public class PizzaViewModel extends AndroidViewModel {

    private static final String TAG = "PizzaViewModel";

    private PizzaRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<PizzaEntity> mObservablePizza;

    public PizzaViewModel(@NonNull Application application,
                           final int pizzaId, PizzaRepository pizzaRepository) {
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
        new CreatePizza(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "create pizza : success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "create pizza : fail");
            }
        }).execute(newPizza);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mPizzaId;

        private final PizzaRepository mRepository;

        public Factory(@NonNull Application application, int pizzaId) {
            Log.d(TAG,"PizzaViewModel enter factory");
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

    public void updatePizza(PizzaEntity pizza, OnAsyncEventListener callback) {
        new UpdatePizza(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
                Log.d(TAG, "updatePizza: success");
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
                Log.d(TAG, "updatePizza: failure", e);
            }
        });
    }

    public void deletePizza(PizzaEntity pizza, OnAsyncEventListener callback) {
        new DeletePizza(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
                Log.d(TAG, "deletePizza: success");
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
                Log.d(TAG, "deletePizza: failure", e);
            }
        }).execute(pizza);

    }
}
