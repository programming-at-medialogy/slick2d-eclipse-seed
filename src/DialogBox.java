//import libs
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;

public class DialogBox {
//variables
	int x, y, width, height;
	static ArrayList<DialogBox> dialogBoxes = new ArrayList();
	ArrayList<ImageCoord> pictures = new ArrayList();
	ArrayList<StringCoord> texts = new ArrayList();
	TrueTypeFont dialogBoxFont;
	BasicGameState state;
	boolean isActive;
	
//constructor
	DialogBox(int x, int y, int width, int height, int fontSize, BasicGameState state){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.state = state;
		dialogBoxFont = Resource.getFont("std", fontSize);
		dialogBoxes.add(this);
		isActive = false;
	}
	
/**
 * Drawing the dialogBoxes at the appropriate location	
 * @param g is the slick 2d graphics element
 * @param state is the basicStateGame state the current game is in
 */
	public static void draw(Graphics g, BasicGameState state){
		for(int i = 0; i < dialogBoxes.size(); i++){
			if (dialogBoxes.get(i).state == state && dialogBoxes.get(i).isActive){
				g.setColor(new Color(0, 0, 0, 150));
				g.fillRect(dialogBoxes.get(i).x, dialogBoxes.get(i).y, dialogBoxes.get(i).width, dialogBoxes.get(i).height);
				//dialogBoxes.get(i).dialogBoxFont.drawString(dialogBoxes.get(i).x + dialogBoxes.get(i).width / 2 - dialogBoxes.get(i).dialogBoxFont.getWidth(dialogBoxes.get(i).text) / 2, dialogBoxes.get(i).y + dialogBoxes.get(i).height / 2 - dialogBoxes.get(i).dialogBoxFont.getHeight(dialogBoxes.get(i).text) / 2, dialogBoxes.get(i).text, new Color(100, 54, 26));
				
				//for each loop
				for (ImageCoord pic : dialogBoxes.get(i).pictures) {
					pic.image.draw(pic.x, pic.y, pic.width, pic.height);
				}
				//for each loop
				for (StringCoord line : dialogBoxes.get(i).texts){
					dialogBoxes.get(i).dialogBoxFont.drawString(line.x, line.y, line.text, new Color(255, 255, 255));
				}
			}
		}
	}
	/**
	 * Used to control when a dialogBox should appear 
	 */
	public void activate (){
		isActive = true;
	}
	/**
	 * Used to control when a dialogBox should not appear 
	 */
	public void deactivate (){
		isActive = false;
	}
	/**
	 * 
	 * @param image takes an image for the ImageCoord arrayList
	 * @param x image x coordinate
	 * @param y image y coordinate
	 * @param width image width
	 * @param height image height
	 */
	public void addImage(Image image, int x, int y, int width, int height){
		x = x - image.getWidth() / 2;
		y = y - image.getHeight() / 2;
		pictures.add(new ImageCoord(x, y, width, height, image));
	}
	/**
	 * 
	 * @param text
	 * @param x
	 * @param y
	 */
	public void addString(String text, int x, int y){
		x = x - dialogBoxFont.getWidth(text) / 2;
		y = y - dialogBoxFont.getHeight(text) / 2;
		texts.add(new StringCoord(x, y, text));
	}
	
	public void removeString() {
		texts.remove(texts.size() - 1);
	}
}

/**
 * 
 * @author frede_000
 *
 */
class ImageCoord {
	int x, y, width , height;
	Image image;
	
	ImageCoord(int x, int y, int width, int height, Image image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
	}
}
class StringCoord{
	int x, y;
	String text;
	StringCoord(int x, int y, String text){
		this.x = x;
		this.y = y;
		this.text = text;
	}
}