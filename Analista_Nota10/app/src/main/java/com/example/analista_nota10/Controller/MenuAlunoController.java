package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.analista_nota10.Model.Discipline;
import com.example.analista_nota10.R;
import com.example.analista_nota10.Service.DisciplineService;

import java.util.List;

public class MenuAlunoController extends AppCompatActivity {
    private Spinner spnDiscipline;
    private List<Discipline> listDisciplines;
    private DisciplineService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_aluno);

        // Spinner

        this.spnDiscipline = (Spinner) this.findViewById(R.id.spinnerOpcoesMenuAluno);

        this.service = new DisciplineService(getApplicationContext());
        this.listDisciplines = service.listDiscipline();


        ArrayAdapter<Discipline> spnDisciplineAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                this.listDisciplines
        );

        this.spnDiscipline.setAdapter(spnDisciplineAdapter);

        // end spinner
    }

    public void generateSimulated (View view){
        String discipline = spnDiscipline.getSelectedItem().toString();
        Bundle param = new Bundle();

        Intent SimuladoController = new Intent(this, SimuladoController.class);
        param.putString("key_discipline", discipline);
        SimuladoController.putExtras(param);
        startActivity(SimuladoController);
    }
    public void buttonDeslogarAluno (View view){
        Intent LoginController = new Intent(this, LoginAlunoController.class);
        startActivity(LoginController);
        onStop();
    }
    protected void onStop() {
        super.onStop();
    }
}
