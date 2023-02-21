package com.dam2.m08.proyectocsmaresme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Set;

public class Home extends AppCompatActivity {

    private final String TAG = "PROYECTO_CS_MARESME___HOME";
    private TextView titulo;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        mostrarDatos();
        Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show();
        titulo = findViewById(R.id.titulo);
        //        recoge el email del usuario y lo mete en el sharedpreferences
        Intent intent = getIntent();
        String usuario_email= intent.getStringExtra("usuario_email");
        Log.d(TAG, "onCreate: " + usuario_email);

        SharedPreferences prefer= getSharedPreferences(getString(R.string.prefer_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefer.edit();
        editor.putString("usuario_email",usuario_email);
        editor.apply();
        BottomNavigationView bottomNavigationView = findViewById(R.id.navView);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.games:
                        startActivity(new Intent(getApplicationContext(), Games.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.chat:
                        startActivity(new Intent(getApplicationContext(), ChatAnonimo.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.setting:

                        startActivity(new Intent(getApplicationContext(), Setting.class));

                }
                return false;
            }
        });
    }

    public void mostrarDatos(){
        FirebaseFirestore db =FirebaseFirestore.getInstance();
        db.collection("Noticias")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                            titulo.setText((CharSequence) documentSnapshot.get("Titulo"));
                            Log.d(TAG, documentSnapshot.getId() + " => " + documentSnapshot.getData());
                        }
                    }
                    }
                });
    }
}
