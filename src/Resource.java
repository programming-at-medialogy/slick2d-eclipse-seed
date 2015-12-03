import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Resource {
	

	Game game;
	int oreResource, clayResource, wheatResource, woodResource, woolResource;
	int x, y;
	int spacing = 125;
	int victoryPoint = 0;
	int roadCount = 0;
	int houseCount = 0;
	int xSpacing = 30;
	int ySpacing = 15;
	int playerNo;
	int armySize = 0;
	Image ore, clay, wood, wool, wheat;
	
	Resource(int x, int y, int playerNo) throws SlickException{
		this.playerNo = playerNo;
		this.x = x;
		this.y = y;
		
		ore = new Image("images/ore.png");
		clay = new Image("images/clay.png");
		wood = new Image("images/wood.png");
		wool = new Image("images/wool.png");
		wheat = new Image("images/wheat.png");
		oreResource = 0;
		clayResource = 0;
		wheatResource = 0;
		woodResource = 0;
		woolResource = 0;
	}
	
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		//ORE//
		g.drawString(""+oreResource, x - xSpacing, y + ySpacing);
		g.drawImage(ore, x, y);
		///////
		
		//CLAY//
		g.drawString(""+clayResource, x - xSpacing, y + ySpacing + spacing/2);
		g.drawImage(clay, x, y + spacing/2);
		////////
		
		//WHEAT//
		g.drawString(""+wheatResource, x - xSpacing, y + ySpacing + spacing);
		g.drawImage(wheat, x, y + spacing);
		/////////
		
		//WOOD//
		g.drawString(""+woodResource, x - xSpacing, y + ySpacing + spacing/2 + spacing);
		g.drawImage(wood, x, y + spacing + spacing/2);
		////////
		
		//WOOL//
		g.drawString(""+woolResource, x - xSpacing, y + ySpacing + spacing * 2);
		g.drawImage(wool, x, y + spacing * 2);
		////////
		
		g.drawString("House count: " + houseCount, 10, 50);
		g.drawString("Road count: " + roadCount, 10, 70);
		g.drawString("Victory points: " + victoryPoint, 10, 90);
		g.drawString("Army size: "+ armySize, 10, 110);

	}
}

