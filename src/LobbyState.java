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
	int boxMovement;
	//initializing and instantiating 
	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
		
		//loading images used in the lobbystate
		background = new Image("resources/background.png");
		joinGame = new Image("resources/joinGame.png");
		joinGameHighlighted = new Image("resources/joinGameHighlighted.png");
		searchForGame = new Image("resources/searchForGame.png");
		searchForGameHighlighted = new Image("resources/searchForGameHighlighted.png");
		backButton = new Image("resources/back.png");
		backButtonHighlighted = new Image("resources/backHighlighted.png");
		boxMovement = Windows.scWidth/2;

		//creating the appropriate buttons at the appropriate locations
		Button Back = new Button(boxMovement - backButton.getWidth()/2, Windows.scHeight/2 + 10, backButton, backButtonHighlighted, this) {
			@Override
			public void isClicked() {
				TextBox.remove();
				s.enterState(States.IntroState);
			}
		};
		
		Button Join = new Button(boxMovement - joinGame.getWidth()/2, Windows.scHeight/2 - 210, joinGame, joinGameHighlighted,this) {
			@Override
			public void isClicked() {
				s.enterState(States.PreGameState);
				System.out.println("join the game already");
			}
		};
		
		Button SearchForGame = new Button(boxMovement - searchForGame.getWidth()/2, Windows.scHeight/2 - 100, searchForGame, searchForGameHighlighted,this) {
			@Override
			public void isClicked() {
				TextBox IpBox = new TextBox(0, 0, 300, 40, 12, this.state){

					@Override
					public void onSubmit() {
						//System.out.println(this.getContent());
					}
				};
				IpBox.setPermissions(false, true, false);
				
				TextBox portBox = new TextBox(0, 50, 300, 40, 12, this.state){
					
					@Override
					public void onSubmit() {
						try {
							NetworkClient.startClient(IpBox.getContent(), this.getContent());
						} catch (IOException e) {
							// TODO Auto-generated catch block
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
		g.setColor(Color.black);
		g.drawString("WELCOME TO SETTLERS, THE GOOD EDITION", Windows.scWidth/2 - 150, 10);	
		//draw buttons
			Button.draw(g, this);
			TextBox.draw(g, this);

	}
	
	//updating new events
	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		Button.update(this);
		TextBox.update(this);

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
