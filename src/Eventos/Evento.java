/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

import java.util.ArrayList;

/**
 *
 * @author PauloCardoso
 */
public class Evento {
    
    private String[] equipas;
    private float[] odds;
    private boolean estado; //true = aberto
    private int vencedor;
    private ArrayList<Historico> historico;
    
    public Evento(){
        equipas = new String[3];
        odds = new float[3];
        estado = false;
        historico = new ArrayList<>();
    }
    
    public Evento(String[] equipas, float[] odds, boolean estado){
        this.equipas = new String[3];
        this.equipas[0] = equipas[0];
        this.equipas[1] = "Empate";
        this.equipas[2] = equipas[1];
        this.odds = new float[3];
        this.odds[0] = odds[0];
        this.odds[1] = odds[1];
        this.odds[2] = odds[2];
        this.estado = estado;
        historico = new ArrayList<>();
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
        actualizaOdds();
        this.odds = odds;
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
    
    public void actualizaOdds(){
        Historico actual = new Historico(this.getOdds());
        historico.add(actual);
    }
    
    public String toString(){
        
        
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        for( int i = 0; i <= odds.length - 1; i++)
        {
            result.append( equipas[i] + " " + odds[i] + "|"); 
        }
        result.append(NEW_LINE);
          for(Historico h: historico){
            result.append(h.toString());
            result.append(NEW_LINE);
        }
        
        return result.toString();
        }
    

}
