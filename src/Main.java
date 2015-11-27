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
	boolean[] tradeRequest; //Do we need this?
	boolean hasRequestedTrade; //Do we need this?
	static boolean userInGame; 
	static float padding;
	static float scFactor;
	Position[] testPositions = new Position[5];
	Building[] testBuildings = new Building[5];
	
	private Image[] hexImg = new Image[6]; // Array for hexagon images
	private Image[] roadImg = new Image[4]; // Array for road images
	private Image[] numImg = new Image [11]; // Array for numbers

	private Image[] crdImg = new Image [Player.resources.length]; // Array for resource cards

	private Image[] buildImg = new Image[4]; // Array for level 1 building images
	private Image[] cityImg = new Image[4]; // Array for level 2 building images

	private Image robImg;
	private Image bkWater;
	static int scWidth = 1440; // Game screen width
	static int scHeight = 900; // Game screen height

	float diameter; // diameter for hexagon placement
	
	float xPos; // hexagon position X
	float yPos; // hexagon position Y
	float angle; // hexagon angle
	float crdPosX; // card position x
	float crdPosY; // card position y
	float buildPosX; // Building position x
	float buildPosY; // Building position y
	
	static float hexHeight; // to get height of hexagon
	static float hexWidth; // to get width of hexagon
	static float crdHeight;
	static float crdWidth;
	
	public Main(String gamename) { // inputs game name from slick2d
		super(gamename); // to get game name
		Hexagon.generateMap();
		Actions.initActions();
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
		for (int c=0; c<5; c++){
			crdImg[c] = new Image ("resources/r_card_"+(c)+".jpg");
		}
		for(int b = 0; b < cityImg.length; b++) {
			
			buildImg[b] = new Image("resources/buildImg" + b + ".png"); //Initializing level 1 building images
			//cityImg[b] = new Image("resources/citeImg" + b + ".png");
			
		}
		
		robImg = new Image("resources/robber.png");

		bkWater = new Image("resources/bkWater.png");
	
		GameData.roads = new ArrayList<Road>();
		
		scFactor = 0.3f; // Dynamic setup: scales images according this value 
		padding = hexImg[0].getWidth()/22*scFactor; // Dynamic setup: space between polygons
		hexWidth = hexImg[0].getWidth();
		hexHeight = hexImg[0].getHeight();
		crdHeight = crdImg[0].getHeight();
		crdWidth = crdImg[0].getWidth();
		
		// just for testing
		testPositions[0] = Position.assignPosition(0, 3);
		testPositions[1] = Position.assignPosition(1, 8);
		testPositions[2] = Position.assignPosition(2, 11);
		testPositions[3] = Position.assignPosition(2, 12);
		testPositions[4] = Position.assignPosition(2, 0);
		
		/*
		for(int i = 0; i < testBuildings.length; i++) {
			
			testBuildings[i] = Building.build(testPositions[i], 1);
			
		}*/
		
		Road.buildRoad(Position.assignPosition(0, 1), Position.assignPosition(0, 2), 0);
		Road.buildRoad(Position.assignPosition(0, 2), Position.assignPosition(0, 3), 0);
		Road.buildRoad(Position.assignPosition(0, 3), Position.assignPosition(1, 8), 0);
		Road.buildRoad(Position.assignPosition(1, 8), Position.assignPosition(1, 9), 0);
		Road.buildRoad(Position.assignPosition(1, 8), Position.assignPosition(1, 7), 0);
		Road.buildRoad(Position.assignPosition(1, 7), Position.assignPosition(1, 6), 0);
		Road.longestRoad();
		
		GameData.buildings = new ArrayList();
		Building.build(Position.assignPosition(0, 0), 0);
		Building.build(Position.assignPosition(2, 10), 1);
	}
	
	@Override
	public void update(GameContainer gc, int i) throws SlickException {	
	}
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		Color bkColor = Color.decode("#5e8ad7"); // create custom color
		g.setBackground(bkColor); // set background color
		
		// move out, since used only once in setup
		/*float scFactor = 0.4f; // Dynamic setup: scales images according this value 
		float padding = hexImg[0].getWidth()/22*scFactor; // Dynamic setup: space between polygons
		diameter = (hexImg[0].getWidth()+ padding)*scFactor; // Dynamic setup: diameter according image width, + padding for space in between*/
		//end. move out, since used only once in setup
		
		drawHexagons(g);
		drawRobber(g);
		drawBuilding(g);
	
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

	//Methods for initial phase
	
	/**
	 * TODO
	 */
	private void drawHexagons(Graphics g) {
		bkWater.draw(scWidth/2-bkWater.getWidth()/2*scFactor, (scHeight/2-bkWater.getHeight()/2*scFactor)-hexHeight*scFactor/1.5f, scFactor );
		//diameter = (hexImg[0].getWidth()+ padding)*scFactor; // Dynamic setup: diameter according image width, + padding for space in between
		Hexagon[] hexagons = Hexagon.getHexagons();
		for (int i = 0; i < hexagons.length; i++){
			xPos = hexagons[i].getX();
			yPos = hexagons[i].getY();
			if(i<19){
			// could maybe also store the center in a variable so we do not have to calculate for each frame
				hexImg[hexagons[i].TYPE.toInt()].draw(xPos + scWidth/2-hexImg[0].getWidth()/2*scFactor, yPos + scHeight/2-hexImg[0].getHeight()/2*scFactor, scFactor);
				numImg[hexagons[i].NUMBER-2].draw(xPos + scWidth/2-numImg[2].getWidth()/2*scFactor, yPos + scHeight/2-numImg[2].getHeight()/2*scFactor, scFactor);
			}else
				hexImg[6].draw(xPos + scWidth/2-hexImg[0].getWidth()/2*scFactor, yPos + scHeight/2-hexImg[0].getHeight()/2*scFactor, scFactor);
		}
		for (int c = 0; c<Player.resources.length;  c++){ // to display resource cards/ number 5 has to be replaced with number of cards player has 
			crdImg[c].draw(crdPosX+(scWidth/2-crdWidth/2*scFactor), crdPosY+(scHeight-crdHeight*scFactor)-crdHeight/100, scFactor);
			crdPosX = cardPosition(c, Player.resources.length); // 5 is to change - amount of resource cards
		}

	}
	
	// Method to get resource cards positions
	float cardPosition(int cardIndex, int cardAmount){
		crdPosX = cardIndex*(crdWidth/2*scFactor)-cardAmount/2*(crdWidth/2*scFactor);
		return crdPosX;
	}
	
	void drawRobber(Graphics g) {
		Hexagon[] hexagons = Hexagon.getHexagons();
		for (int i = 0; i < hexagons.length; i++){
			xPos = hexagons[i].getX();
			yPos = hexagons[i].getY();
			if(hexagons[i].isRobbed()){
				robImg.draw(xPos + scWidth/2-numImg[2].getWidth()/2*scFactor, yPos + scHeight/2-numImg[2].getHeight()/2*scFactor, scFactor);
			}
		}
	}
	
	void drawBuilding(Graphics g) {
		
		//ArrayList<Building> buildings = GameData.buildings;
		
		for (Building building : Building.getBuildings()) {
			float xPos = building.POSITION.getX();
			float yPos = building.POSITION.getY();
			
			if (building.isUpgraded()) {
				cityImg[building.PLAYER].draw(xPos + scWidth/2 - cityImg[building.PLAYER].getWidth()/2 * scFactor, yPos + scHeight/2 - cityImg[building.PLAYER].getHeight()/2 * scFactor);
			} else {
				//int test = scWidth;
				buildImg[building.PLAYER].draw(xPos + scWidth/2 - buildImg[building.PLAYER].getWidth() / 2, yPos + scHeight/2 - buildImg[building.PLAYER].getHeight() / 2);
			}
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
	
	public static void received(String message) {
		System.out.println(message);
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
