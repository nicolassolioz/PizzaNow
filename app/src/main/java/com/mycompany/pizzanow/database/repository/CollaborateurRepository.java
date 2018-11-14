package com.mycompany.pizzanow.database.repository;

import android.arch.lifecycle.LiveData;

import com.mycompany.pizzanow.database.AppDatabase;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;

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

    public LiveData<CollaborateurEntity> getCollaborateur(final int idCollaborateur) {
        return mDatabase.collaborateurDao().getById(idCollaborateur);
    }

    public LiveData<List<CollaborateurEntity>> getAllCollaborateurs(){return mDatabase.collaborateurDao().getAll();}

    public LiveData<CollaborateurEntity> getCollaborateurNom(final String nomCollaborateur) {
        return mDatabase.collaborateurDao().getByName(nomCollaborateur);
    }

    public LiveData<List<CollaborateurEntity>> getCollabPos(final int pos) {
        return mDatabase.collaborateurDao().getCollabPos(pos);
    }

   /* public LiveData<List<ClientWithAccounts>> getOtherClientsWithAccounts(final String owner) {
        return mDatabase.pizzaDao().getOtherClientsWithAccounts(owner);
    }*/

    public void insert(final CollaborateurEntity collab) {
        mDatabase.collaborateurDao().insert(collab);
    }

    public void update(final CollaborateurEntity collab) {
        mDatabase.collaborateurDao().update(collab);
    }

    public void delete(final CollaborateurEntity collab) {
        mDatabase.collaborateurDao().delete(collab);
    }
}
