package com.mycompany.pizzanow.database.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.mycompany.pizzanow.model.Pos;

import java.io.Serializable;

@Entity(tableName = "pos",

        indices = {
                @Index(
                        value = {"Responsable"}
                )}
)
public class PosEntity implements Pos, Comparable, Serializable/*Parcelable*/ {

    @PrimaryKey(autoGenerate = true)
    private Integer IdFiliale;

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

    /*
    protected PosEntity(Parcel in) {
        if (in.readByte() == 0) {
            IdFiliale = null;
        } else {
            IdFiliale = in.readInt();
        }
        Nom = in.readString();
        Adresse = in.readString();
        NPA = in.readInt();
        Localite = in.readString();
        Responsable = in.readInt();
        Email = in.readString();
        Phone = in.readString();
        IdMenu = in.readInt();
    }


    public static final Creator<PosEntity> CREATOR = new Creator<PosEntity>() {
        @Override
        public PosEntity createFromParcel(Parcel in) {
            return new PosEntity(in);
        }

        @Override
        public PosEntity[] newArray(int size) {
            return new PosEntity[size];
        }
    };

    */

    @NonNull
    @Override
    public Integer getIdFiliale() {
        return IdFiliale;
    }

    public void setIdFiliale(@NonNull Integer idFiliale) {this.IdFiliale = idFiliale;}

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

    /*
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getIdFiliale());
        dest.writeString(Nom);
        dest.writeString(getAdresse());
        dest.writeInt(getNPA());
        dest.writeString(getLocalite());
        dest.writeInt(getResponsable());
        dest.writeString(getEmail());
        dest.writeString(getPhone());
        dest.writeInt(getIdMenu());

    }
    */
}
