package com.dam2.m08.proyectocsmaresme.foroanonimo;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


;
import com.dam2.m08.proyectocsmaresme.R;
import com.dam2.m08.proyectocsmaresme.juegos.Games;
import com.dam2.m08.proyectocsmaresme.noticias.Home;
import com.dam2.m08.proyectocsmaresme.settings.Setting;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.List;

public class ChatAnonimo extends AppCompatActivity {
    private final String TAG = "PROYECTO_CS_MARESME___CHATANONIMO";
    private RecyclerView rvComentario;
    private Spinner spinner;
    private ImageView btn_add_comentario;
    private AdaptadorComentarioAnonimo adaptadorComentarioAnonimo;
    public static List<Comentario> listaComentarios;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatanonimo);


        Toast.makeText(this, "CHAT", Toast.LENGTH_SHORT).show();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        cargaDatos();
        setTitle("");
        listaComentarios = new ArrayList<>();
        rvComentario = findViewById(R.id.rv_comentarios);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvComentario.setLayoutManager(linearLayoutManager);
        cargaComentarios();

        adaptadorComentarioAnonimo =  new AdaptadorComentarioAnonimo(getApplicationContext(), listaComentarios);
        rvComentario.setAdapter(adaptadorComentarioAnonimo);


        //spinner
        spinner = findViewById(R.id.spinner_filtro);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categorias_chatanonimo_spinner, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"se ha selecionado el item de "+parent.getSelectedItem() ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //btn_add_comentario
        btn_add_comentario = findViewById(R.id.btn_add_comentario);
        btn_add_comentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AggComentario.class);
                startActivity(intent);
            }
        });
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

    @Override
    public void onBackPressed() {

    }
}
