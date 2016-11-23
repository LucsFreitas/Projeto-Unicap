package com.example.brenon.energysaves3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by prefeitura on 06/10/2016.
 */

public class CriarBanco extends SQLiteOpenHelper{

    private static final String NOME_BANCO = "banco.db";
    private static final String TABELA = "TabelaConsumo";
    private static final String ID = "_id";
    private static final String DATA = "data";
    private static final String CONSUMO= "consumo";
    private static final String CUSTO = "custo";
    private static final String VALORCONT = "valorcont";
    private  static int VERSAO= 1;


    private static final String TABELA2 = "TabelaDica";
    private static final String ID1 = "_id";
    private static final String STATUS= "status";
    private static final String DICA= "dica";
    private static final String DESCRICAO= "descricao";
    private  static int VERSAO1 = 1;

    public CriarBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DATA + " STRING, "
                + CONSUMO + " INTEGER, "
                + CUSTO + " FLOAT, "
                + VALORCONT +" LONG "
                +")";

        String sql2 = "CREATE TABLE "+TABELA2+"("
                + ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + STATUS + " INTEGER, "
                + DICA + " TEXT, "
                + DESCRICAO + " TEXT"
                +")";

        db.execSQL(sql);
        db.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
        db.execSQL("DROP TABLE IF EXISTS" + TABELA2);
        onCreate(db);
    }
}
