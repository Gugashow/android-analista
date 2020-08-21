package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.analista_nota10.Model.Discipline;
import com.example.analista_nota10.R;
import com.example.analista_nota10.Service.DisciplineService;
import com.example.analista_nota10.Service.QuestionsService;

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

        /*this.spnDiscipline.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                // Check questions
                QuestionsService questionsService = new QuestionsService(getApplicationContext());
                Button simulated = (Button) findViewById(R.id.buttonCreateSimulated);
                simulated.setEnabled(true);

                for (Discipline discipline : listDisciplines) {
                    if (discipline.getNameDiscipline().equals(spnDiscipline.getSelectedItem().toString())) {
                        if (questionsService.getQuestionByIdDicipline(discipline.get_id()).size() < 1) {
                            simulated.setEnabled(false);
                            Toast.makeText(MenuAdmController.this, "Sem questões para essa disciplina", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

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

        // Check disciplines
        if(listDisciplines.size() < 1) {
            Toast.makeText(getApplicationContext(), "Cadastre uma disciplina", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check questions
        QuestionsService questionsService = new QuestionsService(getApplicationContext());
        Button simulated = (Button) findViewById(R.id.buttonCreateSimulated);
        simulated.setEnabled(true);

        for (Discipline discipline : listDisciplines) {
            if (discipline.getNameDiscipline().equals(spnDiscipline.getSelectedItem().toString())) {
                if (questionsService.getQuestionByIdDicipline(discipline.get_id()).size() < 1) {
                    simulated.setEnabled(false);
                    Toast.makeText(MenuAdmController.this, "Sem questões para essa disciplina", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }

        String discipline = spnDiscipline.getSelectedItem().toString();
        Bundle param = new Bundle();

        Intent SimuladoController = new Intent(this, SimuladoController.class);
        param.putString("key_discipline", discipline);
        SimuladoController.putExtras(param);
        startActivity(SimuladoController);
    }
    public void buttonDeslogarAdm (View view){
        Intent LoginAlunoController = new Intent(this, LoginAlunoController.class);
        startActivity(LoginAlunoController);
        onStop();
    }
    protected void onStop() {
        super.onStop();
    }
}