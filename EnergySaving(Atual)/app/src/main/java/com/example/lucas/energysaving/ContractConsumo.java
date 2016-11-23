package com.example.lucas.energysaving;

import java.io.Serializable;

/**
 * Created by Flavianne on 23/11/2016.
 */


public class ContractConsumo implements Serializable{


    private String data;
    private int consumo;
    private float custo;
    private long valorContador;

    private ContractConsumo(){}

    public ContractConsumo(String pdata, int pconsumo, float pcusto, long pcontador){
        this.data = pdata;
        this.consumo = pconsumo;
        this.custo = pcusto;
        this.valorContador = pcontador;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getConsumo() {
        return consumo;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public long getValorContador() {
        return valorContador;
    }

    public void setValorContador(long valorContador) {
        this.valorContador = valorContador;
    }



}
