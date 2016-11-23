package com.example.lucas.energysaving;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 22/11/2016.
 */

public class DAOdica {
    private SQLiteDatabase db;
    private CriarBanco banconovo;//utiliza o mesmo banco de daddos para realizar as operaÃ§Ãµes

    public DAOdica(Context context){
        banconovo = new CriarBanco(context);
    }

    public void insertDicas(ContractDica dica){//inserir as dicas no BD

        db = banconovo.getWritableDatabase();//abrir para escrita
        ContentValues cv = new ContentValues();
        cv.put("dica", dica.getTitulo());
        cv.put("descricao", dica.getDescricao());
        db.insert("TabelaDica", null, cv);
        db.close();

    }


    public boolean isEmpty(){//ver se o BD ja etm registros
        db = banconovo.getReadableDatabase();
        String sqlselectTod = "SELECT * FROM TabelaDica";
        Cursor c = db.rawQuery(sqlselectTod, null);
        if(c.moveToFirst())
            return false;//tem algo no BD
        return true;//o BD ta vazio
    }

    //apenas as com id 1
    public List<ContractDica> selectTodosAsDicas() {//listar as dicas que ja foram notificadas
        List<ContractDica> listDicas = new ArrayList<ContractDica>();
        db = banconovo.getReadableDatabase();
        String sqlselectTodosAsDicas = "SELECT * FROM TabelaDica";
        Cursor c = db.rawQuery(sqlselectTodosAsDicas, null);

        if(c.moveToFirst()){
            do {
                if(c.getInt(1) == 1) {
                    int id = c.getInt(0);
                    String titulo = c.getString(2);
                    String descricao = c.getString(3);
                    int status = c.getInt(1);
                    ContractDica dica = new ContractDica(id, titulo, descricao,status);
                    listDicas.add(dica);
                }
            } while (c.moveToNext());
        }
        db.close ();
        return listDicas;
    }


    public List<ContractDica> selectTodos() {
        List<ContractDica> listDicastodas = new ArrayList<ContractDica>();
        db = banconovo.getReadableDatabase();
        String sqlselectTodos = "SELECT * FROM TabelaDica";
        Cursor c = db.rawQuery(sqlselectTodos, null);

        if(c.moveToFirst()){
            do {
                int id = c.getInt(0);
                String titulo = c.getString(2);
                String descricao = c.getString(3);
                int status = c.getInt(1);
                ContractDica dica = new ContractDica(id, titulo, descricao,status);
                listDicastodas.add(dica);
            } while (c.moveToNext());
        }
        db.close ();
        return listDicastodas;
    }


    public ContractDica desbloquear(){
        db = banconovo.getReadableDatabase();
        String sqldesbloquear = "SELECT * FROM TabelaDica";
        Cursor c = db.rawQuery(sqldesbloquear, null);
        ContentValues valores = new ContentValues();
        if(c.moveToFirst())
            do {
                if (c.getInt(1) == 0) {
                    int id = c.getInt(0);
                    String titulo = c.getString(2);
                    String descricao = c.getString(3);
                    int status = c.getInt(1);
                    ContractDica dica = new ContractDica(id, titulo, descricao,status);
                    String _id = String.valueOf(c.getInt(1));
                    valores.put("status", 1);
                    db.update("TabelaDica", valores, "_id =" + id, null);
                    return dica;
                }

            } while (c.moveToNext());

        db.close ();
        return null;
    }
}
