package com.dam2.m08.proyectocsmaresme;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Contactanos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactanos);
        Toast.makeText(this, "Contact US", Toast.LENGTH_SHORT).show();
    }
}