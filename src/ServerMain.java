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
	
	
	public static void addPlayer(int id) {
		//GameData.players.add(new Player(id));
	}
}
