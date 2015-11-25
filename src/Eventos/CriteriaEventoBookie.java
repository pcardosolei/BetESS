/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

import Criteria.Criteria;
import Utilizadores.Bookie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PauloCardoso
 */
public class CriteriaEventoBookie implements Criteria {

    private Bookie bookie;
    
    public CriteriaEventoBookie(Bookie b){
        this.bookie=b;
    }

    @Override
    public List<Evento> meetCriteria(List<Evento> eventos) {
        ArrayList<Evento> aux = new ArrayList<>();
        
        for(Evento a: eventos){
            if(a.getBookie().equals(bookie))
                aux.add(a);
        }
        
        return aux;
    }
    
    
}
