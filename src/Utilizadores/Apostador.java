
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
public class Apostador extends Utilizador {
    
    private int disponivel; 
    
    public Apostador(){
        super();
        disponivel = 0;
    }
    
    public Apostador(String nome,String mail,String password){
        super(nome,mail,password);
        this.disponivel = 0;
    }
    
    public Apostador(String nome,String mail,String password, int disponivel){
        super(nome,mail,password);
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
    
<<<<<<< HEAD
=======
    public boolean testaSaldo(float valor){
        if(valor > disponivel)
            return false;
        else
            return true;
    }
    
    public void retiraValor(float valor){
        this.disponivel -= valor;
    }
    public void actualizaDisponivel(float valor){
        setDisponivel(this.disponivel + valor);   
    }
    
    @Override
>>>>>>> refs/remotes/origin/dev
    public boolean verificaUtilizador(String nome,String password){
        return super.verificaUtilizador(nome, password);
    }
    
    public boolean equals(Object obj){
        if(this == obj) return true;
        if((obj == null) || (this.getClass() != obj.getClass())) return false;
         
        Apostador a = (Apostador) obj;
         
        return(super.equals(a)  && this.disponivel == (a.getDisponivel()));
                                
    }
<<<<<<< HEAD
=======

    @Override
    public void update(Observable o, Object arg) {
        float valor =(float) arg;
        if(o!=null)
            System.out.println("Foi encerrado um evento e ganhou " + valor);
        
    }
>>>>>>> refs/remotes/origin/dev
    
}

