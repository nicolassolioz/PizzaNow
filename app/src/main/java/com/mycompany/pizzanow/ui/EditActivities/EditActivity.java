package com.mycompany.pizzanow.ui.EditActivities;

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
import com.mycompany.pizzanow.database.AppDatabase;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.MenuEntity;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.database.repository.PizzaRepository;
import com.mycompany.pizzanow.database.repository.PosRepository;
import com.mycompany.pizzanow.model.Pizza;
import com.mycompany.pizzanow.ui.Toolbar.ToolbarActivity;
import com.mycompany.pizzanow.util.OnAsyncEventListener;
import com.mycompany.pizzanow.database.repository.CollaborateurRepository;
import com.mycompany.pizzanow.viewmodel.Collaborateur.AllCollaborateurListViewModel;
import com.mycompany.pizzanow.viewmodel.Collaborateur.CollaborateurListViewModel;
import com.mycompany.pizzanow.viewmodel.Collaborateur.CollaborateurViewModel;
import com.mycompany.pizzanow.viewmodel.POS.PosListViewModel;
import com.mycompany.pizzanow.viewmodel.POS.PosViewModel;
import com.mycompany.pizzanow.viewmodel.menu.MenuListViewModel;
import com.mycompany.pizzanow.viewmodel.pizza.PizzaListViewModel;
import com.mycompany.pizzanow.viewmodel.pizza.PizzaViewModel;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends ToolbarActivity {

    private static final String TAG = "Edit Activity";

    private List<PosEntity> mPosEntities;
    //private List<MenuEntity> mMenuEntities;
    private List<CollaborateurEntity> mCollaborateurEntities;
    private List<PizzaEntity> mPizzaEntities;

    private PosEntity mPosEntity;
    private CollaborateurEntity mCollaborateurEntity;
    private PizzaEntity mPizzaEntity;

    private PosListViewModel mPosListViewmodel;
    //private MenuListViewModel mMenuListViewmodel;
    private AllCollaborateurListViewModel mCollaborateurListViewModel;
    private PizzaListViewModel mPizzaListViewModel;

    private PosViewModel mPosViewModel;
    private CollaborateurViewModel mCollaborateurViewModel;
    private PizzaViewModel mPizzaViewModel;

    private Spinner resp;
    private Spinner place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mPosEntities = new ArrayList<>();
        //mMenuEntities = new ArrayList<>();
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



        // (2) Fill list of employees with data
        AllCollaborateurListViewModel.Factory factoryCollab= new AllCollaborateurListViewModel.Factory(getApplication());

        mCollaborateurListViewModel = ViewModelProviders.of(this,factoryCollab).get(AllCollaborateurListViewModel.class);
        mCollaborateurListViewModel.getAllCollabs().observe(this,collaborateurEntities ->  {
            if(collaborateurEntities!=null){
                mCollaborateurEntities = collaborateurEntities;
                fillDDLCollabNames();
            }
        });

        // (3) Fill list of Pizzas with data
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

    //retrieve all points of sales from Database
    public void getAllPos() {

        PosListViewModel.Factory factoryPos = new PosListViewModel.Factory(getApplication());

        mPosListViewmodel = ViewModelProviders.of(this,factoryPos).get(PosListViewModel.class);
        mPosListViewmodel.getAllPos().observe(this,posEntities -> {
            if(posEntities!=null){
                mPosEntities = posEntities;
            }
        });

    }


    //put Pos Names in the dropdownlist
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
                    resp = findViewById(R.id.listPosCollaborateur);
                    resp.setSelection(0);
                }catch(Exception e){
                    Log.d(TAG, "no resp !?"+e);
                }
                //email
                EditText email = findViewById(R.id.editPosEmail);
                email.setText(select.getEmail());
                //phone
                EditText phone = findViewById(R.id.editPosPhone);
                phone.setText(select.getPhone());


                //position should be the ID
                PosRepository posRepository = ((BaseApp) getApplication()).getPosRepository();

                mPosViewModel = new PosViewModel(getApplication(),select.getIdPos(),posRepository);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //put names of employees in the dropdownlist of the pos section
    public void fillDDLPosCollabNames() {
        Spinner dropdown = findViewById(R.id.listPosCollaborateur);
        String[] items = getNamesCollaborateurs();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    // get all names of pos in database and put in a string array
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

    //update pos section
    public void fillPosSection() {
        fillDDLPosNames();
        fillDDLCollabPosNames();

        //fillDDLPosMenuNames();
    }

    //fill dropdownlist with names of employees
    public void fillDDLCollabNames() {
        Spinner dropdown = findViewById(R.id.listCollabo);
        String[] items = getNamesCollaborateurs();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CollaborateurEntity select = mCollaborateurEntities.get(position);
                mCollaborateurEntity = select;
                //Nom
                EditText name = findViewById(R.id.editCollaboName);
                name.setText(select.getNomCollab());
                //Prénom
                EditText address = findViewById(R.id.editCollaboSurname);
                address.setText(select.getPrenomCollab());
                //resp
                try{
                    fillDDLCollabPosNames();
                    place = findViewById(R.id.listCollaboPos);
                    place.setSelection(0);
                }catch(Exception e){
                    Log.d(TAG, "no resp !?"+e);
                }

                //position should be the ID
                CollaborateurRepository collaboRepository = ((BaseApp) getApplication()).getCollaborateurRepository();
                mCollaborateurViewModel = new CollaborateurViewModel(getApplication(),select.getIdCollab(),collaboRepository);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //fill dropdownlist with name of companies
    public void fillDDLCollabPosNames() {
        Spinner dropdown = findViewById(R.id.listCollaboPos);
        String[] items = getNamesPos();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    //get all names of employees and put in string array
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

    //fill dropdownlist with names of all pizzas
    public void fillDDLPizzaNames() {
        Spinner dropdown = findViewById(R.id.listPizza);
        String[] items = getNamesPizzas();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PizzaEntity select = mPizzaEntities.get(position);
                mPizzaEntity = select;
                //name
                EditText name = findViewById(R.id.editPizzaName);
                name.setText(select.getNom());
                //description
                EditText desc = findViewById(R.id.editPizzaDescription);
                desc.setText(select.getDescription());
                //prix
                EditText prix = findViewById(R.id.editPizzaPrice);
                prix.setText(Double.toString(select.getPrix()));

                //position should be the ID
                PizzaRepository pizzaRepository = ((BaseApp) getApplication()).getPizzaRepository();
                mPizzaViewModel = new PizzaViewModel(getApplication(),select.getIdPizza(),pizzaRepository);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //put all names of pizzas in a string array
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

    //update the pos
    public void posUpdate(View view) {

        boolean numeric = true;

        EditText editPosName = findViewById(R.id.editPosName);
        EditText editPosLocalite = findViewById(R.id.editPosLocalite);
        EditText editPosNPA = findViewById(R.id.editPosNPA);
        EditText editPosAddress = findViewById(R.id.editPosAddress);
        EditText editPosEmail = findViewById(R.id.editPosEmail);
        EditText editPosPhone = findViewById(R.id.editPosPhone);


        try {
            Double num = Double.parseDouble(editPosNPA.getText().toString());
        } catch (NumberFormatException e) {
            numeric = false;
        }

        if(numeric)
        {

            int indice = resp.getSelectedItemPosition();
            CollaborateurEntity respPos = mCollaborateurEntities.get(indice);


            mPosEntity.setNom(editPosName.getText().toString());
            mPosEntity.setLocalite(editPosLocalite.getText().toString());
            mPosEntity.setNPA(Integer.parseInt(editPosNPA.getText().toString()));
            mPosEntity.setAdresse(editPosAddress.getText().toString());
            mPosEntity.setEmail(editPosEmail.getText().toString());
            mPosEntity.setPhone(editPosPhone.getText().toString());
            //mPosEntity.setIdMenu(2);
            mPosEntity.setResponsable(respPos.getIdCollab());

            mPosViewModel.updatePos(mPosEntity);

            fillPosSection();

            Toast.makeText(this, "Point of sales changes saved",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Point of sales changes NOT saved, check that NPA is a number",
                    Toast.LENGTH_SHORT).show();
        }


    }

    //insert new pos
    public void posInsert(View view) {

        boolean numeric = true;

        PosEntity newPos = new PosEntity();

        EditText editPosName = findViewById(R.id.editPosName);
        EditText editPosLocalite = findViewById(R.id.editPosLocalite);
        EditText editPosNPA = findViewById(R.id.editPosNPA);
        EditText editPosAddress = findViewById(R.id.editPosAddress);
        EditText editPosEmail = findViewById(R.id.editPosEmail);
        EditText editPosPhone = findViewById(R.id.editPosPhone);

        try {
            Double num = Double.parseDouble(editPosNPA.getText().toString());
        } catch (NumberFormatException e) {
            numeric = false;
        }

        if(numeric)
        {
            int indice = resp.getSelectedItemPosition();
            CollaborateurEntity respPos = mCollaborateurEntities.get(indice);



            newPos.setNom(editPosName.getText().toString());
            newPos.setLocalite(editPosLocalite.getText().toString());
            newPos.setNPA(Integer.parseInt(editPosNPA.getText().toString()));
            newPos.setAdresse(editPosAddress.getText().toString());
            newPos.setEmail(editPosEmail.getText().toString());
            newPos.setPhone(editPosPhone.getText().toString());

            newPos.setResponsable(respPos.getIdCollab());

            if (mPosViewModel == null) {
                PosRepository repository = ((BaseApp) getApplication()).getPosRepository();
                mPosViewModel = new PosViewModel(getApplication(),"1",repository);
            }

            mPosViewModel.createPos(newPos);

            fillPosSection();

            Toast.makeText(this, "New point of sales added",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "New point of sales NOT added check that NPA field is numeric",
                    Toast.LENGTH_SHORT).show();
        }

    }

    //delete the pos
    public void posDelete(View view) {

        mPosViewModel.deletePos(mPosEntity);

        Toast.makeText(this, "Point of sales deleted",
                Toast.LENGTH_SHORT).show();

    }


    //update the employee
    public void collaboUpdate(View view) {

        int indice = place.getSelectedItemPosition();
        PosEntity workPlace = mPosEntities.get(indice);

        EditText editCollabName = findViewById(R.id.editCollaboName);
        EditText editCollabSurname = findViewById(R.id.editCollaboSurname);

        mCollaborateurEntity.setNomCollab(editCollabName.getText().toString());
        mCollaborateurEntity.setPrenomCollab(editCollabSurname.getText().toString());
        mCollaborateurEntity.setIdPosCollab(workPlace.getIdPos());

        mCollaborateurViewModel.updateCollaborateur(mCollaborateurEntity);

        Toast.makeText(this, "Collaborator changes saved",
                Toast.LENGTH_SHORT).show();

        fillCollabSection();

    }

    //insert new employee
    public void collaboInsert(View view) {
        CollaborateurEntity newCollab = new CollaborateurEntity();

        EditText editCollabName = findViewById(R.id.editCollaboName);
        EditText editCollabSurname = findViewById(R.id.editCollaboSurname);

        int indice = place.getSelectedItemPosition();
        PosEntity workPlace = mPosEntities.get(indice);

        newCollab.setNomCollab(editCollabName.getText().toString());
        newCollab.setPrenomCollab(editCollabSurname.getText().toString());
        newCollab.setIdPosCollab(workPlace.getIdPos());

        if (mCollaborateurViewModel == null) {
            CollaborateurRepository repository = ((BaseApp) getApplication()).getCollaborateurRepository();
            mCollaborateurViewModel = new CollaborateurViewModel(getApplication(),"1",repository);
        }

        mCollaborateurViewModel.createCollab(newCollab);

        fillCollabSection();

        Toast.makeText(this, "New collaborator added",
                Toast.LENGTH_SHORT).show();

    }

    //update the employee section
    private void fillCollabSection() {
        fillDDLCollabNames();
        fillDDLCollabPosNames();
        fillDDLPosCollabNames();
    }

    //delete employee
    public void collaboDelete(View view) {
        mCollaborateurViewModel.deleteCollaborateur(mCollaborateurEntity);

        Toast.makeText(this, "Collaborator deleted",
                Toast.LENGTH_SHORT).show();

    }

    //update selected pizza
    public void pizzaUpdate(View view) {

        boolean numeric = true;

        EditText editPizzaName = findViewById(R.id.editPizzaName);
        EditText editPizzaDesc = findViewById(R.id.editPizzaDescription);
        EditText editPizzaPrice = findViewById(R.id.editPizzaPrice);

        try {
            Double num = Double.parseDouble(editPizzaPrice.getText().toString());
        } catch (NumberFormatException e) {
            numeric = false;
        }

        if(numeric)
        {
            mPizzaEntity.setNom(editPizzaName.getText().toString());
            mPizzaEntity.setDescription(editPizzaDesc.getText().toString());
            mPizzaEntity.setPrix(Double.parseDouble(editPizzaPrice.getText().toString()));

            mPizzaViewModel.updatePizza(mPizzaEntity);

            Toast.makeText(this, "Pizza updated",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Pizza NOT updated, check that price field is in the correct format",
                    Toast.LENGTH_SHORT).show();
        }


    }

    //update content of pizza section
    public void fillPizzaSection(){
        fillDDLPizzaNames();
    }

    //insert new pizza
    public void pizzaInsert(View view) {

        EditText editPizzaName = findViewById(R.id.editPizzaName);
        EditText editPizzaDesc = findViewById(R.id.editPizzaDescription);
        EditText editPizzaPrice = findViewById(R.id.editPizzaPrice);

        boolean numeric = true;

        Double num;

        try {
            num = Double.parseDouble(editPizzaPrice.getText().toString());
        } catch (NumberFormatException e) {
            numeric = false;
        }
        if(numeric)
        {

            PizzaEntity newPizza = new PizzaEntity();

            newPizza.setNom(editPizzaName.getText().toString());
            newPizza.setDescription(editPizzaDesc.getText().toString());
            newPizza.setPrix(Double.parseDouble(editPizzaPrice.getText().toString()));

            if (mPizzaViewModel == null) {
                mPizzaViewModel = new PizzaViewModel(getApplication(),"1",PizzaRepository.getInstance());
            }

            mPizzaViewModel.createPizza(newPizza);

            fillPizzaSection();

            Toast.makeText(this, "Pizza created",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Pizza NOT created, check that the price is in number format (Example : 70.2)",
                    Toast.LENGTH_SHORT).show();
        }

    }

    //delete selected pizza
    public void pizzaDelete(View view) {
        mPizzaViewModel.deletePizza(mPizzaEntity);
        Toast.makeText(this, "Pizza deleted",
                Toast.LENGTH_SHORT).show();


    }

}

/*
// (2) Fill list of menus with data
        MenuListViewModel.Factory factoryMenu = new MenuListViewModel.Factory(getApplication());

        mMenuListViewmodel = ViewModelProviders.of(this,factoryMenu).get(MenuListViewModel.class);
        mMenuListViewmodel.getAllMenus().observe(this,menuEntities -> {
            if(menuEntities!=null){
                mMenuEntities = menuEntities;
                fillDDLMenuNames();
            }
        });

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

    public void fillDDLPosMenuNames() {
        Spinner dropdown = findViewById(R.id.listPosMenu);
        String[] items = getNamesMenus();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }


    //menu
                try{
                    fillDDLPosMenuNames();
                    Spinner resp = findViewById(R.id.listPosMenu);
                    resp.setSelection(select.getIdMenu()-1);
                }catch(Exception e){
                    Log.d(TAG, "no menu !?"+e);
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
                try{
                    fillDDLCollabPosNames();
                    ListView resp = findViewById(R.id.listAllPizzas);
                    resp.setAdapter();
                }catch(Exception e){
                    Log.d(TAG, "no resp !?"+e);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void menuUpdate(View view) {

    }

    public void menuInsert(View view) {

    }

    public void menuDelete(View view) {

    }

 */