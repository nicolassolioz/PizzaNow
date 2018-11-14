package com.mycompany.pizzanow.ui;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.mycompany.pizzanow.R;
import com.mycompany.pizzanow.adapter.RecyclerAdapter;
import com.mycompany.pizzanow.database.AppDatabase;
import com.mycompany.pizzanow.database.DataGenerator;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.model.Pizza;
import com.mycompany.pizzanow.util.RecyclerViewItemClickListener;
import com.mycompany.pizzanow.viewmodel.POS.PosListViewModel;
import com.mycompany.pizzanow.viewmodel.pizza.CollaborateurViewModel;
import com.mycompany.pizzanow.viewmodel.pizza.PizzaViewModel;

import android.arch.persistence.room.*;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class MainActivity extends ToolbarActivity {

    private AppDatabase db;

    private static final String TAG = "MainActivity";

    private CollaborateurViewModel mViewModel;
    private CollaborateurEntity mCollaborateur;
    private TextView mEtPizzaName;

    private List<PosEntity> mPosEntities;
    private RecyclerAdapter<PosEntity> mAdapter;
    private PosListViewModel mListViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //initiate database on creation of app

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtPizzaName  = (TextView) findViewById(R.id.textTest);

        /*affichage des succursales : init*/
        RecyclerView recyclerView = findViewById(R.id.CityRecyclerView);
        //layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //separateurs
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        //définition de la liste et des comportements
        mPosEntities = new ArrayList<>();
        mAdapter = new RecyclerAdapter<>(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(MainActivity.this, ShowSuccActivity.class);
                intent.putExtra("posID", mPosEntities.get(position).getIdFiliale());
                startActivity(intent);
            }


            @Override
            public void onItemLongClick(View v, int position) {
                Log.d(TAG, "longClicked position:" + position);
                Log.d(TAG, "longClicked on: " + mPosEntities.get(position).getNom());

            }
        });
        // ouverture du factory
        PosListViewModel.Factory factoryPos = new PosListViewModel.Factory(getApplication());
        mListViewmodel = ViewModelProviders.of(this, factoryPos).get(PosListViewModel.class);
        mListViewmodel.getAllPos().observe(this,posEntities -> {
            if(posEntities!=null){
                mPosEntities = posEntities;
                mAdapter.setData(mPosEntities);
            }
        });

        recyclerView.setAdapter(mAdapter);

        /*affichage des succursales : fin*/

        String nomCollaborateur = "Bocelli";



        CollaborateurViewModel.Factory factory = new CollaborateurViewModel.Factory(getApplication(), 2);
        mViewModel = ViewModelProviders.of(this, factory).get(CollaborateurViewModel.class);

        mViewModel.getCollaborateur().observe(this, collaborateurEntity -> {
            if (collaborateurEntity != null) {
                mCollaborateur = collaborateurEntity;
                updateContent();
            }
            else
            {
                mEtPizzaName.setText("hey hey guess u suk");
            }
        });

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
