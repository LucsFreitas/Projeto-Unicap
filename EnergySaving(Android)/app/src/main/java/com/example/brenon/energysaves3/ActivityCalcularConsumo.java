package com.example.brenon.energysaves3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import java.text.DecimalFormat;

public class ActivityCalcularConsumo extends AppCompatActivity  implements View.OnClickListener{
    //menu
    private ImageButton ib_Calcular;
    private EditText cont_atual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ib_Calcular = (ImageButton)findViewById(R.id.ib_Calcular);
        ib_Calcular.setOnClickListener(this);

        cont_atual = (EditText) findViewById(R.id.cont_atual);
    }
    public void onClick(View v){
        AlertDialog.Builder dig = new AlertDialog.Builder(ActivityCalcularConsumo.this);

        DbHelperConsumo db = new DbHelperConsumo(this);
        String valor = cont_atual.getText().toString();

        if (valor.trim().isEmpty()){
            dig.setMessage("Preencha o campo antes de efetuar o calculo");
            dig.setNeutralButton("Ok",null);
        }
        else {

            long valorContAtual = Long.valueOf(valor).longValue();
            long valorContAnterior = db.retornarUltimo();
            if (valorContAnterior < valorContAtual) {
                float result = (valorContAtual - valorContAnterior) * 0.56f;
                DecimalFormat formatador = new DecimalFormat("0.00");
                dig.setMessage("Resultado: R$ g" + formatador.format(result));
                dig.setNeutralButton("Ok", null);
            } else {
                dig.setMessage("O valor do contador tem que ser maior do que o anterior!");
                dig.setNeutralButton("Ok", null);
            }
        }
        dig.show();
    }
    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //menu
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
}