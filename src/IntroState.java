
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class IntroState extends BasicGameState{
	Image playNow, background, exitGame;


	
	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
		background = new Image("resources/background.png");
		playNow = new Image("resources/Start game.PNG");
		exitGame = new Image("resources/Exit game.PNG");

	}

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {	

		g.drawImage(background, 0, 0, Windows.scWidth, Windows.scHeight, 0, 0, 1366, 768);
		g.setColor(Color.black);
		g.drawString("WELCOME TO SETTLERS, THE GOOD EDITION", 100, 10);
		playNow.draw(10, 100);
		exitGame.draw(10, 210);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {		
		
		//mouse x and y is stored
		int xPos = Mouse.getX();
		int yPos = Windows.scHeight - Mouse.getY();
		
		//hard coded button position for playNow
		if((xPos > 10 && xPos < 337)&&(yPos > 100 && yPos < 185)){
			if(Mouse.isButtonDown(0)){
				s.enterState(States.LobbyState);
			}
		}	
		//hard coded button position for exitGame
		if((xPos > 10 && xPos < 337)&&(yPos > 210 && yPos < 295)){
			if(Mouse.isButtonDown(0)){
				gc.exit();
			}
		}
		//if(gc.getInput().isKeyPressed(Input.KEY_ENTER)) s.enterState(States.gameState);
		
	
	}

	@Override
	//controls the state of the program (menu, chat etc)
	public int getID() {
		return States.IntroState;
	}
}
