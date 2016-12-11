package com.example.lucas.energysaving;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ActivityDetalheCuriosidades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_curiosidades);

        Intent intent = getIntent(); // Recebe os dados passados
        ContractCuriosidades curio = (ContractCuriosidades) intent.getSerializableExtra("CURIOSIDADES"); // Busca o objeto sinalizado como DICA

        TextView titulo = (TextView) findViewById(R.id.tituloC); // Seleciona o TextView com o id Titulo, para inserir o titulo
        titulo.setText(curio.getTitle()); // Insere o titulo no TextView

        TextView desc = (TextView) findViewById(R.id.descC); // Seleciona o TextView com o id Desc, para inserir a descrição
        desc.setText(curio.getDesc()); // Insere a descrição no TextView
        desc.setMovementMethod(new ScrollingMovementMethod());
    }
}
