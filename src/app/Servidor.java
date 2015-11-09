
package app;
 
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
 
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(2500);
        int nclient = 1;
        Sistema sis = new Sistema();
 
        //Preenchimento dos Dados
        System.out.println("Socket criado Porta 2500");
        ThreadServidor tservidor = new ThreadServidor(sis);
        tservidor.start();
        while (true) {
            Socket s = ss.accept();
            //sem join
            ThreadCliente ts = new ThreadCliente(s, nclient, sis);
            ts.start();
            nclient++;
        }
    }
}