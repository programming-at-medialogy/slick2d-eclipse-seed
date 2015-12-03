import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/*
 * The purpose for this class is to get the coordinates of all the places a road can be placed
 * It also checks if an area have been clicked, and if it have, it should spawn a road
 * of the color which has been assigned in the controller class.
 */

public class RoadClickArea {

	//Screen sizes
	static int screenWidth = 1200;
	static int screenHeight = 700;

	Controller control; // need values from the Controller class
	int totalRoads = 72; // int to set the total amount of roads which can be placed (the actual number is lower than 100)
	
	int DiagonalRoadCount = 47; //the amount of diagonal roads
	int StraightRoadCount = 24; //the amount of straight roads

	OnScreenButton roadButton; // instance of the OnScreenButton class. Needed to render and check if the button has been clicked
	Game game; //instance of the game class
	
	// Make an instance of the RoadSpawn class. Used to render images
	RoadSpawn[] DiagonalRoad;
	RoadSpawn[] StraightRoad;

	// Values which defines the size of the area able to be clicked for all of the non-vertical roads.
	int areaClickSizeXsmall = 48;
	int areaClickSizeYsmall = 28;

	// Values to define the size of the larger areas for all the vertical roads.
	int areaClickSizeXbig = 97;
	int areaClickSizeYbig = 56;

	// Boolean arrays to check if an area have been clicked.
	boolean[] DiagonalRoadArea;
	boolean[] StraightRoadArea;

	// Int arrays to store the coordinates of all the areas where a road can be spawned
	int[] diagonal_xpos;
	int[] diagonal_ypos;

	// Same, just for the large roads (vertical roads)
	int[] straight_xpos;
	int[] straight_ypos;



	// Constructor
	RoadClickArea(Controller control) throws SlickException {
		
		this.control = control;
		roadButton = new OnScreenButton(control); // create the button

		DiagonalRoad = new RoadSpawn[totalRoads]; // create 47 instances of the HouseSpawn class
		StraightRoad = new RoadSpawn[totalRoads]; // create 25 instances of the HouseSpawn class

		// make the arrays totalRoads long (72)
		diagonal_xpos = new int[totalRoads];
		diagonal_ypos = new int[totalRoads];

		straight_xpos = new int[totalRoads];
		straight_ypos = new int[totalRoads];

		DiagonalRoadArea = new boolean[totalRoads];
		StraightRoadArea = new boolean[totalRoads];

		placeRoad();
	}


	// update method
	public void update(GameContainer gc, int i) throws SlickException, IOException {
		
		// Class the update method from the OnScreenButton class.
		roadButton.update(gc, i);
		
		//Runs though the for-loop to set them equal to the information in PlayerInformation class
		//TO let every client know that a specific road area has been clicked.
		for (int j = 0; j < totalRoads; j++) {
				StraightRoadArea[j] = game.client.obj.SOCroadAreaStraight[j];
				DiagonalRoadArea[j] = game.client.obj.SOCroadAreaDiagonal[j];
		}

		int xMousePos = Mouse.getX(); // gets x position of mouse
		int yMousePos = Mouse.getY(); // gets y position of mouse

		Input input = gc.getInput(); //Used in the mouse input
		


		// Controls different parameters to see, if one is allowed to place a road
		if (game.client.obj.playerTurn == control.playerNo) { //Needs to be the player's turn
			if (roadButton.buttonRoadControl == true) { // Checks to see of the roadButton has been clicked
			for (i = 0; i < diagonal_xpos.length; i++) {
				if ((xMousePos > diagonal_xpos[i] && xMousePos < diagonal_xpos[i] + areaClickSizeXsmall)
					&& (yMousePos < screenHeight - diagonal_ypos[i]
					&& yMousePos > screenHeight - diagonal_ypos[i] - areaClickSizeYsmall)) {// checks the coordinates of the click
					if (input.isMouseButtonDown(0)) { // has the mouse been clicked?
						control.deselectButtonControl = false;
						if(DiagonalRoadArea[i] != true){ //If the index is false
						placeDiagonalRoad(i); // the diagonal area has been clicked; spawn a road at no. i
						game.client.obj.roadsColourDiagonal[i] = control.playerNo; //Set the area equal to the player no; that player now owns the area
						game.client.obj.SOCroadAreaDiagonal[i] = DiagonalRoadArea[i]; //The area is no longer availbe for the taking. 
						game.client.sendData(game.client.obj); //Send the data
						roadButton.buttonRoadControl = false; // toggles the button false
							}
						}
					}
				}
			}

		// Does the same as above this time for the straight roads.
		if (game.client.obj.playerTurn == control.playerNo) {
			if (roadButton.buttonRoadControl == true) {
				for (i = 0; i < straight_xpos.length; i++) {
					if ((xMousePos > straight_xpos[i] && xMousePos < straight_xpos[i] + areaClickSizeXbig)
						&& (yMousePos < screenHeight - straight_ypos[i]
						&& yMousePos > screenHeight - straight_ypos[i] - areaClickSizeYbig)) {
						if (input.isMouseButtonDown(0)) {
							control.deselectButtonControl = false;
							if(StraightRoadArea[i] != true){			
							placeStraightRoad(i); // if the area is clicked, that area's boolean must become true.
							game.client.obj.roadsColourStraight[i] = control.playerNo;
							game.client.obj.SOCroadAreaStraight[i] = StraightRoadArea[i];
							game.client.sendData(game.client.obj);
							roadButton.buttonRoadControl = false;
							}
						}
					}
				}
			}
		}
	
	}
		
		//Runs through all the diagonal roads to equal them to the information in PlayerInformation
				for (int j = 0; j < DiagonalRoadCount; j++){
					DiagonalRoad[j].playerNo = game.client.obj.roadsColourDiagonal[j];
				}
				
				//Runs through all the straight roads to equal them to the information in PlayerInformation
				for (int j = 0; j < StraightRoadCount; j++){
					StraightRoad[j].playerNo = game.client.obj.roadsColourStraight[j];
				}
	}
	
	
	// Render method the render the images needed.
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		//If the road button has been pressed, then render the image.
		if(roadButton.buttonRoadControl == true){
			roadButton.buttonRoadPressed.render(gc, g);
		}
		
		// Renders all the diagonal roads
		for (int i = 0; i < DiagonalRoadArea.length; i++) {
			if (DiagonalRoadArea[i] == true) {
				DiagonalRoad[i].render(gc, g);
			}
		}

