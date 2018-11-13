package com.mycompany.pizzanow.viewmodel.POS;

import android.app.Application;
import android.app.MediaRouteActionProvider;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;

import com.mycompany.pizzanow.BaseApp;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.database.repository.PosRepository;


import java.util.List;

public class PosListViewModel extends AndroidViewModel {

    private static final String TAG = "PosListViewModel";

    private PosRepository mRepository;

    private final MediatorLiveData<List<PosEntity>> mObservablePos;

    public PosListViewModel(@NonNull Application application, PosRepository posRepository) {
        super(application);

        mRepository = posRepository;

        mObservablePos = new MediatorLiveData<>();

        mObservablePos.setValue(null);

        LiveData<List<PosEntity>> posEntities = mRepository.getAllPos();

        mObservablePos.addSource(posEntities, mObservablePos::setValue);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory{
        @NonNull
        private final Application mApplication;

        private final PosRepository mRepository;

        public Factory(@NonNull Application application){
            mApplication = application;
            mRepository=((BaseApp) application).getPosRepository();
        }

        public <T extends ViewModel> T create(Class<T> modelClass){
            return (T) new PosListViewModel(mApplication, mRepository);
        }

    }

    public LiveData<List<PosEntity>> getAllPos() {return mObservablePos;}
}
