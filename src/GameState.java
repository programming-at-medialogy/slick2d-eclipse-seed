import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState implements KeyListener {
	
	private Image[] hexImg = new Image[6]; // Array for hexagon images
	private Image[] roadImg = new Image[4]; // Array for road images
	private Image[] numImg = new Image [11]; // Array for numbers

	private Image[] diceImg = new Image [6];
	private Image playerBck;
	
	private Image playerTop;
	private Image playerLeft;
	private Image playerRight;

	private Image[] crdImg = new Image [5]; // Array for resource cards
	private Image[] devCrdImg = new Image [5];

	private Image[] buildImg = new Image[4]; // Array for level 1 building images
	private Image[] cityImg = new Image[4]; // Array for level 2 building images
	private static Image[] butImg = new Image[13]; //Action buttons
	
	private static Image robImg;
	private Image bkWater;
	 
	static float hexHeight; // to get height of hexagon
	static float hexWidth; // to get width of hexagon
	static float crdHeight;
	static float crdWidth;
	
	static int buttonWidth = (int)(381*Windows.scFactor);
	static int buttonHeight = (int)(126*Windows.scFactor);
	static int aButtonWidth;
	static int aButtonHeight;
	static int roadsPlaced = 0;
	
	static boolean isPlacingBuilding;
	static boolean isPlacingRoad;
	static boolean moveRobber = false;
	static boolean isClicked = false;
	static boolean isInit = true;
	
	static BasicGameState thisState;
	
	static DialogBox robberWarning;
	DialogBox buildingWarning;
	
	Position startRoadPos;
	Position endRoadPos;
	
    Random rand = new Random();

	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {	
		thisState = this;
		
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
		playerTop = new Image("resources/playerTop.jpg");
		playerLeft = new Image("resources/playerSide.jpg");
		playerRight = new Image("resources/playerSide.jpg");
		robImg = new Image("resources/robber.png");
		bkWater = new Image("resources/bkWater.png");
		playerBck = new Image("resources/playerBck.jpg");
		aButtonWidth = (int)(butImg[0].getWidth()*Windows.scFactor); // Action button width
		aButtonHeight = (int)(butImg[0].getHeight()*Windows.scFactor); // Action button height
		
		buildingWarning = new DialogBox(Windows.scWidth/2 - 250, Windows.scHeight/2 - 250, 500, 500, 30, this);
		buildingWarning.addString("You cannot built here", Windows.scWidth/2, Windows.scHeight/2);
		
		// game data init
		GameData.roads = new ArrayList<Road>();
		GameData.buildings = new ArrayList<Building>();
		GameData.players = new ArrayList<Player>();
		
		
		Actions.initActions();
		hexWidth = hexImg[0].getWidth();
		hexHeight = hexImg[0].getHeight();
		crdWidth = crdImg[0].getWidth()*Windows.scFactor;
		crdHeight = crdImg[0].getHeight()*Windows.scFactor;
		isPlacingBuilding = false;
		isPlacingRoad = false;
		Windows.padding = hexWidth/22 * Windows.scFactor;
		
		//Board Development Buttons
		Button buyRoad = new Button(Windows.scWidth-aButtonWidth,(int)(Windows.scHeight-playerBck.getHeight()*Windows.scFactor)-aButtonHeight*4, aButtonWidth, aButtonHeight, butImg[0], butImg[2], butImg[1], this) {
			@Override
			public void isClicked() {
				if (roadsPlaced == 0 || !isInit)
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
		Button trade = new Button((int)(Windows.scWidth-buttonWidth*2-buttonWidth*1.3), (int)(Windows.scHeight-playerBck.getHeight()*Windows.scFactor-buttonHeight), buttonWidth, buttonHeight, 20, "Trade", this) {
			@Override
			public void isClicked() {		
				System.out.println("Trade");
			}
		};
		Button rollD = new Button((int)(Windows.scWidth-buttonWidth-buttonWidth*1.2),(int)(Windows.scHeight-playerBck.getHeight()*Windows.scFactor-buttonHeight), buttonWidth, buttonHeight, 20, "Roll Dice", this) {
			@Override
			public void isClicked() {	
				if (GameData.turn == GameData.ownIndex && !isInit) {
					Actions.rollDice();
					//GameData.turn = (GameData.turn + 1) % GameData.players.size();
				}
			}
		};
		
		final ListBox chatOutput = new ListBox(0, Windows.scHeight - 35 - 300, (int)(1200*Windows.scFactor), 300, 20, this);
		
		TextBox chatInput = new TextBox(0, Windows.scHeight - 35, (int)(1200*Windows.scFactor), 35, 20, this) {
			@Override
			public void onSubmit() {
				chatOutput.addString(IntroState.playerName + ", " + this.getContent(), GameData.ownIndex);
				Actions.chat(IntroState.playerName + ": " + this.getContent());
				this.clear();
			}
		};
	}
	
	public static void processDie() {
		if (Dice.dice1 + Dice.dice2 == 7 && GameData.turn == GameData.ownIndex) {
			moveRobber = true;
			robberWarning = new DialogBox(Windows.scWidth/2 - 250, Windows.scHeight/2 - 250, 500, 500, 30, thisState);
			robberWarning.activate();
			robberWarning.addImage(robImg, Windows.scWidth/2 + 150, Windows.scHeight/2, 200, 200);
			robberWarning.addString("Move the robber", Windows.scWidth/2, Windows.scHeight/2);
		}
		else if (Dice.dice1 != Dice.dice2 && GameData.turn == GameData.ownIndex)
			endTurn();
		// collect resources
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
		playerTop.draw(Windows.scWidth/2-playerTop.getWidth()/2*Windows.scFactor*0.6f,0,Windows.scFactor*0.6f);
		playerLeft.draw(0,0,Windows.scFactor*0.6f);
		playerRight.draw(Windows.scWidth-playerRight.getWidth()*Windows.scFactor*0.6f,0,Windows.scFactor*0.6f);
		
		drawHexagons(g);
		drawRobber(g);
		drawPlayers(g);

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

		// For displaying resource cards
		for (int c = 0; c < GameData.players.get(GameData.ownIndex).resources.length;  c++){ 
			float crdPosX = cardPosition(c, GameData.players.get(GameData.ownIndex).resources.length); // 5 is to change - amount of resource cards
			crdImg[c].draw(crdPosX+(Windows.scWidth/2-crdWidth), (Windows.scHeight-crdHeight)-crdHeight/3.8f, Windows.scFactor);
			// font for card numbers
			LobbyState.cardNumFont.drawString(crdPosX+(Windows.scWidth/2-crdWidth), (Windows.scHeight+crdHeight-playerBck.getHeight()*Windows.scWidth), "x", new Color(250, 235, 204)); 
		}
		// For displaying Development cards
		for (int d=0; d<5; d++){
			devCrdImg[d].draw(Windows.scWidth-crdWidth, Windows.scHeight-crdHeight, Windows.scFactor);
		}
		ListBox.update(this);
		TextBox.update(this);
		DialogBox.draw(g, this);
	}
	
	
	
	
	
	
	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		Button.update(this);
		ListBox.update(this);
		TextBox.update(this);

		if (robberWarning != null && robberWarning.isActive && !isClicked) {
			if (Mouse.isButtonDown(0))
				robberWarning.deactivate();
		
		} else if (buildingWarning.isActive && !isClicked) {
			if (Mouse.isButtonDown(0))
				buildingWarning.deactivate();
		}
		
		
		else if(Mouse.isButtonDown(0) && isPlacingBuilding && !isClicked) {
			//System.out.println(Mouse.getX() + " " + Mouse.getY());
			Position bPos = Position.findPosition(Mouse.getX() - Windows.scWidth/2, Windows.scHeight - Mouse.getY() - Windows.scHeight/2);
			
			if (bPos != null) {

				//Building building = Building.build(bPos, GameData.ownIndex);
				//if (building != null) {
					//isPlacingBuilding = false;
					if (!isInit)
						Actions.buyBuilding(bPos, GameData.ownIndex);
					else {
						roadsPlaced = 0;
						Actions.placeBuilding(bPos, GameData.ownIndex);
						Actions.endTurn();
					}
					
					isPlacingBuilding = false;
				//}
				//else 
					//buildingWarning.activate();
			} 
		}
		
		else if (Mouse.isButtonDown(0) && isPlacingRoad && !isClicked) {
			Position[] rPos = Position.findPositions(Mouse.getX() - Windows.scWidth/2, Windows.scHeight - Mouse.getY() - Windows.scHeight/2);
			if (rPos != null) {
				//Road.buildRoad(rPos[0], rPos[1], GameData.ownIndex);
				if (!isInit)
					Actions.buyRoad(rPos[0], rPos[1], GameData.ownIndex);
				else
					Actions.placeRoad(rPos[0], rPos[1], GameData.ownIndex);
				
				roadsPlaced++;
				isPlacingRoad = false;
			}
		}
		
		else if(moveRobber && Mouse.isButtonDown(0) && !isClicked){
			Hexagon rHex = Hexagon.findHexagon(Mouse.getX() - Windows.scWidth/2, Windows.scHeight - Mouse.getY() - Windows.scHeight/2);
			if (rHex != null) {
				rHex.rob();
				Actions.moveRobber(rHex.getIndexInArray());
				moveRobber = false;
				endTurn();
			}
		}
		
		if (Mouse.isButtonDown(0)) {
			isClicked = true;
		} else
			isClicked = false;
	}
	
	public static void endTurn() {
		if (GameData.turn == GameData.ownIndex) {
			GameData.turn = (GameData.turn + 1) % GameData.players.size();
			Actions.endTurn();
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return States.GameState;
	}
	@Override
	public void keyPressed(int key, char c) {
		if (key == 1) { //esc
			isPlacingRoad = false;
			isPlacingBuilding = false;
		}
		else
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
	}
	
	// Method to get resource cards positions
		float cardPosition(int cardIndex, int cardAmount){
			float crdPosX = cardIndex*(crdWidth-crdWidth/140)-cardAmount/2*(crdWidth/2)-crdWidth/2.28f;
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
	void drawPlayers(Graphics g){
		TrueTypeFont pointFont = Resource.getFont("std", 15);
		int playerPlace = 0;
		for(int i = 0; i < GameData.players.size(); i++){
			if (i != GameData.ownIndex && playerPlace == 0 ){
				pointFont.drawString(playerLeft.getWidth()/2*Windows.scFactor*0.6f - pointFont.getWidth("" + GameData.players.get(i).getName())/2, 10, GameData.players.get(i).getName(), new Color(250, 235, 204));
				pointFont.drawString(playerLeft.getWidth()/2*Windows.scFactor*0.6f - pointFont.getWidth("" + GameData.players.get(i).points)/2, 80, Integer.toString(GameData.players.get(i).points), new Color(250, 235, 204));
				if(GameData.longestRoad==i){
					pointFont.drawString(playerLeft.getWidth()/2*Windows.scFactor*0.6f - pointFont.getWidth("YES")/2, 150, "YES", new Color(250, 235, 204));
				} else{
					pointFont.drawString(playerLeft.getWidth()/2*Windows.scFactor*0.6f - pointFont.getWidth("NO")/2, 150, "NO", new Color(250, 235, 204));
				}
				pointFont.drawString(playerLeft.getWidth()/2*Windows.scFactor*0.6f - pointFont.getWidth("" + GameData.players.get(i).getTotalAmountOfDevCards())/2, 230, Integer.toString(GameData.players.get(i).getTotalAmountOfDevCards()), new Color(250, 235, 204));
				pointFont.drawString(playerLeft.getWidth()/2*Windows.scFactor*0.6f - pointFont.getWidth("" + GameData.players.get(i).resourceAmount)/2, 300, Integer.toString(GameData.players.get(i).resourceAmount), new Color(250, 235, 204));
				playerPlace = 1;
			} else if (i != GameData.ownIndex && playerPlace == 1 ){
				pointFont.drawString(Windows.scWidth/2 - 200, 50, GameData.players.get(i).getName(), new Color(250, 235, 204));
				pointFont.drawString(Windows.scWidth/2 - 60, 50, Integer.toString(GameData.players.get(i).points), new Color(250, 235, 204));
				if(GameData.longestRoad==i){
					pointFont.drawString(Windows.scWidth/2 + 10, 50, "YES", new Color(250, 235, 204));
				} else{
					pointFont.drawString(Windows.scWidth/2 + 10, 50, "NO", new Color(250, 235, 204));
				}
				pointFont.drawString(Windows.scWidth/2 + 90, 50, Integer.toString(GameData.players.get(i).getTotalAmountOfDevCards()), new Color(250, 235, 204));
				pointFont.drawString(Windows.scWidth/2 + 160, 50, Integer.toString(GameData.players.get(i).resourceAmount), new Color(250, 235, 204));
				playerPlace = 2;
			} else if (i != GameData.ownIndex && playerPlace == 2 ){
				pointFont.drawString(Windows.scWidth - playerLeft.getWidth()/2*Windows.scFactor*0.6f - pointFont.getWidth("" + GameData.players.get(i).getName())/2, 10, GameData.players.get(i).getName(), new Color(250, 235, 204));
				pointFont.drawString(Windows.scWidth -playerLeft.getWidth()/2*Windows.scFactor*0.6f - pointFont.getWidth("" + GameData.players.get(i).points)/2, 80, Integer.toString(GameData.players.get(i).points), new Color(250, 235, 204));
				if(GameData.longestRoad==i){
					pointFont.drawString(Windows.scWidth -playerLeft.getWidth()/2*Windows.scFactor*0.6f - pointFont.getWidth("YES")/2, 150, "YES", new Color(250, 235, 204));
				} else{
					pointFont.drawString(Windows.scWidth -playerLeft.getWidth()/2*Windows.scFactor*0.6f - pointFont.getWidth("NO")/2, 150, "NO", new Color(250, 235, 204));
				}
				pointFont.drawString(Windows.scWidth -playerLeft.getWidth()/2*Windows.scFactor*0.6f - pointFont.getWidth("" + GameData.players.get(i).getTotalAmountOfDevCards())/2, 230, Integer.toString(GameData.players.get(i).getTotalAmountOfDevCards()), new Color(250, 235, 204));
				pointFont.drawString(Windows.scWidth -playerLeft.getWidth()/2*Windows.scFactor*0.6f - pointFont.getWidth("" + GameData.players.get(i).resourceAmount)/2, 300, Integer.toString(GameData.players.get(i).resourceAmount), new Color(250, 235, 204));				playerPlace = 0;
		}
		}
	/*			if(GameData.longestRoad == 0 && GameData.longestRoad !=GameData.ownIndex){
					pointFont.drawString(playerLeft.getWidth()/2*Windows.scFactor*0.3f, 30, "YES", new Color(250, 235, 204));
					pointFont.drawString(Windows.scWidth/2-30, 30, "NO", new Color(250, 235, 204));
					pointFont.drawString(Windows.scWidth-playerLeft.getWidth()/2*Windows.scFactor*0.3f, 30, "NO", new Color(250, 235, 204));
				} else if(GameData.longestRoad == 1 && GameData.longestRoad !=GameData.ownIndex){
					pointFont.drawString(playerLeft.getWidth()/2*Windows.scFactor*0.3f, 30, "NO", new Color(250, 235, 204));
					pointFont.drawString(Windows.scWidth/2-30, 30, "YES", new Color(250, 235, 204));
					pointFont.drawString(Windows.scWidth-playerLeft.getWidth()/2*Windows.scFactor*0.3f, 30, "NO", new Color(250, 235, 204));
				} else if(GameData.longestRoad == 2 && GameData.longestRoad !=GameData.ownIndex){
					pointFont.drawString(playerLeft.getWidth()/2*Windows.scFactor*0.3f, 30, "NO", new Color(250, 235, 204));
					pointFont.drawString(Windows.scWidth/2-30, 30, "NO", new Color(250, 235, 204));
					pointFont.drawString(Windows.scWidth-playerLeft.getWidth()/2*Windows.scFactor*0.3f, 30, "YES", new Color(250, 235, 204));
				} else if(GameData.longestRoad == 3 && GameData.longestRoad !=GameData.ownIndex){
					pointFont.drawString(playerLeft.getWidth()/2*Windows.scFactor*0.3f, 30, "YES", new Color(250, 235, 204));
					pointFont.drawString(Windows.scWidth/2-30, 30, "NO", new Color(250, 235, 204));
					pointFont.drawString(Windows.scWidth-playerLeft.getWidth()/2*Windows.scFactor*0.3f, 30, "NO", new Color(250, 235, 204));
		}*/
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
