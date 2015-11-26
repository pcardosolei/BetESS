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

public class AndCriteria implements Criteria {

   private Criteria criteria;
   private Criteria otherCriteria;

   public AndCriteria(Criteria criteria, Criteria otherCriteria) {
      this.criteria = criteria;
      this.otherCriteria = otherCriteria; 
   }
   
   public AndCriteria(AndCriteria a){
       this.criteria = a.getCriteria();
       this.otherCriteria = a.getOtherCriteria();
   }

   @Override
   public List<Evento> meetCriteria(List<Evento> evento) {
   
      List<Evento> firstCriteriaPersons = getCriteria().meetCriteria(evento);		
      return getOtherCriteria().meetCriteria(firstCriteriaPersons);
   }

   public AndCriteria clone(){
       return new AndCriteria(this);
           
   }

    /**
     * @return the criteria
     */
    public Criteria getCriteria() {
        return criteria;
    }

    /**
     * @return the otherCriteria
     */
    public Criteria getOtherCriteria() {
        return otherCriteria;
    }
}