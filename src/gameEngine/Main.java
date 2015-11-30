package gameEngine;

import org.newdawn.slick.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;



public class Main extends BasicGame
{
	public static int boardWidth = 1920;
	public static int boardHight = 1080;
	private Shape circle = null;
	
	
	public Main(String gamename)
	
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		circle = new Circle (220,265,10);//drawing circle
	}
    
	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
	
	Image background = new Image ("images/boardMap.jpg");//background image	
		g.drawImage(background, 0, 0);// rendering background 
		g.draw(circle);
		//g.drawString("WORKS!", 250, 200);
		
	}

	public static void main(String[] args)
	{
		try
		{
			int maxFPS = 60;// initialising frame rate per second  
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Main("Simple Slick Game"));
			appgc.setTargetFrameRate(maxFPS);// setting fps
			appgc.setDisplayMode(boardWidth, boardHight, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}