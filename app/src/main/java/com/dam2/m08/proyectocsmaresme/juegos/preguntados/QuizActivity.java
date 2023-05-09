package com.dam2.m08.proyectocsmaresme.juegos.preguntados;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.dam2.m08.proyectocsmaresme.R;

import java.util.Objects;

public class QuizActivity extends AppCompatActivity {

    public static final String CORRECT_ANSWER = "correct_answer";
    public static final String CURRENT_QUESTION = "current_question";
    public static final String ANSWER_IS_CORRECT = "answer_is_correct";
    public static final String ANSWER = "answer";

    private int ids_answers[] = {
            R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4
    };
    private String[] questions;

    private TextView text_question;
    private RadioGroup group;
    private Button btn_next, btn_prev;

    private int correct_answer;
    private int current_question;
    private boolean[] answer_is_correct;
    private int[] answer;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i("lifecycle", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putInt(CORRECT_ANSWER, correct_answer);
        outState.putInt(CURRENT_QUESTION, current_question);
        outState.putBooleanArray(ANSWER_IS_CORRECT, answer_is_correct);
        outState.putIntArray(ANSWER, answer);
    }

    @Override
    protected void onStop() {
        Log.i("lifecycle", "onStop");
        super.onStop();
    }

    @Override
    protected void onStart() {
        Log.i("lifecycle", "onStart");
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        Log.i("lifecycle", "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("lifecycle", "onCreate");
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.azuloscurointerfaz));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent = getIntent();
        String eleccion = intent.getStringExtra("election");

        if (Objects.equals(eleccion, "history")){
            questions = new String[]{"¿En que fecha se lanzo el primer IPhone?;29 de Julio de 2007;*29 de Junio de 2007;25 de Noviembre de 2008; Ninguna de las anteriores",
                    "¿Dónde y cuándo se inventó la pólvora?;En Estados Unidos en el siglo XIX;*En China en el siglo IX;En Francia en el siglo XVII;Ninguna de las anteriores",
                    "¿Cuánto tiempo duró la Guerra Fría entre Estados Unidos y la Unión Soviética?;100 años, ente 1899 y 1999;*45 años, entre 1946 y 1991;15 años, entre 1929 y 1944;Ninguna de las anteriores"};
        } else if (Objects.equals(eleccion, "science")){
            questions = new String[]{"¿Cual es el compuesto quimico del agua?;*H²O;HO²;HO;Ninguna de las anteriores;Ninguna de las anteriores",
                    "¿De qué color es la sangre de los peces?;Marrón oscuro;*Rojo;Azul;Ninguna de las anteriores",
                    "¿De donde de saca la sacarina?;Del azúcar;*Del carbón;Del aceite de girasol;Ninguna de las anteriores"
            };
        } else if (Objects.equals(eleccion, "art")){
            questions = new String[]{
                    "¿En que año nacio Leonardo da Vinci?;*15 de Abril de 1452;15 de Junio de 1454;23 de Agosto de 1912;Ninguna de las anteriores;Ninguna de las anteriores",
                    "¿Qué describe una prosopografía?;*El físico de una persona;El fisico y el carácter de una persona;El carácter de una persona;Ninguna de las anteriores",
                    "¿Quién pintó La Capilla Sixtina?;Giorgio Vasari;Leonardo Da Vinci;*Miguel Ángel;Ninguna de las anteriores"
            };
        }

        text_question = (TextView) findViewById(R.id.text_question);
        group = (RadioGroup) findViewById(R.id.answer_group);
        btn_next = (Button) findViewById(R.id.btn_check);
        btn_prev = (Button) findViewById(R.id.btn_prev);


        if (savedInstanceState == null) {
            startOver();
        } else {
            Bundle state = savedInstanceState;
            correct_answer = state.getInt(CORRECT_ANSWER);
            current_question = state.getInt(CURRENT_QUESTION);
            answer_is_correct = state.getBooleanArray(ANSWER_IS_CORRECT);
            answer = state.getIntArray(ANSWER);
            showQuestion();
        }

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                if (current_question < questions.length-1) {
                    current_question++;
                    showQuestion();
                } else {
                    checkResults();
                }
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                if (current_question > 0) {
                    current_question--;
                    showQuestion();
                }
            }
        });
    }

    private void startOver() {
        answer_is_correct = new boolean[questions.length];
        answer = new int[questions.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = -1;
        }
        current_question = 0;
        showQuestion();
    }

    private void checkResults() {
        int correctas = 0, incorrectas = 0, nocontestadas = 0;
        for (int i = 0; i < questions.length; i++) {
            if (answer_is_correct[i]) correctas++;
            else if (answer[i] == -1) nocontestadas++;
            else incorrectas++;
        }

        String message =
                String.format("Correctas: %d\nIncorrectas: %d\nNo contestadas: %d\n",
                        correctas, incorrectas, nocontestadas);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.results);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.finish, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton(R.string.start_over, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startOver();
            }
        });
        builder.create().show();
    }

    private void checkAnswer() {
        int id = group.getCheckedRadioButtonId();
        int ans = -1;
        for (int i = 0; i < ids_answers.length; i++) {
            if (ids_answers[i] == id) {
                ans = i;
            }
        }
        answer_is_correct[current_question] = (ans == correct_answer);
        answer[current_question] = ans;
    }

    private void showQuestion() {
        String q = questions[current_question];
        String[] parts = q.split(";");

        group.clearCheck();

        text_question.setText(parts[0]);
        for (int i = 0; i < ids_answers.length; i++) {
            RadioButton rb = (RadioButton) findViewById(ids_answers[i]);
            String ans = parts[i+1];
            if (ans.charAt(0) == '*') {
                correct_answer = i;
                ans = ans.substring(1);
            }
            rb.setText(ans);
            if (answer[current_question] == i) {
                rb.setChecked(true);
            }
        }
        if (current_question == 0) {
            btn_prev.setVisibility(View.GONE);
        } else {
            btn_prev.setVisibility(View.VISIBLE);
        }
        if (current_question == questions.length-1) {
            btn_next.setText(R.string.finish);
        } else {
            btn_next.setText(R.string.next);
        }
    }
}
