package com.example.lucas.energysaving;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class FragmentHistoricoDicas extends android.support.v4.app.Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hist_dicas, container, false);

        preencherListView(view);

        return view;
    }

    private List<ContractDica> obterDicas () {
        DAOdica dbDicas = new DAOdica(getActivity().getApplicationContext());

        List<ContractDica> listaDicas = dbDicas.selectTodosAsDicas(); // listaDicas recebe o listar todos do BD

        return listaDicas;
    }

    private void preencherListView (View view) {
        final List<ContractDica> listaDicas = obterDicas();

        if (listaDicas.size() == 0) {
            AlertDialog.Builder dig = new AlertDialog.Builder(getActivity().getApplicationContext());

            dig.setMessage("Aqui você pode rever as dicas que já foram desbloqueadas. " +
                    "Porém, no momento, ainda não foi desbloqueada nehuma dica. Volte mais tarde.");
            dig.setNeutralButton("OK", null);
            dig.show();
        } else {
            // Cria uma ArryaList com os titulos, para a listagem
            ArrayList<String> list = new ArrayList<>();

            for (ContractDica aux : listaDicas) {
                list.add(aux.getTitulo());
            }

            String[] dicas = list.toArray(new String[list.size()]);

            // Procura a ListView onde vai ser feito a listagem
            ListView listView = (ListView) view.findViewById(R.id.ListaDicas);

            // Cria o adapter de String para a ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                    android.R.layout.simple_list_item_1, dicas);

            listView.setAdapter(adapter); // Conectar o adapter criado com a ListView

            // Quando clicar em algum item da lista, chama a activity detalhe
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity().getApplicationContext(),
                            ActivityDetalheDica.class);

                    ContractDica d = listaDicas.get(position); // Obtem a dica do array
                    intent.putExtra("DICA", d);

                    startActivity(intent); // Inicia a nova activity
                }
            });

        }
    }

    @Override
    public void onResume(){
        super.onResume();
        preencherListView(view);
    }
}
