package com.mycompany.pizzanow.ui.DisplayActivities;

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
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.ui.Toolbar.ToolbarActivity;
import com.mycompany.pizzanow.util.RecyclerViewItemClickListener;
import com.mycompany.pizzanow.viewmodel.Collaborateur.CollaborateurListViewModel;
import com.mycompany.pizzanow.viewmodel.POS.PosListViewModel;
import com.mycompany.pizzanow.viewmodel.Collaborateur.CollaborateurViewModel;

import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ToolbarActivity {

    private AppDatabase db;

    private static final String TAG = "MainActivity";

    private CollaborateurViewModel mViewModel;
    private CollaborateurEntity mCollaborateur;
    private TextView mEtPizzaName;

    private List<PosEntity> mPosEntities;
    private List<CollaborateurEntity> mCollaborateurEntities;
    private RecyclerAdapter<PosEntity> mAdapter;
    private RecyclerAdapter<CollaborateurEntity> mAdapterCollabo;
    private PosListViewModel mListViewmodel;
    private CollaborateurListViewModel mListViewmodelCollabo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //initiate database on creation of app


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
                PosEntity posEntity = mPosEntities.get(position);
                intent.putExtra("serializable_extra",(Serializable) posEntity);
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

        mListViewmodel = ViewModelProviders.of(this,factoryPos).get(PosListViewModel.class);
        mListViewmodel.getAllPos().observe(this,posEntities -> {
            if(posEntities!=null){
                mPosEntities = posEntities;
                mAdapter.setData(mPosEntities);
            }
        });
        recyclerView.setAdapter(mAdapter);


        /*affichage des succursales : fin*/




        /*
        //recyclerView.setAdapter(mAdapterCollabo);

        int posIdInt = 1;
        Integer posId = posIdInt;
        CollaborateurViewModel.Factory factory = new CollaborateurViewModel.Factory(getApplication(), 7);
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

        updateContent();

        */

    }

    public void onClick(View view){
        Intent intent = new Intent(MainActivity.this, ShowSuccActivity.class);
        startActivity(intent);
    }

    private void updateContent() {
        Integer x = 0;
        int y = 0;
    }

}

/*if(mCollaborateurEntities.equals(null)) {
            mEtPizzaName.setText("is null");
        }
        else
        {
            mEtPizzaName.setText("is not null");
        }
        /*
        if(x.equals(y)) {
            mEtPizzaName.setText("they are equal");
        }
        else
        {
            mEtPizzaName.setText("they are not equal");
        }

        mEtPizzaName.setText(mCollaborateurEntities.get(1).getNomCollab() + " " + mCollaborateurEntities.get(1).getPrenomCollab());

        /*if (mCollaborateur != null) {
            mEtPizzaName.setText(mCollaborateur.getNomCollab() + " " + mCollaborateur.getPrenomCollab());
            //mEtPizzaDescription.setText(mPizza.getDescription());
            //mEtPizzaPrice.setText(Double.toString(mPizza.getPrix()));
        }
        else
        {
            mEtPizzaName.setText("value null sorry why");
        }*/

   /* public String helloValentin(String hello) {
        //dire coucou à Valentin hihi
        System.out.println(hello);
        //répondre à nico
        return "Hello nico";
        new commit
    }*/
