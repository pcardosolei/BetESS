/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import Eventos.Eventos;
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
    private static Eventos eventos;
    
    public static void main(String[] args) {
        // TODO code application logic here
        eventos = new Eventos();
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
            case 3: mostrarEventos();
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
   
   
   //bookie
   public static void criarEvento(){
       eventos.criarEvento();
   }
   
   public static void mostrarEventos(){
       System.out.println(eventos.listaEventos());
   }
   
   public static void editarOdds(){
       eventos.editarOdds();
   }
   
    public static void criarAposta(){
        int codigo;
        Scanner in = new Scanner(System.in);
        System.out.println("Introduza o código do evento");
        codigo = in.nextInt();
      try{
            System.out.println(eventos.getJogos().get(codigo).toString());
            System.out.println("Escolha a Opcao ");
            
            
            Aposta aposta = new Aposta();
    } catch(Exception e){
            System.out.println("Não encontrou o evento");
        }

    }
}
