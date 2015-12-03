import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	
    protected ServerSocket serverSocket = null;
    protected boolean listening = true; //creates an infinite loop
    protected List<ServerThread> clientSockets; //creates a list of serverThreads.
    PlayerInformation serverData; 
    int playerNumber = 1; //sets the starting player number of the first player that connects
    String serverIP = "127.0.0.1"; //IP of the server
    InetAddress addr = InetAddress.getByName(serverIP);
    
    SOCHexMap map; 

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(5555,55,addr); //creates serversocket at given port and IP.
        clientSockets = new ArrayList<>(); //initialize array list of serverThreads.
        map = new SOCHexMap(); //initialize the HexMap, which is what we use to shuffle indexes of tiles and numbers.

        while (listening) { //while true
        	
            Socket clientSocket = serverSocket.accept(); //accept incoming sockets. Anything below this will be ignored until a client connects.
            System.out.println("Client connected: " + clientSocket); //prints out the connected client.
            clientSockets.add(new ServerThread(clientSocket, this)); //add the client to a new serverThread
            serverData = new PlayerInformation(); //object used to send out the player number to each player, and the HexMap array.
            handlePlayer(serverData); //handle the player, which gives player number and array.
        }

        serverSocket.close();
    }

    ///function used to broadcast everything the server receives to all clients///
    public void broadcastObject(Object obj){ 
        for(ServerThread thread : clientSockets) { //for each thread, write object to client in thread.
            thread.write(obj);
        }
    }
    
    ///stores the number and tile indexes in the two arrays
    public void generateMap(PlayerInformation server){
        server.SOCnumberIndex = map.numberIndex;
        server.SOCtileIndex = map.tileIndex;
    }
    
    //handle the player by giving them a player number.
    public void handlePlayer(PlayerInformation server){
        generateMap(server);
        server.playerNumber = playerNumber;
        clientSockets.get(playerNumber - 1).write(server);
        playerNumber++;
    }

    
    public static void main(String[] args) throws IOException {
    	//starts the server
        new Server(4444);
    }
	

}