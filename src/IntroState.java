//importing libs
import org.newdawn.slick.Color;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class IntroState extends BasicGameState{
	//class variables
	Image playNow, background, exitGame;
	boolean play = false;
	boolean exit = false;

	//initializing and instantiating 
	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
		background = new Image("resources/background.png");
		playNow = new Image("resources/Start game.PNG");
		exitGame = new Image("resources/Exit game.PNG");
		
		Button PlayNow = new Button(10, 100, playNow, this) {
			@Override
			public void isClicked() {
				s.enterState(States.LobbyState);
			}
		};
		
		Button ExitGame = new Button(10, 210, exitGame, this) {
			@Override
			public void isClicked() {
				gc.exit();
			}
		};

	}
	
	//draw the appropriate objects on the state
	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {	
		g.drawImage(background, 0, 0, Windows.scWidth, Windows.scHeight, 0, 0, 1366, 768);
		g.setColor(Color.black);
		g.drawString("WELCOME TO SETTLERS, THE GOOD EDITION", 100, 10);
		
		Button.draw(g, this); 
		/*
		playNow.draw(10, 100);
		exitGame.draw(10, 210);*/
	}
	
	//updating new events
	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		Button.update(this);
	/*	if(play == true)
			s.enterState(States.LobbyState);
		
		if(exit == true)
			gc.exit();*/
		//if(gc.getInput().isKeyPressed(Input.KEY_ENTER)) s.enterState(States.gameState);
	}
/*	public void mouseReleased(int button, int xPos, int yPos) {
		
		//mouse x and y is stored
		//int xPos = Mouse.getX();
		//int yPos = Windows.scHeight - Mouse.getY();
		
		//hard coded button position for playNow
		if((xPos > 10 && xPos < 337)&&(yPos > 100 && yPos < 185)){
			play = true;
		}	
		//hard coded button position for exitGame
		if((xPos > 10 && xPos < 337)&&(yPos > 210 && yPos < 295)){
			exit = true;
		}
	}*/

	//Returns the appropriate state ID
	@Override
	public int getID() {
		return States.IntroState;
	}
}
