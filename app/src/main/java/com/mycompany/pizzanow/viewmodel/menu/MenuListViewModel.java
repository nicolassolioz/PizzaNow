package com.mycompany.pizzanow.viewmodel.menu;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.mycompany.pizzanow.BaseApp;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.MenuEntity;
import com.mycompany.pizzanow.database.repository.CollaborateurRepository;
import com.mycompany.pizzanow.database.repository.MenuRepository;
import com.mycompany.pizzanow.viewmodel.Collaborateur.CollaborateurListViewModel;

import java.util.List;

public class MenuListViewModel extends AndroidViewModel {

    private static final String TAG = "CollabListViewModel";

    private MenuRepository menuRepository;

    private final MediatorLiveData<List<MenuEntity>> mObservableMenus;
    //private final MediatorLiveData<List<CollaborateurEntity>> mObservableAllCollaborateurs;

    public MenuListViewModel(@NonNull Application application, MenuRepository repository) {
        super(application);
        menuRepository = repository;

        mObservableMenus = new MediatorLiveData<>();
        //mObservableAllCollaborateurs = new MediatorLiveData<>();

        mObservableMenus.setValue(null);
        //mObservableAllCollaborateurs.setValue(null);

        //LiveData<List<MenuEntity>> menus = menuRepository.getAllMenu();
        //LiveData<List<CollaborateurEntity>> allCollabs = collaborateurRepository.getAllCollaborateurs();

        //mObservableMenus.addSource(menus, mObservableMenus::setValue);
        //mObservableAllCollaborateurs.addSource(allCollabs, mObservableAllCollaborateurs::setValue);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final MenuRepository mRepository;

        public Factory(@NonNull Application application) {
            mApplication = application;
            mRepository = ((BaseApp) application).getMenuRepository();

        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new MenuListViewModel(mApplication, mRepository);
        }
    }

    /**
     * Expose the LiveData Collaborateur query so the UI can observe it.
     */
    public LiveData<List<MenuEntity>> getAllMenus() {
        return mObservableMenus;
    }
}
