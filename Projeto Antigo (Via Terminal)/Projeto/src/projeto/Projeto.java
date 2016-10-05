package projeto;

import Controladores.ControllerConsumo;
import Controladores.ControllerDica;
import java.util.Scanner;

public class Projeto {
    
    public static void main(String[] args) {
        ControllerConsumo controllerConsumo = new ControllerConsumo();
        ControllerDica controllerDica = new ControllerDica();
        Scanner entrada = new Scanner(System.in);
        
        byte op;
        
        
        do{
            System.out.println("1 - Adicionar Consumo");
            System.out.println("2 - Exibir Histórico de Consumo");
            System.out.println("3 - Calcular Parcial de Consumo");
            System.out.println("4 - Histórico de Dicas");
            System.out.println("5 - Sair do Programa");
            System.out.print("Informe a opção desejada:  ");
            op = entrada.nextByte();
            
            switch (op){
                case 1:
                    int mesAux, anoAux;
                    String data, consumo, valorContador, custo;
                    
                    do{
                        System.out.print("\nInforme o mês do consumo (MM): ");            
                        mesAux = entrada.nextInt();
            
                        if (mesAux < 1 || mesAux > 12)
                            System.out.println("Mes invalido! Tente novamente, ou insira -1 pra sair.");
                    } while (mesAux < 1 || mesAux > 12);
        
                    do{
                        System.out.print("Informe o ano do consumo (AAAA): ");
                        anoAux = entrada.nextInt();
            
                        if (anoAux < 2000)
                            System.out.println("Mes invalido! Tente novamente, ou insira -1 pra sair.");
                    } while (anoAux < 2000);
                    
                    data = mesAux + "/" + anoAux;
                    
                    int comp;
                    entrada.nextLine();
                    System.out.print("Informe o consumo (kWh): ");
                    consumo = entrada.nextLine();
            
                    do{
                        System.out.print("Informe o valor do contador (kWh): ");
                        valorContador = entrada.nextLine();
                    
                    comp = valorContador.compareTo(consumo);                    
                    if (comp < 0)
                        System.out.println("O valor do contador deve ser, no mínimo,"
                                + "igual ao valor do consumo!");
                    } while (comp > 0);
            
                    System.out.print("Informe o valor da conta (R$): ");
                    custo = entrada.nextLine();
                    
                    controllerConsumo.adicionar(data, consumo, custo, valorContador);
                    break;
                    
                case 2:
                    controllerConsumo.listar_historico();
                    break;
                    
                case 3:
                    String novoValor;
                    System.out.print("\nInforme o valor atual do contador: ");
                    novoValor = entrada.nextLine();
                    controllerConsumo.calcular(novoValor);
                    break;
                    
                case 4:
                    controllerDica.lista_historico();
                    break;
                    
                case 5:
                    break;
                default:
                    System.out.println("Opção invalida!");                    
            }
        } while (op != 5);
    }          
   
}
