package com.dam2.m08.proyectocsmaresme.juegos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.dam2.m08.proyectocsmaresme.foroanonimo.ChatAnonimo;
import com.dam2.m08.proyectocsmaresme.R;
import com.dam2.m08.proyectocsmaresme.juegos.preguntados.QuizActivity;
import com.dam2.m08.proyectocsmaresme.settings.Setting;
import com.dam2.m08.proyectocsmaresme.noticias.Home;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Games extends AppCompatActivity {
    private LinearLayout linearLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.azuloscurointerfaz));

        BottomNavigationView bottomNavigationView=findViewById(R.id.navView);
        linearLayout = findViewById(R.id.containerBrainTraining);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), QuizActivity.class));

            }
        });

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.games);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.games:
                        startActivity(new Intent(getApplicationContext(),Games.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.chat:
                        startActivity(new Intent(getApplicationContext(), ChatAnonimo.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.setting:
                        startActivity(new Intent(getApplicationContext(), Setting.class));
                        overridePendingTransition(0,0);

                }
                return false;
            }
        });



    }

    @Override
    public void onBackPressed() {

    }

}
