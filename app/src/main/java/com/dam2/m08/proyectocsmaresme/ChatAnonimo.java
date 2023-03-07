package com.dam2.m08.proyectocsmaresme;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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
    private AdaptadorComentario adaptadorComentario;
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

        adaptadorComentario =  new AdaptadorComentario(listaComentarios);
        rvComentario.setAdapter(adaptadorComentario);
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

    private void cargaComentarios() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Comentarios").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot comentario: queryDocumentSnapshots) {
//                    int id = Integer.parseInt(comentario.getString("id"));
                    String nombre = comentario.getString("nombre");
                    String titulo = comentario.getString("titulo");
                    String contenido = comentario.getString("contenido");
                    String fecha = comentario.getString("fecha");
                    listaComentarios.add(new Comentario(13456,nombre,titulo,contenido,fecha));
                    adaptadorComentario.notifyItemRangeChanged(listaComentarios.size(),10);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

    public class AdaptadorComentario extends RecyclerView.Adapter<AdaptadorComentario.AdaptadorComentarioHolder> implements View.OnClickListener {

        List<Comentario> lista;
        private View.OnClickListener listener;
        public AdaptadorComentario(List<Comentario> listaCo){
            this.lista = listaCo;
        }
        @Override
        public void onClick(View view) {
            if (listener!=null){
                listener.onClick(view);
            }
        }

        @NonNull
        @Override
        public AdaptadorComentario.AdaptadorComentarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = getLayoutInflater().inflate(R.layout.comentario_card, parent, false);
            view.setOnClickListener(this);
            Log.d(TAG, "onCreateViewHolder: ");
            return new AdaptadorComentarioHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorComentarioHolder adaptadorComentarioHolder, int position) {
            adaptadorComentarioHolder.imprimir(position);

        }

        @Override
        public int getItemCount() {
            return lista.size();
        }
        public void setOnClickListener(View.OnClickListener listener){
            this.listener = listener;
        }

        class AdaptadorComentarioHolder extends RecyclerView.ViewHolder{
            TextView tvTitulo, tvNombre, tvContenido, tvFecha;
            ImageView imageUser;

            public AdaptadorComentarioHolder(@NonNull View itemView) {
                super(itemView);
                Log.d(TAG, "AdaptadorComentarioHolder: ");
                tvTitulo = itemView.findViewById(R.id.cvTitulo);
                tvNombre = itemView.findViewById(R.id.cvNombre);
                tvContenido = itemView.findViewById(R.id.cvContenido);
                tvFecha = itemView.findViewById(R.id.cvFecha);
//                imageUser = itemView.findViewById(R.id.imageUser);

            }
            public void imprimir (int i){
                Log.d(TAG, "imprimir: ");
                tvTitulo.setText(""+ lista.get(i).getTitulo());
                tvNombre.setText(""+lista.get(i).getNombre());
                tvContenido.setText(""+lista.get(i).getContenido());
                tvFecha.setText(""+lista.get(i).getFecha());
//                Picasso.get().load(listaPeliculas.get(i).getPoster_path()).into(imageUser);0
            }
        }
    }
}
