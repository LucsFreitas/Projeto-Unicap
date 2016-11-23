package com.example.brenon.energysaves3;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;

public class NotificationUtil {
    private static PendingIntent getPendingIntent (Context context, Intent intent, int id){
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        // Esta linha mantém a activity pai na pilha de activities
        stackBuilder.addParentStack(intent.getComponent());

        // Configura a intent que vai abrir ao clicar na notificação
        stackBuilder.addNextIntent(intent);

        // Cria a PendingIntent e atualiza caso exista uma com o mesmo id
        PendingIntent p = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);

        return p;
    }

    public static void createSimple (Context context, Intent intent, String contentTitle, String contentText, int id){
        // Notificação padrão que só aparece quando puxa a barra de notificações

        // Cria a PendingIntent (contém a intent original)
        PendingIntent p = getPendingIntent(context, intent, id);

        // Cria a notificação
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setDefaults(Notification.DEFAULT_ALL); // Ativa as configurações default
        b.setSmallIcon(R.drawable.ic_launcher); // Ícone
        b.setContentTitle(contentTitle); // Titulo
        b.setContentText(contentText); // Texto
        b.setContentIntent(p); // Intent que será chamada ao clicar na notificação
        b.setAutoCancel(true); // Autocancela a notificação ao clicar nela

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, b.build());
    }

    public static void createHeadsUp (Context context, Intent intent, String contentTitle, String contentText, int id){
        // Esse é a notificação do lollipop que aparece na tela, por cima do que tiver

        // Cria a PendingIntent (contém a intent original)
        PendingIntent p = getPendingIntent(context, intent, id);

        // Cria a notificação
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setDefaults(Notification.DEFAULT_ALL); // Ativa as configurações default
        b.setSmallIcon(R.drawable.ic_launcher); // Ícone
        b.setContentTitle(contentTitle); // Titulo
        b.setContentText(contentText); // Texto
        b.setContentIntent(p); // Intent que será chamada ao clicar na notificação
        b.setAutoCancel(true); // Autocancela a notificação ao clicar nela
        // No Android 5.0
        b.setColor(Color.GREEN);
        // Heads-up notification
        b.setFullScreenIntent(p,false); // Configura como uma Heads-Up notification

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, b.build());
    }
}
