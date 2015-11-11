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
public abstract class Utilizador {
    
    private String nome;
    private String mail;
    private String password;
   
    
    public Utilizador(){
        
        nome = "";
        mail = "";
        password = "";
    }
    
    public Utilizador(String nome,String mail,String password){
        
        this.nome = nome;
        this.mail = mail;
        this.password = password;
    }
  
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the disponivel
     */

    
     public String toString(){
        
        
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

      
        result.append(NEW_LINE);
        return result.toString();
        }
     
      //EQUALS
     
    public boolean verificaUtilizador(String nome,String password){
        return(this.nome.equals(nome)
                && this.password.equals(password));
    }
    
    public boolean equals(Object obj)
    {
        boolean iguais = true;
        
        if(this == obj) return true;
        if((obj == null) || (this.getClass() != obj.getClass())) return false;
        
        Utilizador c = (Utilizador) obj;
        
        return(this.nome.equals(c.getNome())
                && this.mail.equals(c.getMail())
                && this.password.equals(c.getPassword()));
          }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
