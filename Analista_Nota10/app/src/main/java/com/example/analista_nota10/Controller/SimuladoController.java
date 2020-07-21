package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.analista_nota10.R;

public class SimuladoController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulado);
    }
    public void buttonDisciplina (View view){
        Intent DisciplinaController = new Intent(this, DisciplinaController.class);
        startActivity(DisciplinaController);
    }
    public void buttonQuestao (View view){
        Intent QuestaoController = new Intent(this, QuestaoController.class);
        startActivity(QuestaoController);
    }
}