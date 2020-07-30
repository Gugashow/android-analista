package com.example.analista_nota10.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.analista_nota10.DataBase.Banco;
import com.example.analista_nota10.Model.Alternative;
import com.example.analista_nota10.Model.Questions;

import java.util.ArrayList;
import java.util.List;

public class AlternativeService {
    public static final String TABELA = "tbAlternativa";
    public static final String ID_ALTERNATIVA = "idAlternativa";
    public static final String ALTERNATIVA = "alternativa";
    public static final String RESPOSTA = "resposta";
    public static final String ID_QUESTAO = "idQuestao";
    public SQLiteDatabase db;
    public Banco banco;

    public AlternativeService(Context context){
        banco = new Banco(context);
    }

    /**
     *
     * @param alternatives
     * @param idQuestao
     * @return id
     */
   public Long createAlternative(List<Alternative> alternatives, Long  idQuestao){
      ContentValues valores;
      long id = -2;

       db = banco.getReadableDatabase();

       if(!banco.tableExists(db, TABELA)){
           onCreate();
       }
       db.close();

       db = banco.getWritableDatabase();
       for(Alternative alternative : alternatives){
           valores = new ContentValues();
           valores.put(ALTERNATIVA, alternative.getAlternative());
           valores.put(RESPOSTA, alternative.getResposta());
           valores.put(ID_QUESTAO, idQuestao);

           id = db.insert(TABELA, null, valores);
       }

       db.close();
       return id;
   }

    /**
     * Creating alternatives table
     */
    public void onCreate() {
        String tableQuestions = QuestionsService.TABELA;
        String sql = " CREATE TABLE IF NOT EXISTS "+TABELA+"("
                + ID_ALTERNATIVA + " integer primary key autoincrement,"
                + ALTERNATIVA + " text,"
                + RESPOSTA + " text,"
                + ID_QUESTAO + " integer,"
                + "foreign key (" + ID_QUESTAO + ") references " + tableQuestions  + " (" + ID_QUESTAO + ") ON DELETE CASCADE"
                +")";
        db.execSQL(sql);
    }

    /**
     * List all alternatives
     * @return alternativeList
     */
    public List<Alternative> getAlternative(){
        List<Alternative> alternativeList = new ArrayList<>();
        Cursor cursor;

        db = banco.getReadableDatabase();
        cursor = db.query(TABELA, null,null, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                Alternative alternative = new Alternative(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3));
                alternativeList.add(alternative);
                cursor.moveToNext();
            }
        }

        db.close();
        return alternativeList;

    }

    /**
     * List alternatives by idQuestion
     * @param idQuestion
     * @return
     */
    public List<Alternative> getAlternativeByQuestion(int idQuestion){
        List<Alternative> alternativeList = new ArrayList<>();
        Cursor cursor;

        db = banco.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM "+TABELA+ " WHERE "+ID_QUESTAO+" = "+idQuestion, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                Alternative alternative = new Alternative(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3));
                alternativeList.add(alternative);
                cursor.moveToNext();
            }
        }

        db.close();
        return alternativeList;

    }

    /**
     * Get alternative
     * @return cursor
     */
    public Alternative getAlternative(String text){
        Cursor cursor;
        db = banco.getReadableDatabase();

        cursor = db.query(TABELA, null, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                Alternative alternative = new Alternative(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3));

                if(alternative.getAlternative().equals(text)){
                    db.close();
                    return alternative;
                }
                cursor.moveToNext();
            }

        }
        db.close();
        return null;
    }
}
