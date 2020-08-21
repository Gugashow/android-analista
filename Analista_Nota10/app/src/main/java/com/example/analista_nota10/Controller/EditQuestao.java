package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.analista_nota10.Model.Discipline;
import com.example.analista_nota10.Model.Questions;
import com.example.analista_nota10.R;
import com.example.analista_nota10.Service.DisciplineService;
import com.example.analista_nota10.Service.QuestionsService;

import java.util.ArrayList;
import java.util.List;

public class EditQuestao extends AppCompatActivity {
    private Spinner spnDiscipline;
    private List<Discipline> listDisciplines;
    private DisciplineService service;
    private QuestionsService questionsService;

    private static final String[] NUMBER = new String[] {
            "One", "Two", "Three", "Four", "Five",
            "Six", "Seven", "Eight", "Nine", "Ten"
    };
    private Button buttonEdit;
    private Button buttonRemove;
    private String oldName;
    private LinearLayout container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_questao);

        // Spinner

        this.spnDiscipline = (Spinner) this.findViewById(R.id.spinnerOpcoes);

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
    public void buttonIrAddQuestoes (View view){
        Intent AddQuestoes = new Intent(this, AddQuestaoController.class);
        startActivity(AddQuestoes);
    }

    public void onSearchQuestion(View view) {

        // Check disciplines
        if(listDisciplines.size() < 1) {
            Toast.makeText(getApplicationContext(), "Cadastre uma disciplina", Toast.LENGTH_SHORT).show();
            return;
        }

        questionsService = new QuestionsService(getApplicationContext());
        List<Questions> questionsList = new ArrayList<>();

        for(Discipline discipline : listDisciplines){
            if(discipline.getNameDiscipline().equals(spnDiscipline.getSelectedItem().toString())){
                questionsList = questionsService.getQuestionByIdDicipline(discipline.get_id());
                break;
            }
        }

        LinearLayout container = (LinearLayout) findViewById(R.id.container);

        if(container != null) {
            container.removeAllViews();
        }

        if(questionsList.size() >= 1){
            loadingQuestions(questionsList, container);
        }else{
            Toast.makeText(getBaseContext(), "Questões não cadastradas para essa disciplina", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadingQuestions(List<Questions> questionsList,LinearLayout container) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, NUMBER);

        for (Questions question : questionsList) {
            LayoutInflater layoutInflater =
                    (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            final View addView = layoutInflater.inflate(R.layout.row_action, null);
            AutoCompleteTextView textOut = (AutoCompleteTextView) addView.findViewById(R.id.textout);

            textOut.setAdapter(adapter);
            textOut.setText(question.getQuestion());
            textOut.setEnabled(false);

            buttonRemove = (Button) addView.findViewById(R.id.remove);
            buttonEdit = (Button) addView.findViewById(R.id.edit);

            final View.OnClickListener thisListenerRemove = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((LinearLayout) addView.getParent()).removeView(addView);
                    AutoCompleteTextView textOut = (AutoCompleteTextView) addView.findViewById(R.id.textout);
                    if (questionsService.removeQuestionByName(textOut.getText().toString())) {
                        Toast.makeText(getBaseContext(), "Questão removida com sucesso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getBaseContext(), "Erro ao remover questão", Toast.LENGTH_SHORT).show();
                    }

                }
            };

            final View.OnClickListener thisListenerEdit = new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    AutoCompleteTextView textOut = (AutoCompleteTextView) addView.findViewById(R.id.textout);
                    buttonEdit = (Button) addView.findViewById(R.id.edit);

                    if(buttonEdit.getText().equals("Editar")) {
                        oldName = textOut.getText().toString();
                        buttonEdit.setText("Salvar");
                        textOut.setEnabled(true);

                    }else {
                        if(questionsService.updateQuestionByName(textOut.getText().toString(), oldName)) {
                            Toast.makeText(getBaseContext(), "Questão alterada com sucesso", Toast.LENGTH_SHORT).show();
                            buttonEdit.setText("Editar");
                            textOut.setEnabled(false);
                            return;
                        }

                        Toast.makeText(getBaseContext(), "Erro ao alterar questão", Toast.LENGTH_SHORT).show();
                        buttonEdit.setText("Editar");
                    }

                }
            };


            buttonRemove.setOnClickListener(thisListenerRemove);
            buttonEdit.setOnClickListener(thisListenerEdit);
            container.addView(addView);
        }
    }
}