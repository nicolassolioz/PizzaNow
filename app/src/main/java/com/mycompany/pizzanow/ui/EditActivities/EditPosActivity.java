package com.mycompany.pizzanow.ui.EditActivities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mycompany.pizzanow.BaseApp;
import com.mycompany.pizzanow.R;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.MenuEntity;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.database.repository.PosRepository;
import com.mycompany.pizzanow.util.OnAsyncEventListener;
import com.mycompany.pizzanow.viewmodel.Collaborateur.AllCollaborateurListViewModel;
import com.mycompany.pizzanow.viewmodel.POS.PosListViewModel;
import com.mycompany.pizzanow.viewmodel.POS.PosViewModel;
import com.mycompany.pizzanow.viewmodel.menu.MenuListViewModel;
import com.mycompany.pizzanow.viewmodel.pizza.PizzaListViewModel;

import java.util.ArrayList;
import java.util.List;

public class EditPosActivity extends EditActivity {/*
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
        setContentView(R.layout.activity_edit_pos);

        // (1) Fill list of pos with data
        PosListViewModel.Factory factoryPos = new PosListViewModel.Factory(getApplication());

        mPosListViewmodel = ViewModelProviders.of(this, factoryPos).get(PosListViewModel.class);
        mPosListViewmodel.getAllPos().observe(this, posEntities -> {
            if (posEntities != null) {
                mPosEntities = posEntities;
                fillDDLPosNames();
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

    public void fillPosSection() {
        fillDDLPosNames();
        fillDDLCollabPosNames();
        fillDDLPosMenuNames();
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
    */
}
