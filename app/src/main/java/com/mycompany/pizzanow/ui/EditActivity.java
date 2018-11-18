package com.mycompany.pizzanow.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mycompany.pizzanow.R;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.MenuEntity;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.viewmodel.Collaborateur.AllCollaborateurListViewModel;
import com.mycompany.pizzanow.viewmodel.Collaborateur.CollaborateurListViewModel;
import com.mycompany.pizzanow.viewmodel.Collaborateur.CollaborateurViewModel;
import com.mycompany.pizzanow.viewmodel.POS.PosListViewModel;
import com.mycompany.pizzanow.viewmodel.menu.MenuListViewModel;
import com.mycompany.pizzanow.viewmodel.pizza.PizzaListViewModel;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends ToolbarActivity {

    private static final String TAG = "Edit Activity";

    private List<PosEntity> mPosEntities;
    private List<MenuEntity> mMenuEntities;
    private List<CollaborateurEntity> mCollaborateurEntities;
    private List<PizzaEntity> mPizzaEntities;

    private PosListViewModel mPosListViewmodel;
    private MenuListViewModel mMenuListViewmodel;
    private AllCollaborateurListViewModel mCollaborateurViewModel;
    private PizzaListViewModel mPizzaListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mPosEntities = new ArrayList<>();
        mMenuEntities = new ArrayList<>();
        mPizzaEntities = new ArrayList<>();
        mCollaborateurEntities = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // (1) Fill list of pos with data
        PosListViewModel.Factory factoryPos = new PosListViewModel.Factory(getApplication());

        mPosListViewmodel = ViewModelProviders.of(this,factoryPos).get(PosListViewModel.class);
        mPosListViewmodel.getAllPos().observe(this,posEntities -> {
            if(posEntities!=null){
                mPosEntities = posEntities;
                fillDDLPosNames();
            }
        });

        // (2) Fill list of menus with data
        MenuListViewModel.Factory factoryMenu = new MenuListViewModel.Factory(getApplication());

        mMenuListViewmodel = ViewModelProviders.of(this,factoryMenu).get(MenuListViewModel.class);
        mMenuListViewmodel.getAllMenus().observe(this,menuEntities -> {
            if(menuEntities!=null){
                mMenuEntities = menuEntities;
                fillDDLMenuNames();
            }
        });

        // (3) Fill list of employees with data
        AllCollaborateurListViewModel.Factory factoryCollab= new AllCollaborateurListViewModel.Factory(getApplication());

        mCollaborateurViewModel = ViewModelProviders.of(this,factoryCollab).get(AllCollaborateurListViewModel.class);
        mCollaborateurViewModel.getAllCollabs().observe(this,collaborateurEntities ->  {
            if(collaborateurEntities!=null){
                mCollaborateurEntities = collaborateurEntities;
                fillDDLCollabNames();
            }
        });

        /*
        ListView allPizzas = findViewById(R.id.listAllPizzas);
        ListView posPizzas = findViewById(R.id.listMenuPizzas);

        String[] items = new String[]{"Margarita", "Diavola"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        allPizzas.setChoiceMode(2);
        posPizzas.setChoiceMode(2);

        allPizzas.setAdapter(adapter);
        posPizzas.setAdapter(adapter);
        */


    }

    public void getAllPos() {

        PosListViewModel.Factory factoryPos = new PosListViewModel.Factory(getApplication());

        mPosListViewmodel = ViewModelProviders.of(this,factoryPos).get(PosListViewModel.class);
        mPosListViewmodel.getAllPos().observe(this,posEntities -> {
            if(posEntities!=null){
                mPosEntities = posEntities;
            }
        });

    }


    public void fillDDLPosNames() {
        Spinner dropdown = findViewById(R.id.listPos);
        String[] items = getNamesPos();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PosEntity select = mPosEntities.get(position);
                //name
                EditText name = findViewById(R.id.editPosName);
                name.setText(select.getNom());
                //address
                EditText address = findViewById(R.id.editPosAddress);
                address.setText(select.getAdresse());
                //NPA
                EditText npa = findViewById(R.id.editPosNPA);
                npa.setText(Integer.toString(select.getNPA()));
                //localite
                EditText localite = findViewById(R.id.editPosLocalite);
                localite.setText(select.getNom());
                //resp
                try{
                    fillDDLCollabPosNames();
                    Spinner resp = findViewById(R.id.listPosCollaborateur);
                    resp.getItemAtPosition(select.getResponsable());
                }catch(Exception e){
                    Log.d(TAG, "no resp !?"+e);
                }
                //email
                EditText email = findViewById(R.id.editPosEmail);
                email.setText(select.getEmail());
                //phone
                EditText phone = findViewById(R.id.editPosPhone);
                phone.setText(select.getPhone());
                //menu
                try{
                    fillDDLPosMenuNames();
                    Spinner resp = findViewById(R.id.listPosMenu);
                    resp.getItemAtPosition(select.getIdMenu());
                }catch(Exception e){
                    Log.d(TAG, "no menu !?"+e);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public String[] getNamesPos() {

        if(mPosEntities.equals(null)) {
            String[] empty = new String[]{"no available data"};
            return empty;
        }
        String[] posNames = new String[mPosEntities.size()];
        for(int i = 0; i<mPosEntities.size(); i++) {
            posNames[i] = mPosEntities.get(i).getNom();
        }

        return posNames;
    }

    public void fillDDLMenuNames() {
        Spinner dropdown = findViewById(R.id.listMenus);
        String[] items = getNamesMenus();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MenuEntity select = mMenuEntities.get(position);
                //name
                EditText name = findViewById(R.id.editPosName);
                name.setText(select.getNomMenu());
                //resp
                /*try{
                    fillDDLCollabPosNames();
                    Spinner resp = findViewById(R.id.listPosCollaborateur);
                    resp.getItemAtPosition(select.getResponsable());
                }catch(Exception e){
                    Log.d(TAG, "no resp !?"+e);
                }
                //email
                EditText email = findViewById(R.id.editPosEmail);
                email.setText(select.getEmail());
                //phone
                EditText phone = findViewById(R.id.editPosPhone);
                phone.setText(select.getPhone());
                //menu
                try{
                    fillDDLPosMenuNames();
                    Spinner resp = findViewById(R.id.listPosMenu);
                    resp.getItemAtPosition(select.getIdMenu());
                }catch(Exception e){
                    Log.d(TAG, "no menu !?"+e);
                }
                */

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void fillDDLPosMenuNames() {
        Spinner dropdown = findViewById(R.id.listPosMenu);
        String[] items = getNamesMenus();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public String[] getNamesMenus() {

        if(mMenuEntities.equals(null)) {
            String[] empty = new String[]{"no available data"};
            return empty;
        }
        String[] menuNames = new String[mMenuEntities.size()];
        for(int i = 0; i<mMenuEntities.size(); i++) {
            menuNames[i] = mMenuEntities.get(i).getNomMenu();
        }

        return menuNames;
    }

    public void fillDDLCollabNames() {
        Spinner dropdown = findViewById(R.id.listCollabo);
        String[] items = getNamesCollaborateurs();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public void fillDDLCollabPosNames() {
        Spinner dropdown = findViewById(R.id.listPosCollaborateur);
        String[] items = getNamesCollaborateurs();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public String[] getNamesCollaborateurs() {

        if(mCollaborateurEntities.equals(null)) {
            String[] empty = new String[]{"no available data"};
            return empty;
        }
        String[] collaborateurNames = new String[mCollaborateurEntities.size()];
        for(int i = 0; i<mCollaborateurEntities.size(); i++) {
            collaborateurNames[i] = mCollaborateurEntities.get(i).getNomCollab() + " " +
                    mCollaborateurEntities.get(i).getPrenomCollab();
        }

        return collaborateurNames;
    }

    public String[] getNamesPizzas() {

        if(mPizzaEntities.equals(null)) {
            String[] empty = new String[]{"no available data"};
            return empty;
        }
        String[] pizzaNames = new String[mPizzaEntities.size()];
        for(int i = 0; i<mPizzaEntities.size(); i++) {
            pizzaNames[i] = mPizzaEntities.get(i).getNom();

        }

        return pizzaNames;
    }

    public void posUpdate(View view) {

    }

    public void posInsert(View view) {

    }

    public void posDelete(View view) {

    }

    public void menuUpdate(View view) {

    }

    public void menuInsert(View view) {

    }

    public void menuDelete(View view) {

    }

    public void collaboUpdate(View view) {

    }

    public void collaboInsert(View view) {

    }

    public void collaboDelete(View view) {

    }




}
