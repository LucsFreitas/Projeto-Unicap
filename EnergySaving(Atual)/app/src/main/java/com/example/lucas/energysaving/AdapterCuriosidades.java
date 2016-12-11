package com.example.lucas.energysaving;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Brenon on 11/12/2016.
 */

public class AdapterCuriosidades extends BaseAdapter {
    private final Context context;
    private List<ContractCuriosidades> list;

    public AdapterCuriosidades(Context context, List<ContractCuriosidades> lista){
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
            itemView = LayoutInflater.from(context).inflate(R.layout.item_curiosidades, parent, false); // Cria o layout
        }

        ContractCuriosidades c = (ContractCuriosidades) getItem(position);

        TextView titulo = (TextView) itemView.findViewById(R.id.titl);
        TextView desc = (TextView) itemView.findViewById(R.id.descri);

        titulo.setText(c.getTitle());
        desc.setText(c.getDesc());

        return itemView;
    }
}
