package com.dam2.m08.proyectocsmaresme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AddNoticia extends AppCompatActivity {

    private Button enviarButton;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText addNoticiaTitulo;
    private  EditText addNoticiaContenido;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_noticia);
        addNoticiaTitulo = findViewById(R.id.addNoticiaTitulo);
        addNoticiaContenido = findViewById(R.id.addNoticiaContenido);
        enviarButton = findViewById(R.id.subirButtonNoticia);


        enviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> noticia = new HashMap<>();
                noticia.put("Titulo", addNoticiaTitulo.getText().toString());
                noticia.put("Imagen", "PRUEBA");
                noticia.put("Cuerpo", addNoticiaContenido.getText().toString());
                String dname = new String();
                for (int i = 0; i < 20; i++) {
                    int rand = new Random().nextInt(52);
                    char start = (rand < 26) ? 'A' : 'a';
                    char c = (char) (start + rand % 26);

                    dname = dname + c;
                }
                db.collection("Noticias").document(dname).set(noticia);
                startActivity(new Intent(getApplicationContext(), Home.class));
                finish();


            }
        });
    }
}
