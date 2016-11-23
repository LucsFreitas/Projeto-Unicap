package com.example.lucas.energysaving;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ActivityHistoricoDicas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_dicas);

        BancoControllerDica dbDicas = new BancoControllerDica(this);

        final List<ModelDicas> listaDicas  = dbDicas.selectTodosAsDicas(); // listaDicas recebe o listar todos do BD

        if(listaDicas.size() == 0){
            AlertDialog.Builder dig = new AlertDialog.Builder(ActivityHistoricoDicas.this);

            dig.setMessage("Nenhuma dica foi notificada");
            dig.setNeutralButton("OK", null);
            dig.show();
        }
        else {
            // Cria uma ArryaList com os titulos, para a listagem
            ArrayList<String> list = new ArrayList<>();

            for (ModelDicas aux : listaDicas) {
                list.add(aux.getTitulo());
            }

            String[] dicas = list.toArray(new String[list.size()]);

            // Procura a ListView onde vai ser feito a listagem
            ListView listView = (ListView) findViewById(R.id.ListaDicas);

            // Cria o adapter de String para a ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dicas);

            listView.setAdapter(adapter); // Conectar o adapter criado com a ListView

            // Quando clicar em algum item da lista, chama a activity detalhe
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ActivityHistoricoDicas.this, ActivityDetalheDica.class);

                    ModelDicas d = listaDicas.get(position); // Obtem a dica do array
                    intent.putExtra("DICA", d);

                    startActivity(intent); // Inicia a nova activity
                }
            });
        }
    }
}
