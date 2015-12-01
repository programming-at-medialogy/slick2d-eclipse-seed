
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

	static int screenWidth = 900;
	static int screenHeight = 700;

	int totalRoads = 72; // int to set the total amount of roads which can be placed (the actual number is lower than 100)

	OnScreenButton roadButton; // instance of the OnScreenButton class. Needed to render and check if the button has been clicked
	Controller control; // need values from the Controller class

	// Values which defines the size of the area able to be clicked for all of
	// the non-vertical roads.
	int areaClickSizeXsmall = 48;
	int areaClickSizeYsmall = 28;

	// Values to define the size of the larger areas for all the vertical roads.
	int areaClickSizeXbig = 97;
	int areaClickSizeYbig = 56;

	// Boolean arrays to check if an area have been clicked.
	boolean[] areaClickSmall;
	boolean[] areaClickBig;

	// Int arrays to store the coordinates of all the areas where a road can be spawned
	int[] arraycoordinateXsmall;
	int[] arraycoordinateYsmall;

	// Same, just for the large roads (vertical roads)
	int[] arraycoordinateXbig;
	int[] arraycoordinateYbig;

	// Make an instance of the RoadSpawn class. Used to render images
	RoadSpawn[] roadV1;
	RoadSpawn[] roadV2;

	// Constructor
	RoadClickArea() throws SlickException {

		roadButton = new OnScreenButton(); // create the button
		control = new Controller(); // create the controller

		roadV1 = new RoadSpawn[totalRoads]; // create 72 instances of the HouseSpawn class
		roadV2 = new RoadSpawn[totalRoads]; // create 72 instances of the HouseSpawn class

		// make the arrays totalRoads long (72)
		arraycoordinateXsmall = new int[totalRoads];
		arraycoordinateYsmall = new int[totalRoads];

		arraycoordinateXbig = new int[totalRoads];
		arraycoordinateYbig = new int[totalRoads];

		areaClickSmall = new boolean[totalRoads];
		areaClickBig = new boolean[totalRoads];

		placeRoad();

	}

	// Render method
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// Renders all the non-vertical roads
		for (int i = 0; i < areaClickSmall.length; i++) {
			if (areaClickSmall[i] == true) {
				roadV1[i].render(gc, g);
			}
		}

		// Renders all the vertical roads
		for (int i = 0; i < areaClickBig.length; i++) {
			if (areaClickBig[i] == true) {
				roadV2[i].render(gc, g);
			}
		}
	}

	// update method
	public void update(GameContainer gc, int i) throws SlickException {

		// Class the update method from the OnScreenButton class.
		roadButton.update(gc, i);

		int xMousePos = Mouse.getX(); // gets x position of mouse
		int yMousePos = Mouse.getY(); // gets y position of mouse

		Input input = gc.getInput();

		// Controls different parameters to see, if one is allowed to place a
		// road
		if (control.isPlayerTurn == true // player turn?
				&& control.placeRoadAmount != 0 // does the player have any roads available?
				&& roadButton.buttonRoadControl == true) { // has the GUI button been pressed?
			for (i = 0; i < arraycoordinateXsmall.length; i++) {
				if ((xMousePos > arraycoordinateXsmall[i] && xMousePos < arraycoordinateXsmall[i] + areaClickSizeXsmall)
						&& (yMousePos < screenHeight - arraycoordinateYsmall[i]
								&& yMousePos > screenHeight - arraycoordinateYsmall[i] - areaClickSizeYsmall)) {// checks
																												// the
																												// coordinates
					if (input.isMouseButtonDown(0)) { // has the mouse been clicked?
						areaClickSmall[i] = true; // the small area has been clicked; spawn a road.
						roadButton.buttonRoadControl = false; // toggles the button false
						
					}
				}
			}
		}

		// Does the same as above.
		if (control.isPlayerTurn == true && control.placeRoadAmount != 0) {
			if (roadButton.buttonRoadControl == true) {
				for (i = 0; i < arraycoordinateXbig.length; i++) {
					if ((xMousePos > arraycoordinateXbig[i] && xMousePos < arraycoordinateXbig[i] + areaClickSizeXbig)
							&& (yMousePos < screenHeight - arraycoordinateYbig[i]
									&& yMousePos > screenHeight - arraycoordinateYbig[i] - areaClickSizeYbig)) {
						if (input.isMouseButtonDown(0)) {
							areaClickBig[i] = true; // if the area is clicked, that area's boolean must become true.
							roadButton.buttonRoadControl = false;
						}
					}
				}
			}
		}
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
			arraycoordinateXsmall[indexerSmall] = (int) (screenWidth / 3 + tileWidth * i);
			arraycoordinateYsmall[indexerSmall] = longOffsetY * 2;
			roadV1[indexerSmall] = new RoadSpawn(arraycoordinateXsmall[indexerSmall],
					arraycoordinateYsmall[indexerSmall], control.playerNo, 1);
			indexerSmall++;
		}

		for (int i = 0; i < 3; i++) {
			arraycoordinateXsmall[indexerSmall] = (int) (xOffset + screenWidth / 3 + tileWidth * i);
			arraycoordinateYsmall[indexerSmall] = longOffsetY * 2;
			roadV1[indexerSmall] = new RoadSpawn(arraycoordinateXsmall[indexerSmall],
					arraycoordinateYsmall[indexerSmall], control.playerNo, 2);
			indexerSmall++;
		}

		for (int i = 0; i < 4; i++) {
			arraycoordinateXbig[indexerBig] = (int) (screenWidth / 3 - xOffset + (xOffset * 2) * i);
			arraycoordinateYbig[indexerBig] = longOffsetY * 2 + shortOffsetY;
			roadV2[indexerBig] = new RoadSpawn(arraycoordinateXbig[indexerBig], arraycoordinateYbig[indexerBig],
					control.playerNo, 6);
			indexerBig++;
		}

		for (int i = 0; i < 4; i++) {
			arraycoordinateXsmall[indexerSmall] = (int) (screenWidth / 3 - xOffset + (xOffset * 2) * i);
			arraycoordinateYsmall[indexerSmall] = longOffsetY * 2 + shortOffsetY * 3;
			roadV1[indexerSmall] = new RoadSpawn(arraycoordinateXsmall[indexerSmall],
					arraycoordinateYsmall[indexerSmall], control.playerNo, 1);
			indexerSmall++;
		}

		for (int i = 0; i < 4; i++) {
			arraycoordinateXsmall[indexerSmall] = (int) (xOffset + screenWidth / 3 - xOffset + (xOffset * 2) * i);
			arraycoordinateYsmall[indexerSmall] = longOffsetY * 2 + shortOffsetY * 3;
			roadV1[indexerSmall] = new RoadSpawn(arraycoordinateXsmall[indexerSmall],
					arraycoordinateYsmall[indexerSmall], control.playerNo, 2);
			indexerSmall++;
		}

		for (int i = 0; i < 5; i++) {
			arraycoordinateXbig[indexerBig] = (int) (screenWidth / 3 - xOffset * 2 + (xOffset * 2) * i);
			arraycoordinateYbig[indexerBig] = longOffsetY * 2 + shortOffsetY * 4;
			roadV2[indexerBig] = new RoadSpawn(arraycoordinateXbig[indexerBig], arraycoordinateYbig[indexerBig],
					control.playerNo, 6);
			indexerBig++;
		}

		for (int i = 0; i < 5; i++) {
			arraycoordinateXsmall[indexerSmall] = (int) (screenWidth / 3 - xOffset * 2 + (xOffset * 2) * i);
			arraycoordinateYsmall[indexerSmall] = longOffsetY * 2 + shortOffsetY * 6;
			roadV1[indexerSmall] = new RoadSpawn(arraycoordinateXsmall[indexerSmall],
					arraycoordinateYsmall[indexerSmall], control.playerNo, 1);
			indexerSmall++;
		}

		for (int i = 0; i < 5; i++) {
			arraycoordinateXsmall[indexerSmall] = (int) (xOffset + screenWidth / 3 - xOffset * 2 + (xOffset * 2) * i);
			arraycoordinateYsmall[indexerSmall] = longOffsetY * 2 + shortOffsetY * 6;
			roadV1[indexerSmall] = new RoadSpawn(arraycoordinateXsmall[indexerSmall],
					arraycoordinateYsmall[indexerSmall], control.playerNo, 2);
			indexerSmall++;
		}

		for (int i = 0; i < 6; i++) {
			arraycoordinateXbig[indexerBig] = (int) (screenWidth / 3 - xOffset * 3 + (xOffset * 2) * i);
			arraycoordinateYbig[indexerBig] = longOffsetY * 3 + shortOffsetY * 5;
			roadV2[indexerBig] = new RoadSpawn(arraycoordinateXbig[indexerBig], arraycoordinateYbig[indexerBig],
					control.playerNo, 6);
			indexerBig++;
		}

		for (int i = 0; i < 5; i++) {
			arraycoordinateXsmall[indexerSmall] = (int) (screenWidth / 3 - xOffset * 2 + (xOffset * 2) * i);
			arraycoordinateYsmall[indexerSmall] = longOffsetY * 3 + shortOffsetY * 7;
			roadV1[indexerSmall] = new RoadSpawn(arraycoordinateXsmall[indexerSmall],
					arraycoordinateYsmall[indexerSmall], control.playerNo, 5);
			indexerSmall++;
		}

		for (int i = 0; i < 5; i++) {
			arraycoordinateXsmall[indexerSmall] = (int) (xOffset + screenWidth / 3 - xOffset * 2 + (xOffset * 2) * i);
			arraycoordinateYsmall[indexerSmall] = longOffsetY * 3 + shortOffsetY * 7;
			roadV1[indexerSmall] = new RoadSpawn(arraycoordinateXsmall[indexerSmall],
					arraycoordinateYsmall[indexerSmall], control.playerNo, 4);
			indexerSmall++;
		}

		for (int i = 0; i < 5; i++) {
			arraycoordinateXbig[indexerBig] = (int) (screenWidth / 3 - xOffset * 2 + (xOffset * 2) * i);
			arraycoordinateYbig[indexerBig] = longOffsetY * 3 + shortOffsetY * 8;
			roadV2[indexerBig] = new RoadSpawn(arraycoordinateXbig[indexerBig], arraycoordinateYbig[indexerBig],
					control.playerNo, 6);
			indexerBig++;
		}

		for (int i = 0; i < 4; i++) {
			arraycoordinateXsmall[indexerSmall] = (int) (xOffset + screenWidth / 3 - xOffset + (xOffset * 2) * i);
			arraycoordinateYsmall[indexerSmall] = longOffsetY * 4 + shortOffsetY * 8;
			roadV1[indexerSmall] = new RoadSpawn(arraycoordinateXsmall[indexerSmall],
					arraycoordinateYsmall[indexerSmall], control.playerNo, 4);
			indexerSmall++;
		}

		for (int i = 0; i < 4; i++) {
			arraycoordinateXsmall[indexerSmall] = (int) (screenWidth / 3 - xOffset + (xOffset * 2) * i);
			arraycoordinateYsmall[indexerSmall] = longOffsetY * 4 + shortOffsetY * 8;
			roadV1[indexerSmall] = new RoadSpawn(arraycoordinateXsmall[indexerSmall],
					arraycoordinateYsmall[indexerSmall], control.playerNo, 5);
			indexerSmall++;
		}

		for (int i = 0; i < 4; i++) {
			arraycoordinateXbig[indexerBig] = (int) (screenWidth / 3 - xOffset + (xOffset * 2) * i);
			arraycoordinateYbig[indexerBig] = longOffsetY * 4 + shortOffsetY * 9;
			roadV2[indexerBig] = new RoadSpawn(arraycoordinateXbig[indexerBig], arraycoordinateYbig[indexerBig],
					control.playerNo, 6);
			indexerBig++;
		}

		// Slope UP
		for (int i = 0; i < 3; i++) {
			arraycoordinateXsmall[indexerSmall] = (int) (screenWidth / 3 + tileWidth * i);
			arraycoordinateYsmall[indexerSmall] = longOffsetY * 5 + shortOffsetY * 9;
			roadV1[indexerSmall] = new RoadSpawn(arraycoordinateXsmall[indexerSmall],
					arraycoordinateYsmall[indexerSmall], control.playerNo, 5);
			indexerSmall++;
		}

		// Slope DOWN
		for (int i = 0; i < 3; i++) {
			arraycoordinateXsmall[indexerSmall] = (int) (xOffset + screenWidth / 3 + tileWidth * i);
			arraycoordinateYsmall[indexerSmall] = longOffsetY * 5 + shortOffsetY * 9;
			roadV1[indexerSmall] = new RoadSpawn(arraycoordinateXsmall[indexerSmall],
					arraycoordinateYsmall[indexerSmall], control.playerNo, 4);
			indexerSmall++;
		}

	}

}
