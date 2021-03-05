import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Date;
public class Serveur {
    public static void main(String[] args) {
        System.out.println("server is up..." );
        try{
            // creation de soket
            DatagramSocket d = new DatagramSocket(5006);
            byte[] tmessage = new byte[100];
            DatagramPacket p = null;
            while (true)
            {
                // creation de datagrame to recive les donnes from Clients
                p = new DatagramPacket(tmessage, tmessage.length);
                // recive data from 1 Client
                d.receive(p);
                String m = new String (tmessage);
                    //////// Send Message to serveur //
                try {
                    DatagramPacket ds = new DatagramPacket(tmessage, tmessage.length, InetAddress.getByName("224.0.0.10"), 2021);
                    DatagramSocket s = new DatagramSocket();
                    // yab3eth tmessage via une adresse de diffusion Multicaste 244.0.0.10
                    s.send(ds);
                } catch (UnknownHostException | SocketException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
               /// Fin Send Message to server ///
                System.out.println("Client Send :" +m);
                // Clear Tmessage
                tmessage = new byte[100];
            }
        
    }
        catch (IOException e){
            System.out.println("Server Down " );
        }
    }


}