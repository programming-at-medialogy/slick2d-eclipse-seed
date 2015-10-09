package mainGame;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;

public class Main extends BasicGame
{
	public static final int _screenWidth = 640;
	public static final int _screenHeight = 480;
	
	public static Image butt;
	public static Image doge;
	
	GameMap map;
	
	public Main(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		butt = new Image("resources/butt.png");
		doge = new Image("resources/doge.png");
		map = new GameMap(_screenWidth, _screenHeight);
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException 
	{
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		//butt.draw(50, 50, _screenWidth-100, _screenHeight-100);
		//butt.setFilter(Image.FILTER_LINEAR);
		map.drawMap(gc, g);
		
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Main("Dick Butt: The Game"));
			appgc.setDisplayMode(_screenWidth, _screenHeight, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}