package com.dam2.m08.proyectocsmaresme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Report extends AppCompatActivity {

    private EditText editTextReport;
    private Button SendButton;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        Toast.makeText(this, "Report", Toast.LENGTH_SHORT).show();



        SendButton = findViewById(R.id.SendButton);

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                try {
                    SaveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(getApplicationContext(), Setting.class));
            }
        });
    }
    public void SaveData() throws IOException {
        EditText editTextTitle = findViewById(R.id.editTextOpinion);
        Map<String, String> document = new HashMap<>();

        document.put("title", editTextTitle.getText().toString());
        String dname = new String();
        for (int i = 0; i < 20; i++) {
            Random r = new Random();
            int rand = new Random().nextInt(52);
            char start = (rand < 26) ? 'A' : 'a';
            char c = (char) (start + rand % 26);

            dname = dname + c;
        }
        db.collection("Reportes").document(dname).set(document);

    }




}