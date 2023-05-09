package com.dam2.m08.proyectocsmaresme.noticias;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.dam2.m08.proyectocsmaresme.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class    AddNoticia extends AppCompatActivity {
    private final static int GALLERY_REQ_CODE = 100;
    private Button enviarButton;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText addNoticiaTitulo;
    private EditText addNoticiaContenido;
    private ProgressBar progressBar;
    private Button elegirFoto;
    private FirebaseStorage storage = FirebaseStorage.getInstance("gs://proyecto-cs-maresme.appspot.com/");
    private String titulo;
    private String cuerpo;
    private ImageView imageView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_noticia);
        addNoticiaTitulo = findViewById(R.id.addNoticiaTitulo);
        addNoticiaContenido = findViewById(R.id.addNoticiaContenido);
        cuerpo = addNoticiaContenido.getText().toString();
        enviarButton = findViewById(R.id.subirButtonNoticia);
        progressBar = findViewById(R.id.progressBarAddNoticia);
        progressBar.setVisibility(View.INVISIBLE);
        elegirFoto = findViewById(R.id.subirFotoAddNoticia);
        imageView = findViewById(R.id.volver);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.azuloscurointerfaz));
        elegirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titulo = addNoticiaTitulo.getText().toString();
                if (titulo.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Agrega un titulo de noticia para continuar", Toast.LENGTH_SHORT).show();
                } else {
                    Intent gallery = new Intent(Intent.ACTION_PICK);
                    gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(gallery, GALLERY_REQ_CODE);
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                progressBar.setVisibility(View.VISIBLE);
                ObjectAnimator.ofInt(progressBar, "progress", 100)
                        .setDuration(500)
                        .start();
                ObjectAnimator objectAnimator = ObjectAnimator.ofInt(progressBar, "progress", 100);
                objectAnimator.setDuration(500);
                objectAnimator.start();
                enviarButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri imageUri = data.getData();
                        StorageReference imagesRef = storage.getReference().child("/" + titulo);
                        UploadTask uploadTask = imagesRef.putFile(imageUri);
                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                imagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        System.out.println(uri);
                                        Map<String, String> noticia = new HashMap<>();
                                        cuerpo = addNoticiaContenido.getText().toString();
                                        if (titulo.isEmpty() || cuerpo.isEmpty()) {
                                            Toast.makeText(getApplicationContext(), "No puede dejar campos vacios", Toast.LENGTH_SHORT).show();
                                        } else {
                                            noticia.put("Titulo", titulo);
                                            noticia.put("Imagen", String.valueOf(uri));
                                            noticia.put("Cuerpo", cuerpo);
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
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Ha ocurrido un problema", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        }
    }
}
