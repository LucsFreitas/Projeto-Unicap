package com.example.lucas.energysaving;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentHistoricoConsumo extends android.support.v4.app.Fragment {
    private CreateListenner mListener;
    private View view;

    private Button bAdicionar;
    private Button bCalcular;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hist_consumo, container, false);

        bAdicionar = (Button) view.findViewById(R.id.ib_ActAdicionar);
        bAdicionar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mListener.onClickAdd();
            }
        });

        bCalcular = (Button) view.findViewById(R.id.ib_ActCalcular);
        bCalcular.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mListener.onClickCalc();
            }
        });

        preencherListView(view);
        return view;
    }

    private List<ContractConsumo> obterConsumos (){
        DAOconsumo dbConsumo = new DAOconsumo(getActivity().getApplicationContext());

        // listaConsumo recebe o listar todos do BD
        List<ContractConsumo> temp = dbConsumo.selectTodosOsConsumos();

        Collections.sort(temp);
        return temp;
    }

    private void preencherListView (View view){
        final List<ContractConsumo> listaModelConsumos = obterConsumos();

        if(listaModelConsumos.size() != 0) {
            ArrayList<String> list = new ArrayList<>(); // Cria uma ArryaList com as datas, para a listagem

            for (ContractConsumo aux : listaModelConsumos) {
                list.add(aux.getData());
            }

            String[] datas = list.toArray(new String[list.size()]);

            ListView listView = (ListView) view.findViewById(R.id.ListaConsumo);

            // Cria o adapter de String para a ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, datas);

            listView.setAdapter(adapter); // Conectar o adapter criado com a ListView

            // Quando clicar em algum item da lista, chama a activity detalhe
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Intent (origem, destino);
                    Intent intent = new Intent(getActivity().getApplicationContext(), ActivityDetalheConsumo.class);

                    // Obtem o consumo do array
                    ContractConsumo c = listaModelConsumos.get(position);
                    // Insere o consumo na intent para ser passado para a outra Activity
                    intent.putExtra("CONSUMO", c);

                    startActivity(intent); // Inicia a nova activity
                }
            });
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (CreateListenner) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " deve implementar CreateEmailListener");
        }
    }

    public void onDestroy() {
        super.onDestroy();
        mListener = null;
    }

    @Override
    public void onResume(){
        super.onResume();
        preencherListView(view);
    }

    public interface CreateListenner{
        void onClickAdd();
        void onClickCalc();
    }
}
