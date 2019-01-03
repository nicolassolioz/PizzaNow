package com.mycompany.pizzanow.database.repository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mycompany.pizzanow.database.AppDatabase;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.database.firebase.CollaboListLiveData;
import com.mycompany.pizzanow.database.firebase.CollaboLiveData;
import com.mycompany.pizzanow.database.firebase.PosLiveData;

import java.util.List;

public class CollaborateurRepository {

    private static final String TAG = "CollaboRepository";
    private static CollaborateurRepository sInstance;

    private final AppDatabase mDatabase;

    private CollaborateurRepository(final AppDatabase database) {
        mDatabase = database;
    }

    public static CollaborateurRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            if (sInstance == null) {
                sInstance = new CollaborateurRepository(database);
            }
        }
        return sInstance;
    }

    public LiveData<CollaborateurEntity> getById(final String collaboId) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("collabos")
                .child(collaboId);
        return new CollaboLiveData(reference);
    }

    public LiveData<List<CollaborateurEntity>> getAllCollaborateurs() {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("collabos");
        return new CollaboListLiveData(reference);
    }


    public LiveData<CollaborateurEntity> getCollaborateurNom(String nomCollaborateur) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("collabos")
                // CODE BORROWED FROM https://stackoverflow.com/questions/38618953/how-to-do-a-simple-search-in-string-in-firebase-database
                .orderByChild("nomCollab")
                .startAt(nomCollaborateur)
                .endAt(nomCollaborateur+"\uf8ff")
                // END OF BORROWED CODE
                .getRef();

        return new CollaboLiveData(reference);
    }


    public LiveData<List<CollaborateurEntity>> getCollabPos(String pos) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("collabos")
                .orderByChild("idPosCollab")
                .startAt(pos)
                .endAt(pos+"\uf8ff")
                .getRef();
        return new CollaboListLiveData(reference);
    }


    public void insert(final CollaborateurEntity collabo) {

        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("collabos");
        String key = reference.push().getKey();
        FirebaseDatabase.getInstance()
                .getReference("collabos")
                .child(key)
                .setValue(collabo, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Insert failure!", databaseError.toException());
                    } else {
                        Log.i(TAG, "Insert successful!");
                    }
                });

    }

    public void update(final CollaborateurEntity collabo) {
        String id = collabo.getIdCollab();
        FirebaseDatabase.getInstance()
                .getReference("collabos")
                .child(id)
                .updateChildren(collabo.toMap(), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Update failure!", databaseError.toException());
                    } else {
                        Log.i(TAG, "Update successful!");
                    }
                });
    }

    public void delete(final CollaborateurEntity collabo) {
        String id = collabo.getIdCollab();
        FirebaseDatabase.getInstance()
                .getReference("collabos")
                .child(id)
                .removeValue((databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Delete failure!", databaseError.toException());
                    } else {
                        Log.d(TAG, "Delete successful!");
                    }
                });
    }
}
