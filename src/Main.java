import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import com.google.gson.Gson;



public class Main extends BasicGame{ //Is not the actually main.
	int rolledNumber; //The number rolled by the dice
	static int longestRoad; //the index of the player in the player-ArraList with the longest road
	int mostKnights; //the index of the player in the player-ArraList with the most knights
	int thief; //the placement of the thief
	int ownIndex; //This particular clients index in the player-array
	ArrayList<Harbour> harbours; //ArrayList containing the Harbour-object that are also part of the game board
	ArrayList<Player> players; //ArrayList containing all the player-objects
	NetworkHelper networkHelper;
	boolean[] tradeRequest; //Do we need this?
	boolean hasRequestedTrade; //Do we need this?
	static boolean userInGame; 
	static float padding;
	static float scFactor;
	Position[] testPositions = new Position[5];
	
	
	
	private Image[] hexImg = new Image[6]; // Array for hexagon images
	private Image[] roadImg = new Image[4]; // Array for road images
	private Image[] numImg = new Image [11]; // Array for numbers
	static int scWidth = 1440; // Game screen width
	static int scHeight = 900; // Game screen height

	float diameter; // diameter for hexagon placement
	boolean hexReady = false; // NOT USED NOW, discuss! To draw hexagons once in rendered. If not used remove // NOT USED NOW, discuss!
	
	float xPos; // hexagon position X
	float yPos; // hexagon position Y
	float angle; // hexagon angle
	
	static float hexHeight; // to get height of hexagon
	static float hexWidth; // to get width of hexagon
	
	public Main(String gamename) { // inputs game name from slick2d
		super(gamename); // to get game name
		Hexagon.generateMap();
		/*Road.buildRoad(new Position(0,0), new Position(1,17), 0);
		Road.buildRoad(new Position(1,17), new Position(1,16), 0);
		Road.buildRoad(new Position(0,0), new Position(0,1), 0);
		Road.buildRoad(new Position(0,1), new Position(0,2), 0);
		Road.buildRoad(new Position(0,2), new Position(0,3), 0);*/
		
		//While-loop containing game flow
		while(userInGame){
			//check for user or server input
			//draw graphics
			//send commands to the server
		    //receive updates about the game from the server 
			//draw graphics
		}
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException { // to initialize elements
		Hexagon.generateMap();
		// to initialize multiple images
		for(int i=0; i<hexImg.length; i++){ //goes trough hexagon array
			hexImg[i] = new Image("resources/hexagon_" + (i) + ".png"); //Assigns every hexagon a name
		}
		for(int r=1; r<4; r++){
			roadImg[r] = new Image("resources/road_" + (r) + ".png"); //initializing road images
		}
		for (int n=2; n<=12; n++){
			numImg[n-2] = new Image("resources/numImg_" + (n) + ".png"); //initializing number images
		}

	
		scFactor = 0.3f; // Dynamic setup: scales images according this value 
		padding = hexImg[0].getWidth()/22*scFactor; // Dynamic setup: space between polygons
		hexWidth = hexImg[0].getWidth();
		hexHeight = hexImg[0].getHeight();
		
		// just for testing
		testPositions[0] = Position.assignPosition(0, 3);
		testPositions[1] = Position.assignPosition(1, 8);
		testPositions[2] = Position.assignPosition(2, 11);
		testPositions[3] = Position.assignPosition(2, 12);
		testPositions[4] = Position.assignPosition(2, 0);
		
		Road.buildRoad(Position.assignPosition(0, 1), Position.assignPosition(0, 2), 0);
		Road.buildRoad(Position.assignPosition(0, 2), Position.assignPosition(0, 3), 0);
		Road.buildRoad(Position.assignPosition(0, 3), Position.assignPosition(1, 8), 0);
		Road.buildRoad(Position.assignPosition(1, 8), Position.assignPosition(1, 9), 0);
		Road.buildRoad(Position.assignPosition(1, 8), Position.assignPosition(1, 7), 0);
		Road.buildRoad(Position.assignPosition(1, 7), Position.assignPosition(1, 6), 0);
		Road.longestRoad();
	}
	
	@Override
	public void update(GameContainer gc, int i) throws SlickException {	
	}
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		Color bkColor = Color.decode("#638fde"); // create custom color
		g.setBackground(bkColor); // set background color
		
		// move out, since used only once in setup
		/*float scFactor = 0.4f; // Dynamic setup: scales images according this value 
		float padding = hexImg[0].getWidth()/22*scFactor; // Dynamic setup: space between polygons
		diameter = (hexImg[0].getWidth()+ padding)*scFactor; // Dynamic setup: diameter according image width, + padding for space in between*/
		//end. move out, since used only once in setup
		
		drawHexagons(g);
		
		//g.drawString("Test", 100, 100);
		
		// testing road methods - feel free to rewrite this part as it was just testing the methods i made for the roads :)
		for (int i = 0; i < Road.getRoads().size(); i++) {
			float x = Road.getRoads().get(i).getCenterX();
			float y = Road.getRoads().get(i).getCenterY();
			
			// i am not sure how this is going to be done, just wanted to prototype it though, and it works but is a bit ugly code
			g.pushTransform();
				g.rotate(x + scWidth/2, y + scHeight/2, Road.getRoads().get(i).getAngle());
				roadImg[1].draw(x + scWidth/2 - roadImg[1].getWidth()/2*scFactor, y + scHeight/2  - roadImg[1].getHeight()/2*scFactor, scFactor);
			g.popTransform();
		}
		
		// just testing the position XY methods
		for (int i = 0; i < testPositions.length; i++) {
			g.drawString("Pos: " + i, testPositions[i].getX() + scWidth/2, testPositions[i].getY() + scHeight/2);
		}

	}
	
