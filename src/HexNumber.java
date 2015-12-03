

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/*
 * Classed used to render the numbers on the tiles. These numbers will be random placed
 */

public class HexNumber {
	
	Image img;
	int x;
	int y;
	int index;
	
	HexNumber(int x, int y, int index) throws SlickException{
		this.x = x;
		this.y = y;
		this.index = index;
		img = new Image("images/tileNumber"+index+".png");
	}
	
	//Render method to render the images.
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawImage(img,x,y);	
	}
}
