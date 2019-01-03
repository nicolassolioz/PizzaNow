package com.mycompany.pizzanow.database.entity;


import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import com.mycompany.pizzanow.model.Collaborateur;

public class CollaborateurEntity implements Collaborateur {

    private String idCollab;
    private String nomCollab;
    private String prenomCollab;
    private String idPosCollab;

    public CollaborateurEntity(){}

    public CollaborateurEntity(Collaborateur collab){
        idCollab=collab.getIdCollab();
        nomCollab=collab.getNomCollab();
        prenomCollab=collab.getPrenomCollab();
        idPosCollab=collab.getIdPosCollab();
    }

    @Exclude
    @Override
    public String getIdCollab() {
        return idCollab;
    }

    public void setIdCollab(String id){this.idCollab=id;}

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
    public String getIdPosCollab() {
        return idPosCollab;
    }

    public void setIdPosCollab(String idPos){this.idPosCollab=idPos;}

    @Override
    public boolean equals(Object obj){
        if (obj== null) return false;
        if (obj==this) return true;
        if (!(obj instanceof CollaborateurEntity)) return false;
        CollaborateurEntity o = (CollaborateurEntity) obj;
        return o.getIdCollab() == this.getIdCollab();
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("nomCollab", nomCollab);
        result.put("prenomCollab", prenomCollab );
        result.put("idPosCollab", idPosCollab);

        return result;
    }

    public int compareTo(Object o) {
        return 0;
    }
}
