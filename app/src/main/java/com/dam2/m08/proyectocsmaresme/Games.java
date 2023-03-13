package com.dam2.m08.proyectocsmaresme;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Games extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games);

        Toast.makeText(this, "GAMES", Toast.LENGTH_SHORT).show();

        BottomNavigationView bottomNavigationView=findViewById(R.id.navView);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.games);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.games:
                        startActivity(new Intent(getApplicationContext(),Games.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.chat:
                        startActivity(new Intent(getApplicationContext(),ChatAnonimo.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.setting:
                        startActivity(new Intent(getApplicationContext(),Setting.class));
                        overridePendingTransition(0,0);

                }
                return false;
            }
        });



    }



}
