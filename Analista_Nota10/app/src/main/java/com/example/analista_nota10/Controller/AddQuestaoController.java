package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.analista_nota10.R;

public class AddQuestaoController extends AppCompatActivity {

    RadioButton radioAlternativaA, radioAlternativaB, radioAlternativaC, radioAlternativaD;
    EditText textRespostaA, textRespostaB, textRespostaC, textRespostaD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questao_controller);
    }
    public void buttonAddQuestao(View view){

        radioAlternativaA = findViewById(R.id.radioAlternativaA);
        radioAlternativaB = findViewById(R.id.radioAlternativaB);
        radioAlternativaC = findViewById(R.id.radioAlternativaC);
        radioAlternativaD = findViewById(R.id.radioAlternativaD);

        textRespostaA = findViewById(R.id.textRespostaA);
        textRespostaB = findViewById(R.id.textRespostaB);
        textRespostaC = findViewById(R.id.textRespostaC);
        textRespostaD = findViewById(R.id.textRespostaD);

        if (radioAlternativaA.isChecked()){
            Toast.makeText(this, "Alternativa correta A", Toast.LENGTH_LONG).show();
        }
        if (radioAlternativaB.isChecked()){
            Toast.makeText(this, "Alternativa correta B", Toast.LENGTH_LONG).show();
        }
        if (radioAlternativaC.isChecked()){
            Toast.makeText(this, "Alternativa correta C", Toast.LENGTH_LONG).show();
        }
        if (radioAlternativaD.isChecked()){
            Toast.makeText(this, "Alternativa correta D", Toast.LENGTH_LONG).show();
        }
    }
}