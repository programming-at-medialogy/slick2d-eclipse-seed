import java.io.IOException;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/*
 * This class renders images on the screen and 
 * checks to see if these images (buttons) have been clicked.
 */
public class OnScreenButton {
	
	int screenWidth = 1200;
	int screenHeight = 700;
	
	//Instances of different classes.
	Controller control;
	Game game;
	SettlementSpawn spawn;
	Trade trade;
	
	//A string to display that it is now your turn.
    String yourTurnString = "It is your turn!";
	
    //Instances of the OnScreenButtonSpawn class, used to render images.
	OnScreenButtonSpawn buttonPlaceHouse;
	OnScreenButtonSpawn buttonPlaceCity;
	OnScreenButtonSpawn buttonPlaceRoad;
	OnScreenButtonSpawn buttonPlaceDice;
	OnScreenButtonSpawn buttonEndTurn;
    OnScreenButtonSpawn buttonYourTurn;
    OnScreenButtonSpawn buttonRoadPressed;
    OnScreenButtonSpawn buttonHousePressed;
    OnScreenButtonSpawn buttonBuyDevCard;
    OnScreenButtonSpawn buttonCityPressed;
    OnScreenButtonSpawn buttonDeselect;
    OnScreenButtonSpawn buttonDeselectPressed;
    OnScreenButtonSpawn buttonTrade;
    OnScreenButtonSpawn buttonTradePressed;

  //Alternative way of rendering images; - declares 5 different images.
    Image chooseOre, chooseClay, chooseWood, chooseWool, chooseWheat;
  //Arrow for trading
    Image tradeArrowDown;
	
  //Booleans to control if a button has been pressed.
	boolean buttonRoadControl = false;
	boolean buttonHouseControl = false;
	boolean buttonCityControl = false;
	boolean buttonTurnControl = false;
	boolean buttonDiceControl = false;
    boolean buttonDevCardControl = false;
    boolean buttonDeselectControl = false;
	
  //The height of the buttons in the game in pixels
	int buttonHeight = 56;
	int buttonWidth = 97;

	//The spacing between different buttons
    int xResourceSpacing = 55;
    int chooseResourcePosX = 680;
    int chooseResourcePosY = 635;
    int buttonSpacing = 65;
    
  //Ints to declare the starting position of buttons
	int buttonStartPosX = screenWidth-buttonWidth-20;
	int buttonStartPosY = screenHeight-buttonHeight-20;
	
	//Int to check whose turn it is.
	int playerTurn;
	
