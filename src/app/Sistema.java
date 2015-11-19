
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import Eventos.*;
import Criteria.*;
import Utilizadores.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author PauloCardoso 
 * @author LuisBrito
 */


/*
PUXAR UMA CAMADA PARA CIMA A CAMADA DE INTERFACE E DEIXAR ESTE COMO CONTROLLER
*/
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
    
    public Sistema(){
        eventos = new HashMap<>();
        bookies = new HashMap<>();
        apostadores = new HashMap<>();
        carregaDados();
    }
   
    public String retornaNotificacoesBookie(int bookie){
        return bookies.get(bookie).retornaNotificacoes().toString();
    }
    public String retornaNotificacoesApostador(int apostador){
        return bookies.get(apostador).retornaNotificacoes().toString();
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
   
   
   //apostador
   public static void criarAposta(){
        int codigo,valor,opcao;
        Scanner in = new Scanner(System.in);
        System.out.println("Introduza o código do evento");
        codigo = Integer.parseInt(in.nextLine());
        System.out.println("Qual e a sua opcao: 0 - TeamA win | 1 - Draw | 2 - TeamB win");
        opcao = Integer.parseInt(in.nextLine());
        System.out.println("Que valor prentende apostar €:");
        valor = Integer.parseInt(in.nextLine());
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
            System.out.println("Não encontrou o evento");
        }

    }
   
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
        Evento evento = new Evento(equipas,odds,bookies.get(bookielogado));
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
            int codigo = Integer.parseInt(in.nextLine());
            System.out.println(eventos.get(codigo).printBet());
        } catch(Exception e){
            System.out.println("Evento não encontrado");
        }
    }
    
    
    public static void verEstadoApostasEvento(){
        Scanner in = new Scanner(System.in);
        System.out.println("Qual é o Evento?");
        try{
            int codigo = Integer.parseInt(in.nextLine());
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
        int codigo = Integer.parseInt(in.nextLine());
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
        codigo = Integer.parseInt(in.nextLine());
        try{
            System.out.println(eventos.get(codigo).toString());
            equipas = eventos.get(codigo).getEquipas();
            System.out.println("Odd Vitoria " + equipas[0]);
            odds[0] =  Float.parseFloat(in.nextLine());
            System.out.println("Odd Empate");
            odds[1] = Float.parseFloat(in.nextLine());
            System.out.println("Odd Vitoria " + equipas[2]);
            odds[2] = Float.parseFloat(in.nextLine());
            eventos.get(codigo).setOdds(odds);
        } catch(Exception e){
            System.out.println("Não encontrou o evento");
        }
        
     }
    
    private static void bookiesFechadoAberto(){
        Scanner in = new Scanner(System.in);
        CriteriaEventoFechado eventofechado = new CriteriaEventoFechado();
        ArrayList<Evento> eventosValues= new ArrayList<>(eventos.values());
        CriteriaEventoBookie eventobookie = new CriteriaEventoBookie();
        CriteriaEventoAberto eventoaberto = new CriteriaEventoAberto();
        
        System.out.println("Bookies");
        for(Integer a: bookies.keySet()){
            System.out.println(a + " - " + bookies.get(a).showBookie());
        }
        int opcao = Integer.parseInt(in.nextLine());
        System.out.println("AND or OR");
        String linha = in.nextLine();
        if(linha.equals("AND")){
            System.out.println("fechado ou aberto");
            String linha2 = in.nextLine();
            if(linha2.equals("fechado")){
                AndCriteria andCriteria = new AndCriteria(eventofechado,eventobookie);
                System.out.println("Eventos");
                System.out.println(andCriteria.meetCriteria(eventosValues,bookies.get(opcao)));
            } else if(linha2.equals("aberto")){
                AndCriteria andCriteria = new AndCriteria(eventoaberto,eventobookie);
                System.out.println("Eventos");
                System.out.println(andCriteria.meetCriteria(eventosValues,bookies.get(opcao)));
            } else
                    System.out.println("ERRO");
       } else if(linha.equals("OR")){
            System.out.println("fechado ou aberto");
            String linha2 = in.nextLine();
            if(linha2.equals("fechado")){
                OrCriteria orCriteria = new OrCriteria(eventofechado,eventobookie);
                System.out.println("Eventos");
                System.out.println(orCriteria.meetCriteria(eventosValues,bookies.get(opcao)));
            }else if(linha2.equals("aberto")){
                OrCriteria orCriteria = new OrCriteria(eventoaberto,eventobookie);
                System.out.println("Eventos");
                System.out.println(orCriteria.meetCriteria(eventosValues,bookies.get(opcao)));
            } else
                    System.out.println("ERRO");

       }
    }
    
    /*
    private static void testarCriteria(){
        int opcao1, opcao2, opcao3;
        Scanner in = new Scanner(System.in);
        ArrayList<Evento> eventosValues= new ArrayList<>(eventos.values());
        List<Evento> aux = new ArrayList<>();
        CriteriaEventoFechado eventofechado = new CriteriaEventoFechado();
        CriteriaEventoAberto eventoaberto = new CriteriaEventoAberto();
        CriteriaEventoBookie eventobookie = new CriteriaEventoBookie();
        System.out.println("Introduza critérios");
        String linha = in.nextLine();
        String[] partes = linha.split(" [&|] ");
        String[] dels = linha.split("[^&|]");
        int i;
        for(i=1;i<partes.length;i++){
            if(dels[i-1].equals("&")){
                CheckBookie(partes[i-1],partes[i]);
                
            } else if(dels[i-1].equals("|")){
                CheckBookie(partes[i-1],partes[i]);
                
            } else break;
                
        }
       }
*/
    private static void carregaDados(){
        
        
        // 2 apostadores
        Apostador apostador1 = new Apostador("paulo","paulo@gmail.com","123");
        Apostador apostador2 = new Apostador("luis","luis@di.uminho.pt","231");
        apostadores.put(apostadores.size(),apostador1);
        apostadores.put(apostadores.size(),apostador2);
        apostadores.get(0).Deposito(200);
        apostadores.get(1).Deposito(300);
        
        //2 bookies
        Bookie bookie1 = new Bookie("nuno santos","nuno@gmail.com","12341");
        Bookie bookie2 = new Bookie("xavier fernandes", "xavier@di.uminho.pt","1231");
        bookies.put(bookies.size(),bookie1);
        bookies.put(bookies.size(),bookie2);
        
        //2 eventos
        Evento evento1 = new Evento(new String[]{"porto","braga"},new float[]{1.5f,2f,3.4f},bookie1);
        Evento evento2 = new Evento(new String[]{"benfica","porto"},new float[]{1.3f,2f,4f},bookie1);
        Evento evento3 = new Evento(new String[]{"freamunde","chaves"}, new float[]{2f,1.5f,2f},bookie2);
        Evento evento4 = new Evento(new String[]{"sporting","arouca"}, new float[]{1.1f,3f,7f},bookie2);
        
        eventos.put(eventos.size(),evento1);
        eventos.put(eventos.size(),evento2);
        eventos.put(eventos.size(),evento3);
        eventos.put(eventos.size(),evento4);
        
        //adicionar apostas
        eventos.get(1).novaAposta(20, 0, apostador2);
        eventos.get(1).novaAposta(20, 1, apostador2);
        eventos.get(2).novaAposta(10,2,apostador2);
        eventos.get(0).novaAposta(150,0, apostador1);
        eventos.get(3).novaAposta(30,1,apostador1);
        eventos.get(0).addObserver(bookie1);
        eventos.get(1).addObserver(bookie1);       
        eventos.get(2).addObserver(bookie1);
        eventos.get(0).addObserver(bookie2);
        eventos.get(1).addObserver(bookie2);
        eventos.get(0).addObserver(apostador2);
        eventos.get(1).addObserver(apostador2);
        eventos.get(2).addObserver(apostador2);
        
    }
}

    /*
    public static void main(String[] args) {
        // TODO code application logic here
        flag1 = true;
        bookielogado = -1;
        apostador = -1;
        
       
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
    */