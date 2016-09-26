package Colecoes;

import Models.Dica;
import java.util.ArrayList;

public class ColecaoDica {
    private ArrayList <Dica> lista;
    
    public ColecaoDica (){
        lista = new ArrayList();
    }
    
    public void salvar (Dica d){
        lista.add(d);
    }
    
    public void remover (String data){
        for (Dica d : lista){
            if (d.getTitulo().compareTo(data) == 0)
                lista.remove(d);
        }
    }
    
    public boolean consultar (String data){
        for (Dica d : lista){
            if (d.getTitulo().compareTo(data) == 0)
                return true;
        }
        return false;
    }
    
    public Dica obterUltima (){
        int tam = lista.size();
        
        return lista.get(tam-1);        
    }
    
    public ArrayList <Dica> listar (){
        ArrayList <Dica> array = new ArrayList();
        
        for (Dica d : lista){
            array.add(d);
        }
        
        return array;
    }
    
    public int size(){
        return lista.size();
    }
    
}
