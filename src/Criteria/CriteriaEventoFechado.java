/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Criteria;

/**
 *
 * @author PauloCardoso
 */

import Eventos.Evento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PauloCardoso
 */
public class CriteriaEventoFechado implements Criteria {

    @Override
    public List<Evento> meetCriteria(List<Evento> eventos) {
           List<Evento> aux = new ArrayList<Evento>();
           
           for(Evento a: eventos)
           {
               if(!a.isEstado())
                   aux.add(a);
           }
           return aux;
    }   
    
    public CriteriaEventoFechado clone(){
        return new CriteriaEventoFechado();
    }
}
