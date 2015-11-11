/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilizadores;

/**
 *
 * @author PauloCardoso
 */
public class Aposta {
 
    private int valor;
    private int opcao;
    private float odd;
    private boolean estado; // true-> aberto
    
    public Aposta(){
        valor = 0;
        opcao = 0;
        odd = 0;
        estado = false;
    }
    
    public Aposta(int opcao, int valor, float odd){
        

        this.opcao = opcao;
        this.valor = valor;
        this.odd = odd; 
        this.estado = true;
    }
    
 

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @return the opcao
     */
    public int getOpcao() {
        return opcao;
    }

    /**
     * @param opcao the opcao to set
     */
    public void setOpcao(int opcao) {
        this.opcao = opcao;
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
     public String toString(){
               
        StringBuilder result = new StringBuilder();

        result.append(this.opcao+"\n");
        result.append(this.valor+"\n");
        return result.toString();
        }

    /**
     * @return the odd
     */
    public float getOdd() {
        return odd;
    }

    /**
     * @param odd the odd to set
     */
    public void setOdd(int odd) {
        this.odd = odd;
    }
}
