package com.dam2.m08.proyectocsmaresme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.dam2.m08.proyectocsmaresme.noticias.Home;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Login extends AppCompatActivity {

    private EditText usuario_login;
    private EditText contraseña_login;
    private TextView recuperaContraseña;
    private Button btn_login;
    private TextView enlaceRegistro;
    private final String TAG ="PROYECTO_CS_MARESME___LOGIN";
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String rol;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setTitle("Login");
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.azulinterfazlogin));
        setup();

    }
    private void setup(){

        recuperaContraseña = findViewById(R.id.recuperarContraseña);
        usuario_login = findViewById(R.id.usuario_login);
        contraseña_login = findViewById(R.id.contraseña_login);
        btn_login = findViewById(R.id.btn_login);
        enlaceRegistro = findViewById(R.id.enlaceRegistro);
        firebaseAuth = FirebaseAuth.getInstance();



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!usuario_login.getText().toString().isEmpty() && !contraseña_login.getText().toString().isEmpty()){
                    Log.d(TAG, "usuario: "+usuario_login.getText().toString() + "contraseña: "+ contraseña_login.getText().toString());
                    firebaseAuth.signInWithEmailAndPassword(usuario_login.getText().toString(),contraseña_login.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    db.collection("Usuarios").document(usuario_login.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            if (Objects.equals(documentSnapshot.getString("Rol"), "Administrador")){
                                               Toast.makeText(getApplicationContext(), "Bienvenido Administrador", Toast.LENGTH_SHORT).show();
                                               rol = "Administrador";
                                            } else if (Objects.equals(documentSnapshot.getString("Rol"), "Usuario")){
                                                Toast.makeText(getApplicationContext(), "Bienvenido Usuario", Toast.LENGTH_SHORT).show();
                                                rol = "Usuario";
                                            } else {
                                                Map<String, String> usuarios = new HashMap<>();
                                                usuarios.put("Rol","usuario");
                                                db.collection("Usuarios").document(usuario_login.getText().toString()).set(usuarios);
                                                rol = "Usuario";
                                                Toast.makeText(getApplicationContext(), "Bienvenido usuario", Toast.LENGTH_SHORT).show();
                                            }
                                            showHome(rol);
                                        }
                                    });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();

                                    showError(e.getMessage());
                                }
                            });
                }else showError("los campos no pueden estar vacios. Intentelo de nuevo!");
            }
        });

        enlaceRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
                finish();
            }
        });

        recuperaContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecuperarContraseña.class);
                startActivity(intent);
            }
        });
    }

    //metodo de inicio de sesion automatico

    public void showHome(String rol){
        SharedPreferences sharedPreferences = getSharedPreferences("Rol",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Rol", rol);
        editor.putString("usuario_email",usuario_login.getText().toString());
        editor.apply();
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
        finish();
    }

    private void showError(String mensaje){
        AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setTitle("Error");
        alert.setMessage(mensaje);
        alert.setCancelable(false);
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alert.create().show();
    }
}
