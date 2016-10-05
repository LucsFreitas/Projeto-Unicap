package Controladores;

import Models.Dica;
import Colecoes.ColecaoDica;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

public class ControllerDica {
    private ColecaoDica listaDicas;    
    Timer timer;
    
    public ControllerDica (){
        listaDicas = new ColecaoDica();
    }
            
    public void gerar_dica(){
        timer = new Timer(); 
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();
        timer.schedule(new dicasDiarias(), time);
    }
    
    class dicasDiarias extends TimerTask {
        @Override
        public void run(){
            String titulo, desc;
            
            titulo = "Que tal economizar no valor da sua conta hoje?";
            desc = "Evite banhos demorados e em horários de pico, das 18h às 21h, "
                    + "e use a chave seletora na posição verão assim vc reduz o consumo em até 30%, "
                    + "e não esqueça ao ensaboar-se, desligue a torneira do chuveiro.";
            
        }
    }
    
    public void adicionar (String titulo, String desc){
        Dica d = new Dica (titulo, desc);
        
        listaDicas.salvar(d);
        System.out.println("Dica Salva");
    }
    
    public void remover (String titulo){
        listaDicas.remover(titulo);
    }            
    
    public void lista_historico(){
        ArrayList <Dica> lista = listaDicas.listar();  
        int i = 1;
        
        for (Dica hist : lista){                
            System.out.println( i + "º Dica:");
            System.out.println("Titulo:\t\t" + hist.getTitulo());
            System.out.println("Descrição:\t" + hist.getDescricao());
        }     
    }
    
}
