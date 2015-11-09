
package app;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
 

public class Cliente {
 
    public static void main(String[] args) throws IOException {
 
        Socket s = new Socket("localhost", 2500);
        Scanner input = new Scanner(System.in);
 
        PrintWriter o = new PrintWriter(s.getOutputStream());
        BufferedReader i = new BufferedReader(new InputStreamReader(s.getInputStream()));
 
        String l = i.readLine();
        System.out.println("Servidor: " + l);
        String clientResponse = input.nextLine();
 
        while (!clientResponse.equals("")) {
            o.println(clientResponse);
            o.flush();
            l = i.readLine(); //pode ser para ver o paragrafo
            System.out.println("Servidor: " + l);
            clientResponse = input.nextLine();
        }
 
        o.println(clientResponse);
        o.flush();
        s.shutdownOutput();
        l = i.readLine();
        System.out.println("Servidor: " + l);
 
    }
 
}