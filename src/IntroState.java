//importing libs
import org.newdawn.slick.Color;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class IntroState extends BasicGameState implements KeyListener{
	//class variables
	Image playNow, playNowHighlighted, background, exitGame, exitGameHighlighted;
	//need to implement a none static bool
	static boolean startGame;
	static String playerName;

	//initializing and instantiating 
	@Override
	public void init(final GameContainer gc, final StateBasedGame s) throws SlickException {
		background = new Image("resources/background.png");
		playNow = new Image("resources/Start game.PNG");
		playNowHighlighted = new Image("resources/StartGameHighlighted.PNG");		
		exitGame = new Image("resources/Exit game.PNG");
		exitGameHighlighted = new Image("resources/ExitGameHighlighted.PNG");
		
		Button PlayNow = new Button(Windows.scWidth/2 - playNow.getWidth()/2, Windows.scHeight/2 - 210, 326, 86, "Play",this) {
			@Override
			public void isClicked() {
				startGame = true;
				TextBox nameBox = new TextBox(Windows.scWidth/2 - 255, 330, 500, 50, this.state){
					@Override
					public void onSubmit() {
						playerName = this.getContent();
						System.out.println(playerName);
						s.enterState(States.LobbyState);
					}
				};
				nameBox.activate();
				nameBox.setPermissions(true, true, true);				
			}
		};
		
		Button ExitGame = new Button(Windows.scWidth/2 - exitGame.getWidth()/2, Windows.scHeight/2 - 10, 326, 86, "Exit",this) {
			@Override
			public void isClicked() {
				startGame = false;
				gc.exit();
			}
		};

	}
	
	//draw the appropriate objects on the state
	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {	
		g.drawImage(background, 0, 0, Windows.scWidth, Windows.scHeight, 0, 0, 1366, 768);
/*		g.setColor(Color.black);
		g.drawString("WELCOME TO SETTLERS, THE GOOD EDITION", Windows.scWidth/2 - 150, 10);	*/
		Resource.titleFont.drawString(Windows.scWidth/2 - 165, 10, "Welcome to Settlers", new Color(0, 0, 0));
		Button.draw(g, this); 
		
		if(startGame == true){
			g.setColor(new Color(150, 150, 150, 100));
			g.fillRect(0, 0, Windows.scWidth, Windows.scHeight);
	/*		g.setColor(Color.black);
			g.drawString("Write your name in the textboxs and hit 'ENTER'", Windows.scWidth/2 - 220, Windows.scHeight/3 + 35);	*/
			Resource.warningFont.drawString(Windows.scWidth/2 - 210, Windows.scHeight/3 + 35, "Write your name in the textbos and hit 'ENTER'", new Color(0, 0, 0));	
		}
		TextBox.draw(g, this);
	}
	
	//updating new events
	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		if(startGame != true){
		Button.update(this);
		}
		
		TextBox.update(this);
	}

	//Returns the appropriate state ID
	@Override
	public int getID() {
		return States.IntroState;
	}
	
	@Override
	public void keyPressed(int key, char c) {
		TextBox.keyPress(key, c, this);
	}
}
