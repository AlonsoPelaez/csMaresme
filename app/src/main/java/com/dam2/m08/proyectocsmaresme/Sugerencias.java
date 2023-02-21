package com.dam2.m08.proyectocsmaresme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Sugerencias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugerencias);
        Toast.makeText(this, "SUGERENCIAS", Toast.LENGTH_SHORT).show();    }
}