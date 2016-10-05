package Controladores;

import Models.Consumo;
import Colecoes.ColecaoConsumo;

import java.util.ArrayList;

public class ControllerConsumo {
    private ColecaoConsumo listaConsumo;
    
    public ControllerConsumo (){
        listaConsumo = new ColecaoConsumo();
    }
    
    public void adicionar (String data, String consumo, String custo, String valorContador){
        boolean contem = listaConsumo.consultar(data);
        
        if (!contem) {
            Consumo c = new Consumo(data, consumo, custo, valorContador);
            listaConsumo.salvar(c);
            System.out.println("\nDados Salvos com sucesso!\n");
        } else
            System.out.println("\nJá existe um consumo adicionado para este mês!\n");
        
    }
    
    public void listar_historico (){
        ArrayList <Consumo> lista = listaConsumo.listar();  
        int i = 1;
        
        for (Consumo hist : lista){                
            System.out.println( i + "º Registo:");
            System.out.println("Data:\t\t\t" + hist.getData());
            System.out.println("Consumo:\t\t" + hist.getConsumo());
            System.out.println("Valor do Contador:\t" + hist.getValorContador());
            System.out.println("Valor da Conta:\t\t" + hist.getCusto());
            System.out.println("");
        }        
        
    }
    
    public void calcular(String novoValor){        
        int tam;
        double calculo;
        
        tam = listaConsumo.size();
                
        if (tam > 0){
            Consumo c = listaConsumo.obterUltimo();
            int valor1, valor2;
            
            do{
                valor1 = Integer.valueOf(novoValor).intValue();
                valor2 = Integer.valueOf(c.getValorContador()).intValue();
                if(valor1 < valor2)
                    System.out.println("O valor atual do contador deve ser maior que o anterior!");
            }while(valor1 < valor2);
            
            calculo = Double.valueOf(novoValor).doubleValue() - Double.valueOf(c.getValorContador()).doubleValue();
            calculo = calculo * 0.56;
        
            System.out.printf("O valor aproximado da sua proxima conta será: R$ %2f", calculo);
        }
        else
            System.out.println("Desculpe, esta função só é acessível após a inserção do 1º consumo mensal.");
    }
}

