package com.example.lucas.energysaving;

import java.io.Serializable;

public class ModelDicas implements Serializable {
    private String titulo;
    private String descricao;
    private int id;
    private int status;

    public ModelDicas(int pid, String pTitulo, String pDesc, int pstatus){
        id = pid;
        titulo = pTitulo;
        descricao = pDesc;
        status = pstatus;
    }

    public String getTitulo (){
        return titulo;
    }

    public int getStatus() {
        return status;
    }

    public String getDescricao (){
        return descricao;
    }

    public int getId(){ return id;}

    public void setId(int id) {
        this.id = id;
    }
}
