package com.example.brenon.energysaves3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BancoControlerDica dbDicas = new BancoControlerDica(this);

        final List<ModelDicas> listaDicas  = dbDicas.selectTodosAsDicas(); // listaDicas recebe o listar todos do BD

        if(listaDicas.size() == 0){
            AlertDialog.Builder dig = new AlertDialog.Builder(ActivityHistoricoDicas.this);

            dig.setMessage("Nenhuma dica foi notificada");
            dig.setNeutralButton("OK", null);
            dig.show();
        }
        else {
            ArrayList<String> list = new ArrayList<>(); // Cria uma ArryaList com os titulos, para a listagem

            for (ModelDicas aux : listaDicas) {
                list.add(aux.getTitulo());
            }

            String[] dicas = list.toArray(new String[list.size()]);

            ListView listView = (ListView) findViewById(R.id.listaD); // Procura a ListView onde vai ser feito a listagem

            // Cria o adapter de String para a ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dicas);

            listView.setAdapter(adapter); // Conectar o adapter criado com a ListView

            // Quando clicar em algum item da lista, chama a activity detalhe
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ActivityHistoricoDicas.this, ActivityDetalheDica.class); // Intent (origem, destino);

                    ModelDicas d = listaDicas.get(position); // Obtem a dica do array
                    intent.putExtra("DICA", d); // Insere a dica na intent para ser passado para a outra Activity

                    startActivity(intent); // Inicia a nova activity
                }
            });
        }
    }
    //Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //Coloca todas as Activitys para o usuario escolher uma, tambem faz parte do menu
        switch (id){
            case R.id.action_settings:
                Intent it = new Intent(this, MainActivity.class);
                startActivity(it);
                break;
            case R.id.action_adicionar:
                Intent it2 = new Intent(this, ActivityAdicionarConsumo.class);
                startActivity(it2);
                break;
            case R.id.action_calcular:
                Intent it3 = new Intent(this, ActivityCalcularConsumo.class);
                startActivity(it3);
                break;
            case R.id.action_historico:
                Intent it4 = new Intent(this, ActivityHistoricoConsumo.class);
                startActivity(it4);
                break;
            case R.id.action_dicas:
                Intent it5 = new Intent(this, ActivityHistoricoDicas.class);
                startActivity(it5);
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
