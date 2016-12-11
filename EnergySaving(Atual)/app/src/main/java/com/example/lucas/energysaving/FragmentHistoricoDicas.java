package com.example.lucas.energysaving;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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

        if (listaDicas.size() > 0){
            ListView listView = (ListView) view.findViewById(R.id.ListaDicas);

            listView.setAdapter(new AdapterDica(getActivity().getApplicationContext(),
                    listaDicas));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Intent (origem, destino);
                    Intent intent = new Intent(getActivity().getApplicationContext(), ActivityDetalheDica.class);

                    // Obtem o consumo do array
                    ContractDica d = listaDicas.get(position);
                    // Insere o consumo na intent para ser passado para a outra Activity
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
