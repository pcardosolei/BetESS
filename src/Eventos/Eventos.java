/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author PauloCardoso
 */
public class Eventos {
    private HashMap<Integer,Evento> jogos;
    
    public Eventos(){
        jogos = new HashMap<>();
    }
    
    public void criarEvento(){
        
        String[] equipas = new String[2];
        float[] odds = new float[3];
        Scanner in = new Scanner(System.in);
        System.out.println("Equipa1");
        equipas[0] = in.nextLine();
        System.out.println("Equipa2");
        equipas[1] = in.nextLine();
        System.out.println("Odd Vitoria Equipa1");
        odds[0] = in.nextFloat();
        System.out.println("Odd Empate");
        odds[1] = in.nextFloat();
        System.out.println("Odd Vitoria Equipa2");
        odds[2] = in.nextFloat();
                     
        Evento evento = new Evento(equipas,odds,true);
        getJogos().put(getJogos().size(),evento);
        System.out.println("Evento Criado");
    }
    
    public String listaEventos(){
        
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        for(Integer evento: getJogos().keySet())
        {
            result.append("EVENTO " + evento + NEW_LINE);
            result.append(getJogos().get(evento).toString()); 
        }
        result.append(NEW_LINE);
        return result.toString();
    }
    
    public void editarOdds(){
        float[] odds = new float[3];
        String[] equipas = new String[3];
        int codigo;
        Scanner in = new Scanner(System.in);
        System.out.println("Introduza o código do evento");
        codigo = in.nextInt();
        try{
            System.out.println(getJogos().get(codigo).toString());
            equipas = getJogos().get(codigo).getEquipas();
            System.out.println("Odd Vitoria " + equipas[0]);
            odds[0] = in.nextFloat();
            System.out.println("Odd Empate");
            odds[1] = in.nextFloat();
            System.out.println("Odd Vitoria " + equipas[2]);
            odds[2] = in.nextFloat(); 
            getJogos().get(codigo).setOdds(odds);
        } catch(Exception e){
            System.out.println("Não encontrou o evento");
        }
        
     }

    /**
     * @return the jogos
     */
    public HashMap<Integer,Evento> getJogos() {
        return jogos;
    }

    /**
     * @param jogos the jogos to set
     */
    public void setJogos(HashMap<Integer,Evento> jogos) {
        this.jogos = jogos;
    }
   
   
}
