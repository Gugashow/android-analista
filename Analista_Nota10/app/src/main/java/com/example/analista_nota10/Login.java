package com.example.analista_nota10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void botaoAcessar (View view){
        BancoController db = new BancoController(getBaseContext());
        Toast.makeText(this, "Olá estou funcionando", Toast.LENGTH_SHORT).show();

    }
    public void linkCadastrar (View view){
        Intent Cadastro = new Intent(this, Cadastro.class);
        startActivity(Cadastro);
    }
}