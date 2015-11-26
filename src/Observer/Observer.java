/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

import Eventos.Evento;



/**
 *
 * @author TEN
 */
public interface Observer {
    
    public void update(Evento o, Object arg);
    public void update(String[] equipas);
}
