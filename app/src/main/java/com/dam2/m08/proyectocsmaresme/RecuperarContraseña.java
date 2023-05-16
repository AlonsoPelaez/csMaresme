package com.dam2.m08.proyectocsmaresme;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dam2.m08.proyectocsmaresme.R;
import com.dam2.m08.proyectocsmaresme.juegos.Games;
import com.dam2.m08.proyectocsmaresme.juegos.snake.SnakeGame;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class RecuperarContraseña extends AppCompatActivity {
    private EditText edtx_email;
    private Button btn_recuperaContraseña;
    private Button btnBackToLogin;
    private FirebaseAuth mAuth;
    private String email="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recuperarpassword);

        mAuth = FirebaseAuth.getInstance();
        edtx_email = findViewById(R.id.edtxt_recuperaContraseña);
        btn_recuperaContraseña = findViewById(R.id.btn_recuperaContraseña);
        btnBackToLogin = findViewById(R.id.btnBackLogin);

        btn_recuperaContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtx_email.getText().toString();
                if (!email.isEmpty()){
                    resetPassword();
                }else{
                    showMessage("No ha sido posible el envio de del mensaje. VERIFIQUE SU CORREO!","Email no Enviado");
                }
            }
        });
        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });
    }
    private void showMessage(String message, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }


    private void resetPassword() {
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    showMessage("El correo ha sido enviado correctamente!","Correo Enviado");
                }else{
                    showMessage("No ha sido posible enviar el correo","Ha Ocurrido un error");
                }
            }
        });
    }

}
