package com.dam2.m08.proyectocsmaresme;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatAnonimo extends AppCompatActivity {
    private final String TAG = "PROYECTO_CS_MARESME___CHATANONIMO";
    private RecyclerView rvComentario;
    private Spinner spinner;
    private AdaptadorComentarioAnonimo adaptadorComentarioAnonimo;
    public static List<Comentario> listaComentarios;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatanonimo);


        Toast.makeText(this, "CHAT", Toast.LENGTH_SHORT).show();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        cargaDatos();
        listaComentarios = new ArrayList<>();
        rvComentario = findViewById(R.id.rv_comentarios);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvComentario.setLayoutManager(linearLayoutManager);
        cargaComentarios();

        adaptadorComentarioAnonimo =  new AdaptadorComentarioAnonimo(getApplicationContext(), listaComentarios);
        rvComentario.setAdapter(adaptadorComentarioAnonimo);

        spinner = findViewById(R.id.spinner_filtro);
        String categorias = String.valueOf(R.array.categorias_chatanonimo_spinner);
        spinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Collections.singletonList(categorias)));


        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.chat);

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
                        overridePendingTransition(0, 0);
                }
                return false;
            }
        });

    }

    private void cargaComentarios() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Comentarios").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot comentario: queryDocumentSnapshots) {

                    int id = Integer.parseInt(comentario.getString("id"));
                    String nombre = comentario.getString("nombre");
                    String titulo = comentario.getString("titulo");
                    String contenido = comentario.getString("contenido");
                    String fecha = comentario.getString("fecha");
                    listaComentarios.add(new Comentario(id,nombre,titulo,contenido,fecha));
                    adaptadorComentarioAnonimo.notifyItemRangeChanged(listaComentarios.size(),10);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }
}
