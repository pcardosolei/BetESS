
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import Eventos.Evento;
import Utilizadores.Apostador;
import Utilizadores.Bookie;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author PauloCardoso
 */
<<<<<<< HEAD
=======


/*

PUXAR UMA CAMADA PARA CIMA A CAMADA DE INTERFACE E DEIXAR ESTE COMO CONTROLLER


*/
>>>>>>> refs/remotes/origin/dev
public class Sistema  {

    /**
     * @param args the command line arguments
     */
    private static HashMap<Integer,Apostador> apostadores;
    private static HashMap<Integer,Bookie> bookies;
    private static HashMap<Integer,Evento> eventos;
    private static int login; //apostador ou bookie
    private static boolean flag1;
    private static int bookie;
    private static int apostador;
    
    public static void main(String[] args) {
        // TODO code application logic here
        eventos = new HashMap<>();
        bookies = new HashMap<>();
        apostadores = new HashMap<>();
        flag1 = true;
        bookie = -1;
        apostador = -1;
        
        carregaDados();
       
        Scanner entrada = new Scanner(System.in);
<<<<<<< HEAD
        int opcao = -1; //opcao scanner      
        
=======
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
                    case 6: consultarSaldo();
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
        System.out.println("1 - Criar Evento");
        System.out.println("2 - Mostrar Eventos");
        System.out.println("3 - Editar Odds");
        System.out.println("4 - Historico odds de um evento");
        System.out.println("5 - Mostrar Interesse em Evento");
        System.out.println("6 - Mostrar Lista de Apostas de Evento");
        System.out.println("7 - Finalizar Evento");
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
>>>>>>> refs/remotes/origin/cardoso-historico
        while(flag1 == true){
            System.out.println("1 - Registar");
            System.out.println("2 - Login");
            System.out.println("0 - Menu Inicial");
            opcao = entrada.nextInt();
            switch(opcao){
                case 1: criarConta();
                    break;
                case 2: Login();
                    break;
                default: flag1 = false;
                    break;
<<<<<<< HEAD
=======
                default: flag1 = false;
                    break;
>>>>>>> refs/remotes/origin/dev
            }
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
                case 5: //mostrarInteresse();
                    break;
                default:
                    System.out.println("Opção inválida.");
                }
            } while(opcao != 0);
        } else if(login==2){
            do{
                DadosMenuApostador();
                opcao = entrada.nextInt();
                switch(opcao){        
                case 1: listaEventos();
                    break;
                case 2: criarAposta();
                    break;
                case 3: //verEstadoAposta();
                    break;
                default:
                    System.out.println("Opção inválida.");
                }
            } while(opcao != 0);
        }
    }
    

    public static void DadosMenuBookie(){
        System.out.println("\tBET ESS");
        System.out.println("Criar Evento");
        System.out.println("Mostrar Eventos");
        System.out.println("Editar Odds");
        System.out.println("Historico odds de um evento");
        System.out.println("Mostrar Interesse em Evento");
        System.out.print("Opcao:");
    }
    
    public static void DadosMenuApostador(){
        System.out.println("\tBET ESS");
        System.out.println("Mostrar Eventos");
        System.out.println("Apostar em Evento");
        System.out.print("Opcao:");
    }
    
   public static void Login(){
     try{
     System.out.println("-- Bookie 1 | Apostador 2");
     Scanner entrada = new Scanner(System.in);
     int opcao = entrada.nextInt();
     String user, pw;
     switch(opcao){
         case 1: System.out.println("Username: ");
                 user = entrada.next();
                 System.out.println("Password: ");
                 pw = entrada.next();    
                 if(!(verificaBookie(user,pw))){ 
                    login = 1;
                    flag1 = false;
                 }
                 break;
         case 2: System.out.print("Username: ");
                 user = entrada.next();
                 System.out.print("Password: ");
                 pw = entrada.next(); 
                 if(!(verificaApostador(user,pw))){
                    login = 2;  
                    flag1 = false; 
                 }
                 break;
        }
    
     } catch(Exception e){
         System.out.println(e);
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
       System.out.println("Quer ser apostador(1) ou bookie (2)?");
       opcao = entrada.nextInt();
       System.out.println("Password");
       password = entrada.nextLine();
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
   
   
   
   public static void criarAposta(){
        int codigo,valor,opcao;
        Scanner in = new Scanner(System.in);
        System.out.println("Introduza o código do evento");
        codigo = in.nextInt();
        System.out.println("Qual e a sua opcao: 0 - TeamA win | 1 - Draw | 2 - TeamB win");
        opcao=in.nextInt();
        System.out.println("Que valor prentende apostar €:");
        valor=in.nextInt();
<<<<<<< HEAD
      try{
            
          eventos.get(codigo).novaAposta(valor, opcao);
          System.out.println("Criou uma aposta no Evento: " + codigo + " E apostou: "+valor +" Euros em: "+ eventos.get(codigo).betRes(opcao));
          
            
    } catch(Exception e){
=======
      try{          
          if(apostadores.get(apostador).testaSaldo(valor)){
          eventos.get(codigo).novaAposta(valor, opcao,apostadores.get(apostador));
          eventos.get(codigo).addObserver(apostadores.get(apostador));
          apostadores.get(apostador).retiraValor(valor);
          System.out.println("Criou uma aposta no Evento: " + codigo + " E apostou: "+valor +" Euros em: "+ eventos.get(codigo).betRes(opcao));
          }
          else {
              System.out.println("Está sem graveto");
          }
        } catch(Exception e){
>>>>>>> refs/remotes/origin/dev
            System.out.println("Não encontrou o evento");
        }

    }
   
<<<<<<< HEAD

=======
   public static void depositar(){
       Scanner in = new Scanner(System.in);
       System.out.println("Valor a depositar?");
       int valor = Integer.parseInt(in.nextLine());
       apostadores.get(apostador).Deposito(valor);
   }
   
   public static void levantar(){
       Scanner in = new Scanner(System.in);
       System.out.println("Valor a levantar?");
       int valor = Integer.parseInt(in.nextLine());
       apostadores.get(apostador).Levantamento(valor);
   }
   
   public static void consultarSaldo(){
       System.out.println(apostadores.get(apostador).getDisponivel() + "€");
   }
>>>>>>> refs/remotes/origin/dev
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
        odds[0] = in.nextFloat();
        System.out.println("Odd Empate");
        odds[1] = in.nextFloat();
        System.out.println("Odd Vitoria Equipa2");
        odds[2] = in.nextFloat();
        Evento evento = new Evento(equipas,odds,true);
        Bookie bookie = bookies.get(1);
        evento.addObserver(bookie);
        eventos.put(eventos.size(),evento);
        } catch(Exception e){
            System.out.println("Evento Não Criado . Introduza odds do tipo inteiro,decimal");
        }
    }
    

    public static String listaApostas(){
    
<<<<<<< HEAD
        StringBuilder result = new StringBuilder();
        String NEW_LINE=System.getProperty("line.separator");  
        return result.toString();
=======
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

>>>>>>> refs/remotes/origin/dev
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
                bookie = a;
            }
        }
        return flag;
    }
    
    // mostra a lista de eventos em que o bookie tem interesse
    public static void listaEventosInteresse(){
    
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        
        ArrayList<Evento> novaLista;
    
    }
    public static void listaEventos(){
        
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        for(Integer evento: eventos.keySet())
        {
            result.append("EVENTO " + evento + NEW_LINE);
            result.append(eventos.get(evento).toString()); 
        }
        result.append(NEW_LINE);
        System.out.println(result.toString());
    }
    
    public static void listaHistoricoEvento(){
        
        Scanner in = new Scanner(System.in);
        System.out.println("Qual o evento que pretende procurar informação?");
        int codigo = in.nextInt();
        try{
            System.out.println(eventos.get(codigo).historicoApostas());
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
        
        Apostador apostador1 = new Apostador("paulo","paulo@gmail.com","123");
        Apostador apostador2 = new Apostador("luis brito","luis@di.uminho.pt","231");
        apostadores.put(apostadores.size(),apostador1);
        apostadores.put(apostadores.size(),apostador2);
<<<<<<< HEAD
=======
        apostadores.get(0).Deposito(200);
        apostadores.get(1).Deposito(300);
        
        //2 bookies
>>>>>>> refs/remotes/origin/dev
        Bookie bookie1 = new Bookie("nuno santos","nuno@gmail.com","12341");
        Bookie bookie2 = new Bookie("xavier fernandes", "xavier@di.uminho.pt","1231");
        bookies.put(bookies.size(),bookie1);
        bookies.put(bookies.size(),bookie2);
        
<<<<<<< HEAD
=======
        //2 eventos
        Evento evento1 = new Evento(new String[]{"porto","braga"},new float[]{1.5f,2f,3.4f});
        Evento evento2 = new Evento(new String[]{"benfica","porto"},new float[]{1.3f,2f,4f});
        
        eventos.put(eventos.size(),evento1);
        eventos.put(eventos.size(),evento2);
        
        //adicionar apostas
        eventos.get(1).novaAposta(20, 0, apostador2);
        eventos.get(0).novaAposta(10,2,apostador2);
        eventos.get(0).novaAposta(200,0, apostador1);
        eventos.get(0).addObserver(bookie2);
        eventos.get(0).addObserver(apostador2);
        
>>>>>>> refs/remotes/origin/dev
    }
}
