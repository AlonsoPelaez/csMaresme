package com.dam2.m08.proyectocsmaresme;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dam2.m08.proyectocsmaresme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarContraseña extends AppCompatActivity {
    private EditText edtx_email;
    private Button btn_recuperaContraseña;
    private FirebaseAuth mAuth;
    private String email="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recuperarpassword);

        mAuth = FirebaseAuth.getInstance();
        edtx_email = findViewById(R.id.edtxt_recuperaContraseña);
        btn_recuperaContraseña = findViewById(R.id.btn_recuperaContraseña);

        btn_recuperaContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtx_email.getText().toString();
                if (!email.isEmpty()){
                    resetPassword();
                }else{
                    Toast.makeText(getApplicationContext(),"Debe de introducir un email",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void resetPassword() {
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Se ha enviado un correo para restablecer tu contraseña",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"No se pudo enviar el corre para restablecer tu contraseña",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
