package com.example.analista_nota10.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.analista_nota10.Model.Login;
import com.example.analista_nota10.R;
import com.example.analista_nota10.Service.LoginService;
import com.example.analista_nota10.Singleton;

public class LoginController extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void buttonLogin(View view){
        LoginService service = new LoginService(getBaseContext());
        Login login = new Login();


        TextView name = (TextView)findViewById(R.id.nameUser);
        TextView password = (TextView)findViewById((R.id.passUser));

        login.setNameUser(name.getText().toString());
        login.setPasswordUser(password.getText().toString());

        if(login.getNameUser().isEmpty() || login.getPasswordUser().isEmpty()){
            Toast.makeText(getApplicationContext(), "Preencha ambos os campos", Toast.LENGTH_LONG).show();
            return;
        }

        Singleton.getInstance().login = login;
        String erro = service.login(login);

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