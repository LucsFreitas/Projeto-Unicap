package Models;

public class Consumo {
    private String data, consumo, custo, valorContador;
        
    public Consumo (String pdata, String pconsumo, String pcusto, String pValorContador){
        data = pdata;
        consumo = pconsumo;
        custo = pcusto;
        valorContador = pValorContador;
    }
    
    public String getData (){
        return data;
    }
    
    public String getConsumo (){
        return consumo;
    }
    
    public String getCusto (){
        return custo;
    }
    
    public String getValorContador (){
        return valorContador;
    }
}