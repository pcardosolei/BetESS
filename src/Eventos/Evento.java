
package Eventos;

import Observer.*;
import java.util.ArrayList;
import Utilizadores.Aposta;
import Utilizadores.Apostador;
import Utilizadores.Bookie;

/**
 *
 * @author PauloCardoso
 */
public class Evento implements Subject {
    
    private Bookie bookie;
    private String[] equipas;
    private float[] odds;
    private boolean estado; //true = aberto
    private int vencedor; //vencedor
    private ArrayList<Historico> historico; //historico de odds
    private ArrayList<Aposta> apostas; 
    private ArrayList<Observer> observers;
    
    public Evento(){
        equipas = new String[3];
        odds = new float[3];
        estado = false;
        apostas = new ArrayList<>();
        historico = new ArrayList<>();
        observers = new ArrayList<>();
        bookie = null;
    }
    
    public Evento(String[] equipas, float[] odds, Bookie bookie){
      
        try{
        this.equipas = new String[3];
        this.equipas[0] = equipas[0];
        this.equipas[1] = "Empate";
        this.equipas[2] = equipas[1];
        this.odds = new float[3];
        this.odds[0] = odds[0];
        this.odds[1] = odds[1];
        this.odds[2] = odds[2];
        this.historico = new ArrayList<>();
        this.apostas = new ArrayList<>();
        this.estado = true;
        observers = new ArrayList<>();
        this.bookie = bookie;
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

    /**
     * @param odds the odds to set
     */
    public void setOdds(float[] odds) {
        this.odds = odds;
        actualizaHistorico(odds);
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
        for(Aposta a : getApostas()){
            a.setEstado(false);
        }
    }
    
    public boolean verificaBookie(Bookie b){
        return this.bookie.equals(b);
    }
    
    public void novaAposta(int valor, int opcao,Apostador apostador){
        float odd;
        odd=this.odds[opcao];
        Aposta newBet = new Aposta(opcao,valor,odd,apostador);
        getApostas().add(newBet);
    }
    
    public String printBet(){
        
        StringBuilder result = new StringBuilder();
        
            result.append("\n Apostas \n");
            for(Aposta a: getApostas())
            {   
                int opcao = a.getOpcao();
                int valor = a.getValor();
                result.append(equipas[opcao] + " - " + valor + "€\n");
            }
            return result.toString();
            }
    
    
    public String toString(){
        
        
        StringBuilder result = new StringBuilder();
        for( int i = 0; i <= odds.length - 1; i++)
        {
            result.append(" | " + equipas[i] + " " + odds[i] + " | "); 
        }
        if(this.estado)
            result.append("Evento aberto\n");
        else
            result.append("Evento finalizado\n");
       return result.toString();
    }

    public String historicoOdds(){
        StringBuilder result = new StringBuilder();
        
        for(Historico h: historico)
        {
            result.append(h.toString());
        }
        return result.toString();
    }
        
    private void actualizaHistorico(float[] odds) {
        Historico actual = new Historico(odds);
        historico.add(actual);
        notifyObserverOdds();
        }

    public void setFinalizado(int vencedor){
        if(this.estado){
        this.vencedor= vencedor;
        this.estado = false;
        for(Aposta a: getApostas()){
            a.actualizaApostador(vencedor);
        }
        notifyObserver();
        } else {
            System.out.println("Erro já se encontra finalizado");
        }
    }

    /**
     * @return the apostas
     */
    public ArrayList<Aposta> getApostas() {
        return apostas;
    }
    
    public ArrayList<Aposta> apostasApostador(Apostador apostador){
        ArrayList<Aposta> aux = new ArrayList<>();
         for(Aposta a: apostas){
                if(a.getApostador().equals(apostador))
                    aux.add(a);
            }
        return aux;
    }
  
    /**
     * @return the bookie
     */
    public Bookie getBookie() {
        return bookie;
    }

    /**
     * @param bookie the bookie to set
     */
    public void setBookie(Bookie bookie) {
        this.bookie = bookie;
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        int observerIndex = observers.indexOf(o);
        observers.remove(observerIndex);
    }

    @Override
    public void notifyObserver() {
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

    @Override
    public void notifyObserverOdds() {
        for(Observer o: observers){
            o.update(equipas);
        }
    }
}
