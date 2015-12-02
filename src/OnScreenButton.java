import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class OnScreenButton {

	int screenWidth = 1200;
	int screenHeight = 700;

	Controller control;
	Game game;
	RoadClickArea roadArea;
	
	String yourTurnString = "It is your turn!";

	OnScreenButtonSpawn buttonPlaceHouse;
	OnScreenButtonSpawn buttonPlaceRoad;
	OnScreenButtonSpawn buttonPlaceDice;
	OnScreenButtonSpawn buttonYourTurn;
	OnScreenButtonSpawn buttonEndTurn;
	OnScreenButtonSpawn buttonRoadPressed;
	OnScreenButtonSpawn buttonHousePressed;
	OnScreenButtonSpawn buttonBuyDevCard;
	
	Image chooseOre, chooseClay, chooseWood, chooseWool, chooseWheat;

	boolean buttonRoadControl = false;
	boolean buttonHouseControl = false;
	boolean buttonTurnControl = false;
	boolean buttonDiceControl = false;
	boolean buttonDevCardControl = false;

	int buttonHeight = 56;
	int buttonWidth = 97;
	
	int xResourceSpacing = 55;
	int chooseResourcePosX = 680;
	int chooseResourcePosY = 635;
	
	int buttonStartPosX = screenWidth - buttonWidth - 20;
	int buttonStartPosY = screenHeight - buttonHeight - 20;
	int buttonSpacing = 65;
	int playerTurn;
	int playerNumber;

	OnScreenButton(Controller control) throws SlickException {

		this.control = control;

		buttonPlaceHouse = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY, 1);
		buttonPlaceRoad  = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY - buttonSpacing, 2);
		buttonPlaceDice  = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY - (buttonSpacing * 2), 3);
		buttonYourTurn    = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY - (buttonSpacing * 3), 4);
		buttonBuyDevCard = new OnScreenButtonSpawn(buttonStartPosX - buttonWidth-9, buttonStartPosY, 5);
		buttonEndTurn = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY - (buttonSpacing * 3), 6);
		buttonRoadPressed = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY - buttonSpacing, 8);
		buttonHousePressed = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY, 9);
		
		chooseOre = new Image("images/ore.png");
        chooseClay = new Image("images/clay.png");
        chooseWood = new Image("images/wood.png");
        chooseWool = new Image("images/wool.png");
        chooseWheat = new Image("images/wheat.png");
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {

		playerTurn = game.client.obj.playerTurn;

		buttonPlaceHouse.render(gc, g);
		buttonPlaceRoad.render(gc, g);
		buttonPlaceDice.render(gc, g);
		buttonEndTurn.render(gc, g);
		buttonBuyDevCard.render(gc, g);
		
		if(control.receivedExCard > 0 || control.receivedMonoCard == true) {
		g.drawString("Choose your resource: ", 720,600);
        g.drawImage(chooseOre, chooseResourcePosX, chooseResourcePosY);
        g.drawImage(chooseClay, chooseResourcePosX+xResourceSpacing, chooseResourcePosY);
        g.drawImage(chooseWood, chooseResourcePosX+xResourceSpacing*2, chooseResourcePosY);
        g.drawImage(chooseWool, chooseResourcePosX+xResourceSpacing*3, chooseResourcePosY);
        g.drawImage(chooseWheat, chooseResourcePosX+xResourceSpacing*4, chooseResourcePosY);
		}
		
		if(playerTurn == 1 && playerTurn == control.playerNo){
			buttonYourTurn.render(gc, g);
			g.drawString(yourTurnString, 930, 447);
		} else if (playerTurn == 2 && playerTurn == control.playerNo){
			buttonYourTurn.render(gc, g);
			g.drawString(yourTurnString, 930, 447);
		} else if (playerTurn == 3 && playerTurn == control.playerNo){
			buttonYourTurn.render(gc, g);
			g.drawString(yourTurnString, 930, 447);
		} else if (playerTurn == 4 && playerTurn == control.playerNo){

			buttonYourTurn.render(gc, g);
			g.drawString(yourTurnString, 930, 447);
		}
		
		g.drawString("Rounds left: " + game.client.obj.roundCount, 380, 6);

	}

	public void update(GameContainer gc, int i) throws SlickException, IOException {

		playerTurn = game.client.obj.playerTurn;
		
		int xMousePos = Mouse.getX(); //gets x position of mouse
		int yMousePos = Mouse.getY(); //gets y position of mouse

		Input input = gc.getInput();
		
		   //ExcavationCard
		if(control.receivedExCard > 0) {
        if((xMousePos > 680 && xMousePos < 680 + 45) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                control.resources.oreResource++;
                control.receivedExCard--;
                }
        }
        if((xMousePos > 680 + xResourceSpacing && xMousePos < 680 + 45 + xResourceSpacing) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                control.resources.clayResource++;
                control.receivedExCard--;
                }
        }
        if((xMousePos > 680 + xResourceSpacing*2 && xMousePos < 680 + 45 + xResourceSpacing*2) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                control.resources.woodResource++;
                control.receivedExCard--;
                }
        }
        if((xMousePos > 680 + xResourceSpacing*3 && xMousePos < 680 + 45 + xResourceSpacing*3) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                control.resources.woolResource++;
                control.receivedExCard--;
                }
        }
        if((xMousePos > 680 + xResourceSpacing*4 && xMousePos < 680 + 45 + xResourceSpacing*4) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                control.resources.wheatResource++;
                control.receivedExCard--;
                }
        	}
		}
		
			//MOnopoly card
			if(control.receivedMonoCard == true) {
		        if((xMousePos > 680 && xMousePos < 680 + 45) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
		                if(input.isMousePressed(0)) {
		                	System.out.println("WOOHOO!");
		                	control.receivedMonoCard = false;
		                }
		        }
		        if((xMousePos > 680 + xResourceSpacing && xMousePos < 680 + 45 + xResourceSpacing) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
		                if(input.isMousePressed(0)) {
		                	control.receivedMonoCard = false;
		                }
		        }
		        if((xMousePos > 680 + xResourceSpacing*2 && xMousePos < 680 + 45 + xResourceSpacing*2) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
		                if(input.isMousePressed(0)) {
		                	control.receivedMonoCard = false;
		                }
		        }
		        if((xMousePos > 680 + xResourceSpacing*3 && xMousePos < 680 + 45 + xResourceSpacing*3) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
		                if(input.isMousePressed(0)) {
		                	control.receivedMonoCard = false;
		                }
		        }
		        if((xMousePos > 680 + xResourceSpacing*4 && xMousePos < 680 + 45 + xResourceSpacing*4) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
		                if(input.isMousePressed(0)) {
		                	control.receivedMonoCard = false;
		                }
		        	}
				}

		// ButtomHouse
		if ((xMousePos > buttonStartPosX && xMousePos < buttonStartPosX + buttonWidth)
				&& (yMousePos < screenHeight - buttonStartPosY
						&& yMousePos > screenHeight - buttonStartPosY - buttonHeight)) {
			if (input.isMouseButtonDown(0)) {
				buttonHouseControl = true;
				buttonRoadControl = false;
			}
		}

		// ButtomRoad
		if ((xMousePos > buttonStartPosX && xMousePos < buttonStartPosX + buttonWidth)
				&& (yMousePos < screenHeight - buttonStartPosY + buttonSpacing
						&& yMousePos > screenHeight - buttonStartPosY + buttonSpacing - buttonHeight)) {
			if (input.isMouseButtonDown(0)) {
				buttonRoadControl = true;
				buttonHouseControl = false;
			}
		}
		// ButtomDice
		if ((xMousePos > buttonStartPosX && xMousePos < buttonStartPosX + buttonWidth)
				&& (yMousePos < screenHeight - buttonStartPosY + (buttonSpacing * 2)
						&& yMousePos > screenHeight - buttonStartPosY + (buttonSpacing * 2) - buttonHeight)) {
			if (input.isMouseButtonDown(0)) {
				buttonDiceControl = true;
				buttonDevCardControl = false;
			}
		}

		// ButtonBuyDevCard
		if ((xMousePos > buttonStartPosX - buttonWidth - 9
				&& xMousePos < (buttonStartPosX - buttonWidth - 9) + buttonWidth)
				&& (yMousePos < screenHeight - buttonStartPosY + buttonSpacing
						&& yMousePos > screenHeight - buttonStartPosY + buttonSpacing - buttonHeight)) {
			if (input.isMousePressed(0)) {
				control.devCardButtonClicked = true;
				buttonDiceControl = false;
				
			}
		}

		// ButtomEndTurn
		if ((xMousePos > buttonStartPosX && xMousePos < buttonStartPosX + buttonWidth)
				&& (yMousePos < screenHeight - buttonStartPosY + buttonSpacing * 3
						&& yMousePos > screenHeight - buttonStartPosY + buttonSpacing * 3 - buttonHeight)) {

			if (input.isMouseButtonDown(0)) {

				if (playerTurn == control.playerNo) {

					if (playerTurn == control.playerNo) {

						if (playerTurn == 1 && playerTurn == control.playerNo) {
							game.client.obj.playerTurn = 2;
							game.client.sendData(game.client.obj);
						}

						else if (playerTurn == 2 && playerTurn == control.playerNo) {
							game.client.obj.playerTurn = 3;
							game.client.sendData(game.client.obj);
						}

						else if (playerTurn == 3 && playerTurn == control.playerNo) {
							game.client.obj.playerTurn = 4;
							game.client.sendData(game.client.obj);
						}

						else if (playerTurn == 4 && playerTurn == control.playerNo) {
							game.client.obj.playerTurn = 1;
							game.client.obj.roundCount--;
							game.client.sendData(game.client.obj);
						}
					}
				}
			}
		}
	}
}
