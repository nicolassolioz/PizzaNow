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

import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.database.repository.PizzaRepository;
import com.mycompany.pizzanow.database.repository.PosRepository;
import com.mycompany.pizzanow.util.OnAsyncEventListener;
import com.mycompany.pizzanow.viewmodel.pizza.PizzaViewModel;

public class PosViewModel extends AndroidViewModel {

    private static final String TAG = "PizzaViewModel";

    private PosRepository posRepository;

    private final MediatorLiveData<PosEntity> mObservablePos;

    public PosViewModel(@NonNull Application application, final String posId, PosRepository repository) {
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

        private final String mPosId;

        private final PosRepository mRepository;

        public Factory(@NonNull Application application, String posId) {
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

    public LiveData<PosEntity> getPos() {
        return mObservablePos;
    }

    public void createPos(PosEntity newPos) {
        ((BaseApp) getApplication()).getPosRepository().insert(newPos);

    }

    public void updatePos(PosEntity pos) {
        ((BaseApp) getApplication()).getPosRepository().update(pos);
    }

    public void deletePos(PosEntity pos) {

        ((BaseApp) getApplication()).getPosRepository().delete(pos);

    }

}
