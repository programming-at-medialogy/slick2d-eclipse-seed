import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState implements KeyListener {
	
	private Image[] hexImg = new Image[6]; // Array for hexagon images
	private Image[] roadImg = new Image[4]; // Array for road images
	private Image[] numImg = new Image [11]; // Array for numbers

	private Image[] diceImg = new Image [6];
	private Image playerBck;

	private Image[] crdImg = new Image [5]; // Array for resource cards
	private Image[] devCrdImg = new Image [5];

	private Image[] buildImg = new Image[4]; // Array for level 1 building images
	private Image[] cityImg = new Image[4]; // Array for level 2 building images
	private static Image[] butImg = new Image[13]; //Action buttons
	
	private Image robImg;
	private Image bkWater;
	 
	static float hexHeight; // to get height of hexagon
	static float hexWidth; // to get width of hexagon
	static float crdHeight;
	static float crdWidth;
	
	static int buttonWidth = (int)(381*Windows.scFactor);
	static int buttonHeight = (int)(126*Windows.scFactor);
	static int aButtonWidth;
	static int aButtonHeight;
	
	static boolean isPlacingBuilding;
	static boolean isPlacingRoad;
	static boolean moveRobber = false;
	
	Position startRoadPos;
	Position endRoadPos;
	
    Random rand = new Random();

	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {		
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
		for (int c=0; c<5; c++){ // for both resource and development cards
			crdImg[c] = new Image ("resources/r_card_"+(c)+".jpg");
			devCrdImg[c] = new Image ("resources/r_card_1.jpg");
		}
		for(int b = 0; b < cityImg.length; b++) {
			buildImg[b] = new Image("resources/buildImg" + b + ".png"); //Initializing level 1 building images
		}
		for (int d=0; d<6; d++){
			diceImg[d] = new Image ("resources/dice_"+(d+1)+".png");
		}
		for (int a=0; a<13; a++){
			butImg[a] = new Image("resources/but_"+(a)+".jpg");
		}
		robImg = new Image("resources/robber.png");
		bkWater = new Image("resources/bkWater.png");
		playerBck = new Image("resources/playerBck.jpg");
		aButtonWidth = (int)(butImg[0].getWidth()*Windows.scFactor); // Action button width
		aButtonHeight = (int)(butImg[0].getHeight()*Windows.scFactor); // Action button height
		
		
		// game data init
		GameData.roads = new ArrayList<Road>();
		GameData.buildings = new ArrayList<Building>();
		GameData.players = new ArrayList<Player>();
		
		Actions.initActions();
		hexWidth = hexImg[0].getWidth();
		hexHeight = hexImg[0].getHeight();
		crdWidth = crdImg[0].getWidth();
		crdHeight = crdImg[0].getHeight();
		isPlacingBuilding = false;
		isPlacingRoad = false;
		Windows.padding = hexWidth/22 * Windows.scFactor;
		
		//Board Development Buttons
		Button buyRoad = new Button(Windows.scWidth-aButtonWidth,(int)(Windows.scHeight-playerBck.getHeight()*Windows.scFactor)-aButtonHeight*4, aButtonWidth, aButtonHeight, butImg[0], butImg[2], butImg[1], this) {
			@Override
			public void isClicked() {
				isPlacingRoad = true;
			}
		};
		Button buySettlement = new Button(Windows.scWidth-aButtonWidth,(int)(Windows.scHeight-playerBck.getHeight()*Windows.scFactor)-aButtonHeight*3, aButtonWidth, aButtonHeight, butImg[3], butImg[5], butImg[4], this) {
			@Override
			public void isClicked() {
				isPlacingBuilding = true;
			}
		};
		
		Button upgCity = new Button(Windows.scWidth-aButtonWidth, (int)(Windows.scHeight-playerBck.getHeight()*Windows.scFactor)-aButtonHeight*2, aButtonWidth, aButtonHeight, butImg[6], butImg[8], butImg[7], this) {
			@Override
			public void isClicked() {
			}
		};
		Button devCard = new Button(Windows.scWidth-aButtonWidth, (int)(Windows.scHeight-playerBck.getHeight()*Windows.scFactor)-aButtonHeight, aButtonWidth, aButtonHeight, butImg[9], butImg[10], butImg[11], this) {
			@Override
			public void isClicked() {
			}
		};
		
		// Board action buttons
		Button trade = new Button(Windows.scWidth-buttonWidth*2-buttonWidth*2,(int)(Windows.scHeight-playerBck.getHeight()*Windows.scFactor-buttonHeight), buttonWidth, buttonHeight, 20, "Trade", this) {
			@Override
			public void isClicked() {		
				System.out.println("Trade");	
			}
		};
		Button rollD = new Button((int)(Windows.scWidth-buttonWidth-buttonWidth*1.2),(int)(Windows.scHeight-playerBck.getHeight()*Windows.scFactor-buttonHeight), buttonWidth, buttonHeight, 20, "Roll Dice", this) {
			@Override
			public void isClicked() {		
				diceNumber(1);
				diceNumber(2);	
				if (Dice.dice1 + Dice.dice2 == 7)
					moveRobber = true;
			}
		};
		


		TextBox chatInput = new TextBox(0, Windows.scHeight - 35, (int)(1200*Windows.scFactor), 35, 20, this) {
			@Override
			public void onSubmit() {
				
			}
		};
		
		ListBox chatOutput = new ListBox(0, Windows.scHeight - 35 - 300, (int)(1200*Windows.scFactor), 300, 20, this);
	}

	int diceNumber(int diceIndex){
		int diceNumber = Dice.RollDice(diceIndex);
		return diceNumber;

	}
	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		Color bkColor = Color.decode("#5e8ad7"); // create custom color
		g.setBackground(bkColor); // set background color
		
		bkWater.draw(Windows.scWidth/2-bkWater.getWidth()/2*Windows.scFactor, (Windows.scHeight/2-bkWater.getHeight()/2*Windows.scFactor)-hexHeight*Windows.scFactor/1.5f, Windows.scFactor );
		playerBck.draw(0, Windows.scHeight-playerBck.getHeight()*Windows.scFactor, Windows.scWidth, playerBck.getHeight()*Windows.scFactor);

		// drawing the UI of the board
		drawHexagons(g);
		drawRobber(g);

		for (int i = 0; i < Road.getRoads().size(); i++) {
			float x = Road.getRoads().get(i).getCenterX();
			float y = Road.getRoads().get(i).getCenterY();
			
			g.pushTransform();
				g.rotate(x + Windows.scWidth/2, y + Windows.scHeight/2, Road.getRoads().get(i).getAngle());
				roadImg[3].draw(x + Windows.scWidth/2 - roadImg[1].getWidth()/2*Windows.scFactor, y + Windows.scHeight/2  - roadImg[1].getHeight()/2*Windows.scFactor, Windows.scFactor);
			g.popTransform();
		}
		
		drawBuilding(g);

		diceImg[Dice.dice1-1].draw((int) Math.random()*(Windows.scWidth-hexWidth*Windows.scFactor*2+diceImg[1].getWidth())+(Windows.scWidth-hexWidth*Windows.scFactor*2)          ,Windows.scHeight/2 , Windows.scFactor*0.8f);
		diceImg[Dice.dice2-1].draw(Windows.scWidth-hexWidth*Windows.scFactor*2-diceImg[1].getWidth()*Windows.scFactor ,Windows.scHeight/2, Windows.scFactor*0.8f);
		Button.draw(g, this);
		
		ListBox.draw(g, this);
		TextBox.draw(g, this);
	}
	

	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		Button.update(this);
		ListBox.update(this);
		TextBox.update(this);

		if(Mouse.isButtonDown(0) && isPlacingBuilding) {
			//System.out.println(Mouse.getX() + " " + Mouse.getY());
			Position bPos = Position.findPosition(Mouse.getX() - Windows.scWidth/2, Windows.scHeight - Mouse.getY() - Windows.scHeight/2);
			
			if (bPos != null) {
				Building building = Building.build(bPos, GameData.ownIndex);
				isPlacingBuilding = false;
			}
		}
		
		if (Mouse.isButtonDown(0) && isPlacingRoad) {
			Position[] rPos = Position.findPositions(Mouse.getX() - Windows.scWidth/2, Windows.scHeight - Mouse.getY() - Windows.scHeight/2);
			if (rPos != null) {
				Road.buildRoad(rPos[0], rPos[1], GameData.ownIndex);
				isPlacingRoad = false;
			}
		}
		
		if(moveRobber && Mouse.isButtonDown(0)){
			Hexagon rHex = Hexagon.findHexagon(Mouse.getX() - Windows.scWidth/2, Windows.scHeight - Mouse.getY() - Windows.scHeight/2);
			if (rHex != null) {
				rHex.rob();
				moveRobber = false;
			}
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return States.GameState;
	}
	@Override
	public void keyPressed(int key, char c) {
		TextBox.keyPress(key, c, this);
	}
	
	// methods for initial phase
	private void drawHexagons(Graphics g) {

		butImg[12].draw(Windows.scWidth-aButtonWidth, (int)((Windows.scHeight-playerBck.getHeight()*Windows.scFactor)-aButtonHeight*4.5), Windows.scFactor);
		Hexagon[] hexagons = Hexagon.getHexagons();
		
		for (int i = 0; i < hexagons.length; i++){
			hexImg[hexagons[i].TYPE.toInt()].draw(hexagons[i].getX() + Windows.scWidth/2-hexImg[0].getWidth()/2*Windows.scFactor, hexagons[i].getY() + Windows.scHeight/2-hexImg[0].getHeight()/2*Windows.scFactor, Windows.scFactor);
			numImg[hexagons[i].NUMBER-2].draw(hexagons[i].getX() + Windows.scWidth/2-numImg[2].getWidth()/2*Windows.scFactor, hexagons[i].getY() + Windows.scHeight/2-numImg[2].getHeight()/2*Windows.scFactor, Windows.scFactor);
		}
		// For displaying resource cards
		for (int c = 0; c<Player.resources.length;  c++){ 
			float crdPosX = cardPosition(c, Player.resources.length); // 5 is to change - amount of resource cards
			crdImg[c].draw(crdPosX+(Windows.scWidth/2-crdWidth/2*Windows.scFactor), (Windows.scHeight-crdHeight*Windows.scFactor)-crdHeight/100, Windows.scFactor);
		}
		for (int d=0; d<5; d++){
			devCrdImg[d].draw(Windows.scWidth-crdWidth*Windows.scFactor, Windows.scHeight-crdHeight*Windows.scFactor, Windows.scFactor);
		}
		ListBox.update(this);
		TextBox.update(this);

	}
	
	// Method to get resource cards positions
		float cardPosition(int cardIndex, int cardAmount){
			float crdPosX = cardIndex*(crdWidth/2* Windows.scFactor)-cardAmount/2*(crdWidth/2*Windows.scFactor);
			return crdPosX;
		}
	
	void drawRobber(Graphics g) {
		Hexagon[] hexagons = Hexagon.getHexagons();
		for (int i = 0; i < hexagons.length; i++){
			if(hexagons[i].isRobbed()){
				robImg.draw(hexagons[i].getX() + Windows.scWidth/2-numImg[2].getWidth()/2*Windows.scFactor, hexagons[i].getY() + Windows.scHeight/2-numImg[2].getHeight()/2*Windows.scFactor, Windows.scFactor);
			}
		}
	}
	
	void drawBuilding(Graphics g) {
		for (Building building : Building.getBuildings()) {
			float xPos = building.POSITION.getX();
			float yPos = building.POSITION.getY();
			if (building.isUpgraded()) {
				cityImg[building.PLAYER].draw(xPos + Windows.scWidth/2 - cityImg[building.PLAYER].getWidth()/2 * Windows.scFactor, yPos + Windows.scHeight/2 - cityImg[building.PLAYER].getHeight()/2 * Windows.scFactor);
			} else {
				buildImg[building.PLAYER].draw(xPos + Windows.scWidth/2 - buildImg[building.PLAYER].getWidth() / 2, yPos + Windows.scHeight/2 - buildImg[building.PLAYER].getHeight() / 2);
			}
		}
	}
	
	public static void received(String message) {
		System.out.println(message);
	}
	
	
}
