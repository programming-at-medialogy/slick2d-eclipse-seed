import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LobbyState extends BasicGameState{
	Image background, joinGame, searchForGame, back;
	
	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
		background = new Image("resources/background.png");
		joinGame = new Image("resources/joinGame.png");
		searchForGame = new Image("resources/searchForGame.png");
		back = new Image("resources/back.png");

		

	}

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0, Windows.scWidth, Windows.scHeight, 0, 0, 1366, 768);
		
		joinGame.draw(10, 100);
		searchForGame.draw(10, 210);
		back.draw(10, 310);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {

		//mouse x and y is stored
		//subtracting the mouse Y because it is inverted
		int xPos = Mouse.getX();
		int yPos = Windows.scHeight - Mouse.getY();
		
		//hard coded button position for join game
		if((xPos > 10 && xPos < 337)&&(yPos > 100 && yPos < 185)){
			if(Mouse.isButtonDown(0)){
				System.out.println("join the fucking game already");
				//Starting the game from main
				//ServerClientDecider.isTest = false;
			}
		}	
		//hard coded button position for search for game
		if((xPos > 10 && xPos < 337)&&(yPos > 210 && yPos < 295)){
			if(Mouse.isButtonDown(0)){
				System.out.println("searcing for game");
			}
		}
		//hard coded button position for back
		if((xPos > 10 && xPos < 337)&&(yPos > 320 && yPos < 405)){
			if(Mouse.isButtonDown(0)){
				s.enterState(States.IntroState);
			}
		}
	}

	@Override
	public int getID() {
		return States.LobbyState;
	}

}
