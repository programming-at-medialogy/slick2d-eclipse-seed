import java.io.*;
import java.net.Socket;


public class ServerThread {
	
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    private Server server;


    public ServerThread(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        connect();
    }

    public void connect() {

        Thread read = new Thread() {
            @Override
            public void run() {
            PlayerInformation obj;
                try {
                    while ((obj = (PlayerInformation) in.readObject()) != null) {
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



    public void write(Object obj) {
        try{
            out.reset();
            out.writeObject(obj);
        }
        catch(IOException e){ e.printStackTrace(); }
    }
	

}