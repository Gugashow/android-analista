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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.analista_nota10.Model.Alternative;
import com.example.analista_nota10.Model.Discipline;
import com.example.analista_nota10.Model.Historico;
import com.example.analista_nota10.Model.Login;
import com.example.analista_nota10.Model.Questions;
import com.example.analista_nota10.R;
import com.example.analista_nota10.Service.AlternativeService;
import com.example.analista_nota10.Service.DisciplineService;
import com.example.analista_nota10.Service.QuestionsService;
import com.example.analista_nota10.Singleton;

import java.util.ArrayList;
import java.util.List;

public class SimuladoController extends AppCompatActivity {

    private List<Questions> questionsList = new ArrayList<>();
    private List<Alternative> alternativeList = new ArrayList<>();
    private Login user = Singleton.getInstance().login;
    private Discipline discipline;
    private LinearLayout container;
    private CheckBox checkBox;
    private ArrayAdapter<String> adapter;
    private int position = 0;
    private int qtdAnswersRight = 0;
    private int qtdAnswers = 0;
    private final static String AVANCAR = "Avançar";
    private final static String CONCLUIR = "Concluir";

    private static final String[] NUMBER = new String[] {
            "One", "Two", "Three", "Four", "Five",
            "Six", "Seven", "Eight", "Nine", "Ten"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulado);

        Intent intentMenu = getIntent();

        Bundle param = intentMenu.getExtras();

        // Loading discipline
        DisciplineService disciplineService = new DisciplineService(getApplicationContext());
        discipline = disciplineService.getDisciplineByName(param.getString("key_discipline"));

        // Loading question
        QuestionsService questionsService = new QuestionsService(getApplicationContext());
        questionsList = questionsService.getQuestionByIdDicipline(discipline.get_id());

        // Loading alternatives
        AlternativeService alternativeService = new AlternativeService(getApplicationContext());

        for (Questions question : questionsList) {
            alternativeList = alternativeService.getAlternativeByQuestion(question.get_id());

            question.setAlternatives(alternativeList);
        }

        loadingComponents();

    }

    private void loadingComponents() {

        // Change button
        if(position >= questionsList.size() - 1){
            Button btnNext = (Button) findViewById(R.id.buttonAfterSimulado);
            btnNext.setText(CONCLUIR);
        }

        if(position < questionsList.size()) {

            TextView textView = (TextView) findViewById(R.id.textViewQuestion);
            textView.setText(questionsList.get(position).getQuestion());

            // alternatives
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, NUMBER);

            container = (LinearLayout) findViewById(R.id.container);
            container.removeAllViews();

            for (Alternative alternatives : questionsList.get(position).getAlternatives()) {
                LayoutInflater layoutInflater =
                        (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row_simulated, null);
                AutoCompleteTextView textOut = (AutoCompleteTextView) addView.findViewById(R.id.textout);
                textOut.setAdapter(adapter);
                textOut.setText(alternatives.getAlternative());
                checkBox = (CheckBox) addView.findViewById(R.id.checkbox);

                if(textOut.getText().toString().equals(alternatives.getAlternative())){
                    if(alternatives.getRespostaUser() != null && alternatives.getRespostaUser().equals(AddQuestaoController.SIM)){
                        checkBox.setChecked(true);
                    }
                }

                final View.OnClickListener thisListenerCheckbox =  new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        AutoCompleteTextView textOut = (AutoCompleteTextView)addView.findViewById(R.id.textout);
                        checkBox = (CheckBox)addView.findViewById(R.id.checkbox);


                        for(Alternative alternative : questionsList.get(position).getAlternatives()){

                            if(alternative.getAlternative().equals(textOut.getText().toString())){
                                alternative.setRespostaUser(AddQuestaoController.SIM);
                            }

                            if(!alternative.getAlternative().equals(textOut.getText().toString())){
                                alternative.setRespostaUser(AddQuestaoController.NAO);
                            }

                        }
                    }
                };

                checkBox.setOnClickListener(thisListenerCheckbox);
                container.addView(addView);
            }

            //end alternatives
        }
    }

    public void buttonAfterSimulado (View view){
        Button btnNext = (Button) findViewById(R.id.buttonAfterSimulado);

        if(btnNext.getText().equals(CONCLUIR)){
            onSave();
        }else if(position < questionsList.size()){
            position++;
            loadingComponents();
        }
    }

    private void onSave() {

        // Check correct answers
        for(Questions question : questionsList){
            for(Alternative alternative : question.getAlternatives()){
                if(alternative.getResposta().equals(AddQuestaoController.SIM) &&
                        alternative.getRespostaUser() != null &&
                        alternative.getResposta().equals(alternative.getRespostaUser())){
                    qtdAnswersRight++;
                }
            }
        }

        // Check qtd answers
        for(Questions question : questionsList){
            for(Alternative alternative : question.getAlternatives()){
                if(alternative.getRespostaUser() != null && alternative.getRespostaUser().equals(AddQuestaoController.SIM)){
                    qtdAnswers++;
                    break;
                }
            }
        }

        // Validation
        if(qtdAnswers < questionsList.size()){
            Toast.makeText(getApplicationContext(), "Responda todas as questões", Toast.LENGTH_LONG).show();
            qtdAnswers = 0;
            qtdAnswersRight = 0;
            return;
        }

        // Score
        float percentage = qtdAnswersRight * 100 / questionsList.size();
        int qtdQuestions = questionsList.size();

        Historico historico = new Historico(
                discipline.get_id(),
                user.get_Id(),
                qtdAnswersRight,
                percentage,
                qtdQuestions
        );

        Intent performance = new Intent(this, PerformanceController.class);
        Bundle param = new Bundle();
        param.putSerializable("historico", historico);

        performance.putExtras(param);

        startActivity(performance);
    }

    public void buttonBeforeSimulado (View view){
        if(position > 0){
            position--;
            Button btnNext = (Button) findViewById(R.id.buttonAfterSimulado);
            btnNext.setText(AVANCAR);

            loadingComponents();
        }
    }

    public void buttonSair (View view){

        if(user.getNameUser().equals(LoginController.ADMIN)){
            Intent menu = new Intent(this, MenuController.class);
            startActivity(menu);
        }

        Intent menuAluno = new Intent(this, MenuAlunoController.class);
        startActivity(menuAluno);
    }
}