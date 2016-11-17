package com.example.brenon.energysaves3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prefeitura on 06/10/2016.
 */

public class BancoControlerDica {
    private SQLiteDatabase db;
    private CriarBanco banconovo;//utiliza o mesmo banco de daddos para realizar as operaÃ§Ãµes

    public BancoControlerDica(Context context){
        banconovo = new CriarBanco(context);
    }

    public void insertDicas(ModelDicas dica){//inserir as dicas no BD

        db = banconovo.getWritableDatabase();//abrir para escrita
        ContentValues cv = new ContentValues();
        cv.put("dica", dica.getTitulo());
        cv.put("descricao", dica.getDescricao());
        db.insert("TabelaDica", null, cv);
        db.close();

    }

    /*listar tudo do BD
    * para pegar o ultimo inserido pode usar o comando
    * c.moveToLast, pq assim posiciona o ponteiro no ultimo inserido
    * e  para mostrar tods faz assim
    * List<ModelConsumo> listaConsumos = dbHelper.selectTodasAsDicas();
    * mais ainda tem mais algumas coisinhas int ai posso fazer quando Lucas ajeitar oq falta*/


    public boolean isEmpty(){//ver se o BD ja etm registros
        db = banconovo.getReadableDatabase();
        String sqlselectTod = "SELECT * FROM TabelaDica";
        Cursor c = db.rawQuery(sqlselectTod, null);
        if(c.moveToFirst())
            return false;//tem algo no BD
        return true;//o BD ta vazio
    }

    //apenas as com id 1
    public List<ModelDicas> selectTodosAsDicas() {//listar as dicas que ja foram notificadas
        List<ModelDicas> listDicas = new ArrayList<ModelDicas>();
        db = banconovo.getReadableDatabase();
        String sqlselectTodosAsDicas = "SELECT * FROM TabelaDica";
        Cursor c = db.rawQuery(sqlselectTodosAsDicas, null);

        if(c.moveToFirst()){
            do {
                if(c.getInt(0) == 1) {
                    int id = c.getInt(0);
                    String titulo = c.getString(1);
                    String descricao = c.getString(2);
                    ModelDicas dica = new ModelDicas(id, titulo, descricao);
                    listDicas.add(dica);
                }
            } while (c.moveToNext());//enquanto tiver algo no BD
        }
        db.close ();
        return listDicas;
    }

    //todas dicas em geral
    public List<ModelDicas> selectTodos() {
        List<ModelDicas> listDicastodas = new ArrayList<ModelDicas>();
        db = banconovo.getReadableDatabase();
        String sqlselectTodos = "SELECT * FROM TabelaDica";
        Cursor c = db.rawQuery(sqlselectTodos, null);

        if(c.moveToFirst()){
            do {
                int id = c.getInt(0);
                String titulo = c.getString(1);
                String descricao = c.getString(2);
                ModelDicas dica = new ModelDicas(id, titulo, descricao);
                listDicastodas.add(dica);
            } while (c.moveToNext());
        }
        db.close ();
        return listDicastodas;
    }


    public ModelDicas desbloquear(){
        db = banconovo.getReadableDatabase();
        String sqldesbloquear = "SELECT * FROM TabelaDica";
        int status = 0;
        String _status = String.valueOf(status);
        Cursor c = db.rawQuery(sqldesbloquear, null);
        ContentValues valores = new ContentValues();
        valores.put("status", 1);
        if(c.moveToFirst())
            do {
                if (c.getInt(1) == 0) {
                    int id = c.getInt(1);
                    String titulo = c.getString(2);
                    String descricao = c.getString(3);
                    ModelDicas dica = new ModelDicas(id, titulo, descricao);
                    db.update("TabelaDica", valores, "status= ?", new String[]{_status});
                    return dica;
                }

            } while (c.moveToNext());

        db.close ();
        return null;
    }
}