package com.mycompany.pizzanow.ui;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mycompany.pizzanow.R;
import com.mycompany.pizzanow.adapter.RecyclerAdapter;
import com.mycompany.pizzanow.database.AppDatabase;
import com.mycompany.pizzanow.database.DataGenerator;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.model.Pizza;
import com.mycompany.pizzanow.viewmodel.pizza.CollaborateurViewModel;
import com.mycompany.pizzanow.viewmodel.pizza.PizzaViewModel;

import android.arch.persistence.room.*;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Observer;

public class MainActivity extends ToolbarActivity {

    private AppDatabase db;

    private CollaborateurViewModel mViewModel;
    private PizzaViewModel mViewModelPizza;
    private CollaborateurEntity mCollaborateur;
    private TextView mEtPizzaName;

    private List<PosEntity> mPosEntities;
    private RecyclerAdapter<PosEntity> mAdapter;
    //private TextView mEtPizzaDescription;
    //private TextView mEtPizzaPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //initiate database on creation of app

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtPizzaName  = (TextView) findViewById(R.id.textTest);

        String nomCollaborateur = "Bocelli";


        PizzaViewModel.Factory factory = new PizzaViewModel.Factory(getApplication(), 2);
        mViewModelPizza = ViewModelProviders.of(this, factory).get(PizzaViewModel.class);

        mViewModelPizza.getPizza().observe(this,pizzaEntity -> {
            if (pizzaEntity != null) {

                mEtPizzaName.setText(pizzaEntity.getNom());
            }
        });

        CollaborateurViewModel.Factory factoryC = new CollaborateurViewModel.Factory(getApplication(), 2);
        mViewModel = ViewModelProviders.of(this, factoryC).get(CollaborateurViewModel.class);

        mViewModel.getCollaborateur().observe(this, collaborateurEntity -> {
            if (collaborateurEntity != null) {
                mCollaborateur = collaborateurEntity;
                updateContent();
            }
            else
            {
                mEtPizzaName.setText("hey hey guess u suk");
            }});

    }

    public void onClick(View view){
        Intent intent = new Intent(MainActivity.this, ShowSuccActivity.class);
        startActivity(intent);
    }

    private void updateContent() {
        if (mCollaborateur != null) {
            mEtPizzaName.setText(mCollaborateur.getNomCollab() + " " + mCollaborateur.getPrenomCollab());
            //mEtPizzaDescription.setText(mPizza.getDescription());
            //mEtPizzaPrice.setText(Double.toString(mPizza.getPrix()));
        }
        else
        {
            mEtPizzaName.setText("value null sorry why");
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
