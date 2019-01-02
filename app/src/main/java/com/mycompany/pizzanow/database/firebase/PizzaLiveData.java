package com.mycompany.pizzanow.database.firebase;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import com.mycompany.pizzanow.database.entity.PizzaEntity;

public class PizzaLiveData extends LiveData<PizzaEntity> {

    private static final String TAG = "PizzaLiveData";

    private final DatabaseReference mReference;
    private final PizzaLiveData.MyValueEventListener mListener = new PizzaLiveData.MyValueEventListener();

    public PizzaLiveData(DatabaseReference ref) {
        this.mReference = ref;
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
            PizzaEntity entity = dataSnapshot.getValue(PizzaEntity.class);
            entity.setIdPizza(dataSnapshot.getKey());
            setValue(entity);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + mReference, databaseError.toException());
        }
    }
}
