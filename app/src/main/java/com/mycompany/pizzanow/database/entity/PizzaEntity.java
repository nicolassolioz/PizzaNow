package com.mycompany.pizzanow.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.mycompany.pizzanow.model.Pizza;

@Entity(tableName = "Pizza", primaryKeys = {"idPizza"})
public class PizzaEntity implements Pizza, Comparable{

    @PrimaryKey(autoGenerate = true)
    private int idPizza;

    @ColumnInfo(name = "Nom")
    private String nom;

    @ColumnInfo(name = "Description")
    private String description;

    @ColumnInfo(name = "Prix")
    private double prix;

    @ColumnInfo(name = "Vegi")
    private Boolean vegi;

    public PizzaEntity(){}

    public PizzaEntity(Pizza pizza){
        idPizza=pizza.getIdPizza();
        nom=pizza.getNom();
        description=pizza.getDescription();
        prix=pizza.getPrix();
        vegi=pizza.getVegi();

    }

    public PizzaEntity(@NonNull int idPizza, String nom, String description, double prix,
                     boolean vegi) {
        this.idPizza = idPizza;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.vegi = vegi;
    }


    @NonNull
    @Override
    public int getIdPizza() {        return idPizza;    }

    public void setIdPizza(@NonNull int id) {
        this.idPizza = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setVegi(boolean vegi) {
        this.vegi = vegi;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getPrix() {
        return prix;
    }

    @Override
    public boolean getVegi() {
        return vegi;
    }



    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(!(obj instanceof PosEntity)) return false;
        PizzaEntity o = (PizzaEntity) obj;
        return o.getIdPizza() == this.getIdPizza();
    }

    @Override
    public String toString(){return nom + " : " + description;}

    @Override
    public int compareTo(@NonNull Object o) {
        return toString().compareTo(o.toString());
    }
}

