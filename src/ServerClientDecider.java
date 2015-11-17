public class ServerClientDecider {
	
	static final boolean isServer = false;
	
	public static void main(String[] args) {
		if (isServer)
			ServerMain.start();
		else
			Main.start();
	}
}
