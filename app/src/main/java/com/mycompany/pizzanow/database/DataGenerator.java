package com.mycompany.pizzanow.database;

import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    public static List<PizzaEntity> generatePizzas() {
        List<PizzaEntity> pizzas = new ArrayList<>();

        PizzaEntity margarita = new PizzaEntity();
        //margarita.setIdPizza(1);
        margarita.setNom("Margarita");
        margarita.setDescription("tomate mozzarella");
        margarita.setPrix(10.5);
        //margarita.setVegi(true);

        PizzaEntity diavola = new PizzaEntity();
        //diavola.setIdPizza(2);
        diavola.setNom("Diavola");
        diavola.setDescription("tomate mozzarella salami piquant poivrons");
        diavola.setPrix(12.5);
        //diavola.setVegi(false);

        PizzaEntity tambola = new PizzaEntity();
        //tambola.setIdPizza(3);
        tambola.setNom("tambola");
        tambola.setDescription("tomate mozzarella jambon");
        tambola.setPrix(12.5);
        //diavola.setVegi(false);

        pizzas.add(margarita);
        pizzas.add(diavola);
        pizzas.add(tambola);

        return pizzas;
    }

    // Collaborators

    public static List<CollaborateurEntity> generateCollaborators(){
        List<CollaborateurEntity> collab = new ArrayList<>();

        CollaborateurEntity  Diego = new CollaborateurEntity();
        Diego.setIdCollab(1);
        Diego.setNomCollab("Pavarotti");
        Diego.setPrenomCollab("Diego");
        Diego.setIdPosCollab(2);

        CollaborateurEntity  Antonio = new CollaborateurEntity();
        Antonio.setIdCollab(2);
        Antonio.setNomCollab("Bocelli");
        Antonio.setPrenomCollab("Antonio");
        Antonio.setIdPosCollab(2);

        CollaborateurEntity  Sara = new CollaborateurEntity();
        Sara.setIdCollab(3);
        Sara.setNomCollab("Bartolli");
        Sara.setPrenomCollab("Sara");
        Sara.setIdPosCollab(2);

        CollaborateurEntity Emmanuella = new CollaborateurEntity();
        Emmanuella.setIdCollab(4);
        Emmanuella.setNomCollab("Domingo");
        Emmanuella.setPrenomCollab("Emmanuella");
        Emmanuella.setIdPosCollab(2);

        CollaborateurEntity Mario = new CollaborateurEntity();
        Mario.setIdCollab(5);
        Mario.setNomCollab("Rossi");
        Mario.setPrenomCollab("Mario");
        Mario.setIdPosCollab(1);

        CollaborateurEntity Suzanna = new CollaborateurEntity();
        Suzanna.setIdCollab(6);
        Suzanna.setNomCollab("Nieto");
        Suzanna.setPrenomCollab("Suzanna");
        Suzanna.setIdPosCollab(1);

        CollaborateurEntity Luciana = new CollaborateurEntity();
        Luciana.setIdCollab(7);
        Luciana.setNomCollab("Giacomo");
        Luciana.setPrenomCollab("Luciana");
        Luciana.setIdPosCollab(1);

        CollaborateurEntity Franco = new CollaborateurEntity();
        Franco.setIdCollab(8);
        Franco.setNomCollab("Ubbiali");
        Franco.setPrenomCollab("Franco");
        Franco.setIdPosCollab(1);

        CollaborateurEntity Laura = new CollaborateurEntity();
        Laura.setIdCollab(9);
        Laura.setNomCollab("Biaggi");
        Laura.setPrenomCollab("Laura");
        Laura.setIdPosCollab(1);

        collab.add(Diego);
        collab.add(Antonio);
        collab.add(Sara);
        collab.add(Emmanuella);
        collab.add(Mario);
        collab.add(Suzanna);
        collab.add(Luciana);
        collab.add(Franco);
        collab.add(Laura);

        return collab;

    }

    //Point of Sales

    public static List<PosEntity> generatePos(){
        List<PosEntity> pos = new ArrayList<>();

        PosEntity posSion = new PosEntity();
        posSion.setIdFiliale(1);
        posSion.setNom("Chez Mario");
        posSion.setAdresse("Rue du Rhône 8");
        posSion.setNPA(1950);
        posSion.setLocalite("Sion");
        posSion.setResponsable(5);
        posSion.setEmail("sion@pizzanow.ch");
        posSion.setPhone("+41 27 327 12 34");
        posSion.setIdMenu(1);

        PosEntity posSierre = new PosEntity();
        posSierre.setIdFiliale(2);
        posSierre.setNom("Chez Diego");
        posSierre.setAdresse("Avenue Général Guisan 13");
        posSierre.setNPA(3950);
        posSierre.setLocalite("Sierre");
        posSierre.setResponsable(1);
        posSierre.setEmail("sierr@pizzanow.ch");
        posSierre.setPhone("+41 27 455 68 68");
        posSierre.setIdMenu(2);

        pos.add(posSion);
        pos.add(posSierre);

        return pos;
    }
}
