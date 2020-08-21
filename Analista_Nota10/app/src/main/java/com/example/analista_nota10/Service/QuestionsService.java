package com.example.analista_nota10.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.analista_nota10.DataBase.Banco;
import com.example.analista_nota10.Model.Discipline;
import com.example.analista_nota10.Model.Login;
import com.example.analista_nota10.Model.Questions;

import java.util.ArrayList;
import java.util.List;

public class QuestionsService {
    public static final String TABELA = "tbQuestoes";
    public static final String ID_QUESTIONS = "idQuestoes";
    public static final String QUESTOES = "questoes";
    public static final String ID_DISCIPLINA = "idDisciplina";
    public static final String SENHA = "senha";
    public SQLiteDatabase db;
    public Banco banco;

    public QuestionsService(Context context){
        banco = new Banco(context);
    }

    /**
     *
     * @param question
     * @return mensagem
     */
   public long createQuestion(Questions question){
      ContentValues valores;
      long id;

       db = banco.getReadableDatabase();

       if(!banco.tableExists(db, TABELA)){
           onCreate();
       }
       db.close();

       if(this.getQuestion(question.getQuestion()) != null) return +1;

       //
       db = banco.getWritableDatabase();
       valores = new ContentValues();
       valores.put(QUESTOES, question.getQuestion());
       valores.put(ID_DISCIPLINA, question.get_idDiscipline());

       id = db.insert(TABELA, null, valores);
       db.close();

        return id;
   }

    /**
     * Creating questions table
     */
    public void onCreate() {
        String tableDiscipline = DisciplineService.TABELA;
        String sql = " CREATE TABLE IF NOT EXISTS "+TABELA+"("
                + ID_QUESTIONS + " integer primary key autoincrement,"
                + QUESTOES + " text,"
                + ID_DISCIPLINA + " integer,"
                + "foreign key (" + ID_DISCIPLINA + ") references " + tableDiscipline  + " (" + ID_DISCIPLINA + ") ON DELETE CASCADE"
                +")";
        db.execSQL(sql);
    }

    /**
     * List all questions
     * @return cursor
     */
    public List<Questions>  getAllQuestions(){
        List<Questions> questionsList = new ArrayList<>();
        Cursor cursor;

        db = banco.getReadableDatabase();
        cursor = db.query(TABELA, null,null, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                Questions question = new Questions(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2));
                questionsList.add(question);
                cursor.moveToNext();
            }
        }

        db.close();
        return questionsList;

    }

    /**
     * Get question
     * @return cursor
     */
    public Questions getQuestion(String text){
        Cursor cursor;
        db = banco.getReadableDatabase();

        cursor = db.query(TABELA, null, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                Questions question = new Questions(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2));

                if(question.getQuestion().equals(text)){
                    db.close();
                    return question;
                }
                cursor.moveToNext();
            }

        }
        db.close();
        return null;
    }

    /**
     *
     * @param idDiscipline
     * @return
     */
    public List<Questions> getQuestionByIdDicipline(int idDiscipline){
        List<Questions> questionsList = new ArrayList<>();
        Cursor cursor;

        db = banco.getReadableDatabase();

        if(!banco.tableExists(db, TABELA)){
            onCreate();
        }

        cursor = db.rawQuery("SELECT * FROM "+TABELA+ " WHERE "+ID_DISCIPLINA+" = "+idDiscipline, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                Questions question = new Questions(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2));
                questionsList.add(question);
                cursor.moveToNext();
            }
        }

        db.close();
        return questionsList;
    }

    /**
     * Remove question
     * @param name
     * @return true (success) | false (error)
     */
    public boolean removeQuestionByName(String name){
        boolean result;

        // Reading data in the bank
        db = banco.getWritableDatabase();
        result = db.delete(TABELA, QUESTOES + " = ?", new String[] {name}) > 0;

        db.close();
        return result;
    }

    /**
     * Update question
     * @param newName
     * @param oldName
     */
    public boolean updateQuestionByName(String newName,  String oldName ){
        boolean result;

        try {
            // Reading and writing data in the bank
            db = banco.getWritableDatabase();

            db.execSQL("UPDATE "+TABELA+" SET "+QUESTOES+ " = "+"'"+newName+"' "+ "WHERE " +QUESTOES+ " = "+"'"+oldName+"'");

            result = true;
        }catch (SQLiteException e){
            e.getStackTrace();
            result = false;
        }

        db.close();
        return result;
    }
}
