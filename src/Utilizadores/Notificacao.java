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
    
    private String texto;
    private boolean estado;
    
    public Notificacao(){
        texto = "";
    }
    
    public Notificacao(String notificacao, boolean estado){
        this.texto = notificacao;
        this.estado = estado;
    }

    /**
     * @return the texto
     */
    public String getNotificao() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setNotificao(String texto) {
        this.texto = texto;
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
    
    @Override
    public String toString(){
    
        
        StringBuilder result = new StringBuilder();
        result.append(this.texto);
        return result.toString();
    }}
