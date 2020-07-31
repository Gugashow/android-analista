package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.analista_nota10.R;

public class LoginAdmController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_adm);
    }
    public void buttonAcessarAdm (View view) {
        LoginController LoginController = new LoginController();
        LoginController.buttonLogin(view);
    }
}
