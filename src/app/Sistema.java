
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
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author PauloCardoso 
 * @author LuisBrito
 */


// falta meter depositos e levantamentos
public class Sistema  {

    /**
     * @param args the command line arguments
     */
    private static HashMap<Integer,Apostador> apostadores;
    private static HashMap<Integer,Bookie> bookies;
    private static HashMap<Integer,Evento> eventos;
    private static int login; //apostador ou bookie
    private static boolean flag1;
    private static int bookielogado;
    private static int apostador;
    
    public static void main(String[] args) {
        // TODO code application logic here
        eventos = new HashMap<>();
        bookies = new HashMap<>();
        apostadores = new HashMap<>();
        flag1 = true;
        bookielogado = -1;
        apostador = -1;
        
        carregaDados();
       
        Scanner entrada = new Scanner(System.in);
        int opcao = -1; //opcao scanner 
        while(opcao!=2){
            System.out.println("1-Entrar na aplicação");
            System.out.println("2-Sair da aplicação");
            opcao = entrada.nextInt();
            if(opcao == 1){
                menuInicial();
            }
            if(login==1){ //bookie
                do{
                    DadosMenuBookie();
                    opcao = entrada.nextInt();    
                    switch(opcao){           
                    case 1: criarEvento();
                        break;
                    case 2: listaEventos();
                        break;
                    case 3: editarOdds();
                        break;
                    case 4: listaHistoricoEvento();
                        break;
                    case 5: mostrarInteresse();
                        break;
                    case 6: listaApostas();
                        break;
                    case 7: finalizarEvento();
                        break;
                    default:
                        break;
                    }
                } while(opcao != 0);
                login = -1;
                flag1 = true;
            } else if(login==2){
                do{
                    DadosMenuApostador();
                    opcao = entrada.nextInt();
                    switch(opcao){        
                    case 1: listaEventos();
                        break;
                    case 2: criarAposta();
                        break;
                    case 3: verEstadoApostasEvento();
                        break;
                    case 4: depositar();
                        break;
                    case 5: levantar();
                        break;
                    default:
                        break;
                    }
                } while(opcao != 0);
                login = -1;
                flag1 = true;
           }
        } 
   }

    public static void DadosMenuBookie(){
        System.out.println("\n\tBET ESS");
        System.out.println("1-Criar Evento");
        System.out.println("2-Mostrar Eventos");
        System.out.println("3-Editar Odds");
        System.out.println("4-Historico odds de um evento");
        System.out.println("5-Mostrar Interesse em Evento");
        System.out.println("6-Mostrar Lista de Apostas de Evento");
        System.out.println("7-Finalizar Evento");
        System.out.print("Opcao:");
    }
    
    public static void DadosMenuApostador(){
        System.out.println("\n\tBET ESS");
        System.out.println("1-Mostrar Eventos");
        System.out.println("2-Apostar em Evento");
        System.out.println("3-Ver Estado Apostas em Evento");
        System.out.println("4-Deposito");
        System.out.println("5-Levantamento");
        System.out.print("Opcao:");
    }
    
