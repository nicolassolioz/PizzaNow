package com.mycompany.pizzanow.database;

import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.MenuEntity;
import com.mycompany.pizzanow.database.entity.MenuPizzaEntity;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {


    // Code that fills the database with initial data

    /*
    public static List<PizzaEntity> generatePizzas() {
        List<PizzaEntity> pizzas = new ArrayList<>();

        PizzaEntity margarita = new PizzaEntity();
        //margarita.setIdPizza(1);
        margarita.setNom("Margherita");
        margarita.setDescription("Tomate, mozzarella");
        margarita.setPrix(10.5);
        //margarita.setVegi(true);

        PizzaEntity diavola = new PizzaEntity();
        //diavola.setIdPizza(2);
        diavola.setNom("Diavola");
        diavola.setDescription("Tomate, Mozzarella, Salami piquant, Poivrons");
        diavola.setPrix(12.5);
        //diavola.setVegi(false);

        PizzaEntity tambola = new PizzaEntity();
        //tambola.setIdPizza(3);
        tambola.setNom("Tambola");
        tambola.setDescription("Tomate, Mozzarella, Jambon");
        tambola.setPrix(12.5);
        //diavola.setVegi(false);

        PizzaEntity Vesuvio = new PizzaEntity();
        //tambola.setIdPizza(3);
        Vesuvio.setNom("Vesuvio");
        Vesuvio.setDescription("Tomate, Mozzarella, Ail, Grana Padano, Roquette, Tomates cerises");
        Vesuvio.setPrix(20.0);
        //diavola.setVegi(false);

        PizzaEntity Krakatoa = new PizzaEntity();
        //tambola.setIdPizza(3);
        Krakatoa.setNom("Krakatoa");
        Krakatoa.setDescription("Tomate, Mozzarella, Chorizo");
        Krakatoa.setPrix(16.5);
        //diavola.setVegi(false);

        PizzaEntity Sakurajima = new PizzaEntity();
        //tambola.setIdPizza(3);
        Sakurajima.setNom("Sakurajima");
        Sakurajima.setDescription("tomate mozzarella jambon");
        Sakurajima.setPrix(24.0);
        //diavola.setVegi(false);

        pizzas.add(margarita);
        pizzas.add(diavola);
        pizzas.add(tambola);
        pizzas.add(Vesuvio);
        pizzas.add(Krakatoa);
        pizzas.add(Sakurajima);

        return pizzas;
    }
    */

    // Collaborators

    /*
    public static List<CollaborateurEntity> generateCollaborateurs(){
        List<CollaborateurEntity> collab = new ArrayList<>();

        CollaborateurEntity  Diego = new CollaborateurEntity();
        Diego.setNomCollab("Pavarotti");
        Diego.setPrenomCollab("Diego");
        Diego.setIdPosCollab(1);

        CollaborateurEntity  Antonio = new CollaborateurEntity();
        Antonio.setNomCollab("Bocelli");
        Antonio.setPrenomCollab("Antonio");
        Antonio.setIdPosCollab(2);

        CollaborateurEntity  Sara = new CollaborateurEntity();
        Sara.setNomCollab("Bartolli");
        Sara.setPrenomCollab("Sara");
        Sara.setIdPosCollab(1);

        CollaborateurEntity Emmanuella = new CollaborateurEntity();
        Emmanuella.setNomCollab("Domingo");
        Emmanuella.setPrenomCollab("Emmanuella");
        Emmanuella.setIdPosCollab(1);

        CollaborateurEntity Mario = new CollaborateurEntity();
        Mario.setNomCollab("Rossi");
        Mario.setPrenomCollab("Mario");
        Mario.setIdPosCollab(1);

        CollaborateurEntity Suzanna = new CollaborateurEntity();
        Suzanna.setNomCollab("Nieto");
        Suzanna.setPrenomCollab("Suzanna");
        Suzanna.setIdPosCollab(1);

        CollaborateurEntity Luciana = new CollaborateurEntity();
        Luciana.setNomCollab("Giacomo");
        Luciana.setPrenomCollab("Luciana");
        Luciana.setIdPosCollab(4);

        CollaborateurEntity Franco = new CollaborateurEntity();
        Franco.setNomCollab("Ubbiali");
        Franco.setPrenomCollab("Franco");
        Franco.setIdPosCollab(4);

        CollaborateurEntity Laura = new CollaborateurEntity();
        Laura.setNomCollab("Biaggi");
        Laura.setPrenomCollab("Laura");
        Laura.setIdPosCollab(4);

        CollaborateurEntity Luigi = new CollaborateurEntity();
        Luigi.setNomCollab("Hendrix");
        Luigi.setPrenomCollab("Luigi");
        Luigi.setIdPosCollab(2);

        CollaborateurEntity Maria = new CollaborateurEntity();
        Maria.setNomCollab("Cobain");
        Maria.setPrenomCollab("Maria");
        Maria.setIdPosCollab(2);

        CollaborateurEntity Rose = new CollaborateurEntity();
        Rose.setNomCollab("Winehouse");
        Rose.setPrenomCollab("Rose");
        Rose.setIdPosCollab(2);

        CollaborateurEntity Steven = new CollaborateurEntity();
        Steven.setNomCollab("Joplin");
        Steven.setPrenomCollab("Steven");
        Steven.setIdPosCollab(2);

        CollaborateurEntity Giovanni = new CollaborateurEntity();
        Giovanni.setNomCollab("Zuckerberg");
        Giovanni.setPrenomCollab("Giovanni");
        Giovanni.setIdPosCollab(3);

        CollaborateurEntity HansJurg = new CollaborateurEntity();
        HansJurg.setNomCollab("Gates");
        HansJurg.setPrenomCollab("Hans-Jurg");
        HansJurg.setIdPosCollab(3);

        CollaborateurEntity Bertha = new CollaborateurEntity();
        Bertha.setNomCollab("Wozniak");
        Bertha.setPrenomCollab("Bertha");
        Bertha.setIdPosCollab(3);


        collab.add(Diego);
        collab.add(Antonio);
        collab.add(Sara);
        collab.add(Emmanuella);
        collab.add(Mario);
        collab.add(Suzanna);
        collab.add(Luciana);
        collab.add(Franco);
        collab.add(Laura);
        collab.add(Luigi);
        collab.add(Maria);
        collab.add(Rose);
        collab.add(Steven);
        collab.add(Giovanni);
        collab.add(HansJurg);
        collab.add(Bertha);

        return collab;

    }
    */

    //Point of Sales

    /*
    public static List<CollaborateurEntity> addPosToCollab(List<CollaborateurEntity> collab) {
        collab.get(0).setIdCollab(1);
        collab.get(1).setIdCollab(1);
        collab.get(2).setIdCollab(1);
        collab.get(3).setIdCollab(1);
        collab.get(4).setIdCollab(2);
        collab.get(5).setIdCollab(2);
        collab.get(6).setIdCollab(2);
        collab.get(7).setIdCollab(2);
        collab.get(8).setIdCollab(2);
        collab.get(9).setIdCollab(3);
        collab.get(10).setIdCollab(3);
        collab.get(11).setIdCollab(3);
        collab.get(12).setIdCollab(3);
        collab.get(13).setIdCollab(4);
        collab.get(14).setIdCollab(4);
        collab.get(15).setIdCollab(4);

        return collab;
    }

    public static List<PosEntity> generatePos(){
        List<PosEntity> pos = new ArrayList<>();

        PosEntity posSion = new PosEntity();
        posSion.setNom("Chez Mario");
        posSion.setAdresse("Rue du Rhône 8");
        posSion.setNPA(1950);
        posSion.setLocalite("Sion");
        posSion.setResponsable(5);
        posSion.setEmail("sion@pizzanow.ch");
        posSion.setPhone("+41 27 327 12 34");
        //posSion.setIdMenu(1);

        PosEntity posSierre = new PosEntity();
        posSierre.setNom("Chez Diego");
        posSierre.setAdresse("Avenue Général Guisan 13");
        posSierre.setNPA(3950);
        posSierre.setLocalite("Sierre");
        posSierre.setResponsable(1);
        posSierre.setEmail("sierre@pizzanow.ch");
        posSierre.setPhone("+41 27 455 68 68");
        //posSierre.setIdMenu(2);

        PosEntity posMartigny = new PosEntity();
        posMartigny.setNom("Chez Luigi");
        posMartigny.setAdresse("Rue des Finettes 23");
        posMartigny.setNPA(1920);
        posMartigny.setLocalite("Martigny");
        posMartigny.setResponsable(10);
        posMartigny.setEmail("martigny@pizzanow.ch");
        posMartigny.setPhone("+41 722 30 50");
        //posMartigny.setIdMenu(2);

        PosEntity posViege = new PosEntity();
        posViege.setNom("Chez Giovanni");
        posViege.setAdresse("Napoleonstrasse 2");
        posViege.setNPA(3930);
        posViege.setLocalite("Visp");
        posViege.setResponsable(14);
        posViege.setEmail("visp@pizzanow.ch");
        posViege.setPhone("+41 27 946 70 80");
        //posViege.setIdMenu(3);

        pos.add(posSion);
        pos.add(posSierre);
        pos.add(posMartigny);
        pos.add(posViege);

        return pos;
    }
    */
}

