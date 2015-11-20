
package Eventos;

import java.util.ArrayList;
import Utilizadores.Aposta;
import Utilizadores.Bookie;
import java.util.Observable;

/**
 *
 * @author PauloCardoso
 */
public class Evento extends Observable {
    
    private String[] equipas;
    private float[] odds;
    private boolean estado; //true = aberto
    private int vencedor;
    private ArrayList<Historico> historico;
    private ArrayList<Aposta> apostas; 
    private ArrayList<Bookie> bookieInteressados;
    private ArrayList<Observers> listaObservadores;
    
    public Evento(){
        equipas = new String[3];
        odds = new float[3];
        estado = false;
        apostas = new ArrayList<>();
        historico = new ArrayList<>();
    }
    
    public Evento(String[] equipas, float[] odds, boolean estado){
      
        try{
        this.equipas = new String[3];
        this.equipas[0] = equipas[0];
        this.equipas[1] = "Empate";
        this.equipas[2] = equipas[1];
        this.odds = new float[3];
        this.odds[0] = odds[0];
        this.odds[1] = odds[1];
        this.odds[2] = odds[2];
        this.estado = estado;
        this.historico = new ArrayList<>();
        this.apostas = new ArrayList<>();
        this.estado = true;
        actualizaHistorico(odds);


        }catch(Exception e){
            System.out.println("Erro na criação do evento");
        }
       
    }

    /**
     * @return the equipas
     */
    public String[] getEquipas() {
        return equipas;
    }

    /**
     * @param equipas the equipas to set
     */
    public void setEquipas(String[] equipas) {
        this.equipas = equipas;
    }

    /**
     * @return the odds
     */
    public float[] getOdds() {
        return odds;
    }

    // adicionar ookie interessado
    public void addInteresse(Bookie b){
        bookieInteressados.add(b);
    }
    
    // verifica se o bookie esta interessado neste evento
    public boolean interessadoEvento(Bookie b){
        
        return(bookieInteressados.contains(b));
    }
    /**
     * @param odds the odds to set
     */
    public void setOdds(float[] odds) {
        this.odds = odds;
        actualizaHistorico(odds);
        setChanged();
        notifyObservers();
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * @return the vencedor
     */
    public int getVencedor() {
        return vencedor;
    }

    /**
     * @param vencedor the vencedor to set
     */
    public void setVencedor(int vencedor) {
        this.vencedor = vencedor;
    }
    
    public String betRes(int res)
    {
        String nome = equipas[res];
        return nome;
    }
    
    public void fecharEvento(){
        this.estado = false;
        for(Aposta a : apostas){
            a.setEstado(false);
        }
    }
    
    public void novaAposta(int valor, int opcao){
        float odd;
        odd=this.odds[opcao];
<<<<<<< HEAD
          
       
       Aposta newBet = new Aposta(opcao,valor,odd);
       apostas.add(newBet);
=======
        Aposta newBet = new Aposta(opcao,valor,odd,apostador);
        getApostas().add(newBet);
>>>>>>> refs/remotes/origin/dev
    }
    public String printBet(){
        
        StringBuilder bets= new StringBuilder();
        
            bets.append("Apostas:\n");
            for(Aposta a: apostas)
            {
                bets.append(a.toString());
            }
            bets.append("-----------------------");
            bets.append("\n");
            return bets.toString();
            }
    
    
    public String toString(){
        
        
        StringBuilder result = new StringBuilder();
        for( int i = 0; i <= odds.length - 1; i++)
        {
            result.append( equipas[i] + " " + odds[i] + " | "); 
        }
        for(Aposta a: apostas)
        {
            result.append(a.toString());
        }
        result.append("\n");
        return result.toString();
        }

        public String historicoApostas(){
        StringBuilder result = new StringBuilder();
        for(Historico h: historico)
        {
            result.append(h.toString()); 
            result.append("\n");

        }
        return result.toString();
    }
        
    private void actualizaHistorico(float[] odds) {
        Historico actual = new Historico(odds);
        historico.add(actual);
<<<<<<< HEAD
=======
        //setChanged();
        //notifyObservers();
        }

    public void setFinalizado(int vencedor){
        if(this.estado){
        this.vencedor= vencedor;
        this.estado = false;
        for(Aposta a: getApostas()){
            a.actualizaApostador(vencedor);
        }
        setChanged();
        notifyObservers();
        } else {
            System.out.println("Erro já se encontra finalizado");
>>>>>>> refs/remotes/origin/dev
        }

    
<<<<<<< HEAD
=======
    public ArrayList<Aposta> apostasApostador(Apostador apostador){
        ArrayList<Aposta> aux = new ArrayList<>();
         for(Aposta a: apostas){
                if(a.getApostador().equals(apostador))
                    aux.add(a);
            }
        return aux;
    }
    
    
    @Override 
    public void addObserver(Observer o){  
        observers.add(o);
        
    }
    
 
    
    @Override
    public void notifyObservers(){   
        for(Observer o: observers){
            if(o.getClass().getName().equals("Utilizadores.Bookie"))
            o.update(this, apostas);
            else
            {
            Apostador actual =(Apostador) o;
            float soma = 0;
            for(Aposta a: apostas){
                if(a.getApostador().equals(actual)){
                    if(a.getOpcao()==vencedor){
                        soma += a.getValor() * a.getOdd();
                    }
                }
            }
            o.update(this,soma);
            }
        }
    }
>>>>>>> refs/remotes/origin/dev
}
