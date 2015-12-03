import java.io.IOException;
import java.util.Scanner;

public class ServerMain {

	public static void start() {
		System.out.println("Server is running");
		ServerActions.initActions();
		NetworkServer networkServer = new NetworkServer();
		networkServer.run();
		GameData.initGameData(networkServer.getNumClients());
		ServerActions.sendPlayerAmount();
		ServerActions.nameRequest();
		ServerActions.generateMap();
	}
	
	
	public static void addPlayer(int id) {
		//GameData.players.add(new Player(id));
	}
}
