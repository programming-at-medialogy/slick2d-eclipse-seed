import java.io.IOException;
import java.util.Scanner;

public class ServerMain {

	public static void start() {
		System.out.println("Server is running");
		NetworkServer networkServer = new NetworkServer();
		System.out.println("Test");
		networkServer.run();
		ServerActions.initActions();
		ServerActions.updateGameData();
		ServerActions.generateMap();
	}
	
	
	public static void addPlayer(int id) {
		//GameData.players.add(new Player(id));
	}
}
