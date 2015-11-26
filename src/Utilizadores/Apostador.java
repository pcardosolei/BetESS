
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilizadores;

import Eventos.Evento;
import Exception.SemSaldoException;
import Observer.Observer;

/**
 *
 * @author PauloCardoso
 */
public class Apostador extends Utilizador implements Observer {
    
    private float disponivel; 
    
    public Apostador(){
        super();
        disponivel = 0;
    }
    
    public Apostador(String nome,String mail,String password,String nickname){
        super(nome,mail,password,nickname);
        this.disponivel = 0;
    }
    
    public Apostador(String nome,String mail,String password, int disponivel,String nickname){
        super(nome,mail,password,nickname);
        this.disponivel = disponivel;
    }
    
    
        public float getDisponivel() {
        return disponivel;
    }

    /**
     * @param disponivel the disponivel to set
     */
    public void setDisponivel(float disponivel) {
        this.disponivel = disponivel;
    }
    
    public void Deposito(float valor){
        this.disponivel += valor;
    }
    
    public void Levantamento(float valor) throws SemSaldoException{
        if(valor > disponivel){
            throw new SemSaldoException(disponivel);
        }
        else{
        this.disponivel -= valor;
            }
        }
    
    public boolean testaSaldo(float valor) throws SemSaldoException{
        if(valor > disponivel){
            throw new SemSaldoException(disponivel);
        }
        else{
        return valor <= disponivel;
        }
    }
    
    public void retiraValor(float valor){
        this.disponivel -= valor;
    }
    public void actualizaDisponivel(float valor){
        setDisponivel(this.disponivel + valor);   
    }
    
    @Override
    public boolean verificaUtilizador(String nome,String password){
        return super.verificaUtilizador(nome, password);
    }
    
    public boolean equals(Object obj){
        if(this == obj) return true;
        if((obj == null) || (this.getClass() != obj.getClass())) return false;
         
        Apostador a = (Apostador) obj;
         
        return(super.equals(a)  && this.disponivel == (a.getDisponivel()));
                                
    }

  

    @Override
    public void update(Evento o, Object arg) {
        float valor =(float) arg;
        if(o!=null){    
            Notificacao nota = new Notificacao("Foi encerrado um evento e ganhou " + valor +"€",false);
            super.addNotificacao(nota);
        }}

    @Override
    public void update(String[] equipas) {
        Notificacao nota = new Notificacao("Foram alteradas as odds do evento "+ equipas[0] + " -- " + equipas[2],false);
        super.addNotificacao(nota);
    }
    
}

