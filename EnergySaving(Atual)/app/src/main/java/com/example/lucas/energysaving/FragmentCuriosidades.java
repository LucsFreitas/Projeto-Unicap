package com.example.lucas.energysaving;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brenon on 11/12/2016.
 */

public class FragmentCuriosidades extends android.support.v4.app.Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_curiosidades, container, false);

        preencherListView(view);

        return view;
    }

    private List<ContractCuriosidades> criarCuriosidades(){
        String title;
        String desc;
        List<ContractCuriosidades> list = new ArrayList<ContractCuriosidades>();
        ContractCuriosidades c;
        //Curiosidade 1
        title = "Luz Solar";
        desc = "Demora cerca de 8 minutos para a " +
                "luz do Sol viajar do Sol à Terra?";
        c = new ContractCuriosidades(title, desc);
        list.add(c);

        //Curiosidade 2
        title = "Descoberta da Eletricidade";
        desc = "A electricidade foi descoberta pelo grego Tales por volta de 600 a.C, " +
                "constatando que ao esfregar um pedaço de âmbar com um pano este atraía pequenos objectos";
        c = new ContractCuriosidades(title, desc);
        list.add(c);

        //Curiosidade 3
        title = "Lampadas Fluoresccente";
        desc = "Uma lâmpada fluorescente usa 75% menos " +
                "energia do que as típicas lâmpadas incandescentes, e duram 10 vezes mais";
        c = new ContractCuriosidades(title, desc);
        list.add(c);

        //Curiosidade 4
        title = "Chuveiros Eletricos";
        desc = "Com um chuveiro de baixo consumo de água, uma família de quatro pessoas, cada um tomando " +
                "um duche de 5 minutos por dia pode economizar 200 euros por ano em despesas de aquecimento de água";
        c = new ContractCuriosidades(title, desc);
        list.add(c);

        //Curiosidade 5
        title = "Campeão em consumir energia";
        desc = "Os Estados Unidos consomem 25% da energia mundial";
        c = new ContractCuriosidades(title, desc);
        list.add(c);

        //Curiosidade 6
        title = "Maquina de Lavar";
        desc = "Ao diminuir a temperatura de lavagem da máquina de lavar roupa de 60°C para 40°C" +
                "pode economizar até 50% do consumo de energia";
        c = new ContractCuriosidades(title, desc);
        list.add(c);

        //Curiosidade 7
        title = "Campeões em Consumo de energia";
        desc = "O frigorífico e congelador absorvem 30% do consumo doméstico de " +
                "electricidade e as máquinas de lavar e secar, cerca de 10%";
        c = new ContractCuriosidades(title, desc);
        list.add(c);

        //Curiosidade 8
        title = "Consumo de um Avião";
        desc = "Um avião Jumbo com o tanque de combustível cheio é suficiente" +
                " para conduzir um automóvel quatro vezes à volta do mundo";
        c = new ContractCuriosidades(title, desc);
        list.add(c);
        return list;
    }
    private void preencherListView (View view) {
        final List<ContractCuriosidades> listaCurio = criarCuriosidades();

        if (listaCurio.size() > 0){
            ListView listView = (ListView) view.findViewById(R.id.ListaCurio);

            listView.setAdapter(new AdapterCuriosidades(getActivity().getApplicationContext(),
                    listaCurio));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Intent (origem, destino);
                    Intent intent = new Intent(getActivity().getApplicationContext(), ActivityDetalheCuriosidades.class);

                    // Obtem o consumo do array
                    ContractCuriosidades c = listaCurio.get(position);
                    // Insere o consumo na intent para ser passado para a outra Activity
                    intent.putExtra("CURIOSIDADES", c);

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
