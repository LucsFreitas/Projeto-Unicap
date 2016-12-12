package com.example.lucas.energysaving;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterDica extends BaseAdapter {
    private final Context context;
    private List<ContractDica> list;

    public AdapterDica(Context context, List<ContractDica> lista){
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
            itemView = LayoutInflater.from(context).inflate(R.layout.item_dica, parent, false); // Cria o layout
        }

        ContractDica d = (ContractDica) getItem(position);

        TextView titulo = (TextView) itemView.findViewById(R.id.tit);
        TextView desc = (TextView) itemView.findViewById(R.id.desc2);

        titulo.setText(d.getTitulo());
        desc.setText(d.getDescricao());

        return itemView;
    }
}
