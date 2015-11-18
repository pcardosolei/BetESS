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
public class Notificacao {
    
    private String notificao;
    private boolean estado;
    
    public Notificacao(){
        notificao = "";
    }
    
    public Notificacao(String notificacao, boolean estado){
        this.notificao = notificacao;
        this.estado = estado;
    }

    /**
     * @return the notificao
     */
    public String getNotificao() {
        return notificao;
    }

    /**
     * @param notificao the notificao to set
     */
    public void setNotificao(String notificao) {
        this.notificao = notificao;
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
        result.append(this.notificao);
        return result.toString();
    }}
