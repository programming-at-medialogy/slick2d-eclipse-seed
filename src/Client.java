import java.io.*;
import java.net.Socket;


public class Client {
	String IP = "127.0.0.1";
    Socket socket = new Socket(IP, 5555); //creates a socket at the given IP and port
    ObjectOutputStream out;
    ObjectInputStream in;
    PlayerInformation obj;

    public Client () throws IOException, ClassNotFoundException {
        out = new ObjectOutputStream(socket.getOutputStream()); //creates an outputstream which writes to the socket	
        in = new ObjectInputStream(socket.getInputStream()); //creates an inputstream which listens to the socket
        obj = new PlayerInformation();
    }

    public void run() {
    	
    	//thread to listen to the server
    final Thread inThread = new Thread(){
        @Override
        public void run(){


            try {
                while((obj = (PlayerInformation) in.readObject()) != null){ //receive object while it is being written to us.

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    };
    inThread.start();
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client = new Client();
        client.run(); //starts the client 
    }
    
    
    public void sendData(Object object) throws IOException{
    	out.writeObject(object);
    }
}