/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilizadores;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author PauloCardoso
 */
public class Bookie extends Utilizador implements Observer {
    
    
    public Bookie(String nome, String email,String password){
        super(nome,email,password);
    }

    
    @Override
    public boolean verificaUtilizador(String nome,String password){
        return super.verificaUtilizador(nome, password);
    }
    
    
    @Override
    public void update(Observable o, Object arg) {
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

}
