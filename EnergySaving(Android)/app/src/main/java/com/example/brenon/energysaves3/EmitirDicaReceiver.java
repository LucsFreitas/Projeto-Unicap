package com.example.brenon.energysaves3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class EmitirDicaReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        BancoControlerDica db = new BancoControlerDica(context);
        ModelDicas d = db.desbloquear();

        if (d != null){
            Intent intent2 = new Intent(context, ActivityDetalheDica.class);
            intent.putExtra("DICA", d);

            String titulo = "Nova Dica Desbloqueada!!!";
            int id = 1;
            NotificationUtil.createHeadsUp(context, intent2, titulo, d.getTitulo(), id);
        }
    }

    public void teste(){

    }
}
