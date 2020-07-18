package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.analista_nota10.R;
import com.example.analista_nota10.Service.CadastroService;

public class CadastroController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }
        public void createAccount(){
            CadastroService service = new CadastroService(getBaseContext());


            TextView name = (TextView)findViewById(R.id.nameUser);
            TextView password = (TextView)findViewById((R.id.passUser));
            TextView passwordConfirm = (TextView)findViewById((R.id.confirmpassUser));
            TextView email = (TextView)findViewById((R.id.emailUser));

            String nameUser = name.getText().toString();
            String emailUser = email.getText().toString();
            String passUser = password.getText().toString();
            String passUserConfirm = passwordConfirm.getText().toString();

            if(!passUser.equals(passUserConfirm)){
                Toast.makeText(getApplicationContext(), "As senhas devem ser iguais", Toast.LENGTH_LONG).show();
            }

            String resultado = service.createAccount(nameUser, emailUser, passUser);

            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
    }
}