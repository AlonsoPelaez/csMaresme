package com.dam2.m08.proyectocsmaresme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sugerencias extends AppCompatActivity {

    private EditText editTextReport;
    private Button SendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugerencias);
        Toast.makeText(this, "SUGERENCIAS", Toast.LENGTH_SHORT).show();
        editTextReport = findViewById(R.id.editTextReport);
        SendButton = findViewById(R.id.SendButton);

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Setting.class));
            }
        });
    }
}