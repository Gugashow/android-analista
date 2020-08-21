package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.analista_nota10.Model.Discipline;
import com.example.analista_nota10.Model.Login;
import com.example.analista_nota10.R;
import com.example.analista_nota10.Service.DisciplineService;
import com.example.analista_nota10.Service.RegisterService;
import com.example.analista_nota10.Singleton;

import java.util.ArrayList;
import java.util.List;

public class AddDisciplineController extends AppCompatActivity {
    private List<Discipline> diciplineList = new ArrayList<>();
    private DisciplineService disciplineService;
    private String oldName;
    private String EDITAR = "Editar";
    private String SALVAR = "Salvar";

    // Components
    private ArrayAdapter<String> adapter;
    private View addView;
    private Button buttonEdit;
    private Button buttonRemove;
    private LinearLayout container;
    private TextView nameDiscipline;
    private AutoCompleteTextView textOut;
    private static final String[] NUMBER = new String[] {
            "One", "Two", "Three", "Four", "Five",
            "Six", "Seven", "Eight", "Nine", "Ten"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discipline);
    }

    public void addDiscipline(View view) {
        DisciplineService service = new DisciplineService(getBaseContext());
        RegisterService serviceUser = new RegisterService(getBaseContext());


        nameDiscipline = (TextView) findViewById(R.id.nameDiscipline);

        if (nameDiscipline.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Preencha o nome da disciplina", Toast.LENGTH_LONG).show();
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
            clearComponent();

            // Opening keyBoard
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .showSoftInput(nameDiscipline, 0);
            
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }
    }
    private void clearComponent() {
        nameDiscipline.setText("");
        nameDiscipline.requestFocus();
    }

    public void onSearchDicipline(View view){
        Discipline dicipline = new Discipline();

        nameDiscipline = findViewById(R.id.nameDiscipline);


        disciplineService = new DisciplineService(getApplicationContext());

        container = (LinearLayout) findViewById(R.id.container);

        if(container != null) container.removeAllViews();


        if(!nameDiscipline.getText().toString().isEmpty()) {
            dicipline = disciplineService.getDisciplineByName(nameDiscipline.getText().toString());

            if(dicipline != null) {
                adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_dropdown_item_1line, NUMBER);

                LayoutInflater layoutInflater =
                        (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                addView = layoutInflater.inflate(R.layout.row_action, null);
                textOut = (AutoCompleteTextView) addView.findViewById(R.id.textout);
                textOut.setAdapter(adapter);
                textOut.setText(dicipline.getNameDiscipline());
                textOut.setEnabled(false);

                buttonRemove = (Button) addView.findViewById(R.id.remove);
                buttonEdit = (Button) addView.findViewById(R.id.edit);

                final View.OnClickListener thisListenerRemove = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((LinearLayout) addView.getParent()).removeView(addView);
                        AutoCompleteTextView textOut = (AutoCompleteTextView) addView.findViewById(R.id.textout);
                        if (disciplineService.removeDisciplineByName(textOut.getText().toString())) {
                            Toast.makeText(getBaseContext(), "Disciplina removida com sucesso", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getBaseContext(), "Erro ao remover disciplina", Toast.LENGTH_SHORT).show();
                        }

                    }
                };

                final View.OnClickListener thisListenerEdit = new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        AutoCompleteTextView textOut = (AutoCompleteTextView) addView.findViewById(R.id.textout);

                        if(buttonEdit.getText().equals(EDITAR)) {
                            oldName = textOut.getText().toString();
                            buttonEdit.setText(SALVAR);
                            textOut.setEnabled(true);
                        }else {
                            if(disciplineService.updateDisciplineByName(textOut.getText().toString(), oldName)) {
                                Toast.makeText(getBaseContext(), "Disciplina atualizada com sucesso", Toast.LENGTH_SHORT).show();
                                buttonEdit.setText(EDITAR);
                                textOut.setEnabled(false);
                                return;
                            }
                            Toast.makeText(getBaseContext(), "Erro ao atualizar a disciplina", Toast.LENGTH_SHORT).show();
                            buttonEdit.setText(EDITAR);
                        }

                    }
                };


                buttonRemove.setOnClickListener(thisListenerRemove);
                buttonEdit.setOnClickListener(thisListenerEdit);
                container.addView(addView);

            }else{
                Toast.makeText(getBaseContext(), "Registro não encontrado", Toast.LENGTH_SHORT).show();
            }

        }else {
            diciplineList = disciplineService.listDiscipline();

            if (diciplineList.size() >= 1) {
                adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_dropdown_item_1line, NUMBER);

                container = (LinearLayout) findViewById(R.id.container);
                container.removeAllViews();

                for (Discipline discipline : diciplineList) {
                    LayoutInflater layoutInflater =
                            (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View addView = layoutInflater.inflate(R.layout.row_action, null);
                    AutoCompleteTextView textOut = (AutoCompleteTextView) addView.findViewById(R.id.textout);
                    textOut.setAdapter(adapter);
                    textOut.setText(discipline.getNameDiscipline());
                    textOut.setEnabled(false);
                    buttonRemove = (Button) addView.findViewById(R.id.remove);
                    buttonEdit = (Button) addView.findViewById(R.id.edit);

                    final View.OnClickListener thisListenerRemove = new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((LinearLayout) addView.getParent()).removeView(addView);
                            AutoCompleteTextView textOut = (AutoCompleteTextView) addView.findViewById(R.id.textout);
                            if (disciplineService.removeDisciplineByName(textOut.getText().toString())) {
                                Toast.makeText(getBaseContext(), "Disciplina removida com sucesso", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getBaseContext(), "Erro ao remover disciplina", Toast.LENGTH_SHORT).show();
                            }

                        }
                    };

                    final View.OnClickListener thisListenerEdit = new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            AutoCompleteTextView textOut = (AutoCompleteTextView) addView.findViewById(R.id.textout);
                            buttonEdit = (Button) addView.findViewById(R.id.edit);
                            if(buttonEdit.getText().equals(EDITAR)) {
                                oldName = textOut.getText().toString();
                                buttonEdit.setText(SALVAR);
                                textOut.setEnabled(true);
                            }else {
                                if(disciplineService.updateDisciplineByName(textOut.getText().toString(), oldName)) {
                                    Toast.makeText(getBaseContext(), "Disciplina atualizada com sucesso", Toast.LENGTH_SHORT).show();
                                    buttonEdit.setText(EDITAR);
                                    textOut.setEnabled(false);
                                    return;
                                }
                                Toast.makeText(getBaseContext(), "Erro ao atualizar a disciplina", Toast.LENGTH_SHORT).show();
                                buttonEdit.setText(EDITAR);
                            }

                        }
                    };


                    buttonRemove.setOnClickListener(thisListenerRemove);
                    buttonEdit.setOnClickListener(thisListenerEdit);
                    container.addView(addView);
                }
            }else{
                Toast.makeText(getBaseContext(), "Sem disciplina cadastrada", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    // Adicionar o botao na tela de disciplina
    public void buttonVoltar(View view) {
        Intent MenuAdm = new Intent(this, MenuAdmController.class);
        startActivity(MenuAdm);
    }

    @Override
    public void onBackPressed() { }
}

