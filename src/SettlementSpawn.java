import java.io.IOException;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/*	This class is used to spawn houses on places where houses may be spawned.
 *  It does so by checking if an area has been clicked, by setting booleans to true
 *  If a boolean is turned true, a house must be spawned at that point.
 */

public class SettlementSpawn {
	
	static int screenWidth = 900;
	static int screenHeight = 700;
	
	//Size of the house - used in the coordinates
	int houseSize = 25;
	
	Controller control; //need values and methods from this class
	OnScreenButton houseButton; //needed to get boolean and rendering of this button
	OnScreenButton cityButton; //needed to get the boolean and rendering of this button
	Game game;
	
	//Make an instance of the HouseSpawn class.
	Settlement[] settlement;
	Resource resource;
	
	//create boolean array to see if an area has been clicked.
	boolean[] areaClickedHouse;
	boolean[] areaClickedCity;
	
	//Used to fine tune the clickable area and placement of the houses.
	int houseXoffset = 9;
	int houseYoffset = 5;
	int cityXoffset = 20;
	int cityYoffset = 30;
	
	//Sets the size of the area one can click
	int areaClickSize = 18;
	
	//Used to determine the coordinates for each clicable point
	int tilewidth = 97;
	int longOffsetY = 56;
	int shortOffsetY = 28;
	
	//indexer to use as a counter
	int indexer;
	
	//Integers to determine the X rows
	int xRow1 = screenWidth/2-tilewidth;
	int xRow2 = screenWidth/2-(tilewidth+tilewidth/2);
	int xRow3 = screenWidth/2-tilewidth*2;
	int xRow4 = screenWidth/2-(tilewidth*2+tilewidth/2);
	
	//Integers to determine the Y rows. Simply to make copy paste easier. 
	int yRow1 = shortOffsetY*2+longOffsetY;
	int yRow2 = shortOffsetY*3+longOffsetY;
	int yRow3 = shortOffsetY*3+longOffsetY*2;
	int yRow4 = shortOffsetY*4+longOffsetY*2;
	int yRow5 = shortOffsetY*4+longOffsetY*3;
	int yRow6 = shortOffsetY*5+longOffsetY*3;
	int yRow7 = shortOffsetY*5+longOffsetY*4;
	int yRow8 = shortOffsetY*6+longOffsetY*4;
	int yRow9 = shortOffsetY*6+longOffsetY*5;
	int yRow10 = shortOffsetY*7+longOffsetY*5;
	int yRow11 = shortOffsetY*7+longOffsetY*6;
	int yRow12 = shortOffsetY*8+longOffsetY*6;
	
	//Arrays to store the coodinate information of X and Y.
	int[] arraycoordinateX;
	int[] arraycoordinateY;
	
	HexPiece[] hexTest = new HexPiece[19];
	
	//Integer which indicates the total amount of click-able areas of the houses. A total of 54 houses can be placed.
	int totalAreas = 54;
	

	//Constructor
	SettlementSpawn(Controller control) throws SlickException {
		
		this.control = control;
		
		settlement = new Settlement[totalAreas]; //create 54 instances of the HouseSpawn class
		areaClickedHouse = new boolean[totalAreas]; //create 54 array slots in the boolean array 
		areaClickedCity = new boolean[totalAreas];
		arraycoordinateX = new int[totalAreas]; //create 54 array slots for the X coodinates
		arraycoordinateY = new int[totalAreas]; //create 54 array slots for the Y coodinates
		indexer = 0; //count, set it to 0
		
		resource = new Resource(15,500,control.playerNo);
		
		//creates a new onScreenButton instance
		houseButton = new OnScreenButton(control);
		cityButton = new OnScreenButton(control);
		houseCoordinates(); //call the method giving the coodinates
	}
	
