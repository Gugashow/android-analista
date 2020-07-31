package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.analista_nota10.R;

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
        nameUserAdm = (EditText) findViewById(R.id.userAdm);
        passUserAdm = (EditText) findViewById(R.id.passAdm);

        if (nameUserAdm.getText().toString().equals(ADMIN) && passUserAdm.getText().toString().equals(ADMIN)){
            Intent MenuAdm = new Intent(this, MenuAdmController.class);
            startActivity(MenuAdm);
        }
        else
            Toast.makeText(this,"Você não é administrador", Toast.LENGTH_LONG).show();
    }
}
