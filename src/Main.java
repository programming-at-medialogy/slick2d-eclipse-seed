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



public class Main extends BasicGame{
	int rolledNumber; //The number rolled by the dice
	static int longestRoad; //the index of the player in the player-ArraList with the longest road
	int mostKnights; //the index of the player in the player-ArraList with the most knights
	int thief; //the placement of the thief
	int ownIndex; //This particular clients index in the player-array
	ArrayList [] hexagons = new ArrayList[19]; //ArrayList containing the Hexagon-objects that defines the game board
	ArrayList<Harbour> harbours; //ArrayList containing the Harbour-object that are also part of the game board
	ArrayList<Player> players; //ArrayList containing all the player-objects
	NetworkHelper networkHelper;
	boolean[] tradeRequest; //Do we need this?
	boolean hasRequestedTrade; //Do we need this?
	static boolean userInGame; 
	
	
	
	private Image[] img = new Image[19]; // Array for hexagon images
	static int scWidth = 1400; // Game screen width
	static int scHeight = 900; // Game screen height

	float diameter; // diameter for hexagon placement
	boolean hexReady = false; // NOT USED NOW, discuss! To draw hexagons once in rendered. If not used remove // NOT USED NOW, discuss!
	
	float xPos; // hexagon position X
	float yPos; // hexagon position Y
	float angle; // hexagon angle
	
	float hexHeight; // to get height of hexagon
	float hexWidth; // to get width of hexagon
	
	public Main(String gamename) { // inputs game name from slick2d
		super(gamename); // to get game name
		Hexagon.generateMap();
		
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
		// to initialize multiple images
		for(int i=0; i<hexagons.length; i++){ //goes trough hexagon array
			img[i] = new Image("resources/hexagon_" + (i) + ".png"); //Assigns every hexagon a name
		}
	}
	
	@Override
	public void update(GameContainer gc, int i) throws SlickException {	
	}
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		Color bkColor = Color.decode("#e5cea1"); // create custom color
		g.setBackground(Color.black); // set background color
		
		// move out, since used only once in setup
		float scFactor = 0.4f; // Dynamic setup: scales images according this value 
		float padding = img[0].getWidth()/22*scFactor; // Dynamic setup: space between polygons
		diameter = (img[0].getWidth()+ padding)*scFactor; // Dynamic setup: diameter according image width, + padding for space in between

		//end. move out, since used only once in setup
		
			for (int i = 0; i<hexagons.length; i++){
				// center hexagon
	
				if(i==0){
					img[i].draw(scWidth/2-img[0].getWidth()/2*scFactor, scHeight/2-img[0].getHeight()/2*scFactor, scFactor); // draw center hexagon
				}
				//middle circle of hexagons
				
				if (i>0 && i<7){
					xPos = (float) (diameter * Math.cos (angle(i))); // draw hexagon in curcular manner
					yPos = (float) (diameter * Math.sin (angle(i)));	      
					img[i].draw(xPos+scWidth/2-img[0].getWidth()/2*scFactor, yPos+scHeight/2-img[0].getHeight()/2*scFactor, scFactor);		
					//myImage.draw(55, 66, 1.2f);
				} 
				// outer circle of hexagons
				if(i>6){
					if(i%2!=0){
						diameter = ( ((img[0].getWidth()*2)+padding) - img[0].getWidth()/4f )*scFactor; // Dynamic setup : diameter for outer polygons according image width. 3.8 is ~third of image that gets inside the cricle
						//diameter = 156;
					}else{
					diameter = (img[0].getWidth()*2)*scFactor+padding; // Dynamic setup : diameter for outer polygons according image width
					}
					//angle +=0.524f;
					 
					
			        xPos = (float) (diameter * Math.cos (angle(i))); 
			        yPos = (float) (diameter * Math.sin (angle(i)));	      
					img[i].draw(xPos+scWidth/2-img[0].getWidth()/2*scFactor, yPos+scHeight/2-img[0].getHeight()/2*scFactor, scFactor);	
				}
				//g.drawString("Text", 250, 200);
			}
		}
	// Function to calculate angle for each polygon
	private float angle(int hexIndex){
		if (hexIndex <=6){
			// for inner
			angle  = hexIndex * 1.048f; // 1.05f is specific angle for all polygons up to 7th.
		}else
			// for outer
			angle  = hexIndex * 0.524f; // 0.524 for hexagons from 7th
		return angle;
	}

	//Methods for initial phase
	
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
	
	public static void main(String[] args) {
		//Initial phase - only done once at game start
		placeBuilding();
		placeRoad();
		
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
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		

		
	}
}
