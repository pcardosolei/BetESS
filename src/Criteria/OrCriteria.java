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
import java.util.List;

public class OrCriteria implements Criteria {

   private Criteria criteria;
   private Criteria otherCriteria;

   public OrCriteria(Criteria criteria, Criteria otherCriteria) {
      this.criteria = criteria;
      this.otherCriteria = otherCriteria; 
   }

   @Override
   public List<Evento> meetCriteria(List<Evento> eventos) {
      List<Evento> firstCriteriaItems = criteria.meetCriteria(eventos);
      List<Evento> otherCriteriaItems = otherCriteria.meetCriteria(eventos);

      for (Evento evento : otherCriteriaItems) {
         if(!firstCriteriaItems.contains(evento)){
            firstCriteriaItems.add(evento);
         }
      }	
      return firstCriteriaItems;
   }
   
   public OrCriteria clone(){return null;}
}
