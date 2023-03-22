package com.dam2.m08.proyectocsmaresme.noticias;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dam2.m08.proyectocsmaresme.foroanonimo.ChatAnonimo;
import com.dam2.m08.proyectocsmaresme.juegos.Games;
import com.dam2.m08.proyectocsmaresme.R;
import com.dam2.m08.proyectocsmaresme.settings.Setting;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Home extends AppCompatActivity {

    private final String TAG = "PROYECTO_CS_MARESME___HOME";
    private List<Noticia> noticiaList;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private String rol;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        mostrarDatos();
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.azuloscurointerfaz));
        noticiaList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show();
        //        recoge el email del usuario y lo mete en el sharedpreferences
        Intent intent = getIntent();
        String usuario_email = intent.getStringExtra("usuario_email");
        SharedPreferences sharedPreferences = getSharedPreferences("Rol", Context.MODE_PRIVATE);
        rol = sharedPreferences.getString("Rol", "");
        System.out.println("Esto es el rol--> "+rol);
        Log.d(TAG, "onCreate: " + usuario_email);

        SharedPreferences prefer = getSharedPreferences(getString(R.string.prefer_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefer.edit();
        editor.putString("usuario_email", usuario_email);
        editor.apply();
        floatingActionButton = findViewById(R.id.add_comentario_chatanonimo);
        System.out.println("--->>>" + rol + usuario_email);
        if (Objects.equals(rol, "Administrador")){
            floatingActionButton.setVisibility(View.VISIBLE);
        } else {
            floatingActionButton.setVisibility(View.INVISIBLE);
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.navView);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), AddNoticia.class);
                startActivity(intent1);
            }
        });

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.games:
                        startActivity(new Intent(getApplicationContext(), Games.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.chat:
                        startActivity(new Intent(getApplicationContext(), ChatAnonimo.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.setting:
                        startActivity(new Intent(getApplicationContext(), Setting.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });
    }

    public void mostrarDatos() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Noticias")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                Log.d(TAG, documentSnapshot.getId() + " => " + documentSnapshot.getData().get("Titulo"));
                                Noticia noticia = new Noticia();
                                noticia.setTitulo(documentSnapshot.getData().get("Titulo").toString());
                                noticia.setCuerpo(documentSnapshot.getData().get("Cuerpo").toString());
                                noticia.setImagen(documentSnapshot.getData().get("Imagen").toString());
                                noticia.setId(documentSnapshot.getId());
                                noticiaList.add(noticia);
                            }

                            putDataIntoReciclerView(noticiaList);
                        }
                    }
                });
    }


    private void putDataIntoReciclerView(List<Noticia> noticiaList) {
        AdaptadorNoticia adaptery = new AdaptadorNoticia(this, noticiaList, rol);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptery);
    }

    @Override
    public void onBackPressed() {

    }

}
