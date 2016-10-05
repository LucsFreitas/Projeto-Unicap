package com.example.brenon.energysaves3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelperConsumo extends SQLiteOpenHelper{

    private static final  String NOME_BASE = "ConsumosBD";
    private  static int VERSAO_BASE = 5;

    public DbHelperConsumo (Context context ){
        super(context, NOME_BASE, null, VERSAO_BASE);
    }

    public void onCreate(SQLiteDatabase db){ /*metodo para criar a tabelado BD*/
        String sqlCreateTabelaConsumo = "CREATE TABLE Consumo("
                + "data STRING,"
                + "consumo INTEGER,"
                + "custo FLOAT,"
                + "valorcontador LONG"
                + ")";
        db.execSQL(sqlCreateTabelaConsumo);/*cria a tabela Consumo*/
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        /* caso precise atualizar versao*/

        String sqlDropTabelaConsumo = "DROP TABLE Consumo";
        db.execSQL(sqlDropTabelaConsumo);
        onCreate(db);
    }

    public void insertConsumo(Consumo consumo){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("data", consumo.getData());
        cv.put("consumo", consumo.getConsumo());
        cv.put("custo", consumo.getCusto());
        cv.put("valorcontador", consumo.getValorContador());

        db.insert("Consumo", null, cv);

        db.close();

    }

    /*listar tudo do BD
    * para pegar o ultimo inserido pode usar o comando
    * c.moveToLast, pq assim posiciona o ponteiro no ultimo inserido
    * e  para mostrar tods faz assim
    * List<Consumo> listaConsumos = dbHelper.selectTodosOsConsumos();
    * mais ainda tem mais algumas coisinhas int ai posso fazer quando Luca ajeitar oq falta*/

    public List<Consumo> selectTodosOsConsumos() {
        List<Consumo> listConsumos = new ArrayList<Consumo>();

        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectTodosConsumos = "SELECT * FROM consumo";

        Cursor c = db.rawQuery(sqlSelectTodosConsumos, null);
        //esse if seria pq tipo caso ele estiver vazio ele vai armazenando
        // todos os objetos na array pra depois retornar a lista com todos
        if(c.moveToFirst()) {
            do {
                String data = c.getString(0);
                int consumo = c.getInt(1);
                float custo = c.getFloat(2);
                long valorcontador = c.getLong(3);
                Consumo consumoN = new Consumo(data, consumo, valorcontador, custo);
                listConsumos.add(consumoN);
            } while (c.moveToNext());
        }
        db.close ();
        return listConsumos;
    }

    public long retornarUltimo() {
        long consumo = 0;
        SQLiteDatabase db = getReadableDatabase();
        String sqlretornarUltimo = "SELECT * FROM consumo";
        Cursor c = db.rawQuery(sqlretornarUltimo, null);
        if(c.moveToLast()){
            consumo = c.getLong(3);
        }
        db.close ();
        return consumo;
    }
}
