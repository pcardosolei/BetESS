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
import Utilizadores.Bookie;
import java.util.List;

public interface Criteria {
     public List<Evento> meetCriteria(List<Evento> eventos);

    public Criteria clone();
     

}
