package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.analista_nota10.Model.Discipline;
import com.example.analista_nota10.Model.Questions;
import com.example.analista_nota10.R;
import com.example.analista_nota10.Service.DisciplineService;

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

    public void onSearchQuestion(View view) {
        // TODO
    }
}