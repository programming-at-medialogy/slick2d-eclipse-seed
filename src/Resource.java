import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Resource {
	
	int oreResource, clayResource, wheatResource, woodResource, woolResource;
	int x, y;
	int spacing = 125;
	int victoryPoint = 0;
	int roadCount = 0;
	int houseCount = 0;
	int xSpacing = 40;
	int ySpacing = 15;
	Image ore, clay, wood, wool, wheat;
	int armySize = 0;
	
	Resource(int x, int y) throws SlickException{
		this.x = x;
		this.y = y;
		
		ore = new Image("images/ore.png");
		clay = new Image("images/clay.png");
		wood = new Image("images/wood.png");
		wool = new Image("images/wool.png");
		wheat = new Image("images/wheat.png");
		oreResource = 0;
		clayResource = 4;
		wheatResource = 2;
		woodResource = 4;
		woolResource = 2;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		//ORE//
		g.drawString(oreResource + " X", x - xSpacing, y + ySpacing);
		g.drawImage(ore, x, y);
		///////
		
		//CLAY//
		g.drawString(clayResource + " X", x - xSpacing, y + ySpacing + spacing/2);
		g.drawImage(clay, x, y + spacing/2);
		////////
		
		//WHEAT//
		g.drawString(wheatResource + " X", x - xSpacing, y + ySpacing + spacing);
		g.drawImage(wood, x, y + spacing);
		/////////
		
		//WOOD//
		g.drawString(woodResource + " X", x - xSpacing, y + ySpacing + spacing/2 + spacing);
		g.drawImage(wool, x, y + spacing + spacing/2);
		////////
		
		//WOOL//
		g.drawString(woolResource + " X", x - xSpacing, y + ySpacing + spacing * 2);
		g.drawImage(wheat, x, y + spacing * 2);
		////////
		
		g.drawString("House count: " + houseCount, x, y + spacing * 6);
		g.drawString("Road count: " + roadCount, x, y + spacing * 7);
		g.drawString("Victory points: " + victoryPoint, x, y + spacing * 9);
	}
	
	

}