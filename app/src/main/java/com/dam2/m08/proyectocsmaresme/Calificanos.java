package com.dam2.m08.proyectocsmaresme;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Calificanos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calificanos);
        Toast.makeText(this, "Report", Toast.LENGTH_SHORT).show();
    }
}