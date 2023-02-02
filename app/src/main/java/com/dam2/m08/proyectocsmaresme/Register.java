package com.dam2.m08.proyectocsmaresme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private EditText usuario_register;
    private EditText contraseña_register;
    private Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        setTitle("Register");

        setup();
    }

    private void setup() {
        usuario_register = findViewById(R.id.usuario_register);
        contraseña_register = findViewById(R.id.contraseña_register);
        btn_register = findViewById(R.id.btn_register);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!usuario_register.getText().toString().isEmpty() && !contraseña_register.getText().toString().isEmpty()){
                    FirebaseAuth.getInstance()
                            .createUserWithEmailAndPassword(usuario_register.getText().toString(), contraseña_register.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        //funcion o intent para enviar al login y que se logue el usuario recien creado
                                        Intent intent = new Intent(Register.this, Login.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        //muestra el error en un toast o un dialog
                                    }
                                }
                            });
                }
            }
        });
    }
}
