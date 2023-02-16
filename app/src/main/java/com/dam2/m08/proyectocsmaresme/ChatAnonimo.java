package com.dam2.m08.proyectocsmaresme;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

public class ChatAnonimo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = getIntent();
        switch (item.getItemId()){
            case R.id.games:
                intent = new Intent(ChatAnonimo.this, Games.class);
                startActivity(intent);
                break;
            case R.id.home:
                intent = new Intent(ChatAnonimo.this, Home.class);
                startActivity(intent);
                break;
            case R.id.configuration:
                intent = new Intent(ChatAnonimo.this, Setting.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
