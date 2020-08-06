package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.analista_nota10.Model.Login;
import com.example.analista_nota10.R;
import com.example.analista_nota10.Service.LoginService;
import com.example.analista_nota10.Singleton;

public class LoginAdmController extends AppCompatActivity {
    public static final String ADMIN = "admin";
    private EditText nameUserAdm;
    private EditText passUserAdm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_adm);
    }
    public void buttonAcessarAdm (View view) {
        LoginService service = new LoginService(getBaseContext());
        Login login = new Login();

        nameUserAdm = (EditText) findViewById(R.id.userAdm);
        passUserAdm = (EditText) findViewById(R.id.passAdm);

        login.setNameUser(nameUserAdm.getText().toString());
        login.setPasswordUser(passUserAdm.getText().toString());

        if(login.getNameUser().isEmpty() || login.getPasswordUser().isEmpty()){
            Toast.makeText(getApplicationContext(), "Preencha ambos os campos", Toast.LENGTH_LONG).show();
            return;
        }

        // Recovering user
        Singleton.getInstance().login = login;

        String result = service.login(login);

        if(result.isEmpty()){

            if(login.getNameUser().equals(ADMIN)){
                Intent Menu = new Intent(this, MenuAdmController.class);
                startActivity(Menu);
            }
        }else {
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }
    }
}
