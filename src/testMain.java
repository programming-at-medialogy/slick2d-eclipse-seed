
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class testMain extends StateBasedGame{
	
	public testMain() {
		super("settlers");
	}

	public static void start() {
		//Initial phase - only done once at game start
		
		try
		{
			AppGameContainer game = new AppGameContainer(new testMain());
			game.setDisplayMode(Windows.scWidth,Windows.scHeight, false); // sets screen size, false or true for full screen
			game.start(); // to start Slick 2D
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setMaximumLogicUpdateInterval(60);		
		gc.setTargetFrameRate(60);
		gc.setAlwaysRender(true);
		gc.setShowFPS(false);
		gc.setVSync(true);		
			
		this.addState(new IntroState());
		//this.addState(new gameState());
	}

}
