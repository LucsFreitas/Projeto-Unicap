package com.example.brenon.energysaves3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelperDicas extends SQLiteOpenHelper{

    private static final  String NOME_BASE = "DicasBD";
    private  static int VERSAO_BASE = 5;

    public DbHelperDicas(Context context ){
        super(context, NOME_BASE, null, VERSAO_BASE);
    }
    /*metodo para criar a tabelado BD*/
    public void onCreate(SQLiteDatabase db){
        String sqlCreateTabelaDicas = "CREATE TABLE Dicas("
                + "id INTEGER,"
                + "titulo TEXT,"
                + "descricao TEXT"
                +")";
        db.execSQL(sqlCreateTabelaDicas);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        /* caso precise atualizar versao*/

        String sqlDropTabelaDicas = "DROP TABLE Dicas";
        db.execSQL(sqlDropTabelaDicas);
        onCreate(db);
    }

    /*metodo inserir no banco de dados, para inserir faz assim
    * onde tiver o objeto que vai ser inserido faz:
    * DbHelper dbHelper = new DbHelper(this) isso serve para auxiliar na insersão
    * agora faz:
    * dbHelper.insertDicas(nomedoobjetoaqui) */

    public void insertDicas(Dicas dica){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", dica.getId());
        cv.put("titulo", dica.getTitulo());
        cv.put("descricao", dica.getDescricao());
        db.insert("Dicas", null, cv);
        db.close();

    }

    /*listar tudo do BD
    * para pegar o ultimo inserido pode usar o comando
    * c.moveToLast, pq assim posiciona o ponteiro no ultimo inserido
    * e  para mostrar tods faz assim
    * List<Consumo> listaConsumos = dbHelper.selectTodasAsDicas();
    * mais ainda tem mais algumas coisinhas int ai posso fazer quando Lucas ajeitar oq falta*/


    public boolean isEmpty(){
        SQLiteDatabase db = getReadableDatabase();
        String sqlselectTod = "SELECT * FROM Dicas";
        Cursor c = db.rawQuery(sqlselectTod, null);
        if(c.moveToFirst())
            return false;//tem algo no BD
        return true;//o BD ta vazio
    }

    //apenas as com id 1
    public List<Dicas> selectTodosAsDicas() {
        List<Dicas> listDicas = new ArrayList<Dicas>();
        SQLiteDatabase db = getReadableDatabase();
        String sqlselectTodosAsDicas = "SELECT * FROM Dicas";
        Cursor c = db.rawQuery(sqlselectTodosAsDicas, null);

        if(c.moveToFirst()){
            do {
                if(c.getInt(0) == 1) {
                    int id = c.getInt(0);
                    String titulo = c.getString(1);
                    String descricao = c.getString(2);
                    Dicas dica = new Dicas(id, titulo, descricao);
                    listDicas.add(dica);
                }
            } while (c.moveToNext());
        }
        db.close ();
        return listDicas;
    }

    //todas dicas em geral
    public List<Dicas> selectTodos() {
        List<Dicas> listDicastodas = new ArrayList<Dicas>();
        SQLiteDatabase db = getReadableDatabase();
        String sqlselectTodos = "SELECT * FROM Dicas";
        Cursor c = db.rawQuery(sqlselectTodos, null);

        if(c.moveToFirst()){
            do {
                int id = c.getInt(0);
                String titulo = c.getString(1);
                String descricao = c.getString(2);
                Dicas dica = new Dicas(id, titulo, descricao);
                listDicastodas.add(dica);
            } while (c.moveToNext());
        }
        db.close ();
        return listDicastodas;
    }


    public Dicas desbloquear(){
        SQLiteDatabase db = getReadableDatabase();
        db = getReadableDatabase();
        String sqldesbloquear = "SELECT * FROM Dicas";
        Cursor c = db.rawQuery(sqldesbloquear, null);
        ContentValues cv = new ContentValues();
        if(c.moveToFirst())
            do {
                if (c.getInt(0) == 0) {
                    int id = c.getInt(0);
                    String titulo = c.getString(1);
                    String descricao = c.getString(2);
                    Dicas dica = new Dicas(id, titulo, descricao);
                    cv.put("id", 1);
                    return dica;
                }

            } while (c.moveToNext());
        db.close ();
        return null;
    }
}
