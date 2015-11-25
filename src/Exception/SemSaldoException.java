/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

/**
 *
 * @author TEN
 */
public class SemSaldoException extends Exception{
    private float disponivel;
    
    public SemSaldoException(float d){
        this.disponivel=d;
    }
    
    public String getMessage(){
        return "So tem disponivel "+ this.disponivel;
    }
}
