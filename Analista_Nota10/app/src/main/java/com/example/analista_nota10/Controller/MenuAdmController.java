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

public class MenuAdmController extends AppCompatActivity {
    private Spinner spnDiscipline;
    private List<Discipline> listDisciplines;
    private DisciplineService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_adm);

        // Spinner

        this.spnDiscipline = (Spinner) this.findViewById(R.id.spinnerOptionsMenu);

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
    public void buttonDisciplina (View view){
        Intent DisciplinaController = new Intent(this, AddDisciplineController.class);
        startActivity(DisciplinaController);
    }
    public void buttonQuestao (View view){
        Intent EditQuestao = new Intent(this, EditQuestao.class);
        startActivity(EditQuestao);
    }
    public void buttonGerarSimulado (View view){
        String discipline = spnDiscipline.getSelectedItem().toString();
        Bundle param = new Bundle();

        Intent SimuladoController = new Intent(this, SimuladoController.class);
        param.putString("key_discipline", discipline);
        SimuladoController.putExtras(param);
        startActivity(SimuladoController);
    }
    public void buttonDeslogarAdm (View view){
        Intent LoginAdmController = new Intent(this, LoginAdmController.class);
        startActivity(LoginAdmController);
        onStop();
    }
    protected void onStop() {
        super.onStop();
    }
}