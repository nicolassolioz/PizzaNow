package com.mycompany.pizzanow.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mycompany.pizzanow.BaseApp;
import com.mycompany.pizzanow.R;
import com.mycompany.pizzanow.adapter.ListAdapter;
import com.mycompany.pizzanow.adapter.RecyclerAdapter;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.database.repository.CollaborateurRepository;
import com.mycompany.pizzanow.model.Collaborateur;
import com.mycompany.pizzanow.viewmodel.Collaborateur.CollaborateurListViewModel;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class SuccDetailsFragment extends Fragment {

    private static final String TAG = "PosDetailPizzasFragment";

    //dataPos
    private PosEntity posEntity ;

    //data Collaborateurs
    private List<CollaborateurEntity> mCollabs;
    private CollaborateurListViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_succ_details, container, false);
        posEntity = ((ShowSuccActivity)this.getActivity()).getPosEntity();


        //Appel
        ImageButton buttonPhone = (ImageButton) rootView.findViewById(R.id.buttonPhone);
        buttonPhone.setOnClickListener(view -> attemptCall(posEntity.getPhone()));

        //Mail

        //Nom des collaborateurs

        mCollabs = new ArrayList<>();
        Integer posEntityId = posEntity.getIdFiliale();
        CollaborateurListViewModel.Factory factory = new CollaborateurListViewModel.Factory(getActivity().getApplication(),1);
        mViewModel = ViewModelProviders.of(this, factory).get(CollaborateurListViewModel.class);
        mViewModel.getCollabPos().observe(this, collaborateurEntities -> {
            if(collaborateurEntities !=null){
                mCollabs = collaborateurEntities;
                 TextView textViewNames = (TextView) rootView.findViewById(R.id.tvSuccDetailNames);
                String namesSeq ="";
                for (Collaborateur c : mCollabs) {
                    if(mCollabs.indexOf(c) == mCollabs.size()-1)
                        namesSeq = namesSeq + c.getPrenomCollab()+"! ";
                    else
                        namesSeq = namesSeq + c.getPrenomCollab()+", ";
                }

                textViewNames.setText(namesSeq);
            }

        });



        /*
        mCollabs = new ArrayList<>();
        Integer posEntityId = posEntity.getIdFiliale();

        CollaborateurRepository rep = ((BaseApp) getActivity().getApplication()).getCollaborateurRepository();
        mViewModel = new CollaborateurListViewModel(getActivity().getApplication(),posEntityId,rep);

        mViewModel.getCollabPos().observe(this,collabEntities -> {
            if(collabEntities!=null){

                mCollabs = collabEntities;

                TextView textViewNames = (TextView) rootView.findViewById(R.id.tvSuccDetailNames);
                String namesSeq = String.valueOf(mCollabs.size());
                for (Collaborateur c : mCollabs
                        ) {
                    namesSeq = namesSeq + c.getPrenomCollab()+", ";

                }

                textViewNames.setText(namesSeq);


            }
        });

        */
        //adresse
        TextView textViewAdress = (TextView) rootView.findViewById(R.id.tvSuccDetailsAdress);
        textViewAdress.setText(posEntity.getAdresse()+" | "+ posEntity.getNPA()+ " " +posEntity.getLocalite());

        return rootView;


    }


    //test de l'appel
    private void attemptCall(String phone) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse(phone));
        try {
            startActivity(callIntent);
            Log.i("Finished making a call", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "Call "+phone+" failed, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}
