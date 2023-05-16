package com.dam2.m08.proyectocsmaresme;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class RecuperarContraseña extends AppCompatActivity {
    private EditText edtx_email;
    private Button btn_recuperaContraseña;
    private Button btnBackToLogin;
    private FirebaseAuth mAuth;
    private String email="";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

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
                    db.collection("Usuarios").document(email).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            resetPassword();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            showMessage("El email brindado NO existe", "Email invalido");
                        }
                    });
                }else{
                    showMessage("El campo Email no puede estar vacio","Email no Enviado");
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }


    private void resetPassword() {
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    showMessage("El correo ha sido enviado correctamente!","Correo Enviado");
                    edtx_email.setText("");
                }else{
                    showMessage("No ha sido posible enviar el correo","Email inexistente");
                }
            }
        });
    }

}