/*    public static List<MenuEntity> generateMenus() {
        List<MenuEntity> menus = new ArrayList<>();

        MenuEntity menuEtendu = new MenuEntity();
        menuEtendu.setNomMenu("Etendu");

        MenuEntity menuNormal = new MenuEntity();
        menuNormal.setNomMenu("Normal");

        MenuEntity menuReduit = new MenuEntity();
        menuReduit.setNomMenu("Réduit");

        menus.add(menuEtendu);
        menus.add(menuNormal);
        menus.add(menuReduit);

        return menus;
    }


    public static List<MenuPizzaEntity> generateMenuPizzas() {
        List<MenuPizzaEntity> menuPizzas = new ArrayList<>();

        MenuPizzaEntity menuPizzaEntity1 = new MenuPizzaEntity();
        menuPizzaEntity1.setIdMenu(1);
        menuPizzaEntity1.setIdPizza(1);

        MenuPizzaEntity menuPizzaEntity2 = new MenuPizzaEntity();
        menuPizzaEntity2.setIdMenu(1);
        menuPizzaEntity2.setIdPizza(2);

        MenuPizzaEntity menuPizzaEntity3 = new MenuPizzaEntity();
        menuPizzaEntity3.setIdMenu(1);
        menuPizzaEntity3.setIdPizza(3);

        MenuPizzaEntity menuPizzaEntity4 = new MenuPizzaEntity();
        menuPizzaEntity4.setIdMenu(1);
        menuPizzaEntity4.setIdPizza(4);

        MenuPizzaEntity menuPizzaEntity5 = new MenuPizzaEntity();
        menuPizzaEntity5.setIdMenu(1);
        menuPizzaEntity5.setIdPizza(5);

        MenuPizzaEntity menuPizzaEntity6 = new MenuPizzaEntity();
        menuPizzaEntity6.setIdMenu(1);
        menuPizzaEntity6.setIdPizza(6);

        MenuPizzaEntity menuPizzaEntity7 = new MenuPizzaEntity();
        menuPizzaEntity7.setIdMenu(2);
        menuPizzaEntity7.setIdPizza(1);

        MenuPizzaEntity menuPizzaEntity8 = new MenuPizzaEntity();
        menuPizzaEntity8.setIdMenu(2);
        menuPizzaEntity8.setIdPizza(2);

        MenuPizzaEntity menuPizzaEntity9 = new MenuPizzaEntity();
        menuPizzaEntity9.setIdMenu(2);
        menuPizzaEntity9.setIdPizza(3);

        MenuPizzaEntity menuPizzaEntity10 = new MenuPizzaEntity();
        menuPizzaEntity10.setIdMenu(2);
        menuPizzaEntity10.setIdPizza(4);

        MenuPizzaEntity menuPizzaEntity11 = new MenuPizzaEntity();
        menuPizzaEntity11.setIdMenu(2);
        menuPizzaEntity11.setIdPizza(6);

        MenuPizzaEntity menuPizzaEntity12 = new MenuPizzaEntity();
        menuPizzaEntity12.setIdMenu(3);
        menuPizzaEntity12.setIdPizza(1);

        MenuPizzaEntity menuPizzaEntity13 = new MenuPizzaEntity();
        menuPizzaEntity13.setIdMenu(3);
        menuPizzaEntity13.setIdPizza(2);

        MenuPizzaEntity menuPizzaEntity14 = new MenuPizzaEntity();
        menuPizzaEntity14.setIdMenu(3);
        menuPizzaEntity14.setIdPizza(3);

        MenuPizzaEntity menuPizzaEntity15 = new MenuPizzaEntity();
        menuPizzaEntity15.setIdMenu(4);
        menuPizzaEntity15.setIdPizza(4);

        return menuPizzas;
    }

    */
