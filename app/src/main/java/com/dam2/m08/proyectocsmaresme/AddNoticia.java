package com.dam2.m08.proyectocsmaresme;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoticia extends AppCompatActivity {

    private Button enviarButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_noticia);

        enviarButton = findViewById(R.id.subirButtonNoticia);


        enviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Subiendo noticia", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
