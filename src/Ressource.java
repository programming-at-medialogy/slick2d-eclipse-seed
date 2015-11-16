import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;

public class Ressource {

	private static Map<String, Image> images;
	private static Map<String, SpriteSheet> sprites;
	private static Map<String, Sound> sounds;
	
	
	public Ressource(){
		images = new HashMap<String, Image>();
		sprites = new HashMap<String, SpriteSheet>();
		sounds = new HashMap<String, Sound>();
		
		try {
			images.put("background", loadImage("res/background.png"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Image loadImage(String path)throws SlickException{
		return new Image(path, false, Image.FILTER_NEAREST);
	}
	
	private SpriteSheet loadSprite(String path, int tw, int th) throws SlickException{
		return new SpriteSheet(loadImage(path), tw, th);		
	}
	
	public static Image getSpriteImage(String getter, int x, int y){
		return sprites.get(getter).getSubImage(x, y);
	}
	
	public static Image getImage(String getter){
		return images.get(getter);
	}
}