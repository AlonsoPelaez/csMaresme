package com.dam2.m08.proyectocsmaresme.juegos.preguntados;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dam2.m08.proyectocsmaresme.R;
import com.dam2.m08.proyectocsmaresme.juegos.Games;

public class OptionQuiz extends AppCompatActivity {

    private Button historyButton;
    private Button scienceButton;
    private Button artButton;
    private ImageButton volverButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_quiz);
        historyButton = findViewById(R.id.historyButton);
        scienceButton = findViewById(R.id.sciencieButton);
        artButton = findViewById(R.id.artButton);
        volverButton = findViewById(R.id.volverOptionQuiz);

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                intent.putExtra("election", "history");
                startActivity(intent);
                finish();
            }
        });


        scienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                intent.putExtra("election", "science");
                startActivity(intent);
                finish();
            }
        });

        artButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                intent.putExtra("election", "art");
                startActivity(intent);
                finish();
            }
        });

        volverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Games.class));
                finish();
            }
        });

    }
}
