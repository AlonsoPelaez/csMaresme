package com.dam2.m08.proyectocsmaresme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Setting extends AppCompatActivity {
    private Button btn_logout;


    private final String TAG = "PROYECTO_CS_MARESME___SETTINGS";

    private Button sugerenciabutton;
    private Button buttonreport;
    private Button rateus;
    private Button contactusbutton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);


        Toast.makeText(this, "SETTING", Toast.LENGTH_SHORT).show();


        sugerenciabutton = findViewById(R.id.button_sugerencia);
        sugerenciabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Sugerencias.class));
            }
        });

        buttonreport = findViewById(R.id.button_report);
        buttonreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Report.class));
            }
        });


        rateus = findViewById(R.id.button_rateus);
        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Calificanos.class));
            }
        });

        contactusbutton = findViewById(R.id.button_contactus);
        contactusbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Contactanos.class));
            }
        });


        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //                        funcion de cerrado de sesion
                SharedPreferences preferences = getSharedPreferences(getString(R.string.prefer_file), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });


    }
}
