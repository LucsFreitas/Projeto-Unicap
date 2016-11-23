package com.example.lucas.energysaving;

import java.io.Serializable;

public class ModelConsumo implements Serializable {
    private String data;
    private int consumo;
    private float custo;
    private long valorContador;

    public ModelConsumo(String pdata, int pconsumo, long pValorContador, float pcusto){
        data = pdata;
        consumo = pconsumo;
        custo = pcusto;
        valorContador = pValorContador;
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
