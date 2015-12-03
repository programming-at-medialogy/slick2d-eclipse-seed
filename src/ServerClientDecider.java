import org.newdawn.slick.SlickException;

public class ServerClientDecider {
	
	static final boolean isServer = false;
	static final boolean isTest = false;
	static final boolean isOther = false;

	
	public static void main(String[] args) {
		if (isOther) {
			BuildTesting.start();
		}
		
		else if (isServer)
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