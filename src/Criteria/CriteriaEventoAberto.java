/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Criteria;

import Eventos.Evento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PauloCardoso
 */
public class CriteriaEventoAberto implements Criteria {

    
    
    @Override
    public List<Evento> meetCriteria(List<Evento> eventos) {
           List<Evento> aux = new ArrayList<>();
           
           for(Evento a: eventos)
           {
               if(a.isEstado())
                   aux.add(a);
           }
           return aux;
    }
    
    public CriteriaEventoAberto clone(){
        return new CriteriaEventoAberto();
    }
}
