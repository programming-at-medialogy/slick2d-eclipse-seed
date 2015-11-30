import java.io.*;
import java.net.*;

public class Client
{
    public static void main(String args[])
    {
        DatagramSocket sock = null;
        int port = 9000;
        String s;
         
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
         
        try
        {
            sock = new DatagramSocket();
             
            InetAddress host = InetAddress.getByName("localhost");
             
            while(true)
            {
                //takes input and sends the packet
                echo("Enter message to send : ");
                s = (String)cin.readLine();
                byte[] b = s.getBytes();
                 
                DatagramPacket  dp = new DatagramPacket(b , b.length , host , port);
                sock.send(dp);
                 
                //now receive reply
                //creating buffer for receiving incoming data
                byte[] buffer = new byte[65536];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                sock.receive(reply);
                 
                byte[] data = reply.getData();
                s = new String(data, 0, reply.getLength());
                 
                //details for incoming data - client ip : client port - client message
                echo(reply.getAddress().getHostAddress() + " : " + reply.getPort() + " - " + s);
            }
        }
         
        catch(IOException e)
        {
            System.err.println("IOException " + e);
        }
    }
     
    //simple function to echo data to terminal
    public static void echo(String message)
    {
        System.out.println(message);
    }
}