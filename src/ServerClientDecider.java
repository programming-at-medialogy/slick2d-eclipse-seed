import org.newdawn.slick.SlickException;

public class ServerClientDecider {
	
	static final boolean isServer = false;
	static final boolean isTest = false;
	
	public static void main(String[] args) {
		if (isServer)
			ServerMain.start();
		else if (!isTest)
			Main.start();
		else {
			try {
				testMain.init();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
