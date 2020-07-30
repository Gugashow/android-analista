package com.example.analista_nota10.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.analista_nota10.DataBase.Banco;
import com.example.analista_nota10.Model.Discipline;
import com.example.analista_nota10.Model.Login;

import java.util.ArrayList;
import java.util.List;

public class DisciplineService {
    protected static final String TABELA = "tbDisciplina";
    protected static final String ID_DISCIPLINA = "idDisciplina";
    protected static final String ID_USUARIO = "idUsuario";
    protected static final String NOME_DISCIPLINA = "nomeDisciplina";
    private SQLiteDatabase db;
    private Banco banco;

    public DisciplineService(Context context){
        banco = new Banco(context);
    }

    /**
     * Creating a discipline
     * @param discipline
     * @return message
     */
    public String createDiscipline(Discipline discipline, Login login) {
        ContentValues values;
        Long result;

        // Checks if table exists
        onCreate();


        //Checks if discipline exists
        if (!listDiscipline().isEmpty()) {

            for(Discipline disc : listDiscipline()){
                if(disc.getNameDiscipline().equals(discipline.getNameDiscipline())){
                    return "Disciplina já cadastrada";
                }
            }
        }

        // Writing and reading data in the bank
        db = banco.getWritableDatabase();
        values = new ContentValues();
        values.put(NOME_DISCIPLINA, discipline.getNameDiscipline());
        values.put(ID_USUARIO, login.get_Id());

        result = db.insert(TABELA, null, values);
        db.close();

        if (result == -1) {
            return "Erro ao inserir registro";
        }

        return "Disciplina cadastrada com sucesso!";

    }


    /*public Cursor listDiscipline(){
        Cursor cursor;

        // Reading data in the bank
        db = banco.getReadableDatabase();
        String[] campos = {ID_DISCIPLINA, ID_USUARIO, NOME_DISCIPLINA};
        cursor = db.query(TABELA, campos,null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;

    }*/


    /**
     * List all disciplines
     * @return listDiscipline
     */
    public List<Discipline>  listDiscipline(){
        List<Discipline> listDiscipline = new ArrayList<Discipline>();
        Cursor cursor;

        // Reading data in the bank
        db = banco.getReadableDatabase();
        cursor = db.query(TABELA, null, null,null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                Discipline discipline = new Discipline(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2));
                listDiscipline.add(discipline);
                cursor.moveToNext();
            }
        }

        db.close();
        return listDiscipline;
    }

    /**
     * Get discipline
     * @return discipline
     */
    public Discipline  getDisciplineByName(String name){
        Cursor cursor;

        // Reading data in the bank
        db = banco.getReadableDatabase();
        cursor = db.query(TABELA, null, null,null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                Discipline discipline = new Discipline(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2));

                if(discipline.getNameDiscipline().equals(name)){
                    db.close();
                    return discipline;
                }
                cursor.moveToNext();
            }
        }

        db.close();
        return null;
    }


    /**
     * Creating disciplines table
     */
    public void onCreate() {
        String tableUser = RegisterService.TABELA;

        // Writing and reading data in the bank
        db = banco.getWritableDatabase();

        String sql = " CREATE TABLE IF NOT EXISTS "+TABELA+"("
                + ID_DISCIPLINA + " integer primary key autoincrement,"
                + ID_USUARIO + " integer,"
                + NOME_DISCIPLINA + " text,"
                + "foreign key (" + ID_USUARIO + ") references " + tableUser  + " (" + ID_USUARIO + ") ON DELETE CASCADE"
                +")";
        db.execSQL(sql);
        db.close();
    }
}
