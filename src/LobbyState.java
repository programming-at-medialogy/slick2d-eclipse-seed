import java.io.IOException;

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
 * LobbyState is the second state in the program
 * Containing two textBoxes; IP and PORT, three game buttons; searchForGame, joinGame, and a back button
 * @author frede_000
 * It extends BasicGameState from Slick2D
 * It implements a KeyListener to be able give keyboard input / output
 */
public class LobbyState extends BasicGameState implements KeyListener{
	//variables 
	Image background;
	int tWidth = (int) (1500*Windows.scFactor);
	int tHeight = (int) (150*Windows.scFactor);
	static boolean findGame;
	//created to control which buttons should accessible at a certain time
	boolean foundIp;
	//Storing IP and port
	String ip, port;
	TrueTypeFont titleFont;
	TrueTypeFont miscFont;
	TrueTypeFont warningFont;
	static TrueTypeFont testFont;

	/**
	 * public void init is the initial phase of the lobbyState
	 * All global variables and objects are initialized here
	 * It is overridden because the class BasicGameState already has a method called public void init
	 * @param gc is states gameContainer
	 * @param s is the current state in which this class functions
	 */	
	@Override
	public void init(GameContainer gc, final StateBasedGame s) throws SlickException {
		//initializing variables and loading image from resources
		background = new Image("resources/background.png");
		titleFont = Resource.getFont("title", 50);
		miscFont = Resource.getFont("std", 14);
		warningFont = Resource.getFont("std", 22);	
		testFont = Resource.getFont("std", 22);
		int bWidth = (int) (1000*Windows.scFactor);
		int bHeight = (int) (300*Windows.scFactor);
		
		/**
		 * Example of button instantiation and the abstracts method isClicked() from the button class
		 * Creating the appropriate buttons at the appropriate locations
		 */

		Button back = new Button(Windows.scWidth/2 - bWidth/2, Windows.scHeight/2 - bHeight/2 + bHeight, bWidth, bHeight, 30, "Back", this) {
			@Override
			public void isClicked() {
				//need to implement a non-static bool
				IntroState.startGame = false;
				TextBox.remove();
				s.enterState(States.IntroState);
			}
		};

		
		/**
		 * Example of a textBox object being instantiated
		 * onSubmit is an abstract method similar to isClicked(), but originates from the TextBox class
		 * 
		 **/

		Button searchForGame = new Button(Windows.scWidth/2 - bWidth/2, Windows.scHeight/2 - bHeight/2 - bHeight, bWidth, bHeight, 30, "Search",this) {

			@Override
			public void isClicked() {
				findGame = true;
				final TextBox IpBox = new TextBox(Windows.scWidth/2 - tWidth/2, Windows.scHeight/2 - tHeight, tWidth, tHeight, 20, this.state){

					@Override
					public void onSubmit() {

					}
				};
				IpBox.activate();
				IpBox.setPermissions(false, true, false);
				
				TextBox portBox = new TextBox(Windows.scWidth/2 - tWidth/4, Windows.scHeight/2 + tHeight, tWidth/2, tHeight, 20, this.state){
					
					@Override
					public void onSubmit() {
						/**
						 * At this specific example onSubmit tries to connect to server.
						 * If there are no server connected it can crash
						 */
						ip = IpBox.getContent();
						port = this.getContent();
						findGame = false;
//						try {
//							NetworkClient.startClient(IpBox.getContent(), this.getContent());
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
						//join button does not show before a game has been searched for
						Button join = new Button(Windows.scWidth/2 - bWidth/2, Windows.scHeight/2 - bHeight/2, bWidth, bHeight, 30, "Join", this.state) {

							@Override
							public void isClicked() {
								s.enterState(States.PreGameState);
								System.out.println("Try to join game");
							}
						};
					}
				};
				/**
				 * 1. false/true if the box should contain alphabetical signs
				 * 2. false/true if the box should contain numeric signs
				 * 3. false/true if the box should contain whitespace
				 */
				portBox.setPermissions(false, true, false);
				System.out.println("searcing for game");
				Actions.initActions();
			}
		};
	}
	
	/**
	 * public void render is the phase where everything is drawn in the LobbyState
	 * @param gc is the gameContainer 
	 * @param s is the current state in which this class functions
	 * @param g is the graphics element slick2D uses to draw things
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		//draw background
		g.drawImage(background, 0, 0, Windows.scWidth, Windows.scHeight, 0, 0, 1366, 768);
		//draw title
		
		//draw buttons
		Button.draw(g, this);
		//draw title
		titleFont.drawString(Windows.scWidth/2 - titleFont.getWidth("Welcome to Settlers")/2, 10, "Welcome to Settlers", new Color(0, 0, 0));
		miscFont.drawString(20, 50, "IP: " + ip, new Color(0, 0, 0));
		miscFont.drawString(20, 80, "Port: " + port, new Color(0, 0, 0));
		
		//if findGame is true, this method should draw a textBox and darken the background 
		if(findGame == true){
			g.setColor(new Color(150, 150, 150, 200));
			g.fillRect(0, 0, Windows.scWidth, Windows.scHeight);
			TextBox.draw(g, this);
			int fY = (int) (200*Windows.scFactor);
			warningFont.drawString(Windows.scWidth/2 - warningFont.getWidth("Enter your server IP")/2, Windows.scHeight/2 - tHeight/2 - fY, "Enter your server IP", new Color(0, 0, 0));
			warningFont.drawString(Windows.scWidth/2 - warningFont.getWidth("Enter your server port")/2, Windows.scHeight/2 + tHeight/2 - 15, "Enter your server port", new Color(0, 0, 0));
		}
		

	}
	
	/**
	 * public void update is the phase where everything is updated each frame in the LobbyState
	 * @param gc is the gameContainer 
	 * @param s is the current state in which this class functions
	 * @param delta is ......
	 */	
	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		//Makes the buttons uninteractable while the findGame is true
		if(findGame != true){
		Button.update(this);
		}
		TextBox.update(this);
	}
	
	/**
	 * method returning this the appropriate state
	 */
	@Override
	public int getID() {
		return States.LobbyState;
	}
	
	/**
	 * Method enabling the textBox to alterable
	 */
	@Override
	public void keyPressed(int key, char c) {
		TextBox.keyPress(key, c, this);
	}
}
