import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client {
	String IP = "127.0.0.1";
    Socket socket = new Socket(IP, 5555);
    ObjectOutputStream out;
    ObjectInputStream in;
    PlayerInformation obj;
    Scanner scanner = new Scanner(System.in);

    public Client () throws IOException, ClassNotFoundException {
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        obj = new PlayerInformation();
    }

    public void run() {
    final Thread inThread = new Thread(){
        @Override
        public void run(){


            try {
                while((obj = (PlayerInformation) in.readObject()) != null){

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
        client.run();
    }
    
    
    public void sendData(Object object) throws IOException{
    	out.writeObject(object);
    }
}