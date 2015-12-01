

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import java.io.IOException;

import org.lwjgl.input.Mouse;

public class Menu extends BasicGameState {

	private int screenHeight = 700;
	
	Image backGroundImg;
	
	Image playGame;
	Image exitGame;
	
	Image playerOneReadyImg;
	Image playerTwoReadyImg;
	Image playerThreeReadyImg;
	Image playerFourReadyImg;
	
	public boolean playerOneReady = false;
	public boolean playerTwoReady = false;
	public boolean playerThreeReady = false;
	public boolean playerFourReady = false;
	
	Game game; 
	Controller controller;
	
	public String playersReady = "Ready Players:";
	int playerNr = 0;

	public Menu(int state) {
		
	}
	
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
			
			backGroundImg = new Image("images/menuBackground.png");
		
			playGame = new Image("images/playGame.png");
			exitGame = new Image("images/exitGame.png");
			
			playerOneReadyImg = new Image("images/playerOneReady.png");
			playerTwoReadyImg = new Image("images/playerTwoReady.png");
			playerThreeReadyImg = new Image("images/playerThreeReady.png");
			playerFourReadyImg = new Image("images/playerFourReady.png");
			
			playerNr = game.client.obj.playerNumber;


	}
	


	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		backGroundImg.draw(0,0);
		
		playGame.draw(870, 420);
		exitGame.draw(10, 570);
		
		g.drawRect(890, 40, 230, 110);
		g.drawString(playersReady, 890, 20);
		
		if(playerOneReady == true) {
			playerOneReadyImg.draw(900, 50);	
		}
		
		if(playerTwoReady == true) {
			playerTwoReadyImg.draw(1010, 50);
		}
		
		if(playerThreeReady == true) {
			playerThreeReadyImg.draw(900, 100);
		}

		if(playerFourReady == true) {
			playerFourReadyImg.draw(1010, 100);
		}
		
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		//gets the x and y position of the mouse
		int xMousePos = Mouse.getX(); //gets x position of mouse
		int yMousePos = Mouse.getY(); //gets y position of mouse
		Input input = gc.getInput(); //needed to get input
		
		
		playerOneReady = game.client.obj.playerOneReady;
		playerTwoReady = game.client.obj.playerTwoReady;
		playerThreeReady = game.client.obj.playerThreeReady;		
		playerFourReady = game.client.obj.playerFourReady;
		

			//playGame click
			if((xMousePos > 870 && xMousePos < 870+304) && (yMousePos < screenHeight-420 && yMousePos > screenHeight-420-282)) {
				if(input.isMouseButtonDown(0)) {
					
					try {
						
					if(playerNr == 1){
					
					game.client.obj.playerOneReady = true;
					game.client.sendData(game.client.obj);

					}
					
					else if(playerNr == 2){
						
					game.client.obj.playerTwoReady = true;
					game.client.sendData(game.client.obj);
					
			
					}
					
					else if(playerNr == 3){
						
					game.client.obj.playerThreeReady = true;
					game.client.sendData(game.client.obj);
						

					}
					
					else if(playerNr == 4){
							
					game.client.obj.playerFourReady = true;
					game.client.sendData(game.client.obj);
							
							}
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}

			
			//exitGame click
			if((xMousePos > 10 && xMousePos < 10+128) && (yMousePos < screenHeight-570 && yMousePos > screenHeight-570-128)) {
				if(input.isMouseButtonDown(0)) {
					System.exit(0);
				}
			}
			
			if(playerOneReady == true && playerTwoReady == true && playerThreeReady == true && playerFourReady == true) {
				sbg.enterState(1);
			}
		
	}

	public int getID() {
		return 0;
	}
	


}
