package com.example.cyberquizbeta2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyberquizbeta2.databinding.ActivityCyberBinding;
import com.example.cyberquizbeta2.databinding.ActivityCyberBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;

public class CyberActivity extends AppCompatActivity {

    ActivityCyberBinding binding;

    ArrayList<Pregunta> questions;
    int index = 0;
    Pregunta question;
    CountDownTimer timer;
    FirebaseFirestore database;
    int correctAnswers = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCyberBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_cyber);

        questions = new ArrayList<>();
        database = FirebaseFirestore.getInstance();

        final String categoryId = getIntent().getStringExtra("categoryId");

        Random random = new Random();
        final int rand = random.nextInt(12);

        database.collection("categories")
                .document(categoryId)
                .collection("questions")
                .whereGreaterThanOrEqualTo("index", rand)
                .orderBy("index")
                .limit(5).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(queryDocumentSnapshots.getDocuments().size() < 5) {
                    database.collection("categories")
                            .document(categoryId)
                            .collection("questions")
                            .whereLessThanOrEqualTo("index", rand)
                            .orderBy("index")
                            .limit(5).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for(DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                Pregunta question = snapshot.toObject(Pregunta.class);
                                questions.add(question);

                        }
                    };
                }); else {
                    for(DocumentSnapshot snapshot : queryDocumentSnapshots) {
                        Pregunta question = snapshot.toObject(Pregunta.class);
                        questions.add(question);

                    }
                    setNextQuestion();
                }

            }
        });
        resetTimer();

    });

    void resetTimer(); {
        timer = new CountDownTimer(25000, 1000) {
            @Override
            public void onTick(long l) {
                binding.tiempo.setText(String.valueOf(l/1000));

            }

            @Override
            public void onFinish() {

            }
        };

    }

    void showAnswer() {
        if(question.getRespuesta().equals(binding.opcion1.getText().toString()))
            binding.opcion1.setBackground(getResources().getDrawable(R.drawable.option_right));
        else if(question.getRespuesta().equals(binding.opcion2.getText().toString()))
            binding.opcion2.setBackground(getResources().getDrawable(R.drawable.option_right));
        else if(question.getRespuesta().equals(binding.opcion3.getText().toString()))
            binding.opcion3.setBackground(getResources().getDrawable(R.drawable.option_right));
        else if(question.getRespuesta().equals(binding.opcion4.getText().toString()))
            binding.opcion4.setBackground(getResources().getDrawable(R.drawable.option_right));
    }

    void setNextQuestion() {
        if(timer != null)
            timer.cancel();

        timer.start();
        if(index < questions.size()) {
            binding.Contador.setText(String.format("%d/%d", (index+1), questions.size()));
            question = questions.get(index);
            binding.Pregunta.setText(question.getPregunta());
            binding.opcion1.setText(question.getOpcion1());
            binding.opcion2.setText(question.getOpcion2());
            binding.opcion3.setText(question.getOpcion3());
            binding.opcion4.setText(question.getOpcion4());
        }
    }

    void checkAnswer(TextView textView){
        String selectedAnswer = textView.getText().toString();
        if(selectedAnswer.equals(question.getRespuesta())) {
            correctAnswers++;
        }
            textView.setBackground(getResources().getDrawable(R.drawable.option_right));
        } else {
            showAnswer();
            textView.setBackground(getResources().getDrawable(R.drawable.option_wrong));
        }

    }

    void reset() {
        binding.opcion1.setBackground(getResources().getDrawable(R.drawable.option_unseleccted));
        binding.opcion2.setBackground(getResources().getDrawable(R.drawable.option_unseleccted));
        binding.opcion3.setBackground(getResources().getDrawable(R.drawable.option_unseleccted));
        binding.opcion4.setBackground(getResources().getDrawable(R.drawable.option_unseleccted));

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.opcion_1:
            case R.id.opcion_2:
            case R.id.opcion_3:
            case R.id.opcion_4:
                if(timer != null)
                    timer.cancel();

                TextView selected = (TextView) view;
                checkAnswer(selected);

                break;

            case R.id.avanzarBt:
                reset();
                if(index <= questions.size()) {
                    index++;
                    setNextQuestion();
                } else {
                    Intent intent = new Intent(CyberActivity.this, ResultActivity.class);
                    intent.putExtra("correct", correctAnswers);
                    intent.putExtra("total", questions.size());
                    startActivity(intent);
                    Toast.makeText(this, "Quiz Terminado.", Toast.LENGTH_SHORT).show();
                }

                break;
                }

    }
}