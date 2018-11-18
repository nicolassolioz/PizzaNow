package com.mycompany.pizzanow.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mycompany.pizzanow.BaseApp;
import com.mycompany.pizzanow.R;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.MenuEntity;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.database.repository.PosRepository;
import com.mycompany.pizzanow.util.OnAsyncEventListener;
import com.mycompany.pizzanow.database.repository.CollaborateurRepository;
import com.mycompany.pizzanow.viewmodel.Collaborateur.AllCollaborateurListViewModel;
import com.mycompany.pizzanow.viewmodel.Collaborateur.CollaborateurListViewModel;
import com.mycompany.pizzanow.viewmodel.Collaborateur.CollaborateurViewModel;
import com.mycompany.pizzanow.viewmodel.POS.PosListViewModel;
import com.mycompany.pizzanow.viewmodel.POS.PosViewModel;
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

    private PosEntity mPosEntity;

    private PosListViewModel mPosListViewmodel;
    private MenuListViewModel mMenuListViewmodel;
    private AllCollaborateurListViewModel mCollaborateurViewModel;
    private PizzaListViewModel mPizzaListViewModel;

    private PosViewModel mPosViewModel;

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

        // (5) Fill list of Pizzas with data
        PizzaListViewModel.Factory factoryPizza= new PizzaListViewModel.Factory(getApplication());

        mPizzaListViewModel = ViewModelProviders.of(this,factoryPizza).get(PizzaListViewModel.class);
        mPizzaListViewModel.getAllPizzas().observe(this,pizzaEntities  ->  {
            if(pizzaEntities!=null){
                mPizzaEntities = pizzaEntities;
                fillDDLPizzaNames();
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
                mPosEntity = select;
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
                localite.setText(select.getLocalite());
                //resp
                try{
                    fillDDLPosCollabNames();
                    Spinner resp = findViewById(R.id.listPosCollaborateur);
                    resp.setSelection(select.getResponsable()-1);
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
                    resp.setSelection(select.getIdMenu()-1);
                }catch(Exception e){
                    Log.d(TAG, "no menu !?"+e);
                }

                PosRepository posRepository = ((BaseApp) getApplication()).getPosRepository();
                mPosViewModel = new PosViewModel(getApplication(),position,posRepository);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void fillDDLPosCollabNames() {
        Spinner dropdown = findViewById(R.id.listPosCollaborateur);
        String[] items = getNamesCollaborateurs();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public void fillDDLPosMenuNames() {
        Spinner dropdown = findViewById(R.id.listPosMenu);
        String[] items = getNamesMenus();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
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
                EditText name = findViewById(R.id.editMenuName);
                name.setText(select.getNomMenu());
                //resp
                /*try{
                    fillDDLCollabPosNames();
                    ListView resp = findViewById(R.id.listAllPizzas);

                    resp.setAdapter();
                }catch(Exception e){
                    Log.d(TAG, "no resp !?"+e);
                }
                */

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void fillPosSection() {
        fillDDLPosNames();
        fillDDLCollabPosNames();
        fillDDLPosMenuNames();
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

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CollaborateurEntity select = mCollaborateurEntities.get(position);
                //Nom
                EditText name = findViewById(R.id.editCollaboName);
                name.setText(select.getNomCollab());
                //Prénom
                EditText address = findViewById(R.id.editCollaboSurname);
                address.setText(select.getPrenomCollab());
                //resp
                try{
                    fillDDLCollabPosNames();
                    Spinner resp = findViewById(R.id.listCollaboPos);
                    resp.setSelection(select.getIdPosCollab()-1);
                }catch(Exception e){
                    Log.d(TAG, "no resp !?"+e);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void fillDDLCollabPosNames() {
        Spinner dropdown = findViewById(R.id.listCollaboPos);
        String[] items = getNamesPos();
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

    public void fillDDLPizzaNames() {
        Spinner dropdown = findViewById(R.id.listPizza);
        String[] items = getNamesPizzas();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PizzaEntity select = mPizzaEntities.get(position);
                //name
                EditText name = findViewById(R.id.editPizzaName);
                name.setText(select.getNom());
                //description
                EditText desc = findViewById(R.id.editPizzaDescription);
                desc.setText(select.getDescription());
                //prix
                EditText prix = findViewById(R.id.editPizzaPrice);
                prix.setText(Double.toString(select.getPrix()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

        EditText editPosName = findViewById(R.id.editPosName);
        EditText editPosLocalite = findViewById(R.id.editPosLocalite);
        EditText editPosNPA = findViewById(R.id.editPosNPA);
        EditText editPosAddress = findViewById(R.id.editPosAddress);
        EditText editPosEmail = findViewById(R.id.editPosEmail);
        EditText editPosPhone = findViewById(R.id.editPosPhone);


        mPosEntity.setNom(editPosName.getText().toString());
        mPosEntity.setLocalite(editPosLocalite.getText().toString());
        mPosEntity.setNPA(Integer.parseInt(editPosNPA.getText().toString()));
        mPosEntity.setAdresse(editPosAddress.getText().toString());
        mPosEntity.setEmail(editPosEmail.getText().toString());
        mPosEntity.setPhone(editPosPhone.getText().toString());
        mPosEntity.setIdMenu(2);
        mPosEntity.setResponsable(1);

        mPosViewModel.updatePos(mPosEntity);

        fillPosSection();

        Toast.makeText(this, "Point of sales changes saved",
                Toast.LENGTH_SHORT).show();

    }

    public void posInsert(View view) {

        PosEntity newPos = new PosEntity();

        EditText editPosName = findViewById(R.id.editPosName);
        EditText editPosLocalite = findViewById(R.id.editPosLocalite);
        EditText editPosNPA = findViewById(R.id.editPosNPA);
        EditText editPosAddress = findViewById(R.id.editPosAddress);
        EditText editPosEmail = findViewById(R.id.editPosEmail);
        EditText editPosPhone = findViewById(R.id.editPosPhone);


        newPos.setNom(editPosName.getText().toString());
        newPos.setLocalite(editPosLocalite.getText().toString());
        newPos.setNPA(Integer.parseInt(editPosNPA.getText().toString()));
        newPos.setAdresse(editPosAddress.getText().toString());
        newPos.setEmail(editPosEmail.getText().toString());
        newPos.setPhone(editPosPhone.getText().toString());
        newPos.setIdMenu(2);
        newPos.setResponsable(1);

        mPosViewModel.createPos(newPos);

        fillPosSection();

        Toast.makeText(this, "New point of sales added",
                Toast.LENGTH_SHORT).show();
    }

    public void posDelete(View view) {

        mPosViewModel.deletePos(mPosEntity, new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                fillPosSection();
            }

            @Override
            public void onFailure(Exception e) {}
        });

        Toast.makeText(this, "Point of sales deleted",
                Toast.LENGTH_SHORT).show();

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
