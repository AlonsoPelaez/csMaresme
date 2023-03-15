package com.dam2.m08.proyectocsmaresme.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dam2.m08.proyectocsmaresme.R;
import com.dam2.m08.proyectocsmaresme.settings.Setting;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Calificanos extends AppCompatActivity {

    private EditText editTextOpinion;
    private Button SendButton;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calificanos);
        Toast.makeText(this, "Calificanos", Toast.LENGTH_SHORT).show();
        editTextOpinion = findViewById(R.id.editTextOpinion);
        SendButton = findViewById(R.id.sendButton);

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
            Map<String, String> document = new HashMap<>();
            RatingBar ratingBar = findViewById(R.id.ratingBar);
            String firebaseUser = FirebaseAuth.getInstance().getCurrentUser().getEmail();

            document.put("Rating", ratingBar.getRating() +"/5.0");
            document.put("Opinion", editTextOpinion.getText().toString());
            document.put("Sent By", firebaseUser);
            String dname = new String();
            for (int i = 0; i < 20; i++) {
                Random r = new Random();
                int rand = new Random().nextInt(52);
                char start = (rand < 26) ? 'A' : 'a';
                char c = (char) (start + rand % 26);

                dname = dname + c;
            }
            db.collection("Opinions").document(dname).set(document);

        }
    }


