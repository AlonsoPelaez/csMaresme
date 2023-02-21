package com.dam2.m08.proyectocsmaresme;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Setting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button btn_logout;
    private Spinner spinner_CambiarIdioma;
    private Spinner spinner_EnviarSugerencia;
    private Spinner spinner_ReportarProblema;
    private Spinner spinner_Calificanos;
    private Spinner spinner_Contactanos;

    private AdapterView<Adapter> adapterIdioma;
    private AdapterView<Adapter> adapterSugerencia;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setting);
        Toast.makeText(this, "SETTING", Toast.LENGTH_SHORT).show();

//        spinner_CambiarIdioma.setOnItemSelectedListener(this);
//        spinner_EnviarSugerencia.setOnItemSelectedListener(this);




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
            }
        });



    }
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {




    }



    public void onNothingSelected(AdapterView<?> adapterIdiomas) {


    }



}