		// Renders all the straight roads
		for (int i = 0; i < StraightRoadArea.length; i++) {
			if (StraightRoadArea[i] == true) {
				StraightRoad[i].render(gc, g);
			}
		}
	}
	
	public void placeDiagonalRoad(int i) {

		//If a player have enough resources to place a road, this can be done and player resources will be reduced.
		if (checkRoadCost()) {
			game.client.obj.playerResource[control.playerNo-1][3]--;
			game.client.obj.playerResource[control.playerNo-1][2]--;
			control.resources.roadCount++;
			DiagonalRoadArea[i] = true;
			}
	}
	
	public void placeStraightRoad(int i) {


		if (checkRoadCost()) {
			game.client.obj.playerResource[control.playerNo-1][3]--;
			game.client.obj.playerResource[control.playerNo-1][2]--;
			control.resources.roadCount++;
			StraightRoadArea[i] = true;
		}
	}
	
	
	/*
	 * A method to check if a player have enough resources.
	 * If a player does have enough resources to place a road, this will return true
	 * Therefore the above methods can be used.
	 */
	public boolean checkRoadCost() {

		boolean isTrue = false;
		if (game.client.obj.playerResource[control.playerNo - 1][3] >= 1
				&& game.client.obj.playerResource[control.playerNo - 1][2] >= 1) {
			isTrue = true;
		}

		return isTrue;
	}

	// This method is used to get all the coordinates of where the roads can be placed
	public void placeRoad() throws SlickException {

		int indexerSmall = 0; // Index to keep track of non-vertical roads
		int indexerBig = 0; // Same, just for vertical roads
		int tileWidth = 97; // the images width is 97 pixels.
		int longOffsetY = 56; // A long offset used to get correct coodinate and placement. Same size as a vertical road
		int shortOffsetY = 28; // A short offset; same size as a non-vertical road

		float xOffset = 97 / 2 + 0.5f; // a specific offset to place and get correct coordinates

		/*
		 * A bunch of for-loops to get to correct coordinates of all the roads.
		 * The for-loops also creates new instances of the RoadSpawn class,
		 * depending on where the area is.
		 */
		
		for (int i = 0; i < 3; i++) {
			diagonal_xpos[indexerSmall] = (int) (screenWidth / 4 + tileWidth * i); //Gets the x pos
			diagonal_ypos[indexerSmall] = longOffsetY * 2; //gets the y pos
			DiagonalRoad[indexerSmall] = new RoadSpawn(diagonal_xpos[indexerSmall],
					diagonal_ypos[indexerSmall], control.playerNo, 1); //new instance of the Diagonal road
			indexerSmall++; //increase the indexer to keep count
		}

		for (int i = 0; i < 3; i++) {
			diagonal_xpos[indexerSmall] = (int) (xOffset + screenWidth / 4 + tileWidth * i);
			diagonal_ypos[indexerSmall] = longOffsetY * 2;
			DiagonalRoad[indexerSmall] = new RoadSpawn(diagonal_xpos[indexerSmall],
					diagonal_ypos[indexerSmall], control.playerNo, 2);
			indexerSmall++;
		}

		for (int i = 0; i < 4; i++) {
			straight_xpos[indexerBig] = (int) (screenWidth / 4 - xOffset + (xOffset * 2) * i);
			straight_ypos[indexerBig] = longOffsetY * 2 + shortOffsetY;
			StraightRoad[indexerBig] = new RoadSpawn(straight_xpos[indexerBig], straight_ypos[indexerBig],
					control.playerNo, 6);
			indexerBig++;
		}

		for (int i = 0; i < 4; i++) {
			diagonal_xpos[indexerSmall] = (int) (screenWidth / 4 - xOffset + (xOffset * 2) * i);
			diagonal_ypos[indexerSmall] = longOffsetY * 2 + shortOffsetY * 3;
			DiagonalRoad[indexerSmall] = new RoadSpawn(diagonal_xpos[indexerSmall],
					diagonal_ypos[indexerSmall], control.playerNo, 1);
			indexerSmall++;
		}

		for (int i = 0; i < 4; i++) {
			diagonal_xpos[indexerSmall] = (int) (xOffset + screenWidth / 4 - xOffset + (xOffset * 2) * i);
			diagonal_ypos[indexerSmall] = longOffsetY * 2 + shortOffsetY * 3;
			DiagonalRoad[indexerSmall] = new RoadSpawn(diagonal_xpos[indexerSmall],
					diagonal_ypos[indexerSmall], control.playerNo, 2);
			indexerSmall++;
		}

		for (int i = 0; i < 5; i++) {
			straight_xpos[indexerBig] = (int) (screenWidth / 4 - xOffset * 2 + (xOffset * 2) * i);
			straight_ypos[indexerBig] = longOffsetY * 2 + shortOffsetY * 4;
			StraightRoad[indexerBig] = new RoadSpawn(straight_xpos[indexerBig], straight_ypos[indexerBig],
					control.playerNo, 6);
			indexerBig++;
		}

		for (int i = 0; i < 5; i++) {
			diagonal_xpos[indexerSmall] = (int) (screenWidth / 4 - xOffset * 2 + (xOffset * 2) * i);
			diagonal_ypos[indexerSmall] = longOffsetY * 2 + shortOffsetY * 6;
			DiagonalRoad[indexerSmall] = new RoadSpawn(diagonal_xpos[indexerSmall],
					diagonal_ypos[indexerSmall], control.playerNo, 1);
			indexerSmall++;
		}

		for (int i = 0; i < 5; i++) {
			diagonal_xpos[indexerSmall] = (int) (xOffset + screenWidth / 4 - xOffset * 2 + (xOffset * 2) * i);
			diagonal_ypos[indexerSmall] = longOffsetY * 2 + shortOffsetY * 6;
			DiagonalRoad[indexerSmall] = new RoadSpawn(diagonal_xpos[indexerSmall],
					diagonal_ypos[indexerSmall], control.playerNo, 2);
			indexerSmall++;
		}

		for (int i = 0; i < 6; i++) {
			straight_xpos[indexerBig] = (int) (screenWidth / 4 - xOffset * 3 + (xOffset * 2) * i);
			straight_ypos[indexerBig] = longOffsetY * 3 + shortOffsetY * 5;
			StraightRoad[indexerBig] = new RoadSpawn(straight_xpos[indexerBig], straight_ypos[indexerBig],
					control.playerNo, 6);
			indexerBig++;
		}

		for (int i = 0; i < 5; i++) {
			diagonal_xpos[indexerSmall] = (int) (screenWidth / 4 - xOffset * 2 + (xOffset * 2) * i);
			diagonal_ypos[indexerSmall] = longOffsetY * 3 + shortOffsetY * 7;
			DiagonalRoad[indexerSmall] = new RoadSpawn(diagonal_xpos[indexerSmall],
					diagonal_ypos[indexerSmall], control.playerNo, 5);
			indexerSmall++;
		}

		for (int i = 0; i < 5; i++) {
			diagonal_xpos[indexerSmall] = (int) (xOffset + screenWidth / 4 - xOffset * 2 + (xOffset * 2) * i);
			diagonal_ypos[indexerSmall] = longOffsetY * 3 + shortOffsetY * 7;
			DiagonalRoad[indexerSmall] = new RoadSpawn(diagonal_xpos[indexerSmall],
					diagonal_ypos[indexerSmall], control.playerNo, 4);
			indexerSmall++;
		}

		for (int i = 0; i < 5; i++) {
			straight_xpos[indexerBig] = (int) (screenWidth / 4 - xOffset * 2 + (xOffset * 2) * i);
			straight_ypos[indexerBig] = longOffsetY * 3 + shortOffsetY * 8;
			StraightRoad[indexerBig] = new RoadSpawn(straight_xpos[indexerBig], straight_ypos[indexerBig],
					control.playerNo, 6);
			indexerBig++;
		}

		for (int i = 0; i < 4; i++) {
			diagonal_xpos[indexerSmall] = (int) (xOffset + screenWidth / 4 - xOffset + (xOffset * 2) * i);
			diagonal_ypos[indexerSmall] = longOffsetY * 4 + shortOffsetY * 8;
			DiagonalRoad[indexerSmall] = new RoadSpawn(diagonal_xpos[indexerSmall],
					diagonal_ypos[indexerSmall], control.playerNo, 4);
			indexerSmall++;
		}

		for (int i = 0; i < 4; i++) {
			diagonal_xpos[indexerSmall] = (int) (screenWidth / 4 - xOffset + (xOffset * 2) * i);
			diagonal_ypos[indexerSmall] = longOffsetY * 4 + shortOffsetY * 8;
			DiagonalRoad[indexerSmall] = new RoadSpawn(diagonal_xpos[indexerSmall],
					diagonal_ypos[indexerSmall], control.playerNo, 5);
			indexerSmall++;
		}

		for (int i = 0; i < 4; i++) {
			straight_xpos[indexerBig] = (int) (screenWidth / 4 - xOffset + (xOffset * 2) * i);
			straight_ypos[indexerBig] = longOffsetY * 4 + shortOffsetY * 9;
			StraightRoad[indexerBig] = new RoadSpawn(straight_xpos[indexerBig], straight_ypos[indexerBig],
					control.playerNo, 6);
			indexerBig++;
		}

		// Slope UP
		for (int i = 0; i < 3; i++) {
			diagonal_xpos[indexerSmall] = (int) (screenWidth / 4 + tileWidth * i);
			diagonal_ypos[indexerSmall] = longOffsetY * 5 + shortOffsetY * 9;
			DiagonalRoad[indexerSmall] = new RoadSpawn(diagonal_xpos[indexerSmall],
					diagonal_ypos[indexerSmall], control.playerNo, 5);
			indexerSmall++;
		}

		// Slope DOWN
		for (int i = 0; i < 3; i++) {
			diagonal_xpos[indexerSmall] = (int) (xOffset + screenWidth / 4 + tileWidth * i);
			diagonal_ypos[indexerSmall] = longOffsetY * 5 + shortOffsetY * 9;
			DiagonalRoad[indexerSmall] = new RoadSpawn(diagonal_xpos[indexerSmall],
					diagonal_ypos[indexerSmall], control.playerNo, 4);
			indexerSmall++;
		}
	}
}