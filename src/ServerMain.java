import java.io.IOException;

public class ServerMain {

	public static void start() {
		System.out.println("Server is running");
		try {
			NetworkServer.startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void received(int clientId, String message) {
		NetworkServer.sendToAll("Message received from " + clientId + ":\n" + message);
		/*if (GameData.players.get(clientId).getPlayerName() == null) {
			GameData.players.get(clientId).setPlayerName(message);
		}*/
	}
	
	public static void addPlayer(int id) {
		//GameData.players.add(new Player(id));
	}
}
