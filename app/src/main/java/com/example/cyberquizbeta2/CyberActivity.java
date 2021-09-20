package com.example.cyberquizbeta2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cyberquizbeta2.databinding.ActivityCyber2Binding;
import com.example.cyberquizbeta2.databinding.ActivityCyberBinding;

import java.util.ArrayList;

public class CyberActivity extends AppCompatActivity {

    ActivityCyberBinding binding;

    ArrayList<Pregunta> questions;
    int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCyberBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_cyber);

        questions = new ArrayList<>();

        questions.add(new Pregunta("Quien fue Pitagoras?",
                "Un profesor", "Un sicario", "Un astronauta", "Un matematico","Un matematico" ));
        questions.add(new Pregunta("Quien fue Pitagoras?",
                "Un profesor", "Un sicario", "Un astronauta", "Un matematico","Un matematico" ));
    }

    void setNextQuestion() {
        if(index < questions.size()) {
            Pregunta question = questions.get(index);
            binding.Pregunta.setText(question.getPregunta());
            binding.opcion1.setText(question.getOpcion1());
            binding.opcion2.setText(question.getOpcion2());
            binding.opcion3.setText(question.getOpcion3());
            binding.opcion4.setText(question.getOpcion4());
        }
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.avanzarBt:
                index++;
                setNextQuestion();
                break;
        }

    }
}