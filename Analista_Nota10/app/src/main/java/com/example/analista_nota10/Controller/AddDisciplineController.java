package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.analista_nota10.R;
import com.example.analista_nota10.Service.DisciplineService;

public class AddDisciplineController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discipline_controller);
    }

    public String addDiscipline(View view){
        DisciplineService service = new DisciplineService(getBaseContext());


        return "sucess";
    }

}