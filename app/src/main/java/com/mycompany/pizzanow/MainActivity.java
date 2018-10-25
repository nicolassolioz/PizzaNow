package com.mycompany.pizzanow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        Intent intent = new Intent(MainActivity.this, SuccDisplayActivity.class);
        startActivity(intent);
    }

   /* public String helloValentin(String hello) {
        //dire coucou à Valentin hihi
        System.out.println(hello);
        //répondre à nico
        return "Hello nico";
    }*/
}
