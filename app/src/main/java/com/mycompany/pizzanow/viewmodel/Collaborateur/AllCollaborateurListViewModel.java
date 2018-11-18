package com.mycompany.pizzanow.viewmodel.Collaborateur;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.mycompany.pizzanow.BaseApp;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.repository.CollaborateurRepository;

import java.util.List;

public class AllCollaborateurListViewModel extends AndroidViewModel {

    private static final String TAG = "CollabListViewModel";

    private CollaborateurRepository collaborateurRepository;

    //private final MediatorLiveData<List<CollaborateurEntity>> mObservableCollaborateursOfPos;
    private final MediatorLiveData<List<CollaborateurEntity>> mObservableAllCollaborateurs;

    public AllCollaborateurListViewModel(@NonNull Application application, CollaborateurRepository repository) {
        super(application);
        collaborateurRepository = repository;

        //mObservableCollaborateursOfPos = new MediatorLiveData<>();
        mObservableAllCollaborateurs = new MediatorLiveData<>();

        //mObservableCollaborateursOfPos.setValue(null);
        mObservableAllCollaborateurs.setValue(null);

        //LiveData<List<CollaborateurEntity>> collabs = collaborateurRepository.getCollabPos(posId);
        LiveData<List<CollaborateurEntity>> allCollabs = collaborateurRepository.getAllCollaborateurs();

        //mObservableCollaborateursOfPos.addSource(collabs, mObservableCollaborateursOfPos::setValue);
        mObservableAllCollaborateurs.addSource(allCollabs, mObservableAllCollaborateurs::setValue);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final CollaborateurRepository mRepository;

        public Factory(@NonNull Application application) {
            mApplication = application;
            mRepository = ((BaseApp) application).getCollaborateurRepository();

        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new AllCollaborateurListViewModel(mApplication, mRepository);
        }
    }

    /**
     * Expose the LiveData Collaborateur query so the UI can observe it.
     */
    /*
    public LiveData<List<CollaborateurEntity>> getCollabPos() {
        return mObservableCollaborateursOfPos;
    }
    */

    public LiveData<List<CollaborateurEntity>> getAllCollabs() {
        return mObservableAllCollaborateurs;
    }

}


