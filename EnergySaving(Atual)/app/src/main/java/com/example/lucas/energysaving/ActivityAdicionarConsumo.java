package com.example.lucas.energysaving;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActivityAdicionarConsumo extends AppCompatActivity implements View.OnClickListener{
    private Button bCancelar; //Aqui esta as variaveis e os campos da tela de Adicionar Conta
    private Button bSalvar;
    private EditText textConsumo;
    private EditText textAno;
    private EditText textCusto;
    private EditText textContador;
    private EditText textMes;//até aqui

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_consumo);

        textConsumo = (EditText) findViewById(R.id.textConsumo); //Aqui realizo a chamada para cada ação da tela de Adicionar
        textContador = (EditText) findViewById(R.id.textContador); // aqui é para o tipo texto
        textMes = (EditText) findViewById(R.id.textMes); // aqui é para o tipo texto
        textAno = (EditText) findViewById(R.id.textAno);
        textCusto = (EditText) findViewById(R.id.textCusto);
        // Configure o SimpleDateFormat no onCreate ou onCreateView

        bSalvar = (Button) findViewById(R.id.bSalvar); // aqui o tipo da ação é um botão
        bSalvar.setOnClickListener(this);

        bCancelar = (Button) findViewById(R.id.bCancelar); // aqui o tipo da ação é um botão
        bCancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) { //nessa parte ele vê se o usuario apertou o botao salvar ou cancelar fazendo ações diferentes
            case R.id.bSalvar: // Mostra que foi enviado
                AlertDialog.Builder dig = new AlertDialog.Builder(ActivityAdicionarConsumo.this);
                String v_mes = textMes.getText().toString(); //pega os valores digitados em Data
                String v_ano = textAno.getText().toString();
                String v_custo = textCusto.getText().toString(); //pega os valores digitados em Data
                String v_cont = textContador.getText().toString(); //pega os valores digitados em Contador
                String v_consumo = textConsumo.getText().toString();
                if(v_mes.trim().isEmpty() || v_ano.trim().isEmpty() || v_cont.trim().isEmpty() || v_consumo.trim().isEmpty() || v_custo.trim().isEmpty()){
                    dig.setMessage("Preencha todos os campos antes de salvar");
                    dig.setNeutralButton("OK", null);
                    dig.show();
                }
                else {
                    int mes = Integer.valueOf(v_mes).intValue();
                    int ano = Integer.valueOf(v_ano).intValue();
                    float custo = Float.valueOf(v_custo).floatValue();
                    long valorCont = Long.valueOf(v_cont).longValue();
                    int consumo = Integer.valueOf(v_consumo).intValue();

                    Calendar cal = Calendar.getInstance();

                    Date data_atual = new Date(System.currentTimeMillis());
                    Date data_informada = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
                    try {
                        data_informada = sdf.parse(v_mes + "/" + v_ano);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    int flag = 1;
                    if (mes < 1 || mes > 12) {
                        dig.setMessage("Mes Invalido");
                        dig.setNeutralButton("OK", null);

                        flag = 0;
                    }
                    if (ano < 2015){
                        dig.setMessage("Ano Invalido. O ano deve ser maior ou igual a 2015.");
                        dig.setNeutralButton("OK", null);
                        dig.show();
                        flag = 0;
                    }
                    if (ano > cal.get(Calendar.YEAR)) {
                        dig.setMessage("Ano Invalido. O ano informado é superior ao atual.");
                        dig.setNeutralButton("OK", null);
                        dig.show();
                        flag = 0;
                    }
                    if (data_atual.compareTo(data_informada) < 0) {
                        dig.setMessage("A data deve ser anterior à atual");
                        dig.setNeutralButton("OK", null);
                        dig.show();
                        flag = 0;
                    }
                    if (custo < 0) {
                        dig.setMessage("Custo invalido");
                        dig.setNeutralButton("OK", null);
                        dig.show();
                        flag = 0;
                    }
                    if (consumo < 0) {
                        dig.setMessage("Consumo invalido");
                        dig.setNeutralButton("OK", null);
                        dig.show();
                        flag = 0;
                    }
                    if (valorCont < 0) {
                        dig.setMessage("Valor do Contador invalido");
                        dig.setNeutralButton("OK", null);
                        dig.show();
                        flag = 0;
                    }
                    if (flag == 1) {
                        BancoControllerConsumo db = new BancoControllerConsumo(this);
                        ModelConsumo c = new ModelConsumo(mes + "/" + ano, consumo, valorCont, custo);

                        db.insertConsumo(c);

                        Toast toast = Toast.makeText(this, "Consumo Adicionado com Sucesso",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                    }
                }
                break;
            case R.id.bCancelar: //Leva o usuario ao menu principal
                finish();
                break;
        }
    }
}
