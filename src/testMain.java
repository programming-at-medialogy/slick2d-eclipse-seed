//importing libs
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This is the actual main running the initial state, IntroState, of the game
 * extends StateBasedGame from slick2D to be able to work with state extending BasicGameState
 * @author frede_000
 *
 */
public class testMain extends StateBasedGame{
	/**
	 * Constructor 
	 * Sets the name of the program on the panel
	 */
	public testMain() {
		super("settlers");
	}
	/**
	 * Instantiating AppGameContainer, which is the gameContainer storing the whole program
	 * Sets display size and mode; full screen or not
	 * Starts the program
	 */
	public static void init() throws SlickException{
AppGameContainer game = new AppGameContainer(new testMain());
			game.setDisplayMode(Windows.scWidth,Windows.scHeight, true); 
			game.start(); 
	}
	
	public static void start() throws SlickException {
		
	}
	/**
	 * initStateList is where states are added to the program via the syntax:
	 * this.addState(new SomeState());
	 * Sets the overall values of the states
	 * gc.setMaximumLogicUpdateInterval(60) sets an automatically update every 60 frame		
	 * gc.setTargetFrameRate(60) is the frameRate
	 * gc.setAlwaysRender(true) should always re-render its content
	 * gc.setShowFPS(false) show fps or not
	 * gc.setVSync(true) .......
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setMaximumLogicUpdateInterval(60);		
		gc.setTargetFrameRate(60);
		gc.setAlwaysRender(true);
		gc.setShowFPS(false);
		gc.setVSync(true);		
		
		this.addState(new IntroState());
		this.addState(new LobbyState());
		this.addState(new PreGameState());
		this.addState(new GameState());
	}
}
