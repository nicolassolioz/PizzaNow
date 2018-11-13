package com.mycompany.pizzanow.ui;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mycompany.pizzanow.R;
import com.mycompany.pizzanow.database.AppDatabase;
import com.mycompany.pizzanow.database.DataGenerator;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.model.Pizza;
import com.mycompany.pizzanow.viewmodel.pizza.PizzaViewModel;

import android.arch.persistence.room.*;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Observer;

public class MainActivity extends ToolbarActivity {

    private AppDatabase db;

    private PizzaViewModel mViewModel;

    private PizzaEntity mPizza;

    private TextView mEtPizzaName;
    //private TextView mEtPizzaDescription;
    //private TextView mEtPizzaPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //initiate database on creation of app

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtPizzaName  = (TextView) findViewById(R.id.textTest);

        int idPizza = 3;


        PizzaViewModel.Factory factory = new PizzaViewModel.Factory(getApplication(), idPizza);
        mViewModel = ViewModelProviders.of(this, factory).get(PizzaViewModel.class);

       mViewModel.getPizza().observe(this, pizzaEntity -> {
            if (pizzaEntity != null) {
                mPizza = pizzaEntity;
                updateContent();
            }
        });

    }

    public void onClick(View view){
        Intent intent = new Intent(MainActivity.this, ShowSuccActivity.class);
        startActivity(intent);
    }

    private void updateContent() {
        if (mPizza != null) {
            mEtPizzaName.setText(mPizza.getNom());
            //mEtPizzaDescription.setText(mPizza.getDescription());
            //mEtPizzaPrice.setText(Double.toString(mPizza.getPrix()));
        }
    }
   /* public String helloValentin(String hello) {
        //dire coucou à Valentin hihi
        System.out.println(hello);
        //répondre à nico
        return "Hello nico";
        new commit
    }*/
}
