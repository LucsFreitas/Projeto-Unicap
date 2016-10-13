package com.example.brenon.energysaves3;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    //Menu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        criar_dicas();
        gerar_dica();
    }
    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
    //Coloca todas as Activitys para o usuario escolher uma, tambem faz parte do menu
        switch (id){
            case R.id.action_settings:
                Intent it = new Intent(this, MainActivity.class);
                startActivity(it);
                break;
            case R.id.action_adicionar:
                Intent it2 = new Intent(this, ActivityAdicionarConsumo.class);
                startActivity(it2);
                break;
            case R.id.action_calcular:
                Intent it3 = new Intent(this, ActivityCalcularConsumo.class);
                startActivity(it3);
                break;
            case R.id.action_historico:
                Intent it4 = new Intent(this, ActivityHistoricoConsumo.class);
                startActivity(it4);
                break;
            case R.id.action_dicas:
                Intent it5 = new Intent(this, ActivityHistoricoDicas.class);
                startActivity(it5);
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    private void criar_dicas (){
        BancoControlerDica dbDicas = new BancoControlerDica(this);
        boolean resp = dbDicas.isEmpty();

        if (resp){
            int i;
            String titulo;
            String desc;

            // Dica 1
            titulo = "Economize durante o dia";
            desc = "Aproveite ao máximo a luz do dia deixando cortinas e portas abertas. " +
                    "Em caso de mesas de trabalho e de leitura, coloque-as próximas às janelas. " +
                    "Essa simples mudança nos cômodos pode gerar muita economia de energia no fim do mês";
            ModelDicas d = new ModelDicas(0, titulo, desc);
            dbDicas.insertDicas(d);

            // Dica 2
            titulo = "Vai comprar um ventilador?";
            desc = "Lembre-se que quanto maior o diâmetro das hélices, maior o consumo. ";
            d = new ModelDicas(0, titulo, desc);
            dbDicas.insertDicas(d);

            // Dica 3
            titulo = "Você sabe qual o horário que a energia é mais cara?";
            desc = "A energia fica mais cara da 18h às 21h. Por isso, se possível, evite usar " +
                    "aparelhos elétricos de alto consumo de energia (como ferros de passar roupa e " +
                    "chuveiros elétricos) durante este horário.";
            d = new ModelDicas(0, titulo, desc);
            dbDicas.insertDicas(d);

            // Dica 4
            titulo = "Que tal pintar a casa?";
            desc = "Ambientes pintados com cores claras, especialmente os tetos, conseguem " +
                    "refletir e espalhar a luz para todo o ambiente, possibilitando utilizar " +
                    "lâmpadas de menor potência.";
            d = new ModelDicas(0, titulo, desc);
            dbDicas.insertDicas(d);

            // Dica 5
            titulo = "Vai comprar algum equipamento elétrico?";
            desc = "Compre equipamentos com o selo Procel de Economia de Energia, \n" +
                    "de preferência da Categoria A, pois tais equipamentos consumem menos energia.";
            d = new ModelDicas(0, titulo, desc);
            dbDicas.insertDicas(d);

            // Dica 6
            titulo = "Lampadas fluorescentes x incandescentes";
            desc = "Lampadas incandescentes geram a luz através de um filamento de tungstenio, onde " +
                    "ao passar corrente, os átomos que o compôem se aquecem, gerando luminosidade, " +
                    "porém tendo uma grande perca de energia devido ao calor gerado. As lâmpadas " +
                    "fluorescentes tem um gás em seu interior que, após terem suas moléculas " +
                    "excitadas pela corrente eletrica, liberam energia.  A maior parte da energia " +
                    "fornecida para lâmpadas fluorescentes é convertida em luz, havendo pouquissimas " +
                    "perdas. Isso as torna mais eficientes, além de durarem cerca de 8 mil horas, " +
                    "contra 1 mil das incandescentes. Por isso, se ainda houver alguma lâmpada " +
                    "incandescente na sua residência, sugerimos substitui-la por uma fluorescente.";
            d = new ModelDicas(0, titulo, desc);
            dbDicas.insertDicas(d);

            // Dica 7
            titulo = "Como economizar energia no computador? desliga-lo ou deixar ligado?";
            desc = "Se o tempo de pausa entre um uso e outro do computador for pequeno, em torno " +
                    "de 15 minutos, por exemplo, não vale à pena ligar e desligar todos os " +
                    "componentes. Apenas desligar o monitor, que consome bastante energia, já " +
                    "ajuda na economia. Agora se as pausas entre os usos forem longas, mais de uma " +
                    "hora, os especialistas aconselham desligar tudo. Outra opção é programá-los " +
                    "para entrar em modo de espera ou desligamento automático após uma hora sem uso, " +
                    "para o caso de o usuário esquecê-lo ligado. Se puder optar entre um computador " +
                    "e um laptop ou notebook prefira esses últimos, que são muito mais econômicos.";
            d = new ModelDicas(0, titulo, desc);
            dbDicas.insertDicas(d);
        }
    }

    public void gerar_dica(){
        Timer timer = new Timer();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 02);
        calendar.set(Calendar.SECOND, 02);
        Date time = calendar.getTime();
        timer.schedule(new dicasDiarias(), time);
    }

    class dicasDiarias extends TimerTask {
        @Override
        public void run(){
            BancoControlerDica db = new BancoControlerDica(MainActivity.this);

            ModelDicas d = db.desbloquear();

            if (d != null){
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(MainActivity.this)
                                .setSmallIcon(R.drawable.ic_launcher)
                                .setAutoCancel(true)
                                .setContentTitle("Nova Dica Desbloqueada")
                                .setContentText(d.getTitulo());

                Intent resultIntent = new Intent(MainActivity.this, ActivityDetalheDica.class);
                resultIntent.putExtra("DICA", d);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);

                stackBuilder.addParentStack(ActivityDetalheDica.class);

                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(0, mBuilder.build());
            }

        }
    }
}
