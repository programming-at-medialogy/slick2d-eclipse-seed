
<<<<<<< HEAD
=======


import java.io.IOException;
import java.util.Random;

>>>>>>> 49235240a6352bad9a3010c5ae679ca1e8d8f2b2
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/*	This class is used to spawn houses on places where houses may be spawned.
 *  It does so by checking if an area has been clicked, by setting booleans to true
 *  If a boolean is turned true, a house must be spawned at that point.
 */

public class HouseClickArea {
	
	static int screenWidth = 900;
	static int screenHeight = 700;
	
	//Size of the house - used in the coordinates
	int houseSize = 25;
	
	Game game;
	Controller control; //need values and methods from this class
	OnScreenButton houseButtom; //needed to get boolean and rendering of this button
	Game game;
	
	//Default string to the mouse. If no input from the mouse is recieved
	public String mouse = "No Input Mouse";
	
	//Make an instance of the HouseSpawn class.
	HouseSpawn[] house;
	HouseSpawn[] houseTest;
	
	//create boolean array to see if an area has been clicked.
	boolean[] areaClicked;
	
	//Used to fine tune the clickable area and placement of the houses.
	int fineTuneX = 9;
	int fineTuneY = 5;
	
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
	
	//Integer which indicates the total amount of click-able areas of the houses. A total of 54 houses can be placed.
	int totalAreas = 54;
	

	//Constructor
	HouseClickArea() throws SlickException {
		
		house = new HouseSpawn[totalAreas]; //create 54 instances of the HouseSpawn class
		houseTest = new HouseSpawn [totalAreas];
		areaClicked = new boolean[totalAreas]; //create 54 array slots in the boolean array 
		arraycoordinateX = new int[totalAreas]; //create 54 array slots for the X coodinates
		arraycoordinateY = new int[totalAreas]; //create 54 array slots for the Y coodinates
		indexer = 0; //count, set it to 0
		
		//creates a new onScreenButton instance
		houseButtom = new OnScreenButton();
		
		control = new Controller(); //set player number
		
		houseCoordinates(); //call the method giving the coodinates
	}
	
	//update method
	public void update(GameContainer gc, int i) throws SlickException, IOException {
		
		
		for(int j = 0;  j < areaClicked.length; j ++) {
			areaClicked[j] = game.client.obj.SOChouseArea[j];
		}
<<<<<<< HEAD
	
	}
=======
		
		

>>>>>>> 49235240a6352bad9a3010c5ae679ca1e8d8f2b2

		
		houseButtom.update(gc, i); //keeps checking if the button has been clicked.
		
		int xMousePos = Mouse.getX(); //gets x position of mouse
		int yMousePos = Mouse.getY(); //gets y position of mouse
		
		mouse = "Mouse X: "+xMousePos+" - Mouse Y: "+yMousePos; //stores it in the string and used in render
		
		Input input = gc.getInput(); //used to get mouse inputs

		
		//For-loop to check if an area has been clicked.
		if(control.isPlayerTurn == true && control.placeHouseAmount != 0) { //if it is the players turn and if the player still have houses to place
		for(i = 0; i< totalAreas; i ++) {
			
		if((xMousePos > arraycoordinateX[i]-fineTuneX && xMousePos < arraycoordinateX[i]+areaClickSize) && (yMousePos < screenHeight-arraycoordinateY[i]+fineTuneY && yMousePos > screenHeight-arraycoordinateY[i]-areaClickSize-fineTuneY)) {
			if(input.isMouseButtonDown(0)) {
				if(houseButtom.buttonHouseControl == true) { //has the house button been pressed?
<<<<<<< HEAD
				areaClicked[i] = true; //if the area is clicked, that area's boolean must become true.

=======
				if(areaClicked[i] != true){
				areaClicked[i] = true;
				game.client.obj.houseColour[i] = control.playerNo;
				game.client.obj.SOChouseArea[i] = areaClicked[i];
				game.client.sendData(game.client.obj);
>>>>>>> 49235240a6352bad9a3010c5ae679ca1e8d8f2b2
				houseButtom.buttonHouseControl = false; //toggle the house button false
						}
					}
				}
				}
			}
		}
	}
<<<<<<< HEAD
=======
		
		for(int j = 0; j < house.length; j ++) {
			house[j].playerNo = game.client.obj.houseColour[j];
		}
	}
