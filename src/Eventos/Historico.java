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


    
    public Historico(float[] odds){
       this.odds = new float[3];
       GregorianCalendar cal = new GregorianCalendar();
       this.odds[0] = odds[0];
       this.odds[1] = odds[1];
       this.odds[2] = odds[2];
       data = cal.getTime();
    }
    
    public String toString(){
        
        StringBuilder result = new StringBuilder();
        
        for( int i = 0; i <= odds.length - 1; i++)
        {
            result.append(odds[i] + " | ");     
        }
        result.append(" -- " + this.data);
        result.append("\n");
        return result.toString();
    }
}