	// Deprecated
	/*private float angle(int hexIndex){
		if (hexIndex <=6){
			// for inner
			angle  = hexIndex * 1.0472f; // 1.05f = 360/6 is specific angle for all polygons up to 7th.
		}else
			// for outer
			angle  = hexIndex * 0.523599f; // 0.523599f = 360/12 for hexagons from 7th
		return angle;
	}*/
	
	//Methods for initial phase
	
	/**
	 * TODO
	 */
	private void drawHexagons(Graphics g) {
		//diameter = (hexImg[0].getWidth()+ padding)*scFactor; // Dynamic setup: diameter according image width, + padding for space in between
		Hexagon[] hexagons = Hexagon.getHexagons();
		for (int i = 0; i < hexagons.length; i++){
			xPos = hexagons[i].getX();
			yPos = hexagons[i].getY();
			
			// could maybe also store the center in a variable so we do not have to calculate for each frame
			hexImg[hexagons[i].TYPE.toInt()].draw(xPos + scWidth/2-hexImg[0].getWidth()/2*scFactor, yPos + scHeight/2-hexImg[0].getHeight()/2*scFactor, scFactor);
			numImg[hexagons[i].NUMBER-2].draw(xPos + scWidth/2-numImg[2].getWidth()/2*scFactor, yPos + scHeight/2-numImg[2].getHeight()/2*scFactor, scFactor);
			
			// old code - moved to the Hexagon.getX() and Hexagon.getY() methods.
			/*
			if(i == 0){			// center hexagon
				hexImg[Hexagon.getHexagons()[i].getType().toInt()].draw(scWidth/2-hexImg[0].getWidth()/2*scFactor, scHeight/2-hexImg[0].getHeight()/2*scFactor, scFactor); // draw center hexagon
				//g.drawString(Integer.toString(Hexagon.getHexagons()[i].getNumber()), scWidth/2, scHeight/2);
				numImg[Hexagon.getHexagons()[i].getNumber()-2].draw(scWidth/2-numImg[2].getWidth()/2*scFactor,scHeight/2-numImg[2].getHeight()/2*scFactor, scFactor);
			} else if (i < 7){ 	//middle circle of hexagons
				xPos = Hexagon.getHexagons()[i].getX();
				yPos = Hexagon.getHexagons()[i].getY();
				hexImg[Hexagon.getHexagons()[i].getType().toInt()].draw(xPos+scWidth/2-hexImg[0].getWidth()/2*scFactor, yPos+scHeight/2-hexImg[0].getHeight()/2*scFactor, scFactor);
				numImg[Hexagon.getHexagons()[i].getNumber()-2].draw(xPos + scWidth/2-numImg[2].getWidth()/2*scFactor,yPos + scHeight/2-numImg[2].getHeight()/2*scFactor, scFactor);
			} else {			//outer circle of hexagons
				/*if(i%2 == 0){ 	// maybe move to a different variable
					diameter = ( ((hexImg[0].getWidth()*2)+padding/2+padding) - hexImg[0].getWidth()/3.85f )*scFactor; // 3.85 CHECK. Dynamic setup : diameter for outer polygons according image width. 3.8 is ~third of image that gets inside the cricle
				}else{
					diameter = ( ((hexImg[0].getWidth()*2)+padding*2))*scFactor; // Dynamic setup : diameter for outer polygons according image width
				}*//*
				
				xPos = Hexagon.getHexagons()[i].getX() * diameter;
				yPos = Hexagon.getHexagons()[i].getY() * diameter;	      
				hexImg[Hexagon.getHexagons()[i].getType().toInt()].draw(xPos+scWidth/2-hexImg[0].getWidth()/2*scFactor, yPos+scHeight/2-hexImg[0].getHeight()/2*scFactor, scFactor);
				numImg[Hexagon.getHexagons()[i].getNumber()-2].draw(xPos+scWidth/2-numImg[2].getWidth()/2*scFactor, yPos+scHeight/2-numImg[2].getHeight()/2*scFactor, scFactor);
				
			}*/
		}
	}
	
	static void placeBuilding(){
		//Method to place buildings at game start
	}
	
	static void placeRoad(){
		//Method to place roads at game start
	}

	//Methods for trade-phase
	
	static void initiateTrade(){
		//Method to use when the player wants to trade with other players
	}
	
	static void getTradeResponse(TradeObject tradeObject){
		//Method called when other users wants to trade resources
	}

	
	// Methods for building-phase
	
	static void BuyCard(){
		//Method used to notify server that user wants to buy a development card
	}
	
	static void buyRoad(){
		//Method used to notify server that user wants to buy a road
	}
	
	static void buyCity(){
		//Method used to notify server that user wants to buy a city
	}
	
	static void upgradeCity(){
		//Method used to notify server that user wants to upgrade a city
	}
	
	static void useDevelopment(){
		//Method used to notify server that user wants to use a developement card
	}

	static void rollDice(){
		//Method used to notify server that user wants to roll the dice
	}
	
	public static void start() {
		//Initial phase - only done once at game start
		
		try
		{
			AppGameContainer game = new AppGameContainer(new Main("Settlers"));
			game.setTargetFrameRate(24); // framerate
			game.setMaximumLogicUpdateInterval(24); // maximum framerate
			game.setVSync(true); // Vertical sync
			game.setDisplayMode(scWidth,scHeight, false); // sets screen size, false or true for full screen
			game.start(); // to start Slick 2D
		}
		catch (SlickException ex)
		{
			//Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
