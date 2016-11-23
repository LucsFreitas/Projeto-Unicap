package com.example.lucas.energysaving;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class ActivityCalcularConsumo extends AppCompatActivity implements View.OnClickListener {
    private Button ib_Calcular;
    private EditText cont_atual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_consumo);

        ib_Calcular = (Button)findViewById(R.id.ib_Calcul);
        ib_Calcular.setOnClickListener(this);

        cont_atual = (EditText) findViewById(R.id.cont_atual);
    }

    public void onClick(View v){
        AlertDialog.Builder dig = new AlertDialog.Builder(ActivityCalcularConsumo.this);

        BancoControllerConsumo db = new BancoControllerConsumo(this);
        String valor = cont_atual.getText().toString();

        if (valor.trim().isEmpty()){
            dig.setMessage("Preencha o campo antes de efetuar o Cálculo");
            dig.setNeutralButton("Ok",null);
        }
        else {

            long valorContAtual = Long.valueOf(valor).longValue();
            long valorContAnterior = db.retornarUltimo();

            if (valorContAnterior < valorContAtual) {
                float result = (valorContAtual - valorContAnterior) * 0.56f;
                DecimalFormat formatador = new DecimalFormat("0.00");
                dig.setMessage("Resultado: R$ " + formatador.format(result));
                dig.setNeutralButton("Ok", null);
            } else {
                dig.setMessage("O valor do contador tem que ser maior do que o informado " +
                        "no cadastro do consumo anterior!");
                dig.setNeutralButton("Ok", null);
            }
        }
        dig.show();
    }
}