	OnScreenButton (Controller control) throws SlickException {
		
		this.control = control;
		trade = new Trade(control);
		
		//Sets the instances of the OnScreenButtonSpawn equal to the position (X,Y) and what number it is.
		buttonPlaceHouse = new OnScreenButtonSpawn(buttonStartPosX - buttonWidth - 9, buttonStartPosY, 7);
		buttonPlaceCity  = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY, 1);
		buttonPlaceRoad  = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY - buttonSpacing, 2);
		buttonPlaceDice  = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY - (buttonSpacing * 2), 3);
        buttonYourTurn = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY - (buttonSpacing * 3), 4);
		buttonBuyDevCard = new OnScreenButtonSpawn(buttonStartPosX - buttonWidth-9, buttonStartPosY - buttonHeight-9, 5);
        buttonEndTurn = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY - (buttonSpacing * 3), 6);
	    buttonRoadPressed = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY - buttonSpacing, 8);
	    buttonHousePressed = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY, 9);
	    buttonCityPressed = new OnScreenButtonSpawn(buttonStartPosX - buttonWidth - 9, buttonStartPosY, 10);
	    buttonDeselect = new OnScreenButtonSpawn(buttonStartPosX - buttonWidth - 9, buttonStartPosY - buttonHeight*2-9*2, 11);
	    buttonDeselectPressed = new OnScreenButtonSpawn(buttonStartPosX - buttonWidth - 9, buttonStartPosY - buttonHeight*2-9*2, 12);
	    buttonTrade = new OnScreenButtonSpawn(buttonStartPosX - buttonWidth - 9, buttonStartPosY - (buttonSpacing * 3), 13);
	    buttonTradePressed = new OnScreenButtonSpawn(buttonStartPosX - buttonWidth - 9, buttonStartPosY - (buttonSpacing * 3), 14);
	    
	  //Reference where the image is located.
	    chooseOre = new Image("images/ore.png");
        chooseClay = new Image("images/clay.png");
        chooseWood = new Image("images/wood.png");
        chooseWool = new Image("images/wool.png");
        chooseWheat = new Image("images/wheat.png");
        
        tradeArrowDown = new Image("images/arrowDown.png");
	}

	/*
	 * The order in which the different images are being render is important to
	 * be sure on what images are being rendered first and last.
	 */
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		//Sets the playerturn equal to what has been received by the server from the PlayerInformation class.
		playerTurn = game.client.obj.playerTurn;
		
		//Renders some of the buttons
		buttonPlaceHouse.render(gc, g);
		buttonPlaceCity.render(gc, g);
		buttonPlaceRoad.render(gc, g);
		buttonPlaceDice.render(gc, g);
		buttonEndTurn.render(gc, g);
        buttonBuyDevCard.render(gc, g);
        trade.render(gc, g);
        
		if (control.deselectButtonControl == true) {
			buttonDeselect.render(gc, g);
		} else if (control.deselectButtonControl != true) {
			buttonDeselectPressed.render(gc, g);
		}
        
		/*
         * Checks to see if the deselect button has been pressed
         * Depending on this it will display one of two images; one grey and one color as
         * indication to which is true.
         */
		if (control.tradeButtonControl == true || control.playerNo != game.client.obj.playerTurn) {
			buttonTradePressed.render(gc, g);
			g.drawImage(tradeArrowDown, chooseResourcePosX-50, chooseResourcePosY-40);
			g.drawString("4 x Resource for 1 x Resource", chooseResourcePosX, chooseResourcePosY-80);
		} else{
			buttonTrade.render(gc, g);
		}

		//if the player receives either a excavation card or monopoly card
        //Spawns different images with spacing between them
        if(control.receivedExCard > 0 || control.receivedMonoCard == true) {
            g.drawString("Choose your resource: ", 720,600);
            g.drawImage(chooseOre, chooseResourcePosX, chooseResourcePosY);
            g.drawImage(chooseClay, chooseResourcePosX+xResourceSpacing, chooseResourcePosY);
            g.drawImage(chooseWood, chooseResourcePosX+xResourceSpacing*2, chooseResourcePosY);
            g.drawImage(chooseWool, chooseResourcePosX+xResourceSpacing*3, chooseResourcePosY);
            g.drawImage(chooseWheat, chooseResourcePosX+xResourceSpacing*4, chooseResourcePosY);
        }

        //Renders the image that it is your turn and you can now end your turn.
        if(control.playerNo == game.client.obj.playerTurn){
            buttonYourTurn.render(gc, g);
            g.drawString(yourTurnString, 800, 447);
        }
      //String in top of screen to indication the rounds left till the game ends
		g.drawString("Rounds left: " + game.client.obj.roundCount, 380, 6);
	}
	
	public void update(GameContainer gc, int i) throws SlickException, IOException {
		//Gets the playerTurn information off the PlayerInformation class
		playerTurn = game.client.obj.playerTurn;
		
		//Calls the update of trade.
		trade.update(gc, i);
		
		int xMousePos = Mouse.getX(); //gets x position of mouse
		int yMousePos = Mouse.getY(); //gets y position of mouse
		
		Input input = gc.getInput(); //gets input from player
		
		int ySpacing= 55;
		
			/////TRADE BUTTON////
		if(control.playerNo == game.client.obj.playerTurn){
			if((xMousePos > buttonStartPosX - buttonWidth - 9 && xMousePos < (buttonStartPosX - buttonWidth - 9) + buttonWidth) &&  (yMousePos < screenHeight - buttonStartPosY + buttonSpacing * 3
					&& yMousePos > screenHeight - buttonStartPosY + buttonSpacing * 3 - buttonHeight)) {
				if(input.isMouseButtonDown(0)) {
					control.tradeButtonControl = true;
					for(int j = 0; j < 5; j++){
					trade.playerResource[j][0] = false;
					trade.playerResource[j][1] = false;
					control.deselectButtonControl = true;
					}
				}
			}
		}
				
			//TRADE - PLAYER//
			if(control.tradeButtonControl == true){
			if((xMousePos > 680 && xMousePos < 680 + 45) && (yMousePos < screenHeight - 635 + ySpacing && yMousePos > screenHeight - 635 - 45 + ySpacing )){
	            if(input.isMousePressed(0)) {
	            	trade.playerResource[0][0] = true;
	            	trade.playerResource[1][0] = false;
	            	trade.playerResource[2][0] = false;
	            	trade.playerResource[3][0] = false;
	            	trade.playerResource[4][0] = false;
	            }
	        }
	        if((xMousePos > 680 + xResourceSpacing && xMousePos < 680 + 45 + xResourceSpacing) && (yMousePos < screenHeight - 635 + ySpacing && yMousePos > screenHeight - 635 - 45 + ySpacing)){
	            if(input.isMousePressed(0)) {
	            	trade.playerResource[3][0] = true;
	            	trade.playerResource[0][0] = false;
	            	trade.playerResource[2][0] = false;
	            	trade.playerResource[1][0] = false;
	            	trade.playerResource[4][0] = false;
	            }
	        }
	        if((xMousePos > 680 + xResourceSpacing*2 && xMousePos < 680 + 45 + xResourceSpacing*2) && (yMousePos < screenHeight - 635 + ySpacing && yMousePos > screenHeight - 635 - 45 + ySpacing)){
	            if(input.isMousePressed(0)) {
	            	trade.playerResource[2][0] = true;
	            	trade.playerResource[1][0] = false;
	            	trade.playerResource[0][0] = false;
	            	trade.playerResource[3][0] = false;
	            	trade.playerResource[4][0] = false;
	            }
	        }
	        if((xMousePos > 680 + xResourceSpacing*3 && xMousePos < 680 + 45 + xResourceSpacing*3) && (yMousePos < screenHeight - 635 + ySpacing && yMousePos > screenHeight - 635 - 45 + ySpacing)){
	            if(input.isMousePressed(0)) {
	            	trade.playerResource[4][0] = true;
	            	trade.playerResource[1][0] = false;
	            	trade.playerResource[2][0] = false;
	            	trade.playerResource[3][0] = false;
	            	trade.playerResource[0][0] = false;
	            }
	        }
	        if((xMousePos > 680 + xResourceSpacing*4 && xMousePos < 680 + 45 + xResourceSpacing*4) && (yMousePos < screenHeight - 635 + ySpacing && yMousePos > screenHeight - 635 - 45 + ySpacing)){
	            if(input.isMousePressed(0)) {
	            	trade.playerResource[1][0] = true;
	            	trade.playerResource[0][0] = false;
	            	trade.playerResource[2][0] = false;
	            	trade.playerResource[3][0] = false;
	            	trade.playerResource[4][0] = false;
	            }
	        }
			}
		
			//TRADE - BANK//
			if(control.tradeButtonControl == true){
            if((xMousePos > 680 && xMousePos < 680 + 45) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                	trade.playerResource[0][1] = true;
                }
            }
            if((xMousePos > 680 + xResourceSpacing && xMousePos < 680 + 45 + xResourceSpacing) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                	trade.playerResource[3][1] = true;
                }
            }
            if((xMousePos > 680 + xResourceSpacing*2 && xMousePos < 680 + 45 + xResourceSpacing*2) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                	trade.playerResource[2][1] = true;
                }
            }
            if((xMousePos > 680 + xResourceSpacing*3 && xMousePos < 680 + 45 + xResourceSpacing*3) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                	trade.playerResource[4][1] = true;
                }
            }
            if((xMousePos > 680 + xResourceSpacing*4 && xMousePos < 680 + 45 + xResourceSpacing*4) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                	trade.playerResource[1][1] = true;
                }
            }
			}

		
			/*
	         * ExcavationCard
	         * Statements to control and check, if a player clicks within one of the images, that is rendered when
	         * the player gets this card. It checks the most left X, the most right X, the most top Y and most low Y
	         * and if the mouse is pressed within, let that player get a new resource of the type clicked on.
	         * The player can choose two resources.
	         */
        if(control.receivedExCard > 0) {
            if((xMousePos > 680 && xMousePos < 680 + 45) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                	game.client.obj.playerResource[control.playerNo-1][0]++;
                    control.receivedExCard--; //The amount of choices you have are being reduced by one
                }
            }
            if((xMousePos > 680 + xResourceSpacing && xMousePos < 680 + 45 + xResourceSpacing) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                	game.client.obj.playerResource[control.playerNo-1][3]++;
                    control.receivedExCard--;
                }
            }
            if((xMousePos > 680 + xResourceSpacing*2 && xMousePos < 680 + 45 + xResourceSpacing*2) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                	game.client.obj.playerResource[control.playerNo-1][2]++;
                    control.receivedExCard--;
                }
            }
            if((xMousePos > 680 + xResourceSpacing*3 && xMousePos < 680 + 45 + xResourceSpacing*3) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                	game.client.obj.playerResource[control.playerNo-1][4]++;
                    control.receivedExCard--;
                }
            }
            if((xMousePos > 680 + xResourceSpacing*4 && xMousePos < 680 + 45 + xResourceSpacing*4) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                	game.client.obj.playerResource[control.playerNo-1][1]++;
                    control.receivedExCard--;
                }
            }
        }

        /*
         * Monopoly card
         * This is the same as above with the EX-Card, however, now the player selects a resource and steals
         * it from the other players, gaining all the resources of the other players, whom looses these.
         */
        if(control.receivedMonoCard == true) {
            if((xMousePos > 680 && xMousePos < 680 + 45) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                	for(int j = 0 ; j<4; j++){
                		if(j != control.playerNo-1){
                			//This will give the player all the resources of the other player (ore in this case) and set theirs equal to 0.
                			game.client.obj.playerResource[control.playerNo-1][0] += game.client.obj.playerResource[j][0];
                        	game.client.obj.playerResource[j][0] = 0;
                		}
                	}
                	game.client.sendData(game.client.obj); //sends the data.
                    control.receivedMonoCard = false;  //the player no longer have the card
                }
            }
            if((xMousePos > 680 + xResourceSpacing && xMousePos < 680 + 45 + xResourceSpacing) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                	for(int j = 0 ; j<4; j++){
                		if(j != control.playerNo-1){
                			game.client.obj.playerResource[control.playerNo-1][3] += game.client.obj.playerResource[j][3];
                        	game.client.obj.playerResource[j][3] = 0;
                		}
                	}
                	game.client.sendData(game.client.obj);
                    control.receivedMonoCard = false;
                }
            }
            if((xMousePos > 680 + xResourceSpacing*2 && xMousePos < 680 + 45 + xResourceSpacing*2) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                	for(int j = 0 ; j<4; j++){
                		if(j != control.playerNo-1){
                			game.client.obj.playerResource[control.playerNo-1][2] += game.client.obj.playerResource[j][2];
                        	game.client.obj.playerResource[j][2] = 0;
                		}
                	}
                	game.client.sendData(game.client.obj);
                    control.receivedMonoCard = false;
                }
            }
            if((xMousePos > 680 + xResourceSpacing*3 && xMousePos < 680 + 45 + xResourceSpacing*3) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                	for(int j = 0 ; j<4; j++){
                		if(j != control.playerNo-1){
                			game.client.obj.playerResource[control.playerNo-1][4] += game.client.obj.playerResource[j][4];
                        	game.client.obj.playerResource[j][4] = 0;
                		}
                	}
                	game.client.sendData(game.client.obj);
                    control.receivedMonoCard = false;
                }
            }
            if((xMousePos > 680 + xResourceSpacing*4 && xMousePos < 680 + 45 + xResourceSpacing*4) && (yMousePos < screenHeight - 635 && yMousePos > screenHeight - 635 - 45 )){
                if(input.isMousePressed(0)) {
                	for(int j = 0 ; j<4; j++){
                		if(j != control.playerNo-1){
                			game.client.obj.playerResource[control.playerNo-1][1] += game.client.obj.playerResource[j][1];
                        	game.client.obj.playerResource[j][1] = 0;
                		}
                	}
                	game.client.sendData(game.client.obj);
                    control.receivedMonoCard = false;
                }
            }
        }
		
		//ButtonHouse - Checks to see if the player have clicked the house button
		if((xMousePos > buttonStartPosX && xMousePos < buttonStartPosX+buttonWidth) && (yMousePos < screenHeight-buttonStartPosY && yMousePos > screenHeight-buttonStartPosY-buttonHeight)) {
			if (input.isMouseButtonDown(0)) {
					buttonRoadControl = false;
					buttonCityControl = false;
					buttonHouseControl = true;
					control.deselectButtonControl = true;//if the player have pressed the button, he can now deselect the button.
			}
		}
		
		//Deselect -- checks to see if the player have clicked the deselect button
		//Sets the other buttons equal to false = no other buttons are pressed.
		if((xMousePos > buttonStartPosX - buttonWidth - 9 && xMousePos < (buttonStartPosX - buttonWidth - 9) + buttonWidth) && (yMousePos < screenHeight - buttonStartPosY + buttonSpacing*2 && yMousePos > screenHeight - buttonStartPosY + buttonSpacing*2 - buttonHeight)) {
			if(input.isMouseButtonDown(0)) {
				buttonRoadControl = false;
				buttonCityControl = false;
				buttonHouseControl = false;
				control.deselectButtonControl = false;
				control.tradeButtonControl = false;
				for(int j = 0; j < 5; j++){
					trade.playerResource[j][0] = false;
					trade.playerResource[j][1] = false;
					}
			}
		}
		
		//ButtonCity Checks to see if the player have clicked the city button
		if ((xMousePos > buttonStartPosX - buttonWidth - 9 && xMousePos < (buttonStartPosX - buttonWidth - 9) + buttonWidth) && (yMousePos < screenHeight - buttonStartPosY && yMousePos > screenHeight - buttonStartPosY - buttonHeight)) {
			if (input.isMouseButtonDown(0)) {
					buttonRoadControl = false;
					buttonCityControl = true;
					buttonHouseControl = false;
					control.deselectButtonControl = true;
			}
		}
			
		//ButtonRoad Checks to see if the player have clicked the road button
		if((xMousePos > buttonStartPosX && xMousePos < buttonStartPosX+buttonWidth) && (yMousePos < screenHeight-buttonStartPosY+buttonSpacing && yMousePos > screenHeight-buttonStartPosY+buttonSpacing-buttonHeight)) {
			if (input.isMouseButtonDown(0)) {
					buttonRoadControl = true;
					buttonCityControl = false;
					buttonHouseControl = false;
					control.deselectButtonControl = true;
			}
		}
			
		//ButtonDice Checks to see if the player have clicked the dice button
			if((xMousePos > buttonStartPosX && xMousePos < buttonStartPosX+buttonWidth) && (yMousePos < screenHeight-buttonStartPosY+(buttonSpacing*2) && yMousePos > screenHeight-buttonStartPosY+(buttonSpacing*2)-buttonHeight)) {
				if(input.isMousePressed(0)) {
					control.diceButtonClicked = true;
					buttonRoadControl = false;
					buttonCityControl = false;
					buttonHouseControl = false;
				}
			}
		
		// ButtonBuyDevCard Checks to see if the player have clicked the card buy button
			if(control.playerNo == game.client.obj.playerTurn){
	        if ((xMousePos > buttonStartPosX - buttonWidth - 9 && xMousePos < (buttonStartPosX - buttonWidth - 9) + buttonWidth) && (yMousePos < screenHeight - buttonStartPosY + buttonSpacing && yMousePos > screenHeight - buttonStartPosY + buttonSpacing - buttonHeight)) {
	            if (input.isMousePressed(0)) {
	                control.devCardButtonClicked = true;
					buttonRoadControl = false;
					buttonCityControl = false;
					buttonHouseControl = false;

	            }
	        }
			}
			
		/*
		 * ButtonEndTurn Checks to see if the player have clicked the endturn
		 * button All the other buttons are returned to false. It sends this
		 * information to the server, to let the next player in line know, that
		 * it is know his turn. Upon the fourth player, it resets to player no. 1
		 */
		if ((xMousePos > buttonStartPosX && xMousePos < buttonStartPosX + buttonWidth)
				&& (yMousePos < screenHeight - buttonStartPosY + buttonSpacing * 3
				&& yMousePos > screenHeight - buttonStartPosY + buttonSpacing * 3 - buttonHeight)) {
			if (input.isMousePressed(0)) {

				//Sets other buttons equal to false
				buttonRoadControl = false;
				buttonCityControl = false;
				buttonHouseControl = false;
				control.diceButtonClicked = false;
                control.devCardButtonClicked = false;
				
				if (playerTurn == control.playerNo) {
					
					//If the player no is 1 and it is the players turn
					if (playerTurn == 1 && playerTurn == control.playerNo) {
						control.clickOnce = 0; //makes sure the player only clicks once.
						game.client.obj.playerTurn = 2; //set the player turn equal to player no 2
						game.client.sendData(game.client.obj); //sends the data to the server.
					}

					else if (playerTurn == 2 && playerTurn == control.playerNo) {
						control.clickOnce = 0;
						game.client.obj.playerTurn = 3;
						game.client.sendData(game.client.obj);

					}

					else if (playerTurn == 3 && playerTurn == control.playerNo) {
						control.clickOnce = 0;
						game.client.obj.playerTurn = 4;
						game.client.sendData(game.client.obj);
					}

					else if (playerTurn == 4 && playerTurn == control.playerNo) {
						control.clickOnce = 0;
						game.client.obj.playerTurn = 1;
						game.client.obj.roundCount--;
						game.client.sendData(game.client.obj);
					}
				}
			}
		}
	}
}