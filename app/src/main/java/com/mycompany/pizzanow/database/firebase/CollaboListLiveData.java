package com.mycompany.pizzanow.database.firebase;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.PizzaEntity;

import java.util.ArrayList;
import java.util.List;

public class CollaboListLiveData extends LiveData<List<CollaborateurEntity>>  {

    private static final String TAG = "CollaboListLiveData";

    private final DatabaseReference mReference;
    private final CollaboListLiveData.MyValueEventListener mListener = new CollaboListLiveData.MyValueEventListener();

    public CollaboListLiveData(DatabaseReference ref) {
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
            setValue(toCollabs(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + mReference, databaseError.toException());
        }
    }

    private List<CollaborateurEntity> toCollabs(DataSnapshot snapshot) {
        List<CollaborateurEntity> collabs = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            CollaborateurEntity entity = childSnapshot.getValue(CollaborateurEntity.class);
            entity.setIdCollab(childSnapshot.getKey());
            collabs.add(entity);
        }
        return collabs;
    }
}
