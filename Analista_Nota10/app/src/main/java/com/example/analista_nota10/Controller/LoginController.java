package com.example.analista_nota10.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.analista_nota10.R;
import com.example.analista_nota10.Service.LoginService;

public class LoginController extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void buttonLogin(View view){
        LoginService service = new LoginService(getBaseContext());


        TextView name = (TextView)findViewById(R.id.nameUser);
        TextView password = (TextView)findViewById((R.id.passUser));
        String nameUser = name.getText().toString();
        String passUser = password.getText().toString();

        String erro = service.login(nameUser, passUser);

        if(erro.isEmpty()){
            Intent Menu = new Intent(this, MenuController.class);
            startActivity(Menu);
        }else{
            Toast.makeText(getApplicationContext(), erro, Toast.LENGTH_LONG).show();
        }
    }

    public void linkCadastrar (View view){
        Intent CadastroController = new Intent(this, RegisterController.class);
        startActivity(CadastroController);
    }
}