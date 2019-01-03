package com.mycompany.pizzanow.database.firebase;


import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;

import java.util.ArrayList;
import java.util.List;

public class PosListLiveData extends LiveData<List<PosEntity>>  {

    private static final String TAG = "AccountListLiveData";

    private final DatabaseReference mReference;
    private final PosListLiveData.MyValueEventListener mListener = new PosListLiveData.MyValueEventListener();

    public PosListLiveData(DatabaseReference ref) {
        mReference = ref;
    }

    @Override
    protected void onActive() {
        Log.d(TAG, "onActive");
        mReference.addValueEventListener(mListener);
    }

    @Override
    protected void onInactive() {
        Log.d(TAG, "onInactive");
    }

    private class MyValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(toAccounts(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + mReference, databaseError.toException());
        }
    }

    private List<PosEntity> toAccounts(DataSnapshot snapshot) {
        List<PosEntity> accounts = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            PosEntity entity = childSnapshot.getValue(PosEntity.class);
            entity.setIdPos(childSnapshot.getKey());
            accounts.add(entity);
        }
        return accounts;
    }

}
