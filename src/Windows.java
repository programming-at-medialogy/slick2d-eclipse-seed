
public class Windows {
/**
 * Quick and easy way to alter the size of the screen
 */
	public static int scWidth = 1440; // Game screen width
	public static int scHeight = 900; // Game screen height
	public static float scFactor = 0.3f; 
	public static boolean fullscreen = true;
	public static float padding = GameState.hexWidth/22*scFactor;
	/*
	public static float screenSizeFactorCalculate(){
		if (scWidth == 1920){
			scFactor = 0.3f;
		}else if (scWidth==1440){
			scFactor = 0.2f;
		}
		
		return scFactor;
	}*/
	
}

