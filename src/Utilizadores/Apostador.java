/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilizadores;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author PauloCardoso
 */
public class Apostador extends Utilizador {
    
    private int disponivel; 
    
    public Apostador(){
        super();
        disponivel = 0;
    }
    
    public Apostador(String nome,String mail){
        super(nome,mail);
    }
    
    public Apostador(String nome,String mail, int disponivel){
        super(nome,mail);
        this.disponivel = disponivel;
    }
    
    
        public int getDisponivel() {
        return disponivel;
    }

    /**
     * @param disponivel the disponivel to set
     */
    public void setDisponivel(int disponivel) {
        this.disponivel = disponivel;
    }
    
    public void Deposito(int valor){
        this.disponivel += valor;
    }
    
    public void Levantamento(int valor){
        if(valor > disponivel){
          System.out.println("não possui fundos.");  
        }
        else{
        this.disponivel -= valor;
            }
        }
    
    
}
