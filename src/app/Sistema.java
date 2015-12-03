
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import Criteria.*;
import Eventos.*;
import Utilizadores.*;
import java.util.ArrayList;
import java.util.HashMap;
import Exception.*;
import java.util.List;

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
    private HashMap<String,Apostador> apostadores;
    private HashMap<String,Bookie> bookies;
    private HashMap<Integer,Evento> eventos;
    
    public Sistema(){
        eventos = new HashMap<>();
        bookies = new HashMap<>();
        apostadores = new HashMap<>();
        carregaDados();
    }
   
    /*
     NOTIFICAÇÕES
    */  
    public String retornaNotificacoesBookie(String bookie){
        return bookies.get(bookie).retornaNotificacoes();
    }
    public String retornaNotificacoesApostador(String apostador){
        return apostadores.get(apostador).retornaNotificacoes();
    }
    
    /*
    LOGIN
    */
    public String verificaApostador(String user, String pw){
        String apostador = "";
        for(String a: apostadores.keySet()){
            if((apostadores.get(a).verificaUtilizador(user, pw))){
                apostador = a;
            }
        }
        return apostador;
    }
    
    public String verificaBookie(String user, String pw){
        String bookie = "";
        for(String a: bookies.keySet()){
            if((bookies.get(a).verificaUtilizador(user, pw))){
                bookie = a;
            }
        }
        return bookie;
    }
    
    /*
    REGISTOS
    */
    
    public void criarApostador(String nome,String email, String password,String nickname){
        Apostador apostador = new Apostador(nome,email,password,nickname);
        apostadores.put(nickname,apostador);
    }
    
    public void criarBookie(String nome,String email, String password,String nickname){
       Bookie bookie = new Bookie(nome,email,password,nickname);
       bookies.put(nickname,bookie); 
    }   
    
   /*
   BOOKIES
   */
    public void criarEvento(String[] equipas,float[] odds, String bookie ){
        Evento evento = new Evento(equipas,odds,bookies.get(bookie));
        Bookie bookieOb = bookies.get(bookie);
        evento.register(bookieOb);
        eventos.put(eventos.size(),evento);
    }
    
    public String listaHistoricoEvento(int codigo){ 
      return eventos.get(codigo).historicoOdds();
    }
    
     public void editarOdds(int codigo, float[] odds,String b) throws BookieIncorretoException{
         if(eventos.get(codigo).verificaBookie(bookies.get(b)))
            eventos.get(codigo).setOdds(odds);  
         else 
            throw new BookieIncorretoException(eventos.get(codigo).getBookie());
     }
     
     public void mostrarInteresse(int codigo,String bookie){      
         eventos.get(codigo).register(bookies.get(bookie));  
        
    }
     
    public  void finalizarEvento(int codigo, int vencedor,String b) throws BookieIncorretoException{
        if(eventos.get(codigo).verificaBookie(bookies.get(b)))
            eventos.get(codigo).setFinalizado(vencedor);  
        else 
            throw new BookieIncorretoException(eventos.get(codigo).getBookie());
    }

    public String listaApostas(int codigo){
        return eventos.get(codigo).printBet();
    }
   /*
    APOSTADOR
    */  
   public void criarAposta(int codigo, String apostador, int valor,int opcao){
          eventos.get(codigo).novaAposta(valor, opcao,apostadores.get(apostador));     
          eventos.get(codigo).register(apostadores.get(apostador));
          apostadores.get(apostador).retiraValor(valor);
    }
   
   public void depositar(int valor,String apostador){
       apostadores.get(apostador).Deposito(valor);
   }
   
   public void levantar(int valor, String apostador) throws SemSaldoException{
       apostadores.get(apostador).Levantamento(valor); //falta o tratamento do erro
   }
   
   public float consultarSaldo(String apostador){
       return apostadores.get(apostador).getDisponivel();
   }
   
   public boolean testarSaldo(String apostador,int valor) throws SemSaldoException{
       return apostadores.get(apostador).testaSaldo(valor);
   }
    
    public String verEstadoApostasEvento(int codigo, String apostador){
        StringBuilder result = new StringBuilder();
       
        ArrayList<Aposta> apostas = new ArrayList<>();
        apostas = eventos.get(codigo).apostasApostador(apostadores.get(apostador));
        result.append(eventos.get(codigo).toString());
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
    
    public String escolha(int codigo,int opcao){
        return eventos.get(codigo).betRes(opcao);
        
    }
    public String[] getEquipas(int codigo){
        return eventos.get(codigo).getEquipas();
    }
    
    public String testaCriteria(String linha){
       
        ArrayList<Evento> eventosValues= new ArrayList<>(eventos.values());
        List<Evento> aux = new ArrayList<>();
        String[] partes = linha.split(" (and|or) ");
        String[] dels1 = linha.split(" ");
        String[] dels = new String[dels1.length];
        int j = 0;
        for(int a=0;a<dels.length;a++){
            if(dels1[a].equals("and") || dels1[a].equals("or")){
                dels[j] = dels1[a];
                j++;
            }
        }
        
        
        Criteria primeiro;
        boolean correcto=true;
            switch(partes[0]){
                case "aberto":
                    primeiro = new CriteriaEventoAberto();
                    break;
                case "fechado":
                    primeiro = new CriteriaEventoFechado();
                    break;
                default:
                    try{
                    primeiro = new CriteriaEventoBookie(bookies.get(partes[0]));
                    } catch(NullPointerException e){
                      throw new NullPointerException();
                    }
                    break;
            }
        
            for(int i=1;i<partes.length;i++){
                Criteria segundo;
                Criteria auxiliar;
                
                switch(partes[i]){
                    case "aberto":
                    segundo =new CriteriaEventoAberto();
                    break;
                case "fechado":
                    segundo = new CriteriaEventoFechado();
                    break;
                default:
                    try{
                    segundo = new CriteriaEventoBookie(bookies.get(partes[i]));
                    } catch(NullPointerException e){
                      throw new NullPointerException();
                    }
                    break;
                
                }
                switch(dels[i-1]){
                   case "and":
                    auxiliar = primeiro.clone();
                    primeiro = new AndCriteria(auxiliar,segundo);
                    break;
                   case "or":
                    auxiliar = primeiro.clone();
                    primeiro = new OrCriteria(auxiliar,segundo);
                    break;
                   default:
                    correcto=false;
                    break;
                }
                
            }
            if(correcto){
                aux = primeiro.meetCriteria(eventosValues);
                return aux.toString();
            }
            else{
                  return "SYNTAX ERROR. \n";
            }
       
        }
        
   
    /*
    
*/
    private  void carregaDados(){
        // 2 apostadores
        Apostador apostador1 = new Apostador("paulo","paulo@gmail.com","123","paulo");
        Apostador apostador2 = new Apostador("luis","luis@di.uminho.pt","231","luis");
        apostadores.put("paulo",apostador1);
        apostadores.put("luis",apostador2);
        apostadores.get("paulo").Deposito(200);
        apostadores.get("luis").Deposito(300);
        
        //2 bookies
        Bookie bookie1 = new Bookie("nuno santos","nuno@gmail.com","12341","nuno");
        Bookie bookie2 = new Bookie("xavier fernandes", "xavier@di.uminho.pt","1231","xavier");
        bookies.put("nuno",bookie1);
        bookies.put("xavier",bookie2);
        
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
        eventos.get(0).register(bookie1);
        eventos.get(1).register(bookie1);       
        eventos.get(2).register(bookie1);
        eventos.get(0).register(bookie2);
        eventos.get(1).register(bookie2);
        eventos.get(0).register(apostador1);
        eventos.get(0).register(apostador2);
        eventos.get(1).register(apostador2);
        eventos.get(2).register(apostador2);    
    }
}

 