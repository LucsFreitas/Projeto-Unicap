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

public class DAOconsumo {
    private SQLiteDatabase db;
    private CriarBanco banco;

    public DAOconsumo(Context context){
        banco = new CriarBanco(context);
    }

    public void insertConsumo(ContractConsumo contractConsumo){

        db = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("data", contractConsumo.getData());
        cv.put("consumo",contractConsumo.getConsumo());
        cv.put("custo",contractConsumo.getCusto());
        cv.put("valorcont", contractConsumo.getValorContador());
        db.insert("TabelaConsumo", null, cv);
        db.close();

    }

    public List<ContractConsumo> selectTodosOsConsumos() {
        List<ContractConsumo> listModelConsumos = new ArrayList<ContractConsumo>();
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
                ContractConsumo modelConsumoN = new ContractConsumo(data, consumo, custo,  valorcontador);
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


    /*Para usar o buscarData chama ele paraecedio com o desbloquear dica, só que ao invés de passar sem nada nos parametros
    * passa a nova data que a pessoa está querendo ser inserida ai aqui ele vai buscar e vai ver se existe se ja existe ele monta um objeto
    * com todos os dados daquela data e retorna senão ele retorna nullo ai sim pode inserir o novo consumo
    * fica tipo
    * retorno = buscarData(a nova data aqui)
    * se o retorno for Nullo insere se não consumo ja existe*/

    public ContractConsumo buscarData(String dataNova){
        db = banco.getReadableDatabase();
        String sqlbuscar = "SELECT * FROM TabelaConsumo";
        Cursor c = db.rawQuery(sqlbuscar, null);
        ContentValues valores = new ContentValues();
        if(c.moveToFirst())
            do {

                if (c.getString(1) == dataNova) {
                    String data = c.getString(1);
                    int consumo = c.getInt(2);
                    float custo = c.getInt(3);
                    long valorContador = c.getLong(4);
                    ContractConsumo consumoR = new ContractConsumo(data, consumo, custo, valorContador);
                    String _id = String.valueOf(c.getInt(1));
                    return consumoR;
                }
            } while (c.moveToNext());

        db.close ();
        return null;
    }

}
