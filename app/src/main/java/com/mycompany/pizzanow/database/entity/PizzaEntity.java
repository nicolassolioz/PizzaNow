package com.mycompany.pizzanow.database.entity;


import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import com.mycompany.pizzanow.model.Pizza;

/*
@Entity(tableName = "Pizza")
public class PizzaEntity implements Pizza, Comparable{

    @PrimaryKey(autoGenerate = true)
    private int idPizza;

    @ColumnInfo(name = "Nom")
    private String nom;

    @ColumnInfo(name = "Description")
    private String description;

    @ColumnInfo(name = "Prix")
    private double prix;

    //@ColumnInfo(name = "Vegi")
    //private Boolean vegi;
*/
public class PizzaEntity implements Pizza {

    private String idPizza;
    private String nom;
    private String description;
    private double prix;

    public PizzaEntity(){}

    public PizzaEntity(Pizza pizza){
        idPizza=pizza.getIdPizza();
        nom=pizza.getNom();
        description=pizza.getDescription();
        prix=pizza.getPrix();
        //vegi=pizza.getVegi();

    }

    /*

    public PizzaEntity(@NonNull int idPizza, String nom, String description, double prix,
                     boolean vegi) {
        this.idPizza = idPizza;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        //this.vegi = vegi;
    }


    @NonNull
    */
    @Exclude
    @Override
    public String getIdPizza() {return idPizza;    }

    public void setIdPizza(String id) {
        this.idPizza = id;
    }

    @Override
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public double getPrix() {
        return prix;
    }

    /*public void setVegi(boolean vegi) {
        this.vegi = vegi;
    }*/


    /*@Override
    public boolean getVegi() {
        return vegi;
    }*/



    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(!(obj instanceof PizzaEntity)) return false;
        PizzaEntity o = (PizzaEntity) obj;
        return o.getIdPizza() == this.getIdPizza();
    }

    @Override
    public String toString(){return nom + " : " + description;}

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("nom", nom);
        result.put("description", description);

        return result;
    }

    /*
    @Override
    public int compareTo(@NonNull Object o) {
        return toString().compareTo(o.toString());
    }
    */
}

