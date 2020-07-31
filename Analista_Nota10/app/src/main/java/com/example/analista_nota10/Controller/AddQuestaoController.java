package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.analista_nota10.Model.Alternative;
import com.example.analista_nota10.Model.Discipline;
import com.example.analista_nota10.Model.Questions;
import com.example.analista_nota10.R;
import com.example.analista_nota10.Service.AlternativeService;
import com.example.analista_nota10.Service.DisciplineService;
import com.example.analista_nota10.Service.QuestionsService;

import java.util.ArrayList;
import java.util.List;

public class AddQuestaoController extends AppCompatActivity{
    private Spinner spnDiscipline;
    private List<Discipline> listDisciplines;
    private List<Alternative> alternativeList = new ArrayList<>();
    private DisciplineService service;
    private AutoCompleteTextView textIn;
    private Button buttonAdd;
    private LinearLayout container;
    private CheckBox checkBox;
    private ArrayAdapter<String> adapter;
    private EditText editText;
    public static final String SIM = "SIM";
    public static final String NAO = "NAO";

    private static final String[] NUMBER = new String[] {
            "One", "Two", "Three", "Four", "Five",
            "Six", "Seven", "Eight", "Nine", "Ten"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questao);

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

        // Alternatives
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, NUMBER);

        textIn = (AutoCompleteTextView)findViewById(R.id.textin);
        textIn.setAdapter(adapter);
        textIn.setHint("Alternativa");

        buttonAdd = (Button)findViewById(R.id.add);
        container = (LinearLayout) findViewById(R.id.container);

        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater =
                        (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row, null);
                AutoCompleteTextView textOut = (AutoCompleteTextView)addView.findViewById(R.id.textout);
                textOut.setAdapter(adapter);
                textOut.setText(textIn.getText().toString());
                Button buttonRemove = (Button)addView.findViewById(R.id.remove);
                checkBox = (CheckBox)addView.findViewById(R.id.checkbox);


                Alternative alternative = new Alternative(
                        textOut.getText().toString(),
                        NAO,
                        textOut
                );

                alternativeList.add(alternative);

                final View.OnClickListener thisListener = new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        ((LinearLayout)addView.getParent()).removeView(addView);

                        listAllAddView();
                    }
                };

                final View.OnClickListener thisListenerCheckbox =  new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        AutoCompleteTextView textOut = (AutoCompleteTextView)addView.findViewById(R.id.textout);
                        checkBox = (CheckBox)addView.findViewById(R.id.checkbox);
                        String text = textOut.getText().toString();
                        String text1 = v.toString();

                        if (checkBox.isChecked()){
                            Toast.makeText(getBaseContext(), "Alternativa correta", Toast.LENGTH_LONG).show();
                        }


                        for(Alternative alternative : alternativeList){

                            if(alternative.getView() == textOut){
                                alternative.setResposta(SIM);
                            }

                            if(alternative.getView() != textOut){
                                alternative.setResposta(NAO);
                            }

                        }
                    }
                };

                buttonRemove.setOnClickListener(thisListener);
                checkBox.setOnClickListener(thisListenerCheckbox);
                container.addView(addView);

                // Clear field
                textIn.setText("");
                textIn.requestFocus();


                listAllAddView();
            }
        });
    }

    private void listAllAddView(){

        int childCount = container.getChildCount();

        for(int i=0; i<childCount; i++){
            View thisChild = container.getChildAt(i);

            AutoCompleteTextView childTextView = (AutoCompleteTextView) thisChild.findViewById(R.id.textout);
            String childTextViewValue = childTextView.getText().toString();

        }

        // end alternatives
    }

    public void buttonAddQuestao(View view){

        editText = (EditText) findViewById(R.id.question);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerOpcoes);

        String question = editText.getText().toString();
        String nameDisc = spinner.getSelectedItem().toString();

        // Recovering discipline
        DisciplineService serviceDisc = new DisciplineService(getApplicationContext());
        Discipline discipline = serviceDisc.getDisciplineByName(nameDisc);

        // Building object
        Questions questions = new Questions(
                question,
                discipline.get_id()
        );

        QuestionsService service = new QuestionsService(getApplicationContext());
        Long idQuestion = service.createQuestion(questions);

        AlternativeService alternativeService = new AlternativeService(getApplicationContext());
        Long idAlternativa = alternativeService.createAlternative(alternativeList, idQuestion);

        if(idQuestion == -1 || idAlternativa == -1 || idAlternativa == -2){
            Toast.makeText(getApplicationContext(), "Erro ao inserir registro", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Registro inserido com sucesso", Toast.LENGTH_LONG).show();
            clearComponent();
            //Intent RecarregarTela = new Intent(this, AddQuestaoController.class);
           // startActivity(RecarregarTela);
        }
    }
    private void clearComponent() {
        container.removeAllViews();
        editText.setText("");
    }
}
