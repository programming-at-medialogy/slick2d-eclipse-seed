import java.io.IOException;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {

	public static final String gamename = "Settlers of Catan"; //Used to set the name of the game window
	public static final int menu = 0; //A game state int for menu
	public static final int playState = 1; //for the actual playstate
	public static final int winState = 2; //for the winstate
	static int screenWidth = 1200; //Declare screen canvas size
	static int screenHeight = 700; //Declare screen canvas size

	static Client client; //Instance of client 
	Play play = new Play(playState); //Needed to go to play state of game
	WinCondition win = new WinCondition(winState); //needed to go to win state of game
	
	
	public Game(String gamename) {
		super(gamename); 
		
		//Adds the different states of the game
		this.addState(new Menu(menu));
		this.addState(win);
		this.addState(play);
		
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		//Initialize the states of the game
		this.getState(winState).init(gc, this);
		this.getState(menu).init(gc, this);
		this.getState(playState).init(gc, this);
		this.enterState(playState); //tell which state to enter first.

	}
	
	
	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			client = new Client(); //Set the client to a new instance of the client
			client.run(); //run the client
			appgc = new AppGameContainer(new Game(gamename)); //Sets the gamescreen and adds the game name
			appgc.setDisplayMode(screenWidth, screenHeight, false); //Size of the canvas
			appgc.start(); //start it.
			
			
			//Catch the different exceptions associated with above
		} catch (SlickException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}