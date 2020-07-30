package com.example.analista_nota10.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.analista_nota10.DataBase.Banco;
import com.example.analista_nota10.Model.Alternative;
import com.example.analista_nota10.Model.Historico;
import com.example.analista_nota10.Model.Questions;

import java.util.ArrayList;
import java.util.List;

public class PerformanceService {
    public static final String TABELA = "tbHistorico";
    public static final String ID_HISTORICO = "idHistorico";
    public static final String ID_DISCIPLINA = "idDisciplina";
    public static final String ID_USUARIO = "idUsuario";
    public static final String QTD_ACERTOS = "quatidadeAcertos";
    public static final String PERCENTUAL = "percentual";
    public static final String QTD_QUESTOES = "quantidadeQuestoes";
    public SQLiteDatabase db;
    public Banco banco;

    public PerformanceService(Context context){
        banco = new Banco(context);
    }

    /**
     *
     * @param historic
     * @return
     */
   public Long saveHistoric(Historico historic){
      ContentValues valores;
      long id = -2;

       db = banco.getReadableDatabase();

       if(!banco.tableExists(db, TABELA)){
           onCreate();
       }
       db.close();

       db = banco.getWritableDatabase();
       valores = new ContentValues();
       valores.put(ID_DISCIPLINA, historic.get_idDiscipline());
       valores.put(ID_USUARIO, historic.get_idUser());
       valores.put(QTD_ACERTOS, historic.getCorrectAnswers());
       valores.put(QTD_QUESTOES, historic.getQtdQuestions());

       id = db.insert(TABELA, null, valores);

       db.close();
       return id;
   }

    /**
     * Creating historic table
     */
    public void onCreate() {
        String diciplineTable = DisciplineService.TABELA;
        String userTable = RegisterService.TABELA;

        String sql = " CREATE TABLE IF NOT EXISTS "+TABELA+"("
                + ID_HISTORICO + " integer primary key autoincrement,"
                + ID_DISCIPLINA + " integer,"
                + ID_USUARIO + " interger,"
                + QTD_ACERTOS + " integer,"
                + QTD_QUESTOES + " integer,"
                + "foreign key (" + ID_DISCIPLINA + ") references " + diciplineTable  + " (" + ID_DISCIPLINA + ") ON DELETE CASCADE,"
                + "foreign key (" + ID_USUARIO + ") references " + userTable  + " (" + ID_USUARIO + ") ON DELETE CASCADE"
                +")";
        db.execSQL(sql);
    }

    /**
     * List historic
     * @return historicList
     */
    public List<Historico> getHistoric(){
        List<Historico> historicList = new ArrayList<>();
        Cursor cursor;

        db = banco.getReadableDatabase();
        cursor = db.query(TABELA, null,null, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                Historico historic = new Historico(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4));
                historicList.add(historic);
                cursor.moveToNext();
            }
        }

        db.close();
        return historicList;

    }

    /**
     * List historic by idDiscipline and idUser
     * @param idQuestion
     * @return
     */
    /*
    public List<Historic> getAlternativeByQuestion(int idDiscipline,  Long idUser){
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

    }*/

    /**
     * Get alternative
     * @return cursor
     */
   /* public Alternative getAlternative(String text){
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
    }*/
}
