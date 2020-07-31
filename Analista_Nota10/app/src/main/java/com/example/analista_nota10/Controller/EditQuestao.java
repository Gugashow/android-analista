package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.analista_nota10.R;

public class EditQuestao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_questao);
    }
    public void buttonIrAddQuestoes (View view){
        Intent AddQuestoes = new Intent(this, AddQuestaoController.class);
        startActivity(AddQuestoes);
    }
}