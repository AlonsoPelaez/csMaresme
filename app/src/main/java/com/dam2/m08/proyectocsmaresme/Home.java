package com.dam2.m08.proyectocsmaresme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    private final String TAG ="PROYECTO_CS_MARESME___HOME";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

//        recoge el email del usuario y lo mete en el sharedpreferences
//        Intent intent = getIntent();
//        String usuario_email= intent.getStringExtra("usuario_email");
//        Log.d(TAG, "onCreate: " + usuario_email);
//
//        SharedPreferences prefer= getSharedPreferences(getString(R.string.prefer_file), Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefer.edit();
//        editor.putString("usuario_email",usuario_email);
//        editor.apply();
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

//               Cierra sesion cuando se pulse el boton y redirige al usuario al login


//                <--
//                SharedPreferences preferences = getSharedPreferences(getString(R.string.prefer_file),Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.clear();
//                editor.apply();
//                Log.d(TAG, "onOptionsItemSelected: "+ editor.clear());
//
//                FirebaseAuth.getInstance().signOut();
//                intent = new Intent(Home.this, Login.class);
//                startActivity(intent);
//                -->

                intent = new Intent(Home.this, Setting.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
