import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class user2 extends JFrame {
    private JTextField textField1;
    private JButton sendButton;
    private JTextArea textArea1Recive;
    private JPanel mainpane;
    private JTextField txt_Nom;
    private static Socket sc;


    public user2(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setContentPane(mainpane);
        setTitle("ChatBot");
        setVisible(true);
        setSize(new Dimension(660,600));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //////// Send Message to serveur //
                try {
                    // Message à envoyer
                    String message = txt_Nom.getText() +" send : "+ textField1.getText();
                    // Conversion vers un tableau de byte
                    byte[] tmessage = message.getBytes();
                    // Construction du paquet à envoyer
                    DatagramPacket d = new DatagramPacket(tmessage, tmessage.length, InetAddress.getLocalHost(), 5006);
                    // Construction de la socket pour l'envoi
                    DatagramSocket s = new DatagramSocket();
                    // Envoyer le paquet
                    s.send(d);
                    textField1.setText("");
                    //   s.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                /// Fin Send Message to server ///


            }
        });
    }

    public static void main(String[] args) {     String chaine = "" ;
        user2 ch = new  user2();
        try {
            while (true){
                byte[] tmessage = new byte[100];
                DatagramPacket d = new DatagramPacket (tmessage , tmessage.length);
                MulticastSocket s = new MulticastSocket(2021) ;
                InetAddress group = InetAddress.getByName("224.0.0.10");
                s.joinGroup(group);
                s.receive(d);
                String m = new String (tmessage);
                chaine += m+"\n" ;
                ch.textArea1Recive.setText(chaine);
            }
        }
        catch (IOException e){
            System.out.println();
        }
    }
}
