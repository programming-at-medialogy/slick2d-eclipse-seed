import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;

/**
 * Class potentially storing all the files from the resource folder. 
 * @author frede_000
 *
 */
public class Resource {


	public static Font [] buttonTempFont;
	public static TrueTypeFont[] buttonFont;
	
	public static void initResources() {
		buttonTempFont = new Font[30];
		buttonFont = new TrueTypeFont[30];
		
		for (int i = 0; i < buttonTempFont.length; i++) {
			buttonTempFont[i] = new Font("resources/16790.ttf", Font.BOLD, i * 2 + 1);
			buttonFont[i] = new TrueTypeFont(buttonTempFont[i], true);
		}
	}
	//public static TrueTypeFont buttonFont = new TrueTypeFont(buttonTempFont, true);
	
	
	
	/**
	 * ATM. the warningFont is the same font as button.  
	 * This declaration might not be needed.
	 */
	public static Font warningTempFont = new Font("resources/16790.ttf", Font.BOLD, 22);
	public static TrueTypeFont warningFont = new TrueTypeFont(warningTempFont, true);
	public static Font listTempFont = new Font("resources/16790.ttf", Font.BOLD, 20);
	public static Font miscTempFont = new Font("resources/16790.ttf", Font.BOLD, 14);
	public static TrueTypeFont miscFont = new TrueTypeFont(miscTempFont, true);	
	
	public static TrueTypeFont listFont = new TrueTypeFont(listTempFont, true);
	public static Font titleTempFont = new Font("resources/TitleFont.ttf", Font.BOLD, 38);
	public static TrueTypeFont titleFont = new TrueTypeFont(titleTempFont, true);


	public Resource() throws SlickException{

	}

}