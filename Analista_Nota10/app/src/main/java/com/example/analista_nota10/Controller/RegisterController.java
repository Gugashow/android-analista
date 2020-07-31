package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.analista_nota10.Model.Login;
import com.example.analista_nota10.R;
import com.example.analista_nota10.Service.RegisterService;

public class RegisterController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
        public void createAccount(View view){
            RegisterService service = new RegisterService(getBaseContext());
            Login login = new Login();

            TextView name = (TextView)findViewById(R.id.nameUser);
            TextView password = (TextView)findViewById((R.id.passUser));
            TextView passwordConfirm = (TextView)findViewById((R.id.confirmpassUser));
            TextView email = (TextView)findViewById((R.id.emailUser));

            login.setNameUser(name.getText().toString());
            login.setEmail(email.getText().toString());
            login.setPasswordUser(password.getText().toString());
            String passUserConfirm = passwordConfirm.getText().toString();

            // Check fields
            if(login.getNameUser().isEmpty() || login.getEmail().isEmpty() || login.getPasswordUser().isEmpty() || passUserConfirm.isEmpty()){
                Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_LONG).show();
                return;
            }

            // Check password
            if(!login.getPasswordUser().equals(passUserConfirm)){
                Toast.makeText(getApplicationContext(), "As senhas devem ser iguais", Toast.LENGTH_LONG).show();
                return ;
            }

            // Creating a account
            String resultado = service.createAccount(login);

            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            if(!resultado.equals("Registro já existe")) {
                Intent LoginController = new Intent(this, com.example.analista_nota10.Controller.LoginController.class);
                startActivity(LoginController);
            }
    }
    // gustavo é lindo
}