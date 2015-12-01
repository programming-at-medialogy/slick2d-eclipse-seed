import java.io.IOException;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {

	public static final String gamename = "Settlers of Catan";
	public static final int menu = 0;
	public static final int playState = 1;
	public static final int winState = 2;
	

	static int screenWidth = 1200;
	static int screenHeight = 700;
	public static int test;
	static Client client;
	Play play = new Play(playState);
	WinCondition win = new WinCondition(winState);
	

	
	
	public Game(String gamename) {
		super(gamename);
		this.addState(new Menu(menu));
		this.addState(win);
		this.addState(play);
		
	}
	

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(winState).init(gc, this);
		this.getState(menu).init(gc, this);
		this.getState(playState).init(gc, this);
		this.enterState(playState);
	}
	
	
	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			client = new Client();
			client.run();
			appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(screenWidth, screenHeight, false);
			appgc.start();
			
			
		} catch (SlickException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}