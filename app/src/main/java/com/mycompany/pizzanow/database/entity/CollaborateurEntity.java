package com.mycompany.pizzanow.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mycompany.pizzanow.model.Collaborateur;

@Entity(tableName = "Collaborateur",

    indices ={
        @Index(
                value = {"idCollab"}
        )
    })

public class CollaborateurEntity implements Collaborateur, Comparable {

    @PrimaryKey(autoGenerate = true)
    private int idCollab;
    private String nomCollab;
    private String prenomCollab;
    @Nullable
    private Integer idPosCollab;

    public CollaborateurEntity(){}

    public CollaborateurEntity(Collaborateur collab){
        idCollab=collab.getIdCollab();
        nomCollab=collab.getNomCollab();
        prenomCollab=collab.getPrenomCollab();
        idPosCollab=collab.getIdPosCollab();
    }

    @Override
    public int getIdCollab() {
        return idCollab;
    }

    public void setIdCollab(int id){this.idCollab=id;}

    @Override
    public String getNomCollab() {
        return nomCollab;
    }

    public void setNomCollab(String nom){this.nomCollab=nom;}

    @Override
    public String getPrenomCollab() {
        return prenomCollab;
    }

    public void setPrenomCollab(String prenom){this.prenomCollab=prenom;}

    @Override
    public Integer getIdPosCollab() {
        return idPosCollab;
    }

    public void setIdPosCollab(int idPos){this.idPosCollab=idPos;}

    @Override
    public boolean equals(Object obj){
        if (obj== null) return false;
        if (obj==this) return true;
        if (!(obj instanceof CollaborateurEntity)) return false;
        CollaborateurEntity o = (CollaborateurEntity) obj;
        return o.getIdCollab() == this.getIdCollab();
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }
}
