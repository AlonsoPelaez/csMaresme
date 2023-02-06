package com.dam2.m08.proyectocsmaresme;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private EditText usuario_register;
    private EditText contraseña_register;
    private Button btn_register;
    private final String TAG ="PROYECTO_CS_MARESME___REGISTER";
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
                                    if (!task.isSuccessful()){
                                        showError(task.getException().getMessage());
                                        Log.d(TAG, "task---: " +task.getException().getMessage());
                                        Log.d(TAG, "task---: " +task.getException().getLocalizedMessage());
                                        Log.d(TAG, "task---: " +task.getException().getCause());
                                        Log.d(TAG, "task---: " +task.getException().getSuppressed());
                                        Log.d(TAG, "task---: " +task.getException().getStackTrace());
                                    }
                                    else {
                                        Intent intent = new Intent(Register.this, Login.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                }
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
                Intent intent = new Intent(Register.this, Register.class);
                startActivity(intent);
            }
        });
        alert.create().show();
    }
}
