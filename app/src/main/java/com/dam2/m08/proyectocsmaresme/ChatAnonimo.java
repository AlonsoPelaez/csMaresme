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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;

public class ChatAnonimo extends AppCompatActivity {
    private final String TAG = "PROYECTO_CS_MARESME___CHATANONIMO";
    private RecyclerView rvComentario;
//    private AdaptadorComentario adaptadorComentario;
    private TextView nombreAnonimo;
    private TextView titulo;
    private TextView contenido;
    private TextView fecha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comentarioanonimo);
//        setContentView(R.layout.chatanonimo);
        Log.d(TAG, "onCreateOptionsMenu: ");

        Toast.makeText(this, "CHAT", Toast.LENGTH_SHORT).show();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        cargaDatos();
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

    private void cargaDatos() {
        nombreAnonimo = findViewById(R.id.cvNombre);
        titulo = findViewById(R.id.cvTitulo);
        contenido = findViewById(R.id.cvContenido);
        fecha = findViewById(R.id.cvFecha);
        Log.d(TAG, "cargaDatos: ");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Comentarios").document("ncbV1D407OEmVODsZgth").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "onComplete: "+ task.getResult().getData());
                    nombreAnonimo.setText(task.getResult().getString("nombre"));
                    titulo.setText(task.getResult().getString("titulo"));
                    contenido.setText(task.getResult().getString("comentario"));
//                    fecha.setText(task.getResult().getString("fecha"));
                    fecha.setText(task.getResult().getString("fecha"));
                }
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
