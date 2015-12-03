

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/*
 * Class to render the tiles (hexPieces) of the game
 * The class also declares the integers used for the x and y middle point of the tiles.
 */

public class HexPiece {
	
	Image img;
	int x;
	int y;
	int index;
	int xMiddle;
	int yMiddle;
	
	HexPiece(int x, int y, int index) throws SlickException{
		this.x = x;
		this.y = y;
		this.index = index;
		xMiddle = x + 48;
		yMiddle = y + 56;
		img = new Image("images/tile"+index+".png");

	}
	
	//Renders the tiles when called.
	public void render(GameContainer gc, Graphics g) throws SlickException
	{	
		g.drawImage(img,x,y);	
	}
}
