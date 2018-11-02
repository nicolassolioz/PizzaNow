package com.mycompany.pizzanow.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import com.mycompany.pizzanow.model.Pos;

@Entity(tableName = "filiale", primaryKeys = {"idFiliale"})
public class PosEntity implements Pos, Comparable{

    @NonNull
    private int idFiliale;

    @ColumnInfo(name = "Nom")
    private String nom;

    @Override
    public int getidFiliale() {
        return 0;
    }

    @Override
    public String getNom() {
        return null;
    }

    @Override
    public String getAdresse() {
        return null;
    }

    @Override
    public int getNPA() {
        return 0;
    }

    @Override
    public String getLocalite() {
        return null;
    }

    @Override
    public int getResponsable() {
        return 0;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getPhone() {
        return null;
    }

    @Override
    public int getMenu() {
        return 0;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }
}
