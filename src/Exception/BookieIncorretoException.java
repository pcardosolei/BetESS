/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

import Utilizadores.Bookie;

/**
 *
 * @author TEN
 */
public class BookieIncorretoException extends Exception {

    private Bookie bookie;
    public BookieIncorretoException(Bookie b){
        bookie = b;
    }
    
    public String getName(){
        return bookie.getNome();
    }
}
