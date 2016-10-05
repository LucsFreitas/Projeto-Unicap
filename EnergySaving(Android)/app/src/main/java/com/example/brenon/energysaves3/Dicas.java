package com.example.brenon.energysaves3;

import java.io.Serializable;

public class Dicas implements Serializable{
    private String titulo;
    private String descricao;
    private int id;

    public Dicas (int pid, String pTitulo, String pDesc){
        id = pid;
        titulo = pTitulo;
        descricao = pDesc;
    }

    public String getTitulo (){
        return titulo;
    }

    public String getDescricao (){
        return descricao;
    }

    public int getId(){ return id;}

    public void setId(int id) {
        this.id = id;
    }
}