

package app;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui
 */
public class ThreadCliente extends Thread {

    Socket b;
    int nclient;
    boolean sessao = false;
    Sistema sis = new Sistema();
    String s[];
    String id = "id";
    int aux = 0;

    public ThreadCliente(Socket s, int n, Sistema sist) {
        nclient = n;
        b = s;
        sis = sist;
        sessao = false;
    }

    public void run() {

        PrintWriter output = null;
        try {
            System.out.println("Cliente aceite..");
            output = new PrintWriter(b.getOutputStream());
            BufferedReader i = new BufferedReader(new InputStreamReader(b.getInputStream()));
            output.println("Servidor ON");
            output.flush();
            String l = i.readLine();

            while (sessao == false) {
                
                //if (s[0].equals("Login") && s.length == 3 && sis.loginUser(s[1], s[2])) {
                    sessao = true;
                }
                output.println("Sessao:" + sessao);
                output.flush();
                l = i.readLine();
        
            
        } catch (IOException ex) {
            Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            output.close();
        }
    }
}
