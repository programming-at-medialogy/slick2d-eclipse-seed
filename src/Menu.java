

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import java.io.IOException;

import org.lwjgl.input.Mouse;

public class Menu extends BasicGameState {

	private int screenHeight = 700;
	
	//Different images used in the menu
	Image backGroundImg;
	Image playGame;
	Image exitGame;
	Image playerOneReadyImg;
	Image playerTwoReadyImg;
	Image playerThreeReadyImg;
	Image playerFourReadyImg;
	
	//Booleans to see if players have clicked the "Play the Game" sign
	public boolean playerOneReady = false;
	public boolean playerTwoReady = false;
	public boolean playerThreeReady = false;
	public boolean playerFourReady = false;
	
	Game game; 
	Controller controller;
	
	//A string to say who is ready
	public String playersReady = "Ready Players:";
	int playerNr = 0;

	public Menu(int state) {
		
	}
	
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
			
			backGroundImg = new Image("images/menuBackground.png"); //background image
			
			playGame = new Image("images/playGame.png"); //Play game sign
			exitGame = new Image("images/exitGame.png"); //exit game door
			
			//Images to display who is ready.
			playerOneReadyImg = new Image("images/playerOneReady.png");
			playerTwoReadyImg = new Image("images/playerTwoReady.png");
			playerThreeReadyImg = new Image("images/playerThreeReady.png");
			playerFourReadyImg = new Image("images/playerFourReady.png");
			
			playerNr = game.client.obj.playerNumber;
	}

	/*
	 * Renders the different images used in the menu
	 */
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
		
		//Sets the booleans equal to the information from the server (other clients)
		//Via the playerinformation class
		playerOneReady = game.client.obj.playerOneReady;
		playerTwoReady = game.client.obj.playerTwoReady;
		playerThreeReady = game.client.obj.playerThreeReady;		
		playerFourReady = game.client.obj.playerFourReady;
		

		//playGame click
		//if a player clicks the Play Game Sign image, their boolean of the number assigned will become true
		//and this data will be sent to the server thus other clients.
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
						e.printStackTrace();
					}
					
				}
			}

			
			//exitGame click
			//Exits the game if a player hits the exit sign
			if((xMousePos > 10 && xMousePos < 10+128) && (yMousePos < screenHeight-570 && yMousePos > screenHeight-570-128)) {
				if(input.isMouseButtonDown(0)) {
					System.exit(0);
				}
			}
			
			//If all players are ready, instantly go to the playState of the game, BY ID 1
			if(playerOneReady == true && playerTwoReady == true && playerThreeReady == true && playerFourReady == true) {
				sbg.enterState(1);
			}
		
	}

	//Menu ID 0:
	public int getID() {
		return 0;
	}
	


}
