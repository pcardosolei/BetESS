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

public class AndCriteria implements Criteria {

   private Criteria criteria;
   private Criteria otherCriteria;

   public AndCriteria(Criteria criteria, Criteria otherCriteria) {
      this.criteria = criteria;
      this.otherCriteria = otherCriteria; 
   }

   @Override
   public List<Evento> meetCriteria(List<Evento> evento) {
   
      List<Evento> firstCriteriaPersons = criteria.meetCriteria(evento);		
      return otherCriteria.meetCriteria(firstCriteriaPersons);
   }

    @Override
    public List<Evento> meetCriteria(List<Evento> eventos, Bookie b) {
      List<Evento> firstCriteriaPersons = criteria.meetCriteria(eventos,b);		
      return otherCriteria.meetCriteria(firstCriteriaPersons,b);
  
    }
}