package com.example.lucas.energysaving;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterConsumo extends BaseAdapter {
    private final Context context;
    private List<ContractConsumo> list;

    public AdapterConsumo(Context context, List<ContractConsumo> lista){
        this.context = context;
        this.list = lista;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;

        if (itemView == null){
            itemView = LayoutInflater.from(context).inflate(R.layout.item_consumo, parent, false); // Cria o layout
        }

        ContractConsumo c = (ContractConsumo) getItem(position);

        TextView data = (TextView) itemView.findViewById(R.id.dataConsumo);
        TextView consumo = (TextView) itemView.findViewById(R.id.valorConsumo);

        data.setText(c.getData());
        consumo.setText("R$ " + Float.toString(c.getCusto()));

        return itemView;
    }
}
