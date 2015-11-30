


import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/* Class to spawn the houses used in the game.
 * The class shows where the image is stored
 * and where it must be rendered. The constructor takes
 * arguments to make sure we can render them where we want.
 * 
 */

public class HouseSpawn {

	Image house; // Used to inform where the image is stored

	// X and Y for the constructor
	int x;
	int y;
	int playerNo;

	// Constructor
	HouseSpawn(int x, int y, int playerNo) throws SlickException {
		this.x = x;
		this.y = y;
		this.playerNo = playerNo;
	}
	
	
	// render method to be called, informing that an image of house.png must be
	// spawned at point X,Y
	public void render(GameContainer gc, Graphics g) throws SlickException {
		house = new Image("images/housePlayer" + playerNo + ".png");
		g.drawImage(house, x, y);
	}
	

	
}


