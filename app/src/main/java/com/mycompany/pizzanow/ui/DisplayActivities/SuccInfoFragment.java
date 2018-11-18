package com.mycompany.pizzanow.ui.DisplayActivities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mycompany.pizzanow.R;
import com.mycompany.pizzanow.adapter.PizzaRecyclerAdapter;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.ui.DisplayActivities.ShowSuccActivity;
import com.mycompany.pizzanow.util.RecyclerViewItemClickListener;
import com.mycompany.pizzanow.viewmodel.pizza.PizzaListViewModel;

import java.util.ArrayList;
import java.util.List;


public class SuccInfoFragment extends Fragment {

    private static final String TAG = "PosInfoPizzasFragment";

    //data pos
    private PosEntity posEntity ;

    //Data pizza
    private List<PizzaEntity> mPizzas;
    private PizzaRecyclerAdapter<PizzaEntity> mAdapter;
    private PizzaListViewModel mViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_succ_info, container,false);

        posEntity = ((ShowSuccActivity)this.getActivity()).getPosEntity();

        //Accueil
        TextView textView = (TextView) rootView.findViewById(R.id.tvWelcomeSuccInfo);
        try {
            textView.append(posEntity.getNom());
        }catch (Exception e){
            Log.d(TAG , "Chargement trop rapide du fragment");
        }


        //gestion de la liste des pizzas
        // 1. get a reference to recyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.PizzasRecyclerView);

        // use a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        mPizzas = new ArrayList<>();

        mAdapter = new PizzaRecyclerAdapter<>(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position:" + position);
                Log.d(TAG, "clicked on: " + mPizzas.get(position).getNom());
            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        });

        PizzaListViewModel.Factory factory = new PizzaListViewModel.Factory(getActivity().getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(PizzaListViewModel.class);
        mViewModel.getAllPizzas().observe(this, pizzaEntities -> {
            if(pizzaEntities != null){
                mPizzas = pizzaEntities;
                mAdapter.setData(mPizzas);
            }
        });

        recyclerView.setAdapter(mAdapter);

        return rootView;

    }


}
