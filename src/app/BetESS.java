/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import Eventos.Evento;
import Utilizadores.Aposta;
import Utilizadores.Apostador;
import Utilizadores.Bookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author PauloCardoso
 */
public class BetESS {

    /**
     * @param args the command line arguments
     */
    private static HashMap<Integer,Apostador> apostadores;
    private static ArrayList<Bookie> bookies;
    private static HashMap<Integer,Evento> eventos;
    
    public static void main(String[] args) {
        // TODO code application logic here
        eventos = new HashMap<>();
        bookies = new ArrayList<>();
        apostadores = new HashMap<>();
        
        
        //start app;
        int opcao;
        Scanner entrada = new Scanner(System.in);
        do{
            menu();
            opcao = entrada.nextInt();
            
            switch(opcao){
            case 1: criarConta();
                break;
                
            case 2: criarEvento();
                break;
            case 3: listaEventos();
                break;
            case 4: editarOdds();
                break;
            case 5: criarAposta();
                break;
            default:
                System.out.println("Opção inválida.");
            }
        } while(opcao != 0);
    }
    
    
     public static void menu(){
        System.out.println("\tBET ESSSS");
        System.out.println("Criar Conta");
        System.out.println("Criar Evento");
        System.out.println("Mostrar Eventos");
        System.out.println("Editar Odds");
        System.out.println("Opcao:");
    }
    
    
   public static void criarConta(){
       Scanner entrada = new Scanner(System.in);
       String nome;
       String email;
       int opcao;
       System.out.println("Introduza o seu nome");
       nome = entrada.nextLine();
       System.out.println("Introduza o seu email");
       email = entrada.nextLine();
       System.out.println("Quer ser apostador(1) ou bookie (2)?");
       opcao = entrada.nextInt();
       switch(opcao){
           case 1: Apostador apostador = new Apostador(nome,email,0);
                   apostadores.put(apostadores.size(),apostador);
                   break;
           case 2: Bookie bookie = new Bookie(nome,email);
                   bookies.add(bookie);
                   break; 
           default: System.out.println("Introduziu dados errados");
                   break;
       }
   }
   
   
   //bookie e apostador
    public static String listaEventos(){
        
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        for(Integer evento: eventos.keySet())
        {
            result.append("EVENTO " + evento + NEW_LINE);
            result.append(eventos.get(evento).toString()); 
        }
        result.append(NEW_LINE);
        return result.toString();
    }
    

   //apostador
    public static void criarAposta(){
        int codigo;
        Scanner in = new Scanner(System.in);
        System.out.println("Introduza o código do evento");
        codigo = in.nextInt();
      try{
            System.out.println(eventos.get(codigo).toString());
            System.out.println("Escolha a Opcao ");
            
            
            Aposta aposta = new Aposta();
    } catch(Exception e){
            System.out.println("Não encontrou o evento");
        }

    }
    
    
    //bookie
    
    //falta associar o bookie
    
    public static void criarEvento(){
        
        String[] equipas = new String[2];
        float[] odds = new float[3];
        Scanner in = new Scanner(System.in);
        System.out.println("Equipa1");
        equipas[0] = in.nextLine();
        System.out.println("Equipa2");
        equipas[1] = in.nextLine();
        System.out.println("Odd Vitoria Equipa1");
        odds[0] = in.nextFloat();
        System.out.println("Odd Empate");
        odds[1] = in.nextFloat();
        System.out.println("Odd Vitoria Equipa2");
        odds[2] = in.nextFloat();
                     
        Evento evento = new Evento(equipas,odds,true);
        eventos.put(eventos.size(),evento);
        System.out.println("Evento Criado");
    }
    
        public static void editarOdds(){
        float[] odds = new float[3];
        String[] equipas = new String[3];
        int codigo;
        Scanner in = new Scanner(System.in);
        System.out.println("Introduza o código do evento");
        try{
            codigo = in.nextInt();
            System.out.println(eventos.get(codigo).toString());
            equipas = eventos.get(codigo).getEquipas();
            System.out.println("Odd Vitoria " + equipas[0]);
            odds[0] = in.nextFloat();
            System.out.println("Odd Empate");
            odds[1] = in.nextFloat();
            System.out.println("Odd Vitoria " + equipas[2]);
            odds[2] = in.nextFloat(); 
            eventos.get(codigo).setOdds(odds);
        } catch(Exception e){
            System.out.println("Não encontrou o evento");
        }
        
     }
     
   
  //inicializar com alguns dados.
    public static void dadosAplicacao(){

          Apostador apostador1 = new Apostador("luis","luis@gmail.com",0);
          Apostador apostador2 = new Apostador("paulo","paulo@gmail.com",0);
          apostadores.put(apostadores.size(),apostador1);
          apostadores.put(apostadores.size(),apostador2);
    }
    
}
