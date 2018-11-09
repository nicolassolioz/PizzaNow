package com.mycompany.pizzanow.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mycompany.pizzanow.R;
import com.mycompany.pizzanow.database.Database;
import com.mycompany.pizzanow.model.Pizza;
import android.arch.persistence.room.*;

public class MainActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //initiate database on creation of app
        Database db = Room.databaseBuilder(getApplicationContext(),
                Database.class, "database-name").build();




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){




        Intent intent = new Intent(MainActivity.this, ShowSuccActivity.class);
        startActivity(intent);
    }

   /* public String helloValentin(String hello) {
        //dire coucou à Valentin hihi
        System.out.println(hello);
        //répondre à nico
        return "Hello nico";
        new commit
    }*/
}
