package com.mycompany.pizzanow.database.repository;

import android.arch.lifecycle.LiveData;

import com.mycompany.pizzanow.database.AppDatabase;
import com.mycompany.pizzanow.database.entity.PosEntity;

import java.util.List;

public class PosRepository {

    private static PosRepository sInstance;

    private final AppDatabase mDatabase;

    private PosRepository (final AppDatabase db){mDatabase=db;}

    public static PosRepository getInstance(final AppDatabase db){
        if(sInstance == null){
            synchronized (PosRepository.class){
                if(sInstance == null){
                    sInstance = new PosRepository(db);
                }
            }

        }
        return sInstance;
    }

    public LiveData<PosEntity> getPos(final int idPos){
        return mDatabase.posDao().getById(idPos);
    }


    public LiveData<List<PosEntity>> getAllPos(){
        return mDatabase.posDao().getAll();
    }

    public void insert(final PosEntity posEntity){
        mDatabase.posDao().insert(posEntity);
    }
    public void update(final PosEntity posEntity){
        mDatabase.posDao().update(posEntity);
    }
    public void delete(final PosEntity posEntity){
        mDatabase.posDao().delete(posEntity);
    }

    // sans liveData
    public PosEntity getPosDirect(int idPos) {return mDatabase.posDao().getOnePos(idPos);}
}
