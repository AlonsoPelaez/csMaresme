package com.dam2.m08.proyectocsmaresme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        Intent intent = getIntent();
        String usuario_email= intent.getStringExtra("usuario_email");

        SharedPreferences prefer= getSharedPreferences(getString(R.string.prefer_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefer.edit();
        editor.putString("usuario_email",usuario_email);
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        getMenuInflater();
        Intent intent;
        switch (item.getItemId()){
            case R.id.games:
                intent = new Intent(Home.this, Games.class);
                startActivity(intent);
                break;
            case R.id.chat:
                intent = new Intent(Home.this, ChatAnonimo.class);
                startActivity(intent);
                break;
            case R.id.configuration:
                intent = new Intent(Home.this, Setting.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
