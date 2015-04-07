package game;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class SimpleSlickGame extends BasicGame
{
	//For width and height 
	public static final int 	WINDOW_WIDTH = 1280;
	public static final int 	WINDOW_HEIGTH = WINDOW_WIDTH /16*9;
	public static final boolean FULLSCREEN = false;
	
	//1280x720 is our base, we use 32x32 tiles but we want it to be 40x40 at 1280x720
    //so our base scale is not 1 but 1.25 actually
	public static final float SCALE 	=(float) (1.25*((double)WINDOW_WIDTH/1280));
	public static final String GAME_NAME = "Mario";
	
	public SimpleSlickGame(){
		super (GAME_NAME);
	}
	
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		
		//addState(new LevelState("Level_0"));
		//this.enterState(0);
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawString("Welcome to Mario", 250, 200);
	}

	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer (new SimpleSlickGame());
		
	//set the size of the display to the width and height and fullscreen or not
    app.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGTH, FULLSCREEN);
    //this will attempt to create a framerate of approximately 60 frames per second
    app.setTargetFrameRate(60);

    app.start();
	
	
	}
}