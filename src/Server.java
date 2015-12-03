import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	
    protected ServerSocket serverSocket = null;
    protected boolean listening = true;
    protected List<ServerThread> clientSockets;
    PlayerInformation serverData;
    int playerNumber = 1;

    SOCHexMap map;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(5555);
        clientSockets = new ArrayList<>();
        map = new SOCHexMap();

        while (listening) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket);
            clientSockets.add(new ServerThread(clientSocket, this));
            serverData = new PlayerInformation();
            handlePlayer(serverData);
        }

        serverSocket.close();
    }

    public void broadcastObject(Object obj){
        for(ServerThread thread : clientSockets) {
            thread.write(obj);
        }
    }

    public void removeSocket(Socket socket) {
        clientSockets.remove(socket);
        System.out.println("socket removed");
    }

    public void generateMap(PlayerInformation server){
        server.SOCnumberIndex = map.numberIndex;
        server.SOCtileIndex = map.tileIndex;
    }

    public void handlePlayer(PlayerInformation server){
        generateMap(server);
        server.playerNumber = playerNumber;
        clientSockets.get(playerNumber - 1).write(server);
        playerNumber++;
    }

    public static void main(String[] args) throws IOException {
        new Server(4444);
    }
	

}