package com.example.brenon.energysaves3;

/**
 * Created by prefeitura on 06/10/2016.
 */


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

public class BancoControlerConsumo {

    private SQLiteDatabase db;
    private CriarBanco banco;

    public BancoControlerConsumo(Context context){
        banco = new CriarBanco(context);
    }

    public void insertConsumo(ModelConsumo modelConsumo){

        db = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("data", modelConsumo.getData());
        cv.put("consumo", modelConsumo.getConsumo());
        cv.put("custo", modelConsumo.getCusto());
        cv.put("valorcont", modelConsumo.getValorContador());

        db.insert("TabelaConsumo", null, cv);

        db.close();

    }

    public List<ModelConsumo> selectTodosOsConsumos() {
        List<ModelConsumo> listModelConsumos = new ArrayList<ModelConsumo>();

        db = banco.getReadableDatabase();

        String sqlSelectTodosConsumos = "SELECT * FROM TabelaConsumo";

        Cursor c = db.rawQuery(sqlSelectTodosConsumos, null);
        //esse if seria pq tipo caso ele estiver vazio ele vai armazenando
        // todos os objetos na array pra depois retornar a lista com todos
        if(c.moveToFirst()) {
            do {
                String data = c.getString(1);
                int consumo = c.getInt(2);
                float custo = c.getFloat(3);
                long valorcontador = c.getLong(4);
                ModelConsumo modelConsumoN = new ModelConsumo(data, consumo, valorcontador, custo);
                listModelConsumos.add(modelConsumoN);
            } while (c.moveToNext());
        }
        db.close ();
        return listModelConsumos;
    }

    public long retornarUltimo() {
        long consumo = 0;
        db = banco.getReadableDatabase();
        String sqlretornarUltimo = "SELECT * FROM TabelaConsumo";
        Cursor c = db.rawQuery(sqlretornarUltimo, null);
        if(c.moveToLast()){
            consumo = c.getLong(3);
        }
        db.close ();
        return consumo;
    }

}

