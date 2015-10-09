package mainGame;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GameMap
{
	Random rand = new Random();
	
	char[] tiles;
	char[][] Map;
	int tileWidth;
	int tileHeight;
	
	Image tile; 
	
	GameMap(int width, int height)
	{
		tileWidth = width/13;
		tileHeight = height/10;
		
		tiles = new char[] {'0', 'D'};
		
		Map = new char[tileWidth][tileHeight];
		for (int i = 0; i < tileWidth; i++)
		{
			for(int j = 0; j < tileHeight; j++)
			{
				Map[i][j] = returnTileType();
			}
		}
		
		
	}
	
	char returnTileType()
	{
		int tmpNum = rand.nextInt(2);
		System.out.println(tmpNum);
		
		char tileType = '0';
		
		if(tmpNum == 0)
			tileType = '0';
		else if (tmpNum == 1)
			tileType = 'D';
		
		return tileType;
	}
	
	void drawMap(GameContainer gc, Graphics g) throws SlickException
	{
		int xPos, yPos;
		char arrayContent;
		
		for (int i = 0; i < tileWidth; i++)
		{
			for(int j = 0; j < tileHeight; j++)
			{
				xPos = i;
				yPos = j;
				
				arrayContent = Map[xPos][yPos];
				if (arrayContent == '0')
				{
					tile = Main.butt;
					tile.draw(xPos*tileWidth, yPos*tileHeight, 
							tileWidth, tileHeight);
				}
				else if (arrayContent == 'D')
				{
					tile = Main.doge;
					tile.draw(xPos*tileWidth, yPos*tileHeight, 
							tileWidth, tileHeight);
				}
			}
		}
		
		
	}

}
