import org.newdawn.slick.SlickException;

public class ServerClientDecider {
	
	static final boolean isServer = false;
	static final boolean isTest = true;
	
	public static void main(String[] args) {
		if (isServer)
			ServerMain.start();
		else {
			try {
				testMain.init();
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}
}