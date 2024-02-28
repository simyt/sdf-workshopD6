package sg.edu.nus.iss.WorkshopD6.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApp 
{
    public static void main( String[] args ) throws IOException
    {
        int port = 12345; //default port to use if there nothing is provided
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        System.out.println("Connected to client");
        String cookieFile = args[1];
        //create a threadPool to handle client requests
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        ServerSocket server = new ServerSocket(port);
        System.out.printf("Cookie server started %s", port);
        while (true) {
            Socket sock = server.accept();
            CookieClientHandler cch = new CookieClientHandler(sock, cookieFile);
            threadPool.submit(cch);
            
        }
        
    }

}
