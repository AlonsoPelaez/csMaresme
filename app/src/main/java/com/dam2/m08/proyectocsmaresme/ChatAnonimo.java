package com.dam2.m08.proyectocsmaresme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

public class ChatAnonimo extends AppCompatActivity {
    private final String TAG = "PROYECTO_CS_MARESME___CHATANONIMO";
    private RecyclerView rvComentario;
//    private AdaptadorComentario adaptadorComentario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.comentarioanonimo);
        setContentView(R.layout.chatanonimo);
        Log.d(TAG, "onCreateOptionsMenu: ");

        Toast.makeText(this, "CHAT", Toast.LENGTH_SHORT).show();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        rvComentario = findViewById(R.id.rv_comentarios);
//
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//        rvComentario.setLayoutManager(linearLayoutManager);
//
//        adaptadorComentario =  new AdaptadorComentario();
//        rvComentario.setAdapter(adaptadorComentario);
        Log.d(TAG, "onCreate: ");


        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.chat);

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
                        overridePendingTransition(0, 0);
                }
                return false;
            }
        });

    }

//    public class AdaptadorComentario extends RecyclerView.Adapter<AdaptadorComentario.AdaptadorComentarioHolder> implements View.OnClickListener {
//
//        @Override
//        public void onClick(View view) {
//
//        }
//
//        @NonNull
//        @Override
//        public AdaptadorComentario.AdaptadorComentarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//            View view = getLayoutInflater().inflate(R.layout.comentario_card, parent, false);
//            view.setOnClickListener(this);
//            Log.d(TAG, "onCreateViewHolder: ");
//            return new AdaptadorComentarioHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull AdaptadorComentario.AdaptadorComentarioHolder holder, int position) {
//            holder.imprimir(position);
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return 0;
//        }
//
//        class AdaptadorComentarioHolder extends RecyclerView.ViewHolder{
//            TextView tvTitulo, tvNombre, tvContenido, tvFecha;
//            ImageView imageUser;
//
//            public AdaptadorComentarioHolder(@NonNull View itemView) {
//                super(itemView);
//                Log.d(TAG, "AdaptadorComentarioHolder: ");
//                tvTitulo = itemView.findViewById(R.id.cvTitulo);
//                tvNombre = itemView.findViewById(R.id.cvNombre);
//                tvContenido = itemView.findViewById(R.id.cvContenido);
//                tvFecha = itemView.findViewById(R.id.cvFecha);
//                imageUser = itemView.findViewById(R.id.imageUser);
//
//            }
//            public void imprimir (int i){
//                Log.d(TAG, "imprimir: ");
//                tvTitulo.setText("titulo");
//                tvNombre.setText("nombre");
//                tvContenido.setText("contenido");
//                tvFecha.setText("fecha");
////                Picasso.get().load(listaPeliculas.get(i).getPoster_path()).into(imageUser);
//            }
//        }
//    }
}
