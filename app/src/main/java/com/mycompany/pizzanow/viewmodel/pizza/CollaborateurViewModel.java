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
import com.mycompany.pizzanow.database.async.collaborateur.DeleteCollaborateur;
import com.mycompany.pizzanow.database.async.collaborateur.UpdateCollaborateur;
import com.mycompany.pizzanow.database.async.pizza.DeletePizza;
import com.mycompany.pizzanow.database.async.pizza.UpdatePizza;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.repository.CollaborateurRepository;
import com.mycompany.pizzanow.database.repository.PizzaRepository;
import com.mycompany.pizzanow.util.OnAsyncEventListener;

public class CollaborateurViewModel extends AndroidViewModel {

    private static final String TAG = "CollaborateurViewModel";

    private CollaborateurRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<CollaborateurEntity> mObservableCollaborateur;

    public CollaborateurViewModel(@NonNull Application application,
                          final int idCollaborateur, CollaborateurRepository collaborateurRepository) {
        super(application);

        mRepository = collaborateurRepository;

        mObservableCollaborateur = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableCollaborateur.setValue(null);

        LiveData<CollaborateurEntity> collaborateur = mRepository.getCollaborateurNom("Bocelli");

        // observe the changes of the pizza entity from the database and forward them
        mObservableCollaborateur.addSource(collaborateur, mObservableCollaborateur::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mIdCollaborateur;

        private final CollaborateurRepository mRepository;

        public Factory(@NonNull Application application, int idCollaborateur) {
            Log.d(TAG,"CollaborateurViewModel enter factory");
            mApplication = application;
            Log.d(TAG,"CollaborateurViewModel enter factory, app set");
            mIdCollaborateur = idCollaborateur;
            Log.d(TAG,"CollaborateurViewModel enter factory,  id Collaborateur set");


            mRepository = ((BaseApp) application).getCollaborateurRepository();
            Log.d(TAG,"CollaborateurViewModel enter factory, Repository set");

        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new CollaborateurViewModel(mApplication, mIdCollaborateur, mRepository);
        }
    }

    /**
     * Expose the LiveData PizzaEntity query so the UI can observe it.
     */
    public LiveData<CollaborateurEntity> getCollaborateur() {
        return mObservableCollaborateur;
    }

    public void updateCollaborateur(CollaborateurEntity collab, OnAsyncEventListener callback) {
        new UpdateCollaborateur(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
                Log.d(TAG, "updateCollaborateur: success");
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
                Log.d(TAG, "updateCollaborateur: failure", e);
            }
        });
    }

    public void deleteCollaborateur(CollaborateurEntity collab, OnAsyncEventListener callback) {
        new DeleteCollaborateur(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
                Log.d(TAG, "deleteCollaborateur: success");
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
                Log.d(TAG, "deleteCollaborateur: failure", e);
            }
        }).execute(collab);

    }
}
