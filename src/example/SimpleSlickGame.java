package example;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SimpleSlickGame extends BasicGame
{
	private Image ball = null;
	private Image gameBackground = null;
	private Image player = null;
	
	static int sHeight = 640;
	static int sWidth = 480;
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		
		//Initializes images.
		ball = new Image("data/ball.png");
		gameBackground = new Image("data/bg.png");
		player = new Image("data/paddle.png");
		
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		//Renders the images
		gameBackground.draw();
		g.drawString("Arcade Game", 250, 200);
		player.draw(250, 400);
		ball.draw(290, 365);
		
		
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Arcade"));
			appgc.setDisplayMode(sHeight, sWidth, false);
			
			
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}