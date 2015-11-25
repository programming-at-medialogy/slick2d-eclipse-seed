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
	Image playNow, playNowHighlighted, background, exitGame, exitGameHighlighted;
	boolean play = false;
	boolean exit = false;

	//initializing and instantiating 
	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
		background = new Image("resources/background.png");
		playNow = new Image("resources/Start game.PNG");
		playNowHighlighted = new Image("resources/StartGameHighlighted.PNG");		
		exitGame = new Image("resources/Exit game.PNG");
		exitGameHighlighted = new Image("resources/ExitGameHighlighted.PNG");
		
		Button PlayNow = new Button(Windows.scWidth/2 - playNow.getWidth()/2, Windows.scHeight/2 - 210, playNow, playNowHighlighted,this) {
			@Override
			public void isClicked() {
				s.enterState(States.LobbyState);
			}
		};
		
		Button ExitGame = new Button(Windows.scWidth/2 - exitGame.getWidth()/2, Windows.scHeight/2 - 10, exitGame, exitGameHighlighted,this) {
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
		g.drawString("WELCOME TO SETTLERS, THE GOOD EDITION", Windows.scWidth/2 - 150, 10);	
		Button.draw(g, this); 
	}
	
	//updating new events
	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		Button.update(this);
	}

	//Returns the appropriate state ID
	@Override
	public int getID() {
		return States.IntroState;
	}
}
