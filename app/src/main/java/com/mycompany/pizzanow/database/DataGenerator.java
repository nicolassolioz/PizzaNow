package com.mycompany.pizzanow.database;

import com.mycompany.pizzanow.database.entity.PizzaEntity;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    public static List<PizzaEntity> generatePizzas() {
        List<PizzaEntity> pizzas = new ArrayList<>();

        PizzaEntity margarita = new PizzaEntity();
        margarita.setIdPizza(1);
        margarita.setNom("Margarita");
        margarita.setDescription("tomate mozzarella");
        margarita.setPrix(10.5);
        margarita.setVegi(true);

        PizzaEntity diavola = new PizzaEntity();
        diavola.setIdPizza(2);
        diavola.setNom("Diavola");
        diavola.setDescription("tomate mozzarella salami piquant poivrons");
        diavola.setPrix(12.5);
        diavola.setVegi(false);

        pizzas.add(margarita);
        pizzas.add(diavola);

        return pizzas;
    }
}
