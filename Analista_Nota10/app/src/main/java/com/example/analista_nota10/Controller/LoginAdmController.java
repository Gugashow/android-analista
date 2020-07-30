package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.analista_nota10.Model.Login;
import com.example.analista_nota10.R;
import com.example.analista_nota10.Service.LoginService;
import com.example.analista_nota10.Singleton;

public class LoginAdmController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_adm);
    }

    public void buttonAcessarAdm(View view){
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
        String result = service.login(login);

        if(result.isEmpty()){
            Intent MenuController = new Intent(this, com.example.analista_nota10.Controller.MenuController.class);
            startActivity(MenuController);
        }else{
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }
    }
}
