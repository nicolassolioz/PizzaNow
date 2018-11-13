package com.mycompany.pizzanow.ui;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mycompany.pizzanow.R;
import com.mycompany.pizzanow.adapter.RecyclerAdapter;
import com.mycompany.pizzanow.database.AppDatabase;
import com.mycompany.pizzanow.database.DataGenerator;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.model.Pizza;
import com.mycompany.pizzanow.util.RecyclerViewItemClickListener;
import com.mycompany.pizzanow.viewmodel.POS.PosListViewModel;
import com.mycompany.pizzanow.viewmodel.pizza.PizzaViewModel;

import android.arch.persistence.room.*;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class MainActivity extends ToolbarActivity {

    private AppDatabase db;

    private PizzaViewModel mViewModel;
    private PizzaEntity mPizza;
    private TextView mEtPizzaName;

    private List<PosEntity> mPosEntities;
    private RecyclerAdapter<PosEntity> mAdapter;
    private PosListViewModel posListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //initiate database on creation of app

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.CityRecyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        mPosEntities = new ArrayList<>();
        mAdapter = new RecyclerAdapter<>(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(MainActivity.this, ShowSuccActivity.class);
                intent.putExtra("pos", position);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        });

        PosListViewModel.Factory factory = new PosListViewModel.Factory(getApplication());
        posListViewModel = ViewModelProviders.of(this, factory).get(PosListViewModel.class);
        posListViewModel.getAllPos().observe(this, posEntities -> {
            if(posEntities != null){
                mPosEntities = posEntities;
                mAdapter.setData(mPosEntities);
            }
        });

        recyclerView.setAdapter(mAdapter);


        mEtPizzaName  = (TextView) findViewById(R.id.textTest);
        int idPizza = 3;
        PizzaViewModel.Factory factory2 = new PizzaViewModel.Factory(getApplication(), idPizza);
        mViewModel = ViewModelProviders.of(this, factory2).get(PizzaViewModel.class);
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
            mEtPizzaName.setText(mPizza.getDescription());
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
