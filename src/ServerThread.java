import java.io.*;
import java.net.Socket;


public class ServerThread {
	
    Socket socket;
    ObjectInputStream in; //inputstream used to read from the socket.
    ObjectOutputStream out; //outputstream used to write to the socket.
    private Server server;


    public ServerThread(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        in = new ObjectInputStream(socket.getInputStream()); //read from the specified socket
        out = new ObjectOutputStream(socket.getOutputStream());//write to the specified socket
        connect(); //starts the thread.
    }

    public void connect() {

        Thread read = new Thread() {
            @Override
            public void run() {
            PlayerInformation obj;
                try {
                    while ((obj = (PlayerInformation) in.readObject()) != null) { //while we are receiving an object, broadcast it.
                            server.broadcastObject(obj);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        };
        read.start();
    }


    ///function used in the broadcast function to write to each client///
    public void write(Object obj) {
        try{
            out.reset(); 
            out.writeObject(obj);
        }
        catch(IOException e){ e.printStackTrace(); }
    }
	

}