package com.dam2.m08.proyectocsmaresme;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class Login extends AppCompatActivity {

    private EditText usuario_login;
    private EditText contraseña_login;
    private Button btn_login;
    private TextView enlaceRegistro;
    private final String TAG ="PROYECTO_CS_MARESME___LOGIN";
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setTitle("Login");
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.azulInterfaz));


        usuario_login = findViewById(R.id.usuario_login);
        contraseña_login = findViewById(R.id.contraseña_login);
        btn_login = findViewById(R.id.btn_login);
        enlaceRegistro = findViewById(R.id.enlaceRegistro);
        enlaceRegistro.setText("¿Ya tienes una cuenta? Registrate");
        firebaseAuth = FirebaseAuth.getInstance();
        setup();
    }
    private void setup(){


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!usuario_login.getText().toString().isEmpty() && !contraseña_login.getText().toString().isEmpty()){
                    Log.d(TAG, "usuario: "+usuario_login.getText().toString() + "contraseña: "+ contraseña_login.getText().toString());
                    firebaseAuth.signInWithEmailAndPassword(usuario_login.getText().toString(),contraseña_login.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Intent intent = new Intent(getApplicationContext(), Home.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "El usuario o contraseña no son correctos", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        enlaceRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }
    private void showError(String mensaje){
        AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setTitle("Error");
        alert.setMessage(mensaje);
        alert.setCancelable(false);
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Login.this, Login.class);
                startActivity(intent);
            }
        });
        alert.create().show();
    }
}
