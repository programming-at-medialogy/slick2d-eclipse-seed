//importing libs
import org.newdawn.slick.Color;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * IntroState is the first state in the program
 * Containing one textBoxe; playerName, and two game buttons; startGame and a back button
 * @author frede_000
 * It extends BasicGameState from Slick2D
 * It implements a KeyListener to be able give keyboard input / output
 */
public class IntroState extends BasicGameState implements KeyListener{
	//class variables
	Image background;
	static boolean startGame;
	static String playerName;
	int tWidth = (int) (1500*Windows.scFactor);
	int tHeight = (int) (150*Windows.scFactor);

	/**
	 * public void init is the initial phase of the IntroState
	 * All global variables and objects are initialized here
	 * It is overridden because the class BasicGameState already has a method called public void init
	 * @param gc is states gameContainer
	 * @param s is the current state in which this class functions
	 */	
	@Override
	public void init(final GameContainer gc, final StateBasedGame s) throws SlickException {
		background = new Image("resources/background.png");
		Resource.initResources();
		int bWidth = (int) (1000*Windows.scFactor);
		int bHeight = (int) (300*Windows.scFactor);
		
		/**
		 * Example of button instantiation and the abstracts method isClicked() from the button class
		 * Example of textBox instantiation and the abstracts method onSubmit() from the button class
		 * Creating the appropriate buttons and textBox at the appropriate locations
		 */
		Button PlayNow = new Button(Windows.scWidth/2 - bWidth/2, Windows.scHeight/2 - bHeight, bWidth, bHeight, 15, "Play",this) {
			@Override
			public void isClicked() {
				startGame = true;
				TextBox nameBox = new TextBox(Windows.scWidth/2 - tWidth/2, Windows.scHeight/2 - tHeight, tWidth, tHeight, 15, this.state){
					@Override
					public void onSubmit() {
						playerName = this.getContent();
						System.out.println(playerName);
						s.enterState(States.LobbyState);
					}
				};
				nameBox.activate();
				
				/**
				 * 1. false/true if the box should contain alphabetical signs
				 * 2. false/true if the box should contain numeric signs
				 * 3. false/true if the box should contain whitespace
				 */
				nameBox.setPermissions(true, true, true);				
			}
		};
		
		Button ExitGame = new Button(Windows.scWidth/2 - bWidth/2, Windows.scHeight/2, bWidth, bHeight, 15, "Exit",this) {
			@Override
			public void isClicked() {
				startGame = false;
				gc.exit();
			}
		};
	}
	/**
	 * public void render is the phase where everything is drawn in the IntroState
	 * @param gc is the gameContainer 
	 * @param s is the current state in which this class functions
	 * @param g is the graphics element slick2D uses to draw things
	 */	
	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {	
		g.drawImage(background, 0, 0, Windows.scWidth, Windows.scHeight, 0, 0, 1366, 768);
		Resource.titleFont.drawString(Windows.scWidth/2 - 165, 10, "Welcome to Settlers", new Color(0, 0, 0));
		Button.draw(g, this); 
		
		//if startGame is true, this method should draw a textBox and darken the background 
		if(startGame == true){
			g.setColor(new Color(150, 150, 150, 200));
			g.fillRect(0, 0, Windows.scWidth, Windows.scHeight);
			
			int fY = (int) (200*Windows.scFactor);
			Resource.warningFont.drawString(Windows.scWidth/2 - tWidth/2, Windows.scHeight/2 - tHeight/2 - fY, "Write your name in the textbos and hit 'ENTER'", new Color(0, 0, 0));	
			TextBox.draw(g, this);
		}
	}
	
	/**
	 * public void update is the phase where everything is updated each frame in the IntroState
	 * @param gc is the gameContainer 
	 * @param s is the current state in which this class functions
	 * @param delta is ......
	 */	
	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		//Makes the buttons uninteractable while the findGame is true
		if(startGame != true){
		Button.update(this);
		}
		
		TextBox.update(this);
	}

	/**
	 * method returning this the appropriate state
	 */
	@Override
	public int getID() {
		return States.IntroState;
	}
	
	/**
	 * Method enabling the textBox to alterable
	 */
	@Override
	public void keyPressed(int key, char c) {
		TextBox.keyPress(key, c, this);
	}
}