	//update method
	public void update(GameContainer gc, int i) throws SlickException, IOException {
		
		//System.out.println(control.numberTest[0]);
		for(int j = 0;  j < areaClickedHouse.length; j ++) {
			areaClickedHouse[j] = game.client.obj.SOChouseArea[j];
			areaClickedCity[j] = game.client.obj.SOCcityArea[j];
		}
		

		houseButton.update(gc, i); //keeps checking if the button has been clicked.
		cityButton.update(gc, i);
		
		int xMousePos = Mouse.getX(); //gets x position of mouse
		int yMousePos = Mouse.getY(); //gets y position of mouse
		
		Input input = gc.getInput(); //used to get mouse inputs
		
		
		//////////////HOUSE SETTLEMENT//////////////
		//For-loop to check if an area has been clicked, if it has and house button has been clicked, place a house.
		if (game.client.obj.playerTurn == control.playerNo) {
			if (houseButton.buttonHouseControl == true) {
				for (i = 0; i < totalAreas; i++) {
					if ((xMousePos > arraycoordinateX[i] - houseXoffset
							&& xMousePos < arraycoordinateX[i] + areaClickSize)
							&& (yMousePos < screenHeight - arraycoordinateY[i] + houseYoffset
							&& yMousePos > screenHeight - arraycoordinateY[i] - areaClickSize - houseYoffset)) {
						if (input.isMousePressed(0)) {
							houseButton.buttonHouseControl = false;
							control.deselectButtonControl = false;
							if (areaClickedHouse[i] != true) {
								placeHouse(i);
								game.client.obj.settlementPos[i][0] = settlement[i].xMiddle;
								game.client.obj.settlementPos[i][1] = settlement[i].yMiddle;
								game.client.obj.settlementPos[i][2] = control.playerNo - 1;
								game.client.obj.houseColour[i] = control.playerNo;
								game.client.obj.SOChouseArea[i] = areaClickedHouse[i];
								game.client.obj.areaClickedOwnership[i] = control.playerNo;
								game.client.sendData(game.client.obj);
							}
						}
					}
				}
			}
		}
		
			//////////////CITY SETTLEMENT//////////////
		if (game.client.obj.playerTurn == control.playerNo) {
			if (cityButton.buttonCityControl == true) {
			//For-loop to check if you own the house area and it has been clicked. If it has and city button has been clicked, place a city.
				for (i = 0; i < totalAreas; i++) {
					if ((xMousePos > arraycoordinateX[i] - houseXoffset
							&& xMousePos < arraycoordinateX[i] + areaClickSize)
							&& (yMousePos < screenHeight - arraycoordinateY[i] + houseYoffset
							&& yMousePos > screenHeight - arraycoordinateY[i] - areaClickSize - houseYoffset)) {
						if (input.isMouseButtonDown(0)) {
							cityButton.buttonCityControl = false;
							control.deselectButtonControl = false;
							if (game.client.obj.areaClickedOwnership[i] == control.playerNo) {
								placeCity(i);
								game.client.obj.cityColour[i] = control.playerNo;
								game.client.obj.SOCcityArea[i] = areaClickedCity[i];
								game.client.sendData(game.client.obj);
							}
						}
					}
				}
			}
		}
		
		for(int j = 0; j < settlement.length; j ++) {
			settlement[j].playerNo = game.client.obj.houseColour[j];
		}

	}
	
	//Render method
	public void render(GameContainer gc, Graphics g) throws SlickException
	{	
		if(houseButton.buttonHouseControl == true) {
            houseButton.buttonHousePressed.render(gc, g);
        }
		
		if(cityButton.buttonCityControl == true) {
			cityButton.buttonCityPressed.render(gc, g);
		}
		
		//For-loop used to render all the clicked areas. If an area is true, a house should be spawned.
		for(int i = 0; i < areaClickedHouse.length; i ++) {
			if(areaClickedHouse[i] == true) {
				settlement[i].render(gc, g); //renders the houses
			}
		}
		
		for(int i = 0; i < areaClickedCity.length; i ++) {
			if(areaClickedCity[i] == true) {
				settlement[i].houseType = 1;
			}
		}
	}
	
	
	public void placeHouse(int i) {

		if (checkHouseCost()) {
			game.client.obj.playerResource[control.playerNo-1][1]--; //WHEAT
			game.client.obj.playerResource[control.playerNo-1][2]--; //WOOD
			game.client.obj.playerResource[control.playerNo-1][3]--; //CLAY
			game.client.obj.playerResource[control.playerNo-1][4]--; //WOOL
			control.resources.victoryPoint++;
			control.resources.houseCount++;
			game.client.obj.playerVictoryPoints[control.playerNo-1][0] = control.resources.victoryPoint;
			areaClickedHouse[i] = true;
		}
	}
	
	
	public void placeCity(int i) {

		if (checkCityCost()) {
			game.client.obj.playerResource[control.playerNo-1][0] -=3;
			game.client.obj.playerResource[control.playerNo-1][1] -=2;
			
			control.resources.victoryPoint++;
			control.resources.houseCount++;
			game.client.obj.playerVictoryPoints[control.playerNo-1][0] = control.resources.victoryPoint;
			areaClickedCity[i] = true;
		}
	}

