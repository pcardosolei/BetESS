

package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rui
 */
public class ThreadServidor extends Thread {

  
    Sistema sis = new Sistema();
    int aux = 0;

    public ThreadServidor(Sistema sist) {
        sis = sist;
    }

    public void run() {
        try {
            System.out.println("Consola Servidor ON");
            BufferedReader i = new BufferedReader(new InputStreamReader(System.in));
            String l = i.readLine();
            while (true) {//alterei para exit para nao me desligar o servidor por engano 
                /*
                    case "Iniciar":
                        if (s.length == 3) {
                            if (s[1].equals("Tarefa")) {
                              //  emexec.add(sis.TarefabyName(s[2]));
                              //  sis.realizaTarefa(s[2]);
                              //  this.reservas.put((id.concat(Integer.toString(aux))), sis.TarefabyName(s[2]));
                                System.out.println("Tarefa Reservada com sucesso ID: " + (id.concat(Integer.toString(aux))));
                                aux++;
                            } else {
                                System.out.println(" --- Dados invalidos ---");
                                System.out.flush();
                            }
                        } else {
                           System.out.println(" --- Numero de campos introduzidos incorretos ---");       
                           System.out.flush();
                        }         
                        System.out.flush();
                        break;
                        
                }
                   */
                System.out.flush();
                l = i.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.close();
        }
    }
}
