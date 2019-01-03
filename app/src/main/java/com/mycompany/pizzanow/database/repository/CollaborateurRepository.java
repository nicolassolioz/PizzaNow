package com.mycompany.pizzanow.database.repository;

import android.arch.lifecycle.LiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mycompany.pizzanow.database.AppDatabase;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.database.firebase.PosLiveData;

import java.util.List;

public class CollaborateurRepository {

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

    /*public LiveData<CollaborateurEntity> getById(int idCollaborateur) {
        return mDatabase.collaborateurDao().getById(idCollaborateur);
    }

    public LiveData<List<CollaborateurEntity>> getAllCollaborateurs(){return mDatabase.collaborateurDao().getAll();}

    public LiveData<CollaborateurEntity> getCollaborateurNom( String nomCollaborateur) {
        return mDatabase.collaborateurDao().getByName(nomCollaborateur);
    }
    */

    public LiveData<PosEntity> getCollabPos(String pos) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("pos")
                .child(pos);
        return new PosLiveData(reference);
    }

   /* public LiveData<List<ClientWithAccounts>> getOtherClientsWithAccounts(final String owner) {
        return mDatabase.pizzaDao().getOtherClientsWithAccounts(owner);
    }

    public void insert(final CollaborateurEntity collab) {
        mDatabase.collaborateurDao().insert(collab);
    }

    public void update(final CollaborateurEntity collab) {
        mDatabase.collaborateurDao().update(collab);
    }

    public void delete(final CollaborateurEntity collab) {
        mDatabase.collaborateurDao().delete(collab);
    }*/
}
