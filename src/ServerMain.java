import java.io.IOException;
import java.util.Scanner;

public class ServerMain {

	public static void start() {
		System.out.println("Server is running");
		NetworkServer networkServer = new NetworkServer();
		System.out.println("Test");
		networkServer.run();
		GameData.initGameData(networkServer.getNumClients());
		System.out.println(networkServer.getNumClients());
		ServerActions.initActions();
		ServerActions.sendPlayerAmount();
		ServerActions.nameRequest();
		ServerActions.generateMap();
	}
	
	
	public static void addPlayer(int id) {
		//GameData.players.add(new Player(id));
	}
}
