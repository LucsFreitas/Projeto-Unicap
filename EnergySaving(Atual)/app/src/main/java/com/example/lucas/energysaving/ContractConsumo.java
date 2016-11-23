package com.example.lucas.energysaving;

import java.io.Serializable;

public class ContractConsumo implements Serializable, Comparable<ContractConsumo> {


    private String data;
    private int consumo;
    private float custo;
    private long valorContador;

    public ContractConsumo(String pdata, int pconsumo, float pcusto, long pcontador){
        this.data = pdata;
        this.consumo = pconsumo;
        this.custo = pcusto;
        this.valorContador = pcontador;
    }

    public String getData() {
        return data;
    }

    public int getConsumo() {
        return consumo;
    }

    public float getCusto() {
        return custo;
    }

    public long getValorContador() {
        return valorContador;
    }

    @Override
    public int compareTo(ContractConsumo o) {
        char [] temp1 = this.data.toCharArray();
        char [] temp2 = o.getData().toCharArray();

        int mes1, ano1;
        mes1 = ((int) temp1[0] * 10) + (int) temp1[1] ;
        ano1 = ((int) temp1[3] * 1000) + ((int) temp1[4] * 100) +
                ((int) temp1[5] * 10) + ((int) temp1[6]);

        int mes2, ano2;
        mes2 = ((int) temp2[0] * 10) + (int) temp2[1] ;
        ano2 = ((int) temp2[3] * 1000) + ((int) temp2[4] * 100) +
                ((int) temp2[5] * 10) + ((int) temp2[6]);

        if (ano1 > ano2)
            return -1;
        else if (ano1 < ano2)
            return 1;

        else{
            if (mes1 > mes2)
                return -1;
            else
                return 1;
        }
    }
}
