package com.mycompany.pizzanow.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.MenuEntity;

import java.util.List;

@Dao
public interface CollaborateurDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CollaborateurEntity> collaborateurs);

    @Insert
    void insert(CollaborateurEntity collaborateurEntity);

    @Update
    void update(CollaborateurEntity collaborateurEntity);

    @Delete
    void delete(CollaborateurEntity collaborateurEntity);

    @Query("SELECT * FROM Collaborateur WHERE idCollab = :id")
    LiveData<CollaborateurEntity> getById(int id);

    @Query("SELECT * FROM Collaborateur WHERE idPosCollab = :idFiliale")
    LiveData<List<CollaborateurEntity>> getCollabPos(Integer idFiliale);

    @Query("SELECT * FROM Collaborateur WHERE nomCollab = :nomCollaborateur")
    LiveData<CollaborateurEntity> getByName(String nomCollaborateur);


}
