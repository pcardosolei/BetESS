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
public class CriteriaEventoAberto implements Criteria {

    @Override
    public List<Evento> meetCriteria(List<Evento> eventos) {
           List<Evento> aux = new ArrayList<Evento>();
           
           for(Evento a: eventos)
           {
               if(a.isEstado())
                   aux.add(a);
           }
           return aux;
    }

    @Override
    public List<Evento> meetCriteria(List<Evento> eventos, Bookie b) {
          List<Evento> aux = new ArrayList<>();
           
           for(Evento a: eventos)
           {
               if(a.isEstado())
                   aux.add(a);
           }
           return aux;
    }
    
}