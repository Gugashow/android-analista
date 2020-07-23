package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.analista_nota10.R;

public class MenuController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void buttonDisciplina (View view){
        Intent DisciplinaController = new Intent(this, AddDisciplineController.class);
        startActivity(DisciplinaController);
    }
    public void buttonQuestao (View view){
        Intent QuestaoController = new Intent(this, AddQuestaoController.class);
        startActivity(QuestaoController);
    }
    public void buttonGerarSimulado (View view){
        Intent SimuladoController = new Intent(this, SimuladoController.class);
        startActivity(SimuladoController);
    }
}