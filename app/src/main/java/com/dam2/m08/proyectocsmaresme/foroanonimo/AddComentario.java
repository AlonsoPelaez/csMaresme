package com.dam2.m08.proyectocsmaresme.foroanonimo;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dam2.m08.proyectocsmaresme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AddComentario extends AppCompatActivity {
    private Spinner spinner;
    private Button btn_aceptar;
    private Button btn_cancelar;
    private EditText titulo;
    private EditText contenido;
    private String idCategoria;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final String TAG ="CS_MARESME_CHATANONIMO";
    private String rol;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcomentario);
        Intent intent = getIntent();
        rol = intent.getStringExtra("Rol");
        titulo = findViewById(R.id.edtxt_Titulo);
        contenido = findViewById(R.id.edtxt_Contenido);

        btn_aceptar = findViewById(R.id.btn_aceptar_formulario_add_comentario);
        btn_cancelar = findViewById(R.id.btn_cancelar_formulario_add_comentario);
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!titulo.getText().equals("") || !contenido.getText().equals("")){
                    String time = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(new Date());

                    HashMap map = new HashMap();
                    map.put("categoria",idCategoria);
                    map.put("titulo",titulo.getText());
                    map.put("contenido",contenido.getText());
                    map.put("nombre", "Anonimo");
                    map.put("fecha", time);


                    db.collection("Comentarios").document().set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(getApplicationContext(), ChatAnonimo.class);
                                startActivity(intent);
                            }
                            else {
                                Log.d(TAG, "error: "+ task.getException().getMessage());
                            }
                        }
                    });
                }
            }
        });
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatAnonimo.class);
                startActivity(intent);
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int ancho = displayMetrics.widthPixels;
        int alto = displayMetrics.heightPixels;

        getWindow().setLayout((int) (ancho * 0.85), (int)(alto * 0.80));




        //spinner
        spinner = findViewById(R.id.spinner_filtro);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categorias_chatanonimo_spinner, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               idCategoria = (String) parent.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