	public boolean checkHouseCost() {
		boolean isTrue = false;
		if (game.client.obj.playerResource[control.playerNo-1][1] >= 1 && game.client.obj.playerResource[control.playerNo-1][2] >= 1
				&& game.client.obj.playerResource[control.playerNo-1][3] >= 1 && game.client.obj.playerResource[control.playerNo-1][4] >= 1) {
			isTrue = true;
		}

		return isTrue;
	}
	
	public boolean checkCityCost() {
		boolean isTrue = false;
		if (game.client.obj.playerResource[control.playerNo-1][0] >= 3 && game.client.obj.playerResource[control.playerNo-1][1] >= 2) {
			isTrue = true;
		}
		return isTrue;
	}
	

	
	//method to get the coodinates of all the houses/areas
	//The method works by running through a serie of for-loops which stores the coordinates of each point
	//where a house can be placed. These are stored in the arraycoordinatesX/Y. 
	//The indexer keeps incrementing to make sure we start at the correct position in the arrays.
	public void houseCoordinates () throws SlickException {
		
		for(int i = 0; i< 3; i++){
			arraycoordinateX[indexer] = xRow1+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow1-houseSize/2;
			settlement[indexer] = new Settlement(arraycoordinateX[indexer]-houseXoffset, arraycoordinateY[indexer]-houseYoffset, control.playerNo,0); //the fineTune integers are used to more precisely place the houses
			indexer++;	
		}
		
		for(int i = 0; i< 4; i++){
			arraycoordinateX[indexer] = xRow2+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow2-houseSize/2;
			settlement[indexer] = new Settlement(arraycoordinateX[indexer]-houseXoffset, arraycoordinateY[indexer]-houseYoffset, control.playerNo,0); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<4; i++){
			arraycoordinateX[indexer] = xRow2+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow3-houseSize/2;
			settlement[indexer] = new Settlement(arraycoordinateX[indexer]-houseXoffset, arraycoordinateY[indexer]-houseYoffset, control.playerNo,0); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<5; i++) {
			arraycoordinateX[indexer] = xRow3+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow4-houseSize/2;
			settlement[indexer] = new Settlement(arraycoordinateX[indexer]-houseXoffset, arraycoordinateY[indexer]-houseYoffset, control.playerNo,0); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<5; i++) {
			arraycoordinateX[indexer] = xRow3+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow5-houseSize/2;
			settlement[indexer] = new Settlement(arraycoordinateX[indexer]-houseXoffset, arraycoordinateY[indexer]-houseYoffset, control.playerNo,0); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<6; i++){
			arraycoordinateX[indexer] = xRow4+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow6-houseSize/2;
			settlement[indexer] = new Settlement(arraycoordinateX[indexer]-houseXoffset, arraycoordinateY[indexer]-houseYoffset, control.playerNo,0); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<6; i++){
			arraycoordinateX[indexer] = xRow4+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow7-houseSize/2;
			settlement[indexer] = new Settlement(arraycoordinateX[indexer]-houseXoffset, arraycoordinateY[indexer]-houseYoffset, control.playerNo,0); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<5; i++) {
			arraycoordinateX[indexer] = xRow3+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow8-houseSize/2;
			settlement[indexer] = new Settlement(arraycoordinateX[indexer]-houseXoffset, arraycoordinateY[indexer]-houseYoffset, control.playerNo,0); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<5; i++) {
			arraycoordinateX[indexer] = xRow3+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow9-houseSize/2;
			settlement[indexer] = new Settlement(arraycoordinateX[indexer]-houseXoffset, arraycoordinateY[indexer]-houseYoffset, control.playerNo,0); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<4; i++){
			arraycoordinateX[indexer] = xRow2+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow10-houseSize/2;
			settlement[indexer] = new Settlement(arraycoordinateX[indexer]-houseXoffset, arraycoordinateY[indexer]-houseYoffset, control.playerNo,0); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<4; i++){
			arraycoordinateX[indexer] = xRow2+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow11-houseSize/2;
			settlement[indexer] = new Settlement(arraycoordinateX[indexer]-houseXoffset, arraycoordinateY[indexer]-houseYoffset, control.playerNo,0); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<3; i++){
			arraycoordinateX[indexer] = xRow1+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow12-houseSize/2;
			settlement[indexer] = new Settlement(arraycoordinateX[indexer]-houseXoffset, arraycoordinateY[indexer]-houseYoffset, control.playerNo,0); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
			
	}
}
