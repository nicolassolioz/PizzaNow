package com.mycompany.pizzanow.database.entity;


import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.mycompany.pizzanow.model.Pos;

public class PosEntity implements Pos, Serializable {

    private String IdPos;
    private String Nom;
    private String Adresse;
    private int NPA;
    private String Localite;
    private String Responsable;
    private String Email;
    private String Phone;
    private String IdFiliale;

    public PosEntity(){}

    public PosEntity(Pos pos){
        IdFiliale=getIdFiliale();
        Nom=getNom();
        Adresse=getAdresse();
        NPA=getNPA();
        Localite=getLocalite();
        Responsable=getResponsable();
        Email=getEmail();
        Phone=getPhone();
       // IdMenu=getIdMenu();
    }

    @Exclude
    @Override
    public String getIdFiliale() {
        return IdFiliale;
    }

    public String getIdPos() {return IdPos;}

    public void setIdFiliale(String idFiliale) {this.IdFiliale = idFiliale;}

    @Override
    public String getNom() {
        return Nom;
    }

    public void setNom(String nom){this.Nom = nom;}

    @Override
    public String getAdresse() { return Adresse; }

    public void setAdresse(String adress){this.Adresse=adress;}

    @Override
    public int getNPA() { return NPA; }

    public void setNPA(int npa) {this.NPA=npa;}

    public void setIdPos(String idPos) {this.IdPos=idPos;}

    @Override
    public String getLocalite() {
        return Localite;
    }

    public void setLocalite(String localite){this.Localite=localite;}

    @Override
    public String getResponsable() {
        return Responsable;
    }

    public void setResponsable(String responsable){ this.Responsable=responsable;}

    @Override
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {this.Email=email;}

    @Override
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone){this.Phone=phone;}

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(!(obj instanceof PosEntity)) return false;
        PosEntity o = (PosEntity) obj;
        return o.getIdFiliale() == this.getIdFiliale();
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("nom", Nom);
        result.put("adresse", Adresse);
        result.put("npa", NPA);
        result.put("localite", Localite);
        result.put("Responsable", Responsable);
        result.put("email", Email);
        result.put("phone", Phone);

        return result;
    }

    @Override
    public String toString(){return Nom + " : " + Adresse;}
}
