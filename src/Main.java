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
	
	
	private Image[] img= new Image[20];

	
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
		
		// to initialize multiple images
		for(int i=0; i<hexagons.length; i++){
			img[i] = new Image("resources/hexagon_" + (i+1) + ".png");
		}
	}
	
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
		float diameter = 90;
		float angle = 0;
		float x;
		float y;
		
		for (int i = 0; i<hexagons.length-2; i++){
			// center 
			if(i==0){
				img[i].draw(scHeight/2, scWidth/2);
			}
			//middle circle
			if (i<6){
				angle+=1.05;
		        x = (float) (diameter * Math.cos (angle));
		        y = (float) (diameter * Math.sin (angle));	      
				img[i+1].draw(x+scHeight/2, y+scWidth/2);				
				if(i==5){
					diameter += 89;
					//cLength += i == 6 ?  1.05 : 0.50;
				}

			} 
			// outer circle
			else{
				if (i == 6)
					angle = 0.523f;
				if(i%2!=0){
					diameter = 155;
				}else{
					diameter = 179;
				}
					
				angle +=0.523;
		        x = (float) (diameter * Math.cos (angle));
		        y = (float) (diameter * Math.sin (angle));	      
				img[i+1].draw(x+scHeight/2, y+scWidth/2);
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
