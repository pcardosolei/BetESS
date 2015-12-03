/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilizadores;


import Eventos.Evento;
import Observer.Observer;
import java.util.ArrayList;


/**
 *
 * @author PauloCardoso
 */
public class Bookie extends Utilizador implements Observer {
    
    
    public Bookie(String nome, String email,String password,String nickname){
        super(nome,email,password,nickname);
    }

    
    @Override
    public boolean verificaUtilizador(String nome,String password){
        return super.verificaUtilizador(nome, password);
    }
    
    @Override
    public void update(Evento o,Object arg) {
        ArrayList<Aposta> apostas =(ArrayList<Aposta>) arg;
        int soma = 0;
        if(o!=null){
             for(Aposta a: apostas){
                 soma += a.getValor();
             }
             Notificacao nota = new Notificacao("Foram apostados " + soma + "€", false);
             super.addNotificacao(nota);
        }
    }

    @Override
    public void update(String[] equipas) {
        Notificacao nota = new Notificacao("Foram alteradas as odds do evento "+ equipas[0] + " -- " + equipas[2],false);
        super.addNotificacao(nota);
    }
    
}
