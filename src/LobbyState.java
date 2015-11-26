import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LobbyState extends BasicGameState implements KeyListener{
	//image objects
	Image background, joinGame, joinGameHighlighted, searchForGame, searchForGameHighlighted, backButton, backButtonHighlighted;
	int boxMovement, ipBoxX, ipBoxY, portBoxX, portBoxY;
	static boolean findGame;
	//initializing and instantiating 
	@Override
	public void init(GameContainer gc, final StateBasedGame s) throws SlickException {
		
		//loading images used in the lobbystate
		background = new Image("resources/background.png");
		joinGame = new Image("resources/joinGame.png");
		joinGameHighlighted = new Image("resources/joinGameHighlighted.png");
		searchForGame = new Image("resources/searchForGame.png");
		searchForGameHighlighted = new Image("resources/searchForGameHighlighted.png");
		backButton = new Image("resources/back.png");
		backButtonHighlighted = new Image("resources/backHighlighted.png");
		boxMovement = Windows.scWidth/2;
		ipBoxX = Windows.scWidth/2 - Windows.scWidth/4;
		ipBoxY = 300;
		portBoxX = Windows.scWidth/2 - 150;
		portBoxY = 385;

		//creating the appropriate buttons at the appropriate locations
		Button Back = new Button(boxMovement - backButton.getWidth()/2, Windows.scHeight/2 + 10, 326, 86, "Back", this) {
			@Override
			public void isClicked() {
				//need to implement a non-static bool
				IntroState.startGame = false;
				TextBox.remove();
				s.enterState(States.IntroState);
			}
		};
		
		Button Join = new Button(boxMovement - joinGame.getWidth()/2, Windows.scHeight/2 - 210, 326, 86, "Join",this) {
			@Override
			public void isClicked() {
				s.enterState(States.PreGameState);
				System.out.println("join the game already");
			}
		};
		
		Button SearchForGame = new Button(boxMovement - searchForGame.getWidth()/2, Windows.scHeight/2 - 100, 326, 86, "Search",this) {
			@Override
			public void isClicked() {
				findGame = true;
				final TextBox IpBox = new TextBox(ipBoxX, ipBoxY, 500, 50, this.state){

					@Override
					public void onSubmit() {
						//System.out.println(this.getContent());
						findGame = false;
					}
				};
				IpBox.activate();
				IpBox.setPermissions(false, true, false);
				
				TextBox portBox = new TextBox(portBoxX, portBoxY, 300, 40, this.state){
					
					@Override
					public void onSubmit() {
						/**
						 * Atm onSubmit tries to link to a server, thus crashing if it is not created 
						 * 
						 */
						findGame = false;
						try {
							NetworkClient.startClient(IpBox.getContent(), this.getContent());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				};
				portBox.setPermissions(false, true, false);
				System.out.println("searcing for game");
			}
		};
	}

	//draw the appropriate objects on the state
	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		//draw background
		g.drawImage(background, 0, 0, Windows.scWidth, Windows.scHeight, 0, 0, 1366, 768);
		//draw title
		
//		g.drawString("WELCOME TO SETTLERS, THE GOOD EDITION", Windows.scWidth/2 - 150, 10);	
		//draw buttons
		Button.draw(g, this);
		//draw title
		Resource.titleFont.drawString(Windows.scWidth/2 - 165, 10, "Welcome to Settlers", new Color(0, 0, 0));
		
		if(findGame == true){
			TextBox.draw(g, this);
			g.setColor(new Color(150, 150, 150, 100));
			g.fillRect(0, 0, Windows.scWidth, Windows.scHeight);
	/*		g.setColor(Color.black);
			g.drawString("Write your name in the textboxs and hit 'ENTER'", Windows.scWidth/2 - 220, Windows.scHeight/3 + 35);	*/
			Resource.warningFont.drawString(ipBoxX + 150, ipBoxY - 30, "Write your server IP", new Color(0, 0, 0));
			Resource.warningFont.drawString(portBoxX + 30, portBoxY - 30, "Write server socket port", new Color(0, 0, 0));
		}
	}
	
	//updating new events
	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		if(findGame != true){
		Button.update(this);
		}	TextBox.update(this);

	}
	//Returns the appropriate state ID
	@Override
	public int getID() {
		return States.LobbyState;
	}
	
	@Override
	public void keyPressed(int key, char c) {
		TextBox.keyPress(key, c, this);
	}
}
