package com.mycompany.pizzanow.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.mycompany.pizzanow.model.MenuPizza;

@Entity(tableName = "MenuPizza",
        foreignKeys = {
        @ForeignKey(
                entity = PizzaEntity.class,
                parentColumns = "idPizza",
                childColumns = "idPizza",
                onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
                entity = MenuEntity.class,
                parentColumns = "idMenu",
                childColumns = "idMenu",
                onDelete =  ForeignKey.CASCADE
        )},
        indices = {
                @Index(
                        value = {"idMenuPizza"}
                )}
)
public class MenuPizzaEntity implements MenuPizza, Comparable {

    @PrimaryKey(autoGenerate = true)
    private int idMenuPizza;

    @ColumnInfo(name = "idMenu")
    private int idMenu;

    @ColumnInfo(name = "idPizza")
    private int idPizza;


    public MenuPizzaEntity() {}

    public MenuPizzaEntity(MenuPizzaEntity menuPizzaEntity) {
        idMenuPizza = menuPizzaEntity.getIdMenuPizza();
        idMenu = menuPizzaEntity.getIdMenu();
        idPizza = menuPizzaEntity.getIdPizza();
    }

    public MenuPizzaEntity(int idMenuPizza, int idMenu, int idPizza) {
        this.idMenuPizza = idMenuPizza;
        this.idMenu = idMenu;
        this.idPizza = idPizza;
    }

    @Override
    public int getIdMenuPizza() {
        return this.idMenuPizza;
    }

    @Override
    public int getIdPizza() {
        return this.idPizza;
    }

    @Override
    public int getIdMenu() {
        return this.idMenu;
    }

    public void setIdMenuPizza(int idMenuPizza) {
        this.idMenuPizza = idMenuPizza;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public void setIdPizza(int idPizza) {
        this.idPizza = idPizza;
    }
    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(!(obj instanceof PosEntity)) return false;
        MenuPizzaEntity o = (MenuPizzaEntity) obj;
        return o.getIdMenuPizza() == this.getIdMenuPizza();
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return toString().compareTo(o.toString());
    }
}