>>>>>>> 49235240a6352bad9a3010c5ae679ca1e8d8f2b2
	
	//Render method
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
		//For-loop used to render all the clicked areas. If an area is true, a house should be spawned.
		for(int i = 0; i < areaClicked.length; i ++) {
			if(areaClicked[i] == true) {
				house[i].render(gc, g); //renders the houses
			}
		}
		
		g.drawString(mouse, 10, screenHeight-houseSize); //Rendered string to display x and y of mouse
	
	}


	
	//method to get the coodinates of all the houses/areas
	//The method works by running through a serie of for-loops which stores the coordinates of each point
	//where a house can be placed. These are stored in the arraycoordinatesX/Y. 
	//The indexer keeps incrementing to make sure we start at the correct position in the arrays.
	public void houseCoordinates () throws SlickException {
		
		for(int i = 0; i< 3; i++){
			arraycoordinateX[indexer] = xRow1+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow1-houseSize/2;
			house[indexer] = new HouseSpawn(arraycoordinateX[indexer]-fineTuneX, arraycoordinateY[indexer]-fineTuneY, control.playerNo); //the fineTune integers are used to more precisely place the houses
			indexer++;	
		}
		
		for(int i = 0; i< 4; i++){
			arraycoordinateX[indexer] = xRow2+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow2-houseSize/2;
			house[indexer] = new HouseSpawn(arraycoordinateX[indexer]-fineTuneX, arraycoordinateY[indexer]-fineTuneY, control.playerNo); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<4; i++){
			arraycoordinateX[indexer] = xRow2+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow3-houseSize/2;
			house[indexer] = new HouseSpawn(arraycoordinateX[indexer]-fineTuneX, arraycoordinateY[indexer]-fineTuneY, control.playerNo); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<5; i++) {
			arraycoordinateX[indexer] = xRow3+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow4-houseSize/2;
			house[indexer] = new HouseSpawn(arraycoordinateX[indexer]-fineTuneX, arraycoordinateY[indexer]-fineTuneY, control.playerNo); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<5; i++) {
			arraycoordinateX[indexer] = xRow3+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow5-houseSize/2;
			house[indexer] = new HouseSpawn(arraycoordinateX[indexer]-fineTuneX, arraycoordinateY[indexer]-fineTuneY, control.playerNo); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<6; i++){
			arraycoordinateX[indexer] = xRow4+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow6-houseSize/2;
			house[indexer] = new HouseSpawn(arraycoordinateX[indexer]-fineTuneX, arraycoordinateY[indexer]-fineTuneY, control.playerNo); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<6; i++){
			arraycoordinateX[indexer] = xRow4+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow7-houseSize/2;
			house[indexer] = new HouseSpawn(arraycoordinateX[indexer]-fineTuneX, arraycoordinateY[indexer]-fineTuneY, control.playerNo); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<5; i++) {
			arraycoordinateX[indexer] = xRow3+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow8-houseSize/2;
			house[indexer] = new HouseSpawn(arraycoordinateX[indexer]-fineTuneX, arraycoordinateY[indexer]-fineTuneY, control.playerNo); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<5; i++) {
			arraycoordinateX[indexer] = xRow3+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow9-houseSize/2;
			house[indexer] = new HouseSpawn(arraycoordinateX[indexer]-fineTuneX, arraycoordinateY[indexer]-fineTuneY, control.playerNo); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<4; i++){
			arraycoordinateX[indexer] = xRow2+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow10-houseSize/2;
			house[indexer] = new HouseSpawn(arraycoordinateX[indexer]-fineTuneX, arraycoordinateY[indexer]-fineTuneY, control.playerNo); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<4; i++){
			arraycoordinateX[indexer] = xRow2+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow11-houseSize/2;
			house[indexer] = new HouseSpawn(arraycoordinateX[indexer]-fineTuneX, arraycoordinateY[indexer]-fineTuneY, control.playerNo); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
		
		for(int i = 0; i<3; i++){
			arraycoordinateX[indexer] = xRow1+tilewidth*i-(houseSize/2);
			arraycoordinateY[indexer] = yRow12-houseSize/2;
			house[indexer] = new HouseSpawn(arraycoordinateX[indexer]-fineTuneX, arraycoordinateY[indexer]-fineTuneY, control.playerNo); //the fineTune integers are used to more precisely place the houses
			indexer++;
		}
	}
}
