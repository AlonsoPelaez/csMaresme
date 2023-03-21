package com.dam2.m08.proyectocsmaresme.juegos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dam2.m08.proyectocsmaresme.foroanonimo.ChatAnonimo;
import com.dam2.m08.proyectocsmaresme.R;
import com.dam2.m08.proyectocsmaresme.settings.Setting;
import com.dam2.m08.proyectocsmaresme.noticias.Home;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Games extends AppCompatActivity {
    private String rol;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games);
        Intent intent = getIntent();
        rol = intent.getStringExtra("Rol");
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
                        startActivity(new Intent(getApplicationContext(), Home.class).putExtra("Rol", rol));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.games:
                        startActivity(new Intent(getApplicationContext(),Games.class).putExtra("Rol", rol));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.chat:
                        startActivity(new Intent(getApplicationContext(), ChatAnonimo.class).putExtra("Rol", rol));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.setting:
                        startActivity(new Intent(getApplicationContext(), Setting.class).putExtra("Rol", rol));
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
