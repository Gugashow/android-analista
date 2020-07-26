package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.analista_nota10.Model.Discipline;
import com.example.analista_nota10.Model.Login;
import com.example.analista_nota10.R;
import com.example.analista_nota10.Service.DisciplineService;
import com.example.analista_nota10.Service.RegisterService;
import com.example.analista_nota10.Singleton;

public class AddDisciplineController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discipline_controller);
    }

    public void addDiscipline(View view) {
        DisciplineService service = new DisciplineService(getBaseContext());
        RegisterService serviceUser = new RegisterService(getBaseContext());


        TextView nameDiscipline = (TextView) findViewById(R.id.addDisciplina);
        if (nameDiscipline.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Preencha o campo", Toast.LENGTH_LONG).show();
        } else {

            Discipline discipline = new Discipline();
            discipline.setNameDiscipline(nameDiscipline.getText().toString());

            // Recovering user data
            Login login = Singleton.getInstance().login;
            Login user = serviceUser.getUserByName(login.getNameUser().trim());
            login.set_Id(user.get_Id());

            // Creating discipline
            String result = service.createDiscipline(discipline, login);

            // Cleaning field
            nameDiscipline.setText("");
            nameDiscipline.requestFocus();

            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }
    }

}