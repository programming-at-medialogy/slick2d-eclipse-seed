import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LobbyState extends BasicGameState{
	//image objects
	Image background, joinGame, searchForGame, backButton;
	
	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
		//loading images used in the lobbystate
		background = new Image("resources/background.png");
		joinGame = new Image("resources/joinGame.png");
		searchForGame = new Image("resources/searchForGame.png");
		backButton = new Image("resources/back.png");

		//creating the appropriate buttons at the appropriate locations
		Button Back = new Button(10, 310, backButton, this) {
			@Override
			public void isClicked() {
				s.enterState(States.IntroState);
			}
		};
		
		Button Join = new Button(10, 100, joinGame, this) {
			@Override
			public void isClicked() {
				System.out.println("join the game already");
			}
		};
		
		Button SearchForGame = new Button(10, 210, searchForGame, this) {
			@Override
			public void isClicked() {
				System.out.println("searcing for game");
			}
		};

	}

	//draw the appropriate objects on the state
	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		//draw background
		g.drawImage(background, 0, 0, Windows.scWidth, Windows.scHeight, 0, 0, 1366, 768);
		//draw buttons
			Button.draw(g, this);
		/*joinGame.draw(10, 100);
		searchForGame.draw(10, 210);
		backButton.draw(10, 310);*/
	}
	
	//updating new events
	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		Button.update(this);
	//	if(back == true)
			//s.enterState(States.IntroState);
	}
	
/*	@Override
	public void mouseReleased(int button, int xPos, int yPos) {
		System.out.println("Test");
		//mouse x and y is stored
		//subtracting the mouse Y because it is inverted
		//int xPos = Mouse.getX();
		//int yPos = Windows.scHeight - Mouse.getY();
		
		//hard coded button position for join game
		if((xPos > 10 && xPos < 337)&&(yPos > 100 && yPos < 185)){
				//Starting the game from main
				//ServerClientDecider.isTest = false;
		}	
		//hard coded button position for search for game
		if((xPos > 10 && xPos < 337)&&(yPos > 210 && yPos < 295)){
		}
		//hard coded button position for back
		if((xPos > 10 && xPos < 337)&&(yPos > 320 && yPos < 405)){
			back = true;
		}
	}*/

	//Returns the appropriate state ID
	@Override
	public int getID() {
		return States.LobbyState;
	}

}
