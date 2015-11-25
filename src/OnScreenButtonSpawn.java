


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class OnScreenButtonSpawn {


	Image button;
	
	//X and Y for the constructor
	int x;
	int y;
	int buttonNo;
	
	//Constructor
	OnScreenButtonSpawn(int x, int y, int buttonNo) throws SlickException {
		this.x = x;
		this.y = y;
		this.buttonNo = buttonNo;
		button = new Image("images/button"+buttonNo+".png");
	}
	
	//render method to be called, informing that an image of house.png must be spawned at point X,Y
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawImage(button,x,y);
	}
}
