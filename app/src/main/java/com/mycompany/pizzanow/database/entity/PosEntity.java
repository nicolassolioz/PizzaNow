package com.mycompany.pizzanow.database.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.mycompany.pizzanow.model.Pos;

@Entity(tableName = "pos",
        foreignKeys =
        @ForeignKey(
                entity = PosEntity.class,
                parentColumns = "idCollab",
                childColumns = "Responsable",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {
                @Index(
                        value = {"Responsable"}
                )}
)
public class PosEntity implements Pos, Comparable{

    @PrimaryKey(autoGenerate = true)
    private int IdFiliale;

    @ColumnInfo(name = "Nom")
    private String Nom;

    @ColumnInfo(name = "Adresse")
    private String Adresse;

    @ColumnInfo(name = "NPA")
    private int NPA;

    @ColumnInfo(name = "Localite")
    private String Localite;

    @ColumnInfo(name = "Responsable")
    private int Responsable;

    @ColumnInfo(name = "Email")
    private String Email;

    @ColumnInfo(name = "Phone")
    private String Phone;

    @ColumnInfo(name = "Menu")
    private int IdMenu;

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
        IdMenu=getIdMenu();
    }

    public PosEntity(@NonNull int idFiliale, String nom, String adress, int npa,
                     String localite, int responsable, String email, String phone,
                     int idMenu){
        this.IdFiliale=idFiliale;
        this.Nom=nom;
        this.Adresse=adress;
        this.NPA=npa;
        this.Localite=localite;
        this.Responsable=responsable;
        this.Email=email;
        this.Phone=phone;
        this.IdMenu=idMenu;
    }

    @NonNull
    @Override
    public int getIdFiliale() {
        return IdFiliale;
    }

    public void setIdFiliale(@NonNull int idFiliale) {this.IdFiliale = idFiliale;}

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

    @Override
    public String getLocalite() {
        return Localite;
    }

    public void setLocalite(String localite){this.Localite=localite;}

    @Override
    public int getResponsable() {
        return Responsable;
    }

    public void setResponsable(int responsable){ this.Responsable=responsable;}

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
    public int getIdMenu() { return IdMenu; }

    public void setIdMenu(int idMenu){ this.IdMenu=idMenu;}

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(!(obj instanceof PosEntity)) return false;
        PosEntity o = (PosEntity) obj;
        return o.getIdFiliale() == this.getIdFiliale();
    }

    @Override
    public String toString(){return Nom + " " + Localite;}

    @Override
    public int compareTo(@NonNull Object o) {
        return toString().compareTo(o.toString());
    }
}
