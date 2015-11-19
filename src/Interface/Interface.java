/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import app.Sistema;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author PauloCardoso e Luis Brito
 */
public class Interface {
    
    private static boolean flag1;
    private static Sistema sistema;
    private static int bookie; //Mudar para string
    private static int apostador; //Mudar para string
    private static int login;
    
    public static void main(String[] args) {
        
        flag1 = true;
        bookie = -1;
        apostador = -1;
        
        sistema = new Sistema();
        Scanner entrada = new Scanner(System.in);
        int opcao = -1; //opcao scanner 
        while(opcao!=2){
            System.out.println("1-Entrar na aplicação");
            System.out.println("2-Sair da aplicação");
            opcao = Integer.parseInt(entrada.nextLine());
            if(opcao == 1){
                menuInicial();
            }
            if(login==1){ //bookie
              parteBookie();
            } else if(login==2){
              parteApostador();  
           }
        } 
    }
   
    public static void parteBookie(){
                int opcao;
                Scanner entrada = new Scanner(System.in);
                System.out.println("----Notificações----");
                //colocar o sistema a devolver notificaçoes
                System.out.println(sistema.retornaNotificacoesBookie(bookie)); 
                do{
                    DadosMenuBookie();
                    opcao = Integer.parseInt(entrada.nextLine());  
                    switch(opcao){           
                    case 1: //criarEvento();
                        break;
                    case 2: //listaEventos();
                        break;
                    case 3: //editarOdds();
                        break;
                    case 4: //listaHistoricoEvento();
                        break;
                    case 5: //mostrarInteresse();
                        break;
                    case 6: //listaApostas();
                        break;
                    case 7: //finalizarEvento();
                        break;
                    case 8: //bookiesFechadoAberto();
                        break;
                    default:
                        break;
                    }
                } while(opcao != 0);
                login = -1;
                flag1 = true;
    }
    
    public static void parteApostador(){
                int opcao;
                Scanner entrada = new Scanner(System.in);
                System.out.println("----Notificações----");
                System.out.println(sistema.retornaNotificacoesApostador(apostador));
                do{
                    DadosMenuApostador();
                    opcao = Integer.parseInt(entrada.nextLine());
                    switch(opcao){        
                    case 1: //listaEventos();
                        break;
                    case 2: //criarAposta();
                        break;
                    case 3: //verEstadoApostasEvento();
                        break;
                    case 4: //depositar();
                        break;
                    case 5: //levantar();
                        break;
                    case 6: //consultarSaldo();
                        break;
                    default:
                        break;
                    }
                } while(opcao != 0);
                login = -1;
                flag1 = true;
    }   
   
   public static void Login(){
     System.out.println("-- Bookie 1 | Apostador 2");
     Scanner entrada = new Scanner(System.in);
     String user, pw;
     try{
     int opcao = Integer.parseInt(entrada.nextLine());
     switch(opcao){
         case 1: System.out.print("Username: ");
                 user = entrada.nextLine();
                 System.out.print("Password: ");
                 pw = entrada.nextLine();    
                 if(!(sistema.verificaBookie(user,pw))){ 
                    login = 1;
                    flag1 = false;
                 }
                 break;
         case 2: System.out.print("Username: ");
                 user = entrada.nextLine();
                 System.out.print("Password: ");
                 pw = entrada.nextLine(); 
                 if(!(sistema.verificaApostador(user,pw))){
                    login = 2;  
                    flag1 = false; 
                 }
                 break;
            }
        } catch (NumberFormatException e) {
         System.out.println("erro na leitura de dados");
        }
    }   
    
    /*
   public static void criarConta(){
       Scanner entrada = new Scanner(System.in);
       String nome;
       String email;
       String password;
       int opcao;
       System.out.println("Introduza o seu nome");
       nome = entrada.nextLine();
       System.out.println("Introduza o seu email");
       email = entrada.nextLine();
       System.out.println("Password");
       password = entrada.next();
       System.out.println("Quer ser apostador(1) ou bookie (2)?");
       opcao = Integer.parseInt(entrada.nextLine());     
       switch(opcao){
           case 1: Apostador apostador = new Apostador(nome,email,password,0);
                   apostadores.put(apostadores.size(),apostador);
                   break;
           case 2: Bookie bookie = new Bookie(nome,email,password);
                   bookies.put(bookies.size(),bookie);
                   break; 
           default: System.out.println("Introduziu dados errados");
                   break;
       }
   }
   */
    /*
        MENUS
    */
    public static void DadosMenuBookie(){
        System.out.println("\n\tBET ESS");
        System.out.println("1 - Criar Evento");
        System.out.println("2 - Mostrar Eventos");
        System.out.println("3 - Editar Odds");
        System.out.println("4 - Historico odds de um evento");
        System.out.println("5 - Mostrar Interesse em Evento");
        System.out.println("6 - Mostrar Lista de Apostas de Evento");
        System.out.println("7 - Finalizar Evento");
        System.out.println("8 - Teste Critérios");
        System.out.println("0 - Menu Inicial");
        System.out.print("Opcao:");
    }
    
    public static void DadosMenuApostador(){
        System.out.println("\n\tBET ESS");
        System.out.println("1 - Mostrar Eventos");
        System.out.println("2 - Apostar em Evento");
        System.out.println("3 - Ver Estado Apostas em Evento");
        System.out.println("4 - Deposito");
        System.out.println("5 - Levantamento");   
        System.out.println("6 - ConsultarSaldo");
        System.out.println("0 - Menu Inicial");
        System.out.print("Opcao: ");
    }
    
    public static void menuInicial(){
       Scanner entrada = new Scanner(System.in);
        int opcao; //opcao scanner      
        try{
        while(flag1 == true){
            System.out.println("1 - Registar");
            System.out.println("2 - Login");
            System.out.println("0 - Menu Inicial");
            opcao = Integer.parseInt(entrada.nextLine());
            switch(opcao){
                case 1: //criarConta(); 
                    break;
                case 2: //Login(); 
                    break;
                default: flag1 = false;
                    break;
            }
        }
        } catch (InputMismatchException e){
        System.out.println("Introduza um caracter numérico");
        }
    }
}
