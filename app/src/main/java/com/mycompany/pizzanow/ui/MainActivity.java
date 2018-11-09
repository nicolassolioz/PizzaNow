package com.mycompany.pizzanow.ui;

import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mycompany.pizzanow.R;
import com.mycompany.pizzanow.database.Database;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.model.Pizza;
import android.arch.persistence.room.*;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends ToolbarActivity {

    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //initiate database on creation of app

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Database.getInstance(getApplicationContext());
        db.initializeData(db);

        LiveData<List<PizzaEntity>> pizzas = db.pizzaDao().getAll();

        final TextView textViewToChange = (TextView) findViewById(R.id.textTest);


        String pizzaName = pizzas.getValue().get(0).getNom();
        textViewToChange.setText(pizzaName);
    }

    public void onClick(View view){



        Intent intent = new Intent(MainActivity.this, ShowSuccActivity.class);
        startActivity(intent);
    }

   /* public String helloValentin(String hello) {
        //dire coucou à Valentin hihi
        System.out.println(hello);
        //répondre à nico
        return "Hello nico";
        new commit
    }*/
}
