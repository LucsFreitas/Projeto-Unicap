package com.example.brenon.energysaves3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class ActivityDetalheConsumo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_consumo);

        Intent intent = getIntent(); // Recebe os dados passados
        ModelConsumo c = (ModelConsumo) intent.getSerializableExtra("CONSUMO"); // Busca o objeto sinalizado como CONSUMO

        TextView data = (TextView) findViewById(R.id.data); // Seleciona o TextView com o id Data, para inserir a data
        data.setText(c.getData()); // Insere a data no TextView

        TextView consumo = (TextView) findViewById(R.id.consumo); // Seleciona o TextView com o id ModelConsumo, para inserir a data
        consumo.setText(String.valueOf(c.getConsumo())); // Insere oconsumo no TextView

        TextView valorCont = (TextView) findViewById(R.id.valorCont); // Seleciona o TextView com o id valorCont, para inserir a data
        valorCont.setText(Long.toString(c.getValorContador())); // Insere o valorCont no TextView

        TextView custo = (TextView) findViewById(R.id.custo); // Seleciona o TextView com o id custo, para inserir a data
        custo.setText(Float.toString(c.getCusto())); // Insere o custo no TextView
    }
}

