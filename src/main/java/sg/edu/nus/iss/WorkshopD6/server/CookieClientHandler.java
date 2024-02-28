package sg.edu.nus.iss.WorkshopD6.server;

import java.io.IOException;
import java.net.Socket;

public class CookieClientHandler implements Runnable{
    private Socket sock;
    private String cookieFile;

    public CookieClientHandler(Socket s, String cookieFile) {
        this.sock = s;
        this.cookieFile = cookieFile;
    }

    @Override
    public void run() {
        NetworkIO netIO = null;
        String req = "";
        String randomCookieResp = "";
        try {
            netIO = new NetworkIO(sock);
            while (true) {
                //System.out.println("Received command from client");
                req = netIO.read();
                if (req.trim().equals("end")) {
                    System.out.println("Client request to close connection");
                    break;
                }
                if (req.trim().equals("get-cookie")) {
                    randomCookieResp = Cookie.getRandomCookie(this.cookieFile);
                    netIO.write("cookie-text_" + randomCookieResp);
                    break;
                } else {
                    netIO.write("ERROR! Invalid command");
                }
            }
            netIO.close();
            sock.close();
            System.out.println("\nExiting the thread");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
