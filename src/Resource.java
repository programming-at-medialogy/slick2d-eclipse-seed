import java.awt.Font;
import java.util.ArrayList;
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


	public static ArrayList<Font> stdTempFont;
	public static ArrayList<TrueTypeFont> stdFont;
	public static ArrayList<Integer> stdFontSize;
	public static ArrayList<Font> titleTempFont;// = new Font("resources/TitleFont.ttf", Font.BOLD, 38);
	public static ArrayList<TrueTypeFont> titleFont;// = new TrueTypeFont(titleTempFont, true);
	public static ArrayList<Integer> titleFontSize;
	
	public static void initResources() {
		stdTempFont = new ArrayList<Font>();
		stdFont = new ArrayList<TrueTypeFont>();
		stdFontSize = new ArrayList<Integer>();
		titleTempFont = new ArrayList<Font>();
		titleFont = new ArrayList<TrueTypeFont>();
		titleFontSize = new ArrayList<Integer>();
	}
	//public static TrueTypeFont buttonFont = new TrueTypeFont(buttonTempFont, true);


	public Resource() throws SlickException{

	}

	public static TrueTypeFont getFont(String type, int fontSize) {
		boolean hasFont = false;
		TrueTypeFont retFont = null;
		ArrayList<TrueTypeFont> tempFont;
		ArrayList<Integer> tempFontSize;
		if (type.equals("std")) {
			tempFont = stdFont;
			tempFontSize = stdFontSize;
		} else {
			tempFont = titleFont;
			tempFontSize = titleFontSize;
		}
		
		for (int i = 0; i < tempFont.size(); i++) {
			System.out.println(fontSize + " " + tempFontSize.get(i));
			if (tempFontSize.get(i) == fontSize) {
				System.out.println("Already there");
				hasFont = true;
				retFont = tempFont.get(i);
				break;
			}
		}
		
		if (!hasFont) {
			if (type.equals("std")) {
				stdFont.add(new TrueTypeFont(new Font("resources/OratorStd.ttf", Font.PLAIN, fontSize), true));
				stdFontSize.add(fontSize);
			}
			else {
				titleFont.add(new TrueTypeFont(new Font("resources/TitleFont.ttf", Font.BOLD, fontSize), true));
				titleFontSize.add(fontSize);
			}
			retFont = tempFont.get(tempFont.size() - 1);
		}
		System.out.println();
		return retFont;
	}
}