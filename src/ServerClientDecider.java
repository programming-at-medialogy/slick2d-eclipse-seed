public class ServerClientDecider {
	
	static final boolean isServer = true;
	
	public static void main(String[] args) {
		if (isServer)
			ServerMain.start();
		else
			Main.start();
	}
}
