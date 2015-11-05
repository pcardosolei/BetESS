/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author PauloCardoso
 */
public class Historico {
     private float[] odds;
     private Date data;
//private time 


    
    public Historico(String[] equipas, float[] odds){
       this.odds = new float[3];
       GregorianCalendar cal = new GregorianCalendar();
       this.odds[0] = odds[0];
       this.odds[1] = odds[1];
       this.odds[2] = odds[2];
    }
}
