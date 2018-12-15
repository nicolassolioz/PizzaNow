package com.mycompany.pizzanow.database.firebase;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mycompany.pizzanow.database.entity.PizzaEntity;

import java.util.ArrayList;
import java.util.List;

public class PizzaListLiveData extends LiveData<List<PizzaEntity>> {

    private static final String TAG = "AccountListLiveData";

    private final DatabaseReference mReference;
    private final MyValueEventListener mListener = new MyValueEventListener();

    public PizzaListLiveData(DatabaseReference ref) {
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

    private List<PizzaEntity> toAccounts(DataSnapshot snapshot) {
        List<PizzaEntity> accounts = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            PizzaEntity entity = childSnapshot.getValue(PizzaEntity.class);
            entity.setIdPizza(childSnapshot.getKey());
            accounts.add(entity);
        }
        return accounts;
    }
}
