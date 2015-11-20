
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
    private static int apostador;
    
    public Sistema(){
        eventos = new HashMap<>();
        bookies = new HashMap<>();
        apostadores = new HashMap<>();
        carregaDados();
    }
   
    /*
     NOTIFICAÇÕES
    */  
    public String retornaNotificacoesBookie(int bookie){
        return bookies.get(bookie).retornaNotificacoes();
    }
    public String retornaNotificacoesApostador(int apostador){
        return apostadores.get(apostador).retornaNotificacoes();
    }
    
    /*
    LOGIN
    */
    public int verificaApostador(String user, String pw){
        int apostador = -1;
        for(Integer a: apostadores.keySet()){
            if((apostadores.get(a).verificaUtilizador(user, pw))){
                apostador = a;
            }
        }
        return apostador;
    }
    
    public int verificaBookie(String user, String pw){
        int bookie = -1;
        for(Integer a: bookies.keySet()){
            if((bookies.get(a).verificaUtilizador(user, pw))){
                bookie = a;
            }
        }
        return bookie;
    }
    
    /*
    REGISTOS
    */
    
    public void criarApostador(String nome,String email, String password){
        Apostador apostador = new Apostador(nome,email,password,0);
        apostadores.put(apostadores.size(),apostador);
    }
    
    public void criarBookie(String nome,String email, String password){
       Bookie bookie = new Bookie(nome,email,password);
       bookies.put(bookies.size(),bookie); 
    }   
    
   /*
   BOOKIES
   */
    public void criarEvento(String[] equipas,float[] odds, int bookie ){
        Evento evento = new Evento(equipas,odds,bookies.get(bookie));
        Bookie bookieOb = bookies.get(bookie);
        evento.addObserver(bookieOb);
        eventos.put(eventos.size(),evento);
    }
    
    public String listaHistoricoEvento(int codigo){ 
      return eventos.get(codigo).historicoOdds();
    }
    
     public void editarOdds(int codigo, float[] odds){
         eventos.get(codigo).setOdds(odds);      
     }
     
     public void mostrarInteresse(int codigo,int bookie){      
         eventos.get(codigo).addObserver(bookies.get(bookie));  
        
    }
     
    public static void finalizarEvento(int codigo, int vencedor){
            eventos.get(codigo).setFinalizado(vencedor);  
    }

    public String listaApostas(int codigo){
        return eventos.get(codigo).printBet();
    }
   /*
    APOSTADOR
    */  
   public void criarAposta(int codigo, int apostador, int valor,int opcao){
          eventos.get(codigo).novaAposta(valor, opcao,apostadores.get(apostador));     
          eventos.get(codigo).addObserver(apostadores.get(apostador));
          apostadores.get(apostador).retiraValor(valor);
    }
   
   public void depositar(int apostador,int valor){
       apostadores.get(apostador).Deposito(valor);
   }
   
   public void levantar(int valor, int apostador){
       apostadores.get(apostador).Levantamento(valor); //falta o tratamento do erro
   }
   
   public float consultarSaldo(){
       return apostadores.get(apostador).getDisponivel();
   }
   
   public boolean testarSaldo(int apostador,int valor){
       return apostadores.get(apostador).testaSaldo(valor);
   }
    
    public String verEstadoApostasEvento(int codigo, int apostador){
        StringBuilder result = new StringBuilder();
       
        ArrayList<Aposta> apostas = new ArrayList<>();
        apostas = eventos.get(codigo).apostasApostador(apostadores.get(apostador));
            for(Aposta a: apostas){    
                result.append(a);
            }   
            return result.toString();
    }
    /*
     AMBOS
    */
    
    public String listaEventos(){
        
        StringBuilder result = new StringBuilder();
        for(Integer evento: eventos.keySet()) {
            result.append("\nEVENTOS " + evento + ": ");
            result.append(eventos.get(evento).toString()); 
        }
        return result.toString();
    }
    
    public String showEvento(int codigo){
        return eventos.get(codigo).toString();
    }
    
    /*
     AUXILIAR
    */
    
    public String[] getEquipas(int codigo){
        return eventos.get(codigo).getEquipas();
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