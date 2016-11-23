package com.example.lucas.energysaving;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by lucas on 22/11/2016.
 */

public class EmitirDicaReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        BancoControllerDica db = new BancoControllerDica(context);
        ModelDicas d = db.desbloquear();

        if (d != null){
            Intent intent2 = new Intent(context, ActivityDetalheDica.class);
            intent.putExtra("DICA", d);

            String titulo = "Nova Dica Desbloqueada!!!";
            int id = 1;
            NotificationUtil.createHeadsUp(context, intent2, titulo, d.getTitulo(), id);
        }
    }
}
