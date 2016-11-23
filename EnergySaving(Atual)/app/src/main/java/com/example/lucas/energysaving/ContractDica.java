package com.example.lucas.energysaving;


import java.io.Serializable;

public class ContractDica implements Serializable {
    private int id;
    private String titulo;
    private String descricao;
    private int status;

    private  ContractDica(){}

    public ContractDica(int pid, String pTitulo, String pDesc, int pstatus){
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
