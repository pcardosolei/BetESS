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
    private int jogo;
    private int opcao;
    private int odd;
    private boolean estado;
    
    public Aposta(){
        valor = 0;
        jogo = 0;
        opcao = 0;
        odd = 0;
        estado = false;
    }
    
    public Aposta(int jogo, int opcao, int valor, int odd){
        
        this.jogo = jogo;
        this.opcao = opcao;
        this.valor = valor;
        this.odd = odd; 
        this.estado = estado;
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
     * @return the jogo
     */
    public int getJogo() {
        return jogo;
    }

    /**
     * @param jogo the jogo to set
     */
    public void setJogo(int jogo) {
        this.jogo = jogo;
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

        result.append(this.jogo+"\n");
        result.append(this.opcao+"\n");
        result.append(this.valor+"\n");
        return result.toString();
        }

    /**
     * @return the odd
     */
    public int getOdd() {
        return odd;
    }

    /**
     * @param odd the odd to set
     */
    public void setOdd(int odd) {
        this.odd = odd;
    }
}
