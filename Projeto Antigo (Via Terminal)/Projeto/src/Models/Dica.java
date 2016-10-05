package Models;

public class Dica {
    private String titulo, descricao;
    
    public Dica (String pTitulo, String pDesc){
        titulo = pTitulo;
        descricao = pDesc;
    }
    
    public String getTitulo (){
        return titulo;
    }
    
    public String getDescricao (){
        return descricao;
    }
}
