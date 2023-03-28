package com.dam2.m08.proyectocsmaresme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity {

    private EditText usuario_register;
    private EditText contraseña_register;
    private Button btn_register;
    private final String TAG = "PROYECTO_CS_MARESME___REGISTER";
    private Button btnVolverAtras;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        usuario_register = findViewById(R.id.usuario_register);
        contraseña_register = findViewById(R.id.contraseña_register);
        btn_register = findViewById(R.id.btn_register);
        btnVolverAtras = findViewById(R.id.volverAtras);
        setTitle("Register");
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.azulinterfazlogin));
        setup();
        Log.d(TAG, "onCreate: ");
    }

    private void setup() {

        btn_register.setOnClickListener(view -> {

            if (!usuario_register.getText().toString().isEmpty() && !contraseña_register.getText().toString().isEmpty()) {
                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(usuario_register.getText().toString(), contraseña_register.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (!task.isSuccessful()) {
                                showError(task.getException().getMessage());
                                Log.d(TAG, "getexception: ");
                            } else {
                                Intent i = new Intent(getApplicationContext(), Login.class);
                                startActivity(i);
                                finish();
                                Log.d(TAG, "onComplete: ");
                            }
                        });
            }else showError("Los campos no pueden estar vacios");
        });


        btnVolverAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showError (String mensaje){
        AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setTitle("Error");
        alert.setMessage(mensaje);
        alert.setCancelable(false);
        alert.setPositiveButton("ok", (dialogInterface, i) -> {

        });
        alert.create().show();
    }

}


