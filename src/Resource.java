import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;


public class Resource {

//	private static Map<String, Image> images; 
//	private static Map<String, SpriteSheet> sprites;
	
	public static Font buttonTempFont = new Font("resources/16790.ttf", Font.BOLD, 32);
	public static TrueTypeFont buttonFont = new TrueTypeFont(buttonTempFont, true);


	public Resource() throws SlickException{

/*		images = new HashMap<String, Image>();
		sprites = new HashMap<String, SpriteSheet>();
		
		try {
			images.put("button", loadImage("resources/hexagon_0.png"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
/*	private Image loadImage(String path)throws SlickException{
		return new Image(path, false, Image.FILTER_NEAREST);
	}
	
	public static Image getSpriteImage(String getter, int x, int y){
		return sprites.get(getter).getSubImage(x, y);
	}
	
	public static Image getImage(String getter){
		return images.get(getter);
	}*/
}