package Colecoes;

import Models.Consumo;
import java.util.ArrayList;

public class ColecaoConsumo {
    private ArrayList <Consumo> lista;
    
    public ColecaoConsumo (){
        lista = new ArrayList();
    }
    
    public void salvar (Consumo c){
        lista.add(c);
    }
    
    public void remover (Consumo c){
        lista.remove(c);
    }
    
    public boolean consultar (String data){
        for (Consumo c : lista){
            if (c.getData().compareTo(data) == 0)
                return true;
        }
        return false;
    }
    
    public Consumo obterUltimo (){
        int tam = lista.size();
        
        return lista.get(tam-1);
         
    }
    
    public ArrayList <Consumo> listar (){
        ArrayList <Consumo> array = new ArrayList();
        
        for (Consumo c : lista){
            array.add(c);
        }
        
        return array;
    }
    
    public int size(){
        return lista.size();
    }
    
}
