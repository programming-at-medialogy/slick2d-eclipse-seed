import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;



public class Main extends BasicGame{
	int rolledNumber; //The number rolled by the dice
	int longestRoad; //the index of the player in the player-ArraList with the longest road
	int mostKnights; //the index of the player in the player-ArraList with the most knights
	int thief; //the placement of the thief
	int ownIndex; //This particular clients index in the player-array
	ArrayList [] hexagons = new ArrayList[20]; //ArrayList containing the Hexagon-objects that defines the game board
	ArrayList<Harbour> harbours; //ArrayList containing the Harbour-object that are also part of the game board
	ArrayList<Player> players; //ArrayList containing all the player-objects
	NetworkHelper networkHelper;
	boolean[] tradeRequest; //Do we need this?
	boolean hasRequestedTrade; //Do we need this?
	static boolean userInGame; 
	static int scHeight = 1400;
	static int scWidth = 900;
	

	private Image img = null;

	
	public Main(String gamename) {
		// TODO Auto-generated constructor stub
		super(gamename);
		
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
	public void init(GameContainer gc) throws SlickException {
		//for(int i=0; i<hexagons.length; i++){
			
		 img = new Image("resources/hexagon.png");
		//}
	}
	/*
	public Vector2d rotate(double angle)
	{
		double xPrime = this.x*Math.cos(angle) - this.y*Math.sin(angle);
		double yPrime = this.x*Math.sin(angle) + this.y*Math.cos(angle);
		return new Vector2d(xPrime, yPrime);
	}
	*/

	
	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	

	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
		float xLength = 150;
		float yLength = 150;
		float angle=0;
		float x;
		float y;
		
		for (int i = 0; i<20; i++){
			
	        x = (float) (xLength * Math.cos (angle));
	        y = (float) (yLength * Math.sin (angle));
	        
			img.draw(x+720, y+400);
			
			if (i<5){
				angle+=1.05;
			} else{
				angle +=0.45;
			}
			
			if(i==5){
				xLength += 100;
				yLength += 100;
				
			}
		}
		//g.setBackground(Color.WHITE);
		//g.drawString("Works", 250, 200);
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
			game.setTargetFrameRate(60); // framerate
			game.setMaximumLogicUpdateInterval(60); // maximum framerate
			game.setVSync(true); // Vertical sync
			game.setDisplayMode(scHeight, scWidth, false); // sets screen size, false or true for full screen
			game.start(); // to start Slick 2D
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		

		
	}
}
