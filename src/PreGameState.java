//import lib
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * PreGameState is the third state in the program
 * Containing a chat box, a play game button, and a back button
 * @author frede_000
 * It extends BasicGameState from Slick2D
 * It implements a KeyListener to be able give keyboard input / output
 */
public class PreGameState extends BasicGameState implements KeyListener{
	
	//image objects 
	Image background;
	TrueTypeFont titleFont;

	/**
	 * public void init is the initial phase of the PreGameState
	 * All global variables and objects are initialized here
	 * It is overridden because the class BasicGameState already has a method called public void init
	 * @param gc is states gameContainer
	 * @param s is the current state in which this class functions
	 */	
	@Override
	public void init(GameContainer gc, final StateBasedGame s) throws SlickException { 
		//instantiate objects
		background = new Image("resources/background.png");	
		titleFont = Resource.getFont("title", 38);

		/**
		 * Example of button instantiation and the abstracts method isClicked() from the button class
		 */
		int bWidth = (int) (1000*Windows.scFactor);
		int bHeight = (int) (300*Windows.scFactor);
		
		/**
		 * Example of button instantiation and the abstracts method isClicked() from the button class
		 */
		Button Back = new Button(Windows.scWidth/4 - bWidth/2, Windows.scHeight - 136, bWidth, bHeight, 30, "Back", this) {
			@Override
			public void isClicked() {
				s.enterState(States.LobbyState);
			}
		};
		
		Button startGame = new Button(Windows.scWidth - Windows.scWidth/4 - bWidth/2, Windows.scHeight - 136, bWidth, bHeight, 30, "Start Game", this) {
			@Override
			public void isClicked() {
				//not implemented yet, should not we accessible before 4 players are ready
				Hexagon.generateMap();
				s.enterState(States.GameState);
			}
		};
		/**
		 * The listBox is the chat windows
		 * that shows the output text from all players
		 * the textbBox is where the unique player can write 
		 * their messages
		 * onSubmit is an abstract method similar to isClicked() from the button class
		 */
		
		int lWidth = (int) (1200*Windows.scFactor);
		int lHeight = (int)(1500*Windows.scFactor);
		int tHeight = (int)(150*Windows.scFactor);
		int lY = (int) (300*Windows.scFactor);
		int tY = (int) (1800*Windows.scFactor);
		final ListBox box = new ListBox(Windows.scWidth / 2 - lWidth/2, lY, lWidth, lHeight, 20, this);
		TextBox textField = new TextBox(Windows.scWidth / 2 - lWidth/2, tY, lWidth, tHeight, 20, this) {
			@Override
			public void onSubmit() {
				box.addString(IntroState.playerName + ": " + this.getContent(), GameData.ownIndex);
				//Actions.chat(IntroState.playerName + ": " + this.getContent());
				this.clear();
			}
		};
		textField.activate();
		box.activate();
	}
	/**
	 * public void render is the phase where everything is drawn in the PreGameState
	 * @param gc is the gameContainer 
	 * @param s is the current state in which this class functions
	 * @param g is the graphics element slick2D uses to draw things
	 */
	//draws all objects
	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		//draw background
		g.drawImage(background, 0, 0, Windows.scWidth, Windows.scHeight, 0, 0, 1366, 768);
		//draw title
		titleFont.drawString(Windows.scWidth/2 - 165, 10, "Welcome to Settlers", new Color(0, 0, 0));

		//draw buttons, listbox, and textbox
		Button.draw(g, this);
		ListBox.draw(g, this);
		TextBox.draw(g, this);
	}
	/**
	 * public void update is the phase where everything is updated each frame in the PreGameState
	 * @param gc is the gameContainer 
	 * @param s is the current state in which this class functions
	 * @param delta is ......
	 */
	//updates the flow
	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		//update buttons
		Button.update(this);
		ListBox.update(this);
		TextBox.update(this);
	}

	/**
	 * method returning this state
	 */
	//State ID
	@Override
	public int getID() {
		return States.PreGameState;
	}
	
	/**
	 * Method enabling the textBox to alterable
	 */
	@Override
	public void keyPressed(int key, char c) {
		TextBox.keyPress(key, c, this);
	}

}
