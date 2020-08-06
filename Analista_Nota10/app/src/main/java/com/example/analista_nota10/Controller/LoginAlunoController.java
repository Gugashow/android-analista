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

public class LoginAlunoController extends AppCompatActivity {
    private TextView name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_aluno);
    }

    public void buttonLogin(View view){
        LoginService service = new LoginService(getBaseContext());
        Login login = new Login();


        name = (TextView)findViewById(R.id.nameUser);
        password = (TextView)findViewById((R.id.passUser));

        login.setNameUser(name.getText().toString());
        login.setPasswordUser(password.getText().toString());

        if(login.getNameUser().isEmpty() || login.getPasswordUser().isEmpty()){
            Toast.makeText(getApplicationContext(), "Preencha ambos os campos", Toast.LENGTH_LONG).show();
            return;
        }

        // Recovering user
        Singleton.getInstance().login = login;

        String result = service.login(login);

        if(result.isEmpty()){
            Intent Menu = new Intent(this, MenuAlunoController.class);
            startActivity(Menu);
        }else{
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }
    }
    public void linkCadastrar (View view){
        Intent RegisterController = new Intent(this, RegisterController.class);
        startActivity(RegisterController);
    }
    public void buttonTelaAdm (View view){
        Intent LoginAdmController = new Intent(this, LoginAdmController.class);
        startActivity(LoginAdmController);
    }
}