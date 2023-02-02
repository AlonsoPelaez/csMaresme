package com.dam2.m08.proyectocsmaresme;

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

public class Login extends AppCompatActivity {

    private EditText usuario_login;
    private EditText contraseña_login;
    private Button btn_login;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setTitle("Login");

        setup();
    }
    private void setup(){
        usuario_login = findViewById(R.id.usuario_login);
        contraseña_login = findViewById(R.id.contraseña_login);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!usuario_login.getText().toString().isEmpty() && !contraseña_login.getText().toString().isEmpty()){
                    FirebaseAuth.getInstance()
                            .signInWithEmailAndPassword(usuario_login.getText().toString(), contraseña_login.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        //funcion o intent que manda al inicio de la app
                                    }
                                    else {
                                        //mostrar error si algo sale mal con un dialog o toast
                                    }
                                }
                            });
                }
            }
        });
    }
}
