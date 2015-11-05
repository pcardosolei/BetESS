/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

/**
 *
 * @author PauloCardoso
 */
public class Historico {
    private String[] equipas;
    private int[] odds;
    //private time 

    public Historico(){
        
    }
    
    public Historico(String[] equipas, int[] odds){
        this.equipas = equipas;
        this.odds = odds;
    }
}
