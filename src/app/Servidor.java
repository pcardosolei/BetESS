
package app;
 
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
 

public class Servidor {
 
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(2500);
        int nclient = 1;
     //   Sistema sis = new Sistema();
     //   ArrayList<Tarefa> emcurso = new ArrayList<Tarefa>();
 
        //Preenchimento dos Dados
        System.out.println("Socket criado Porta 2500");
        //ThreadSVSV tservidor = new ThreadSVSV(sis, emcurso);
        tservidor.start();
        while (true) {
            Socket s = ss.accept();
            //sem join
            ThreadSV ts = new ThreadSV(s, nclient, sis, emcurso);
            ts.start();
            nclient++;
        }
    }
}