package com.example.lucas.energysaving;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriarBanco extends SQLiteOpenHelper {
    private  static int VERSAO= 1;
    private static final String NOME_BANCO = "banco.db";
    private static final String TABELACONSUMO = "TabelaConsumo";
    private static final String ID = "_id";
    private static final String DATA = "data";
    private static final String CONSUMO= "consumo";
    private static final String CUSTO = "custo";
    private static final String VALORCONT = "valorcont";

    private  static int VERSAO1;
    private static final String TABELADICA = "TabelaDica";
    private static final String ID1 = "_id";
    private static final String STATUS= "status";
    private static final String DICA= "dica";
    private static final String DESCRICAO= "descricao";


    public CriarBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELACONSUMO+"("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DATA + " STRING, "
                + CONSUMO + " INTEGER, "
                + CUSTO + " FLOAT, "
                + VALORCONT +" LONG "
                +")";

        String sql2 = "CREATE TABLE "+TABELADICA+"("
                + ID1 + " INTEGER PRIMARY KEY, "
                + STATUS + " INTEGER, "
                + DICA + " TEXT, "
                + DESCRICAO + " TEXT"
                +")";

        db.execSQL(sql);
        db.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELACONSUMO);
        db.execSQL("DROP TABLE IF EXISTS" + TABELADICA);
        onCreate(db);
    }
}
