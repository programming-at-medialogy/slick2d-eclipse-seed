
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/*
 * Class to get the images for the roads
 */

public class RoadSpawn {

	Image road;
	
	//X and Y for the constructor
	int x;
	int y;
	int roadNo; //which road number must be used?
	int playerNo; //what player number has been assigned in the controller class?
	
	//Constructor
	RoadSpawn(int x, int y, int playerNo, int roadNo) throws SlickException {
		this.x = x;
		this.y = y;
		this.roadNo = roadNo;
		this.playerNo = playerNo;
		road = new Image("images/roadPlayer"+playerNo+"_"+roadNo+".png"); //images are named: roadPlayer and needs two numbers; a player number and a road number
	}
	
	//render method to be called, informing that an image of house.png must be spawned at point X,Y
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawImage(road,x,y); //needed to render the images
	}
}
