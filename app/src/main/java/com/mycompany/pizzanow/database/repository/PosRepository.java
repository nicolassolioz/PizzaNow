package com.mycompany.pizzanow.database.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.mycompany.pizzanow.database.AppDatabase;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.database.firebase.PosListLiveData;
import com.mycompany.pizzanow.database.firebase.PosLiveData;

import java.util.List;

public class PosRepository {

    private static final String TAG = "PosRepository";

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

    public LiveData<PosEntity> getPos(final String posId) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("pos")
                .child(posId);
        return new PosLiveData(reference);
    }


    /*
    public LiveData<PosEntity> getPosBasedOnName(String namePos) {

        /*
        CODE BORROWED FROM
        https://stackoverflow.com/questions/38618953/how-to-do-a-simple-search-in-string-in-firebase-database
        */
        // START OF BORROWED CODE
        //create query

        /*
        Query query = FirebaseDatabase.getInstance()
                .getReference("pos")
                .orderByChild("Nom")
                .startAt(namePos)
                .endAt(namePos+"\uf8ff");


        //
        // END OF BORROWED CODE

        DatabaseReference reference = FirebaseDatabase.getInstance()
            .getReference("pos")
                .child(reference.);


        // END OF BORROWED CODE


        return new PosLiveData(reference);

    }
    */


    public LiveData<List<PosEntity>> getAllPos() {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("pos");
        return new PosListLiveData(reference);
    }

    public void insert(final PosEntity pos) {

        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("pos");
        String key = reference.push().getKey();
        FirebaseDatabase.getInstance()
                .getReference("pos")
                .child(key)
                .setValue(pos, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Insert failure!", databaseError.toException());
                    } else {
                        Log.i(TAG, "Insert successful!");
                    }
                });

    }

    public void update(final PosEntity pos) {
        String id = pos.getIdPos();
        FirebaseDatabase.getInstance()
                .getReference("pos")
                .child(id)
                .updateChildren(pos.toMap(), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Update failure!", databaseError.toException());
                    } else {
                        Log.i(TAG, "Update successful!");
                    }
                });
    }

    public void delete(final PosEntity pos) {
        String id = pos.getIdPos();
        FirebaseDatabase.getInstance()
                .getReference("pos")
                .child(id)
                .removeValue((databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Delete failure!", databaseError.toException());
                    } else {
                        Log.d(TAG, "Delete successful!");
                    }
                });
    }

    public void transaction(final PosEntity sender, final PosEntity recipient){
        String senderId = sender.getIdPos();
        String recipientId = recipient.getIdPos();
        final DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();
        rootReference.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                rootReference
                        .child("pos")
                        .child(senderId)
                        .updateChildren(sender.toMap());

                rootReference
                        .child("pos")
                        .child(recipientId)
                        .updateChildren(recipient.toMap());

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                if (databaseError != null) {
                    Log.d(TAG, "Transaction failure!", databaseError.toException());
                } else {
                    Log.d(TAG, "Transaction successful!");
                }
            }
        });
    }


}
