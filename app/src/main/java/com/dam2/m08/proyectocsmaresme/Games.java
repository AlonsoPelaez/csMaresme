package com.dam2.m08.proyectocsmaresme;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Games extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
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
            case R.id.home:
                intent = new Intent(Games.this, Home.class);
                startActivity(intent);
                break;
            case R.id.chat:
                intent = new Intent(Games.this, Chat.class);
                startActivity(intent);
                break;
            case R.id.configuration:
                intent = new Intent(Games.this, Setting.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
