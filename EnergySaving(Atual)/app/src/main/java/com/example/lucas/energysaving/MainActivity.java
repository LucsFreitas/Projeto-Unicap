package com.example.lucas.energysaving;

import android.app.AlarmManager;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bAdicionar;
    private Button bCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bCalcular = (Button)findViewById(R.id.ib_ActCalcular);
        bAdicionar = (Button)findViewById(R.id.ib_ActAdicionar);
        bCalcular.setOnClickListener(this);
        bAdicionar.setOnClickListener(this);

        criar_dicas();
        agendarNotificacao();
        preencherListView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        preencherListView();
    }

    @Override
    public void onClick(View v){ // Quando o usuário clicar em Adicionar ou Calcular
        switch (v.getId()) {
            case R.id.ib_ActAdicionar: // Inicia a Activity Adicionar
                Intent intent1 = new Intent (this, ActivityAdicionarConsumo.class);
                startActivity(intent1);
                break;
            case R.id.ib_ActCalcular: // Inicia a Activity Calcular
                Intent intent2 = new Intent (this, ActivityCalcularConsumo.class);
                startActivity(intent2);
                break;
        }
    }

    private void criar_dicas (){
        DAOdica dbDicas = new DAOdica(this);
        boolean resp = dbDicas.isEmpty();

        if (resp){
            int i;
            String titulo;
            String desc;
            int status;

            // Dica 1
            titulo = "Economize durante o dia";
            desc = "Aproveite ao máximo a luz do dia deixando cortinas e portas abertas. " +
                    "Em caso de mesas de trabalho e de leitura, coloque-as próximas às janelas. " +
                    "Essa simples mudança nos cômodos pode gerar muita economia de energia no fim do mês";
            status = 0;
            ContractDica d = new ContractDica(0, titulo, desc, status);
            dbDicas.insertDicas(d);

            // Dica 2
            titulo = "Vai comprar um ventilador?";
            desc = "Lembre-se que quanto maior o diâmetro das hélices, maior o consumo. ";
            status = 0;
            d = new ContractDica(1, titulo, desc, status );
            dbDicas.insertDicas(d);

            // Dica 3
            titulo = "Você sabe qual o horário que a energia é mais cara?";
            desc = "A energia fica mais cara da 18h às 21h. Por isso, se possível, evite usar " +
                    "aparelhos elétricos de alto consumo de energia (como ferros de passar roupa e " +
                    "chuveiros elétricos) durante este horário.";
            status = 0;
            d = new ContractDica(2, titulo, desc, status);
            dbDicas.insertDicas(d);

            // Dica 4
            titulo = "Que tal pintar a casa?";
            desc = "Ambientes pintados com cores claras, especialmente os tetos, conseguem " +
                    "refletir e espalhar a luz para todo o ambiente, possibilitando utilizar " +
                    "lâmpadas de menor potência.";
            status = 0;
            d = new ContractDica(3, titulo, desc, status);
            dbDicas.insertDicas(d);

            // Dica 5
            titulo = "Vai comprar algum equipamento elétrico?";
            desc = "Compre equipamentos com o selo Procel de Economia de Energia, \n" +
                    "de preferência da Categoria A, pois tais equipamentos consumem menos energia.";
            status = 0;
            d = new ContractDica(4, titulo, desc,status);
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
            status = 0;
            d = new ContractDica(5, titulo, desc,status);
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
            status = 0;
            d = new ContractDica(6, titulo, desc,status);
            dbDicas.insertDicas(d);
        }
    }

    private void preencherListView (){
        DAOconsumo dbConsumo = new DAOconsumo(this);

        // listaConsumo recebe o listar todos do BD
        final List<ContractConsumo> listaModelConsumos = dbConsumo.selectTodosOsConsumos();

        if(listaModelConsumos.size() == 0){
            AlertDialog.Builder dig = new AlertDialog.Builder(MainActivity.this);

            dig.setMessage("Você ainda não cadastrou nenhum consumo. Quando cadastrar, será aqui " +
                    "que eles irão ser mostrados.");
            dig.setNeutralButton("OK", null);
            dig.show();
        }
        else {
            ArrayList<String> list = new ArrayList<>(); // Cria uma ArryaList com as datas, para a listagem

            for (ContractConsumo aux : listaModelConsumos) {
                list.add(aux.getData());
            }

            String[] datas = list.toArray(new String[list.size()]);

            ListView listView = (ListView) findViewById(R.id.ListaConsumo);

            // Cria o adapter de String para a ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);

            listView.setAdapter(adapter); // Conectar o adapter criado com a ListView

            // Quando clicar em algum item da lista, chama a activity detalhe
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Intent (origem, destino);
                    Intent intent = new Intent(MainActivity.this, ActivityDetalheConsumo.class);

                    // Obtem o consumo do array
                    ContractConsumo c = listaModelConsumos.get(position);
                    // Insere o consumo na intent para ser passado para a outra Activity
                    intent.putExtra("CONSUMO", c);

                    startActivity(intent); // Inicia a nova activity
                }
            });
        }
    }

    private long getTime(){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.HOUR_OF_DAY, 20);
        c.set(Calendar.MINUTE, 18);
        c.set(Calendar.SECOND, 00);
        //c.add(Calendar.DAY_OF_MONTH, 1);

        long time = c.getTimeInMillis();

        return time;
    }

    private void agendarNotificacao (){
        Intent intent = new Intent(this, EmitirDicaReceiver.class);
        AlarmUtil.scheduleRepeat(this, intent, getTime(), AlarmManager.INTERVAL_DAY);
    }
}
