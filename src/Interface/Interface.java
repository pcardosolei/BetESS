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
                do{
                     //colocar o sistema a devolver notificaçoes
                     System.out.println("----Notificações----"); 
                     System.out.println(sistema.retornaNotificacoesBookie(bookie));     
                    DadosMenuBookie();
                    opcao = Integer.parseInt(entrada.nextLine());  
                    switch(opcao){           
                    case 1: criarEvento();
                        break;
                    case 2: System.out.println(sistema.listaEventos());
                        break;
                    case 3: editarOdds(); //aqui falta testar quem muda as odds
                        break;
                    case 4: listaHistoricoEvento();
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
                 do{
                    System.out.println("----Notificações----");
                    System.out.println(sistema.retornaNotificacoesApostador(apostador));
                    DadosMenuApostador();
                    opcao = Integer.parseInt(entrada.nextLine());
                    switch(opcao){        
                    case 1: System.out.println(sistema.listaEventos());
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
     System.out.println("-- Bookie 1 | Apostador 2 --");
     Scanner entrada = new Scanner(System.in);
     String user, pw;
     try{
     int opcao = Integer.parseInt(entrada.nextLine());
     switch(opcao){
         case 1: System.out.print("Username: ");
                 user = entrada.nextLine();
                 System.out.print("Password: ");
                 pw = entrada.nextLine(); 
                 bookie = sistema.verificaBookie(user,pw);
                 if(bookie != -1){ 
                    login = 1;
                    flag1 = false;
                 }
                 break;
         case 2: System.out.print("Username: ");
                 user = entrada.nextLine();
                 System.out.print("Password: ");
                 pw = entrada.nextLine(); 
                 apostador = sistema.verificaApostador(user,pw);
                 if(apostador != -1){
                    login = 2;  
                    flag1 = false; 
                 }
                 break;
            }
        } catch (NumberFormatException e) {
         System.out.println("erro na leitura de dados");
        }
    }   

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
       password = entrada.nextLine();
       System.out.println("Quer ser apostador(1) ou bookie (2)?");
       opcao = Integer.parseInt(entrada.nextLine());     
       switch(opcao){
           case 1: sistema.criarApostador(nome, email, password);
                   break;
           case 2: sistema.criarBookie(nome, email, password);
                   break; 
           default: System.out.println("Introduziu dados errados");
                   break;
       }
   }
   
   //bookie
    public static void criarEvento(){
        
        String[] equipas = new String[2];
        float[] odds = new float[3];
        Scanner in = new Scanner(System.in);
        System.out.println("Equipa1");
        equipas[0] = in.nextLine();
        System.out.println("Equipa2");
        equipas[1] = in.nextLine();
        try{
        System.out.println("Odd Vitoria Equipa1");
        odds[0] = Float.parseFloat(in.nextLine());
        System.out.println("Odd Empate");
        odds[1] = Float.parseFloat(in.nextLine());
        System.out.println("Odd Vitoria Equipa2");
        odds[2] = Float.parseFloat(in.nextLine());
        sistema.criarEvento(equipas,odds,bookie);
        } catch(Exception e){
            System.out.println("Evento Não Criado . Introduza odds do tipo inteiro,decimal");
        }
    }
    
    public static void editarOdds(){
        float[] odds = new float[3];
        String[] equipas = new String[3];
        int codigo;
        Scanner in = new Scanner(System.in);
        System.out.println("Introduza o código do evento");
        codigo = Integer.parseInt(in.nextLine());
        try{
            System.out.println(sistema.showEvento(codigo));
            equipas = sistema.getEquipas(codigo);
            System.out.println("Odd Vitoria " + equipas[0]);
            odds[0] =  Float.parseFloat(in.nextLine());
            System.out.println("Odd Empate");
            odds[1] = Float.parseFloat(in.nextLine());
            System.out.println("Odd Vitoria " + equipas[2]);
            odds[2] = Float.parseFloat(in.nextLine());
            sistema.editarOdds(codigo,odds);
        } catch(Exception e){
            System.out.println("Não encontrou o evento");
        }
    }
    
    public static void listaHistoricoEvento(){
        Scanner in = new Scanner(System.in);
        System.out.println("Qual o evento que pretende procurar informação?");
        int codigo = Integer.parseInt(in.nextLine());
        try{
            System.out.println(sistema.listaHistoricoEvento(codigo));
        } catch (NullPointerException e){
            System.out.println("Evento não existente");
        }
    }
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
                case 1: criarConta(); 
                    break;
                case 2: Login(); 
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
