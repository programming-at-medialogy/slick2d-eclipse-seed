

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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
	
	HexNumber(int x, int y) throws SlickException {
		this.x = x;
		this.y = y;
		img = new Image("images/desertPiece.png");
	}
	
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawImage(img,x,y);	
	}
}
