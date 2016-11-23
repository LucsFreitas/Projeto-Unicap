package com.example.lucas.energysaving;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityDetalheDica extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_dica);

        Intent intent = getIntent(); // Recebe os dados passados
        ContractDica dica = (ContractDica) intent.getSerializableExtra("DICA"); // Busca o objeto sinalizado como DICA

        TextView titulo = (TextView) findViewById(R.id.titulo); // Seleciona o TextView com o id Titulo, para inserir o titulo
        titulo.setText(dica.getTitulo()); // Insere o titulo no TextView

        TextView desc = (TextView) findViewById(R.id.desc); // Seleciona o TextView com o id Desc, para inserir a descrição
        desc.setText(dica.getDescricao()); // Insere a descrição no TextView
    }
}
