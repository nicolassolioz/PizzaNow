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

import java.util.List;

public class CollaborateurListViewModel extends AndroidViewModel {

    private static final String TAG = "CollabListViewModel";

    private CollaborateurRepository collaborateurRepository;

    private final MediatorLiveData<List<CollaborateurEntity>> mObservableCollaborateursOfPos;
    //private final MediatorLiveData<List<CollaborateurEntity>> mObservableAllCollaborateurs;

    public CollaborateurListViewModel(@NonNull Application application,
                                      final int posId, CollaborateurRepository repository) {
        super(application);
        collaborateurRepository = repository;

        mObservableCollaborateursOfPos = new MediatorLiveData<>();
        //mObservableAllCollaborateurs = new MediatorLiveData<>();

        mObservableCollaborateursOfPos.setValue(null);
        //mObservableAllCollaborateurs.setValue(null);

        LiveData<List<CollaborateurEntity>> collabs = collaborateurRepository.getCollabPos(posId);
        //LiveData<List<CollaborateurEntity>> allCollabs = collaborateurRepository.getAllCollaborateurs();

        mObservableCollaborateursOfPos.addSource(collabs, mObservableCollaborateursOfPos::setValue);
        //mObservableAllCollaborateurs.addSource(allCollabs, mObservableAllCollaborateurs::setValue);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mPosId;

        private final CollaborateurRepository mRepository;

        public Factory(@NonNull Application application, int PosId) {
            mApplication = application;
            mPosId=PosId;
            mRepository = ((BaseApp) application).getCollaborateurRepository();

        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new CollaborateurListViewModel(mApplication,mPosId, mRepository);
        }
    }

    /**
     * Expose the LiveData Collaborateur query so the UI can observe it.
     */
    public LiveData<List<CollaborateurEntity>> getCollabPos() {
        return mObservableCollaborateursOfPos;
    }

    /*
    public LiveData<List<CollaborateurEntity>> getAllCollabs() {
        return mObservableAllCollaborateurs;
    }
    */
}


