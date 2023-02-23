package com.dam2.m08.proyectocsmaresme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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



        session();
        setup();

    }
    private void setup(){

        usuario_login = findViewById(R.id.usuario_login);
        contraseña_login = findViewById(R.id.contraseña_login);
        btn_login = findViewById(R.id.btn_login);
        enlaceRegistro = findViewById(R.id.enlaceRegistro);
        enlaceRegistro.setText("¿Ya tienes una cuenta? Registrate");
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
                                    showHome();
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
            }
        });
    }
//    private void limpiaCampos(){
//        usuario_login.setText("");
//        contraseña_login.setText("");
//    }

    //metodo de inicio de sesion automatico
    private void session(){
        SharedPreferences prefer= getSharedPreferences(getString(R.string.prefer_file), Context.MODE_PRIVATE);
        String usuario_email = prefer.getString("usuario_email",null);
        Log.d(TAG, "usuario_email: "+ usuario_email);

        if (usuario_email != null){
            Intent intent = new Intent(getApplicationContext(), Home.class);
            intent.putExtra("usuario_email",usuario_email);
            Log.d(TAG, "usuario_email: "+ usuario_email);
            startActivity(intent);
        }
    }

    public void showHome(){
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.putExtra("usuario_email",usuario_login.getText().toString());
        startActivity(intent);
    }

    private void showError(String mensaje){
        AlertDialog.Builder alert= new AlertDialog.Builder(getApplicationContext());
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
