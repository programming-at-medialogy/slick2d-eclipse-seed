package mainGame;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;

public class Main extends BasicGame
{
	private static final int _screenWidth = 800;
	private static final int _screenHeight = 600;
	
	Image butt;
	
	public Main(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		butt = new Image("resources/butt.png");
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException 
	{
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		butt.draw(50, 50, _screenWidth-100, _screenHeight-100);
		butt.setFilter(Image.FILTER_LINEAR);
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