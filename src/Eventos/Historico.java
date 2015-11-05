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

 
    public Historico(float[] odds){
        GregorianCalendar cal = new GregorianCalendar();
        this.odds = new float[3];
        this.odds[0] = odds[0];
        this.odds[1] = odds[1];
        this.odds[2] = odds[2];
        this.data = cal.getTime();
    }
    
    public String toString(){
        
        int i;
        StringBuilder result = new StringBuilder();

        for(i=0; i < 3; i++){
            result.append(this.odds[i]+" ");
        }
        result.append(this.data+"\n");
        return result.toString();      
    }
    
}
