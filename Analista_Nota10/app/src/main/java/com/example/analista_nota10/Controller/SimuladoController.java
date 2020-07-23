package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.analista_nota10.R;

public class SimuladoController extends AppCompatActivity {

    TextView mostrarRespostaA, mostrarRespostaB, mostrarRespostaC, mostrarRespostaD;
    RadioButton capturarAlternativaA, capturarAlternativaB, capturarAlternativaC, capturarAlternativaD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulado);
    }
    public void buttonAfterSimulado (View view){
        mostrarRespostaA = findViewById(R.id.mostrarRespostaA);
        mostrarRespostaB = findViewById(R.id.mostrarRespostaB);
        mostrarRespostaC = findViewById(R.id.mostrarRespostaC);
        mostrarRespostaD = findViewById(R.id.mostrarRespostaD);

        capturarAlternativaA = findViewById(R.id.capturarAlternativaA);
        capturarAlternativaB = findViewById(R.id.capturarAlternativaB);
        capturarAlternativaC = findViewById(R.id.capturarAlternativaC);
        capturarAlternativaD = findViewById(R.id.capturarAlternativaD);
    }

    public void buttonBeforeSimulado (View view){

    }
    public void buttonSair (View view){
        Intent Menu = new Intent(this, MenuController.class);
        startActivity(Menu);
    }
}