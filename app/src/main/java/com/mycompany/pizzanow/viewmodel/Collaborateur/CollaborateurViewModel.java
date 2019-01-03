package com.mycompany.pizzanow.viewmodel.Collaborateur;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.mycompany.pizzanow.BaseApp;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.repository.CollaborateurRepository;
import com.mycompany.pizzanow.util.OnAsyncEventListener;

public class CollaborateurViewModel extends AndroidViewModel {

    private static final String TAG = "CollaborateurViewModel";

    private CollaborateurRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<CollaborateurEntity> mObservableCollaborateur;

    public CollaborateurViewModel(@NonNull Application application,
                          final String idCollaborateur, CollaborateurRepository collaborateurRepository) {
        super(application);

        mRepository = collaborateurRepository;

        mObservableCollaborateur = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableCollaborateur.setValue(null);

        LiveData<CollaborateurEntity> collaborateur = mRepository.getById(idCollaborateur);

        // observe the changes of the pizza entity from the database and forward them
        mObservableCollaborateur.addSource(collaborateur, mObservableCollaborateur::setValue);
    }

    public void createCollab(CollaborateurEntity newCollab) {
        ((BaseApp) getApplication()).getCollaborateurRepository().insert(newCollab);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final String mIdCollaborateur;

        private final CollaborateurRepository mRepository;

        public Factory(@NonNull Application application, String idCollaborateur) {
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

    public void updateCollaborateur(CollaborateurEntity collab) {

        ((BaseApp) getApplication()).getCollaborateurRepository().update(collab);
    }

    public void deleteCollaborateur(CollaborateurEntity collab) {

        ((BaseApp) getApplication()).getCollaborateurRepository().delete(collab);

    }
}