   public static void menuInicial(){
       Scanner entrada = new Scanner(System.in);
        int opcao; //opcao scanner      
        try{
        while(flag1 == true){
            System.out.println("1 - Registar");
            System.out.println("2 - Login");
            System.out.println("0 - Sair");
            opcao = entrada.nextInt();
            switch(opcao){
                case 1: criarConta(); //falta acabar
                    break;
                case 2: Login(); //certo
                    break;
                default: break;
            }
        }
        } catch (InputMismatchException e){
        System.out.println("Introduza um caracter numérico");
        }
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
                 if(!(verificaBookie(user,pw))){ 
                    login = 1;
                    flag1 = false;
                 }
                 break;
         case 2: System.out.print("Username: ");
                 user = entrada.nextLine();
                 System.out.print("Password: ");
                 pw = entrada.nextLine(); 
                 if(!(verificaApostador(user,pw))){
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
       password = entrada.next();
       System.out.println("Quer ser apostador(1) ou bookie (2)?");
       opcao = entrada.nextInt();      
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
   
   
   //apostador
   public static void criarAposta(){
        int codigo,valor,opcao;
        Scanner in = new Scanner(System.in);
        System.out.println("Introduza o código do evento");
        codigo = in.nextInt();
        System.out.println("Qual e a sua opcao: 0 - TeamA win | 1 - Draw | 2 - TeamB win");
        opcao=in.nextInt();
        System.out.println("Que valor prentende apostar €:");
        valor=in.nextInt();
      try{          
          eventos.get(codigo).novaAposta(valor, opcao,apostadores.get(apostador));
          eventos.get(codigo).addObserver(apostadores.get(apostador));
          System.out.println("Criou uma aposta no Evento: " + codigo + " E apostou: "+valor +" Euros em: "+ eventos.get(codigo).betRes(opcao));
          
        } catch(Exception e){
            System.out.println("Não encontrou o evento");
        }

    }
   
   public static void depositar(){
       Scanner in = new Scanner(System.in);
       int valor = Integer.parseInt(in.nextLine());
       apostadores.get(apostador).Deposito(valor);
   }
   
   public static void levantar(){
       Scanner in = new Scanner(System.in);
       int valor = Integer.parseInt(in.nextLine());
       apostadores.get(apostador).Levantamento(valor);
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
        Evento evento = new Evento(equipas,odds);
        Bookie bookie = bookies.get(bookielogado);
        evento.addObserver(bookie);
        eventos.put(eventos.size(),evento);
        } catch(Exception e){
            System.out.println("Evento Não Criado . Introduza odds do tipo inteiro,decimal");
        }
    }
    
    public static void mostrarInteresse(){
        Scanner in = new Scanner(System.in);
        System.out.println("Qual é o Evento?");
        try{
            int codigo = Integer.parseInt(in.nextLine());
            eventos.get(codigo).addObserver(bookies.get(bookielogado));  
        } catch(InputMismatchException e){
            System.out.println("Não encontrou o evento");
        }
    }
    public static void finalizarEvento(){
        Scanner in = new Scanner(System.in);
        System.out.println("Qual é o Evento?");
        try{
            int codigo = Integer.parseInt(in.nextLine());
            System.out.println(eventos.get(codigo).toString());
            System.out.println("Vencedor?");
            int vencedor = Integer.parseInt(in.nextLine());
            eventos.get(codigo).setFinalizado(vencedor);
        } catch(NullPointerException e){
            System.out.println("Evento não encontrado");
        } catch(InputMismatchException e){
            System.out.println("Erro na Leitura");
        }
        
    }

    public static void listaApostas(){
        Scanner in = new Scanner(System.in);
        System.out.println("Qual é o Evento?");
        try{
            int codigo = in.nextInt();
            System.out.println(eventos.get(codigo).printBet());
        } catch(Exception e){
            System.out.println("Evento não encontrado");
        }
    }
    
    public static void verEstadoApostasEvento(){
        Scanner in = new Scanner(System.in);
        System.out.println("Qual é o Evento?");
        try{
            int codigo = in.nextInt();
            ArrayList<Aposta> apostas = new ArrayList<>();
            apostas = eventos.get(codigo).apostasApostador(apostadores.get(apostador));
            for(Aposta a: apostas){
                System.out.println(a.toString());
            }
        } catch(NullPointerException e){
            System.out.println("Evento não encontrado");
        }

    }
            
    public static boolean verificaApostador(String user, String pw){
        boolean flag = true;
        for(Integer a: apostadores.keySet()){
            if((apostadores.get(a).verificaUtilizador(user, pw))){
                flag= false;
                apostador = a;
            }
        }
        return flag;
    }
    
    public static boolean verificaBookie(String user, String pw){
        boolean flag = true;
        for(Integer a: bookies.keySet()){
            if((bookies.get(a).verificaUtilizador(user, pw))){
                flag= false;
                bookielogado = a;
            }
        }
        return flag;
    }
    public static void listaEventos(){
        
        StringBuilder result = new StringBuilder();

        for(Integer evento: eventos.keySet())
        {
            result.append("\nEVENTO " + evento + ": ");
            result.append(eventos.get(evento).toString()); 
        }
        System.out.println(result.toString());
    }
    
    public static void listaHistoricoEvento(){
        
        Scanner in = new Scanner(System.in);
        System.out.println("Qual o evento que pretende procurar informação?");
        int codigo = in.nextInt();
        try{
            System.out.println(eventos.get(codigo).historicoOdds());
        } catch (NullPointerException e){
            System.out.println("Evento não existente");
        }
    }
    public static void editarOdds(){
        float[] odds = new float[3];
        String[] equipas = new String[3];
        int codigo;
        Scanner in = new Scanner(System.in);
        System.out.println("Introduza o código do evento");
        codigo = in.nextInt();
        try{
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
    
    private static void carregaDados(){
        
        
        // 2 apostadores
        Apostador apostador1 = new Apostador("paulo","paulo@gmail.com","123");
        Apostador apostador2 = new Apostador("luis brito","luis@di.uminho.pt","231");
        apostadores.put(apostadores.size(),apostador1);
        apostadores.put(apostadores.size(),apostador2);
        
        //2 bookies
        Bookie bookie1 = new Bookie("nuno santos","nuno@gmail.com","12341");
        Bookie bookie2 = new Bookie("xavier fernandes", "xavier@di.uminho.pt","1231");
        bookies.put(bookies.size(),bookie1);
        bookies.put(bookies.size(),bookie2);
        
        //2 eventos
        Evento evento1 = new Evento(new String[]{"porto","braga"},new float[]{1.5f,2f,3.4f});
        Evento evento2 = new Evento(new String[]{"benfica","porto"},new float[]{1.3f,2f,4f});
        
        eventos.put(eventos.size(),evento1);
        eventos.put(eventos.size(),evento2);
        
        //adicionar apostas
        eventos.get(1).novaAposta(20, 0, apostador2);
        eventos.get(0).novaAposta(10,2,apostador2);
        eventos.get(0).novaAposta(200,0, apostador1);
    }
}
