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
	//ArrayList [] hexagons = new ArrayList[19]; //ArrayList containing the Hexagon-objects that defines the game board
	ArrayList<Harbour> harbours; //ArrayList containing the Harbour-object that are also part of the game board
	ArrayList<Player> players; //ArrayList containing all the player-objects
	NetworkHelper networkHelper;
	boolean[] tradeRequest; //Do we need this?
	boolean hasRequestedTrade; //Do we need this?
	static boolean userInGame; 
	
	
	
	private Image[] hexImg = new Image[6]; // Array for hexagon images
	private Image[] roadImg = new Image[4]; // Array for road images
	static int scWidth = 1440; // Game screen width
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
		Road.buildRoad(new Position(0,0), new Position(1,17), 0);
		Road.buildRoad(new Position(1,17), new Position(1,16), 0);
		Road.buildRoad(new Position(0,0), new Position(0,1), 0);
		Road.buildRoad(new Position(0,1), new Position(0,2), 0);
		Road.buildRoad(new Position(0,2), new Position(0,3), 0);
		
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
		float scFactor = 0.4f; // Dynamic setup: scales images according this value 
		float padding = hexImg[0].getWidth()/22*scFactor; // Dynamic setup: space between polygons
		diameter = (hexImg[0].getWidth()+ padding)*scFactor; // Dynamic setup: diameter according image width, + padding for space in between
		//end. move out, since used only once in setup
		
			// To display hexagons	
			for (int i = 0; i<Hexagon.getHexagons().length; i++){
				
				// center hexagon
				if(i==0){
					hexImg[Hexagon.getHexagons()[i].getType().toInt()].draw(scWidth/2-hexImg[0].getWidth()/2*scFactor, scHeight/2-hexImg[0].getHeight()/2*scFactor, scFactor); // draw center hexagon
				}
				
				//middle circle of hexagons
				if (i>0 && i<7){
					xPos = (float) (diameter * Math.cos (angle(i))); // draw hexagon in curcular manner
					yPos = (float) (diameter * Math.sin (angle(i)));	      
					hexImg[Hexagon.getHexagons()[i].getType().toInt()].draw(xPos+scWidth/2-hexImg[0].getWidth()/2*scFactor, yPos+scHeight/2-hexImg[0].getHeight()/2*scFactor, scFactor);	
					
					//myImage.draw(55, 66, 1.2f);
				} 
				
				//outer circle of hexagons
				if(i>6){
					if(i%2!=0){
						diameter = ( ((hexImg[0].getWidth()*2)+padding/2+padding) - hexImg[0].getWidth()/3.85f )*scFactor; // 3.85 CHECK. Dynamic setup : diameter for outer polygons according image width. 3.8 is ~third of image that gets inside the cricle
						//diameter = 156;
					}else{
					diameter = ( ((hexImg[0].getWidth()*2)+padding*2))*scFactor; // Dynamic setup : diameter for outer polygons according image width
					}
					//angle +=0.524f;
					
			        xPos = (float) (diameter * Math.cos (angle(i))); 
			        yPos = (float) (diameter * Math.sin (angle(i)));	      
					hexImg[Hexagon.getHexagons()[i].getType().toInt()].draw(xPos+scWidth/2-hexImg[0].getWidth()/2*scFactor, yPos+scHeight/2-hexImg[0].getHeight()/2*scFactor, scFactor);	
				}
				//g.drawString("Text", 250, 200);
				
				
				//To display roads
				for (int r =1; r<=36; r++){ //36 because there are 6 hexagons in middle circle and there can be 6 road for each 
					xPos = (float) (diameter * Math.cos (angle(i))); // draw hexagon in circular manner
					yPos = (float) (diameter * Math.sin (angle(i)));
					
					//roadImg[1].draw(xPos+scWidth/2, yPos+scHeight/2, scFactor);	
					roadImg[1].setRotation(60f);
					
				}
			} // End of display hexagons

		}
	// Function to calculate angle for each polygon
	private float angle(int hexIndex){
		if (hexIndex <=6){
			// for inner
			angle  = hexIndex * 1.0472f; // 1.05f = 360/6 is specific angle for all polygons up to 7th.
		}else
			// for outer
			angle  = hexIndex * 0.523599f; // 0.523599f = 360/12 for hexagons from 7th
		return angle;
	}
	
	public static void main(String[] args) {
		//Initial phase - only done once at game start
		
		try
		{
			AppGameContainer game = new AppGameContainer(new Main("Settlers"));
			game.setTargetFrameRate(24); // framerate
			game.setMaximumLogicUpdateInterval(24); // maximum framerate
			game.setVSync(true); // Vertical sync
			game.setDisplayMode(scWidth,scHeight, true); // sets screen size, false or true for full screen
			game.start(); // to start Slick 2D
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		

		
	}
}
