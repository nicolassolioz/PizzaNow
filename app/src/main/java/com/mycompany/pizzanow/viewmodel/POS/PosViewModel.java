package com.mycompany.pizzanow.viewmodel.POS;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.mycompany.pizzanow.BaseApp;
import com.mycompany.pizzanow.database.async.pizza.UpdatePizza;

import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.database.repository.PizzaRepository;
import com.mycompany.pizzanow.database.repository.PosRepository;
import com.mycompany.pizzanow.util.OnAsyncEventListener;
import com.mycompany.pizzanow.viewmodel.pizza.PizzaViewModel;

public class PosViewModel extends AndroidViewModel {

    private static final String TAG = "PizzaViewModel";

    private PosRepository posRepository;

    private  final MediatorLiveData<PosEntity> mObservablePos;

    public PosViewModel(@NonNull Application application, final int posId, PosRepository repository){
        super(application);

        posRepository = repository;
        mObservablePos = new MediatorLiveData<>();
        mObservablePos.setValue(null);

        LiveData<PosEntity> pos = posRepository.getPos(posId);

        mObservablePos.addSource(pos, mObservablePos::setValue);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mPosId;

        private final PosRepository mRepository;

        public Factory(@NonNull Application application, int posId) {
            mApplication = application;
            mPosId = posId;
            mRepository = ((BaseApp) application).getPosRepository();

        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new PosViewModel(mApplication, mPosId, mRepository);
        }
    }

    public LiveData<PosEntity> getPos() {return mObservablePos;}


}
