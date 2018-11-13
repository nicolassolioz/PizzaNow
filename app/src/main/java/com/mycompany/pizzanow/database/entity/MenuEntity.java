package com.mycompany.pizzanow.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.mycompany.pizzanow.model.Menu;

@Entity(tableName = "Menu")
public class MenuEntity implements Menu, Comparable {

    @PrimaryKey(autoGenerate = true)
    private int idMenu;

    @ColumnInfo(name = "Nom")
    private String nom;


    public MenuEntity() {}

    public MenuEntity(Menu menu) {
        idMenu = menu.getIdMenu();
        nom = menu.getNomMenu();
    }

    public MenuEntity(@NonNull int idMenu, String nom) {
        this.idMenu = idMenu;
        this.nom = nom;
    }


    @Override
    public int getIdMenu() {
        return this.idMenu;
    }

    @Override
    public String getNomMenu() {
        return this.nom;
    }

    public void setIdMenu(@NonNull int idMenu) {
        this.idMenu = idMenu;
    }

    public void setNomMenu(@NonNull String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(!(obj instanceof PosEntity)) return false;
        MenuEntity o = (MenuEntity) obj;
        return o.getIdMenu() == this.getIdMenu();
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return toString().compareTo(o.toString());
    }
}
