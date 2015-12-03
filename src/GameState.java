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
	
	TrueTypeFont playerFont;
	TrueTypeFont pointFont;
	
	private Image[] hexImg = new Image[6]; // Array for hexagon images
	private Image[] roadImg = new Image[4]; // Array for road images
	private Image[] numImg = new Image [11]; // Array for numbers

	private Image[] diceImg = new Image [6];
	private Image playerBck;
	
	private Image playerTopRed;
	private Image playerTopGreen;
	private Image playerTopBlue;
	private Image playerTopOrange;
	private Image playerSideRed;
	private Image playerSideGreen;
	private Image playerSideBlue;
	private Image playerSideOrange;

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
	
	static int[] trading;
	static int[] tradingRec;
	
	static BasicGameState thisState;
	
	static DialogBox robberWarning;
	DialogBox buildingWarning;
	DialogBox instructionWarning;
	static DialogBox victoryWarning;
	
	Position startRoadPos;
	Position endRoadPos;
	
	DialogBox tradeBox;
	static DialogBox tradeAcceptBox;
	static DialogBox tradeDeclineBox;
	static DialogBox tradeChooseBox;
	Button[] adjustButtons;
	Button[] adjustRecButtons;
	Button[] sendButtons;
	
    Random rand = new Random();
    
    static TrueTypeFont tradeFont;
    
	static boolean isInitializingTrade = false;
	static boolean isChoosingTrade = false;
	
	static Button declineTrade;
	static Button acceptTrade;
	static Button cancleButton;
	static TrueTypeFont tempFont;

	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {	
		trading = new int[5];
		tradingRec = new int[5];
		tradeFont = Resource.getFont("std", 30);
		
		tradeBox = new DialogBox(0, 0, Windows.scWidth, Windows.scHeight, 40, this);
		tradeBox.deactivate();
		tradeBox.addString("Others: ", 200, 125 / 2 + 40);
		tradeBox.addString("You: ", 200, 225 / 2 + 40 + 225 + 100);
		
		tradeAcceptBox = new DialogBox(Windows.scWidth/2 - 250, Windows.scHeight/2 - 250, 500, 500, 30, this);
		tradeAcceptBox.addString("Trade accepted", Windows.scWidth/2, Windows.scHeight/2);
		tradeAcceptBox.deactivate();
		
		tradeChooseBox = new DialogBox(0, 0, Windows.scWidth, Windows.scHeight, 30, this);
		
		tradeDeclineBox = new DialogBox(Windows.scWidth/2 - 250, Windows.scHeight/2 - 250, 500, 500, 30, this);
		tradeDeclineBox.addString("Trade declined", Windows.scWidth/2, Windows.scHeight/2);
		tradeDeclineBox.deactivate();
		
		adjustButtons = new Button[10];
		adjustRecButtons = new Button[10];
		
		thisState = this;
		tempFont = Resource.getFont("std", 30);
		playerFont = Resource.getFont("std", 15);
		
		instructionWarning = new DialogBox(Windows.scWidth/2 - 250, Windows.scHeight/2 - 250, 500, 500, 30, thisState);
		instructionWarning.activate();
		instructionWarning.addString("Welcome to Settlers", Windows.scWidth/2, Windows.scHeight/2 - 200);
		instructionWarning.addString("Instructions: Flow of the Game", Windows.scWidth/2, Windows.scHeight/2 - 200 + tempFont.getHeight("IGF"));
		instructionWarning.addString("- Start of turn", Windows.scWidth/2, Windows.scHeight/2 - 200 + (2*tempFont.getHeight("IGF")));
		instructionWarning.addString("- Trade", Windows.scWidth/2, Windows.scHeight/2 - 200 + (3*tempFont.getHeight("IGF")));
		instructionWarning.addString("- Build", Windows.scWidth/2, Windows.scHeight/2 - 200 + (4*tempFont.getHeight("IGF")));
		instructionWarning.addString("- Roll Dice", Windows.scWidth/2, Windows.scHeight/2 - 200 + (5*tempFont.getHeight("IGF")));
		instructionWarning.addString("- End of turn", Windows.scWidth/2, Windows.scHeight/2 - 200 + (6*tempFont.getHeight("IGF")));


		
		// to initialize multiple images
		for(int i=0; i<hexImg.length; i++){ //goes trough hexagon array
			hexImg[i] = new Image("resources/hexagon_" + (i) + ".png"); //Assigns every hexagon a name
		}
		for(int r = 0; r < roadImg.length; r++){
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
		playerTopRed = new Image("resources/playerTopRed.jpg");
		playerTopGreen = new Image("resources/playerTopGreen.jpg");
		playerTopBlue = new Image("resources/playerTopBlue.jpg");
		playerTopOrange = new Image("resources/playerTopOrange.jpg");
		playerSideRed = new Image("resources/playerSideRed.jpg");
		playerSideGreen = new Image("resources/playerSideGreen.jpg");
		playerSideBlue = new Image("resources/playerSideBlue.jpg");
		playerSideOrange = new Image("resources/playerSideOrange.jpg");
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
				isInitializingTrade = true;
				tradeBox.activate();
				int wPadding = 125;
				int hPadding = 225;
				sendButtons = new Button[GameData.players.size() - 1];
				for (int i = 0; i < adjustButtons.length; i++) {
					adjustButtons[i] = new Button((i % 5) * wPadding + Windows.scWidth/2 - 2 * wPadding - 80 / 2, (i % 2) * hPadding + 150 - hPadding / 2 - 40/2, 80, 40, 30, (i % 2 == 0) ? "+" : "-", this.state, true) {
						@Override
						public void isClicked() {
							if (this.message.equals("+")) {
								addTResource(this);
							} else {
								removeTResource(this);
							}
						}
					};
					adjustRecButtons[i] = new Button((i % 5) * wPadding + Windows.scWidth/2 - 2 * wPadding - 80 / 2, (i % 2) * hPadding + 150 + hPadding + 100 - hPadding / 2 - 40/2, 80, 40, 30, (i % 2 == 0) ? "+" : "-", this.state, true) {
						@Override
						public void isClicked() {
							if (this.message.equals("+")) {
								addTResource(this);
							} else {
								removeTResource(this);
							}
						}
					};
				}
				
				for (int i = 0; i < GameData.players.size(); i++) {
					System.out.println("i: " + i + " ownIndex: " + GameData.ownIndex);
					if (i != GameData.ownIndex) {
						sendButtons[i] = new Button(i * Windows.scWidth / 7 + Windows.scWidth / 7, Windows.scHeight - 100, tradeFont.getWidth("Send to " + GameData.players.get(i).getName()) + 20, 40, 30, "Send to " + GameData.players.get(i).getName(), this.state, true) {
							@Override
							public void isClicked() {
								sendTradeRequest(this);
							}
						};
					} else {
						cancleButton = new Button(i * Windows.scWidth / 7 + Windows.scWidth / 7, Windows.scHeight - 100, tradeFont.getWidth("Cancle") + 20, 40, 30, "Cancle", this.state, true) {
							@Override
							public void isClicked() {
								cancleTrade();
							}
						};
					}
				}
				
				
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

		
		drawHexagons(g);
		drawRobber(g);
		drawPlayers(g);

		for (int i = 0; i < Road.getRoads().size(); i++) {
			float x = Road.getRoads().get(i).getCenterX();
			float y = Road.getRoads().get(i).getCenterY();
			
			g.pushTransform();
				g.rotate(x + Windows.scWidth/2, y + Windows.scHeight/2, Road.getRoads().get(i).getAngle());
				roadImg[GameData.roads.get(i).PLAYER_INDEX].draw(x + Windows.scWidth/2 - roadImg[1].getWidth()/2*Windows.scFactor, y + Windows.scHeight/2  - roadImg[1].getHeight()/2*Windows.scFactor, Windows.scFactor);
			g.popTransform();
		}
		drawBuilding(g);
		diceImg[Dice.dice1-1].draw((int) Math.random()*(Windows.scWidth-hexWidth*Windows.scFactor*2+diceImg[1].getWidth())+(Windows.scWidth-hexWidth*Windows.scFactor*2)          ,Windows.scHeight/2 , Windows.scFactor*0.8f);
		diceImg[Dice.dice2-1].draw(Windows.scWidth-hexWidth*Windows.scFactor*2-diceImg[1].getWidth()*Windows.scFactor ,Windows.scHeight/2, Windows.scFactor*0.8f);
		
		Button.draw(g, this);
		ListBox.draw(g, this);
		TextBox.draw(g, this);
		drawOwnCards(g);

		// For displaying resource cards
		for (int c = 0; c < GameData.players.get(GameData.ownIndex).resources.length;  c++){ 
			float crdPosX = cardPosition(c, GameData.players.get(GameData.ownIndex).resources.length); // 5 is to change - amount of resource cards
			crdImg[c].draw(crdPosX+(Windows.scWidth/2-crdWidth), (Windows.scHeight-crdHeight)-crdHeight/3.8f, Windows.scFactor);
			// font for card numbers
			LobbyState.cardNumFont.drawString(crdPosX+(Windows.scWidth/2-crdWidth), (Windows.scHeight+crdHeight-playerBck.getHeight()*Windows.scWidth), "FISSE", new Color(250, 235, 204)); 
		}
		// For displaying Development cards
		for (int d=0; d<5; d++){
			devCrdImg[d].draw(Windows.scWidth-crdWidth, Windows.scHeight-crdHeight, Windows.scFactor);
		}
		for(int i = 0; i < GameData.players.size(); i ++){
			if(GameData.players.get(i) == GameData.players.get(GameData.turn)){
				pointFont = Resource.getFont("pik", 25);
				Color color = new Color(0,0,0);
				if(i == 0){
					color = new Color(255,140,0);
				} else if(i == 1){
					color = new Color(0,0,255);
				} else if(i == 2){
					color = new Color(255,0,0);
				} else if(i == 3){
					color = new Color(0,255,0);
				}
				tempFont.drawString(Windows.scWidth/6 - tempFont.getWidth(GameData.players.get(i).getName())/2, 20, GameData.players.get(i).getName()+ "'s turn", color);
			}
		}
		DialogBox.draw(g, this);
		Button.drawSpecial(g, this);
		
		if (isInitializingTrade) {
			int wPadding = 125;
			int hPadding = 200;
			for (int i = 0; i < crdImg.length; i++) {
				crdImg[i].draw(i * wPadding + Windows.scWidth/2 - 2 * wPadding - (wPadding - 5) / 2, 150 - (hPadding - 25) / 2, wPadding - 5, hPadding - 25);
				tradeFont.drawString(i * wPadding + Windows.scWidth/2 - 2 * wPadding - tradeFont.getWidth("" + trading[i]) / 2, 150 - tradeFont.getHeight("" + trading[i]) / 2 + hPadding / 2 + 50, "" + trading[i]);
				crdImg[i].draw(i * wPadding + Windows.scWidth/2 - 2 * wPadding - (wPadding - 5) / 2, 150 - (hPadding - 25) / 2 + hPadding + 125, wPadding - 5, hPadding - 25);
				tradeFont.drawString(i * wPadding + Windows.scWidth/2 - 2 * wPadding - tradeFont.getWidth("" + trading[i]) / 2, 150 - tradeFont.getHeight("" + tradingRec[i]) / 2 + hPadding / 2 + 50  + hPadding + 125, "" + tradingRec[i]);
			}
		}
		
		if (isChoosingTrade) {
			int wPadding = 125;
			int hPadding = 200;
			for (int i = 0; i < crdImg.length; i++) {
				crdImg[i].draw(i * wPadding + Windows.scWidth/2 - 2 * wPadding - (wPadding - 5) / 2, 150 - (hPadding - 25) / 2, wPadding - 5, hPadding - 25);
				tradeFont.drawString(i * wPadding + Windows.scWidth/2 - 2 * wPadding - tradeFont.getWidth("" + GameData.tObject.wants[i]) / 2, 100 - tradeFont.getHeight("" + GameData.tObject.wants[i]) / 2 + hPadding / 2 + 50, "" + GameData.tObject.wants[i]);
				crdImg[i].draw(i * wPadding + Windows.scWidth/2 - 2 * wPadding - (wPadding - 5) / 2, hPadding + 100 + 150 - (hPadding - 25) / 2, wPadding - 5, hPadding - 25);
				tradeFont.drawString(i * wPadding + Windows.scWidth/2 - 2 * wPadding - tradeFont.getWidth("" + GameData.tObject.has[i]) / 2, hPadding + 100 + 100 - tradeFont.getHeight("" + GameData.tObject.has[i]) / 2 + hPadding / 2 + 50, "" + GameData.tObject.has[i]);
			}
			tradeFont.drawString(Windows.scWidth/2 - tradeFont.getWidth("Offer from: " + GameData.players.get(GameData.turn).getName()) / 2, hPadding + 100 + 175 - tradeFont.getHeight("Offer from: " + GameData.players.get(GameData.turn).getName()) / 2 + hPadding / 2 + 50, "Offer from: " + GameData.players.get(GameData.turn).getName());
		}
		
	}
	
	public void addTResource(Button button) {
		System.out.println("Adding");
		int index = 0;
		for (int i = 0; i < adjustButtons.length; i++) {
			if (adjustButtons[i] == button) {
				trading[i % 5]++;
				break;
			} else if (adjustRecButtons[i] == button) {
				tradingRec[i % 5]++;
				break;
			}
		}
		
		//trading[index % 5]++;
		//System.out.println("Add: " + index % 5);
	}
	
	public void removeTResource(Button button) {
		System.out.println("Removing");
		int index = 0;
		for (int i = 0; i < adjustButtons.length; i++) {
			if (adjustButtons[i] == button) {
				if (trading[i % 5] > 0)
					trading[i % 5]--;
				break;
			} else if (adjustRecButtons[i] == button) {
				if (tradingRec[i % 5] > 0)
					tradingRec[i % 5]--;
				break;
			}
		}
		
		/*if (trading[index % 5] > 0)
			trading[index % 5]--;*/
		//System.out.println("Remove: " + index % 5);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		Button.update(this);
		ListBox.update(this);
		TextBox.update(this);
		
			for(int i = 0; i < GameData.players.size(); i++){
				if(GameData.players.get(i).hasWon == true && victoryWarning != null){	
					victoryWarning.activate();
				}
			}
		
		if (instructionWarning != null && instructionWarning.isActive && !isClicked) {
			if (Mouse.isButtonDown(0))
				instructionWarning.deactivate();
			
		} else if (robberWarning != null && robberWarning.isActive && !isClicked) {
			if (Mouse.isButtonDown(0))
				robberWarning.deactivate();
		
		} else if (buildingWarning.isActive && !isClicked) {
			if (Mouse.isButtonDown(0))
				buildingWarning.deactivate();
		} else if (tradeAcceptBox.isActive && !isClicked) {
			if (Mouse.isButtonDown(0))
				tradeAcceptBox.deactivate();
		} else if (tradeDeclineBox.isActive && !isClicked) {
			if (Mouse.isButtonDown(0))
				tradeDeclineBox.deactivate();
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
		playerFont = Resource.getFont("std", 15);
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
	
	void drawOwnCards(Graphics g){
		pointFont = Resource.getFont("pik", 25);
		Color color = new Color(0,0,0);
		if(GameData.ownIndex == 0){
			color = new Color(255,140,0);
		} else if(GameData.ownIndex == 1){
			color = new Color(0,0,255);
		} else if(GameData.ownIndex == 2){
			color = new Color(255,0,0);
		} else if(GameData.ownIndex == 3){
			color = new Color(0,255,0);
		}
		for (int c = 0; c < GameData.players.get(GameData.ownIndex).resources.length;  c++){ 
			float crdPosX = cardPosition(c, GameData.players.get(GameData.ownIndex).resources.length);		
			pointFont.drawString(crdPosX+(Windows.scWidth/2-crdWidth/2)- pointFont.getWidth("0")/2, Windows.scHeight - (pointFont.getHeight("0")+20), "" +GameData.players.get(GameData.ownIndex).getAmountOfResources(c), color);
		}
		pointFont.drawString(Windows.scWidth - 320, Windows.scHeight - 150, "Score: \n " + GameData.players.get(GameData.ownIndex).points, color);
	}
	
	void drawPlayers(Graphics g){
		int playerPlace = 0;
		for(int i = 0; i < GameData.players.size(); i++){
			if (i != GameData.ownIndex && playerPlace == 0 ){
				if(i == 0){
					playerSideOrange.draw(0,0,Windows.scFactor*0.6f);
				} else if(i == 1){
					playerSideBlue.draw(0,0,Windows.scFactor*0.6f);
				} else if(i == 2){
					playerSideRed.draw(0,0,Windows.scFactor*0.6f);
				} else if(i == 3){
					playerSideGreen.draw(0,0,Windows.scFactor*0.6f);
				}
				
				playerFont.drawString(playerSideRed.getWidth()/2*Windows.scFactor*0.6f - playerFont.getWidth("" + GameData.players.get(i).getName())/2, 10, GameData.players.get(i).getName(), new Color(250, 235, 204));
				playerFont.drawString(playerSideRed.getWidth()/2*Windows.scFactor*0.6f - playerFont.getWidth("" + GameData.players.get(i).points)/2, 80, Integer.toString(GameData.players.get(i).points), new Color(250, 235, 204));
				if(GameData.longestRoad==i){
					playerFont.drawString(playerSideRed.getWidth()/2*Windows.scFactor*0.6f - playerFont.getWidth("YES")/2, 150, "YES", new Color(250, 235, 204));
				} else{
					playerFont.drawString(playerSideRed.getWidth()/2*Windows.scFactor*0.6f - playerFont.getWidth("NO")/2, 150, "NO", new Color(250, 235, 204));
				}
				playerFont.drawString(playerSideRed.getWidth()/2*Windows.scFactor*0.6f - playerFont.getWidth("" + GameData.players.get(i).getTotalAmountOfDevCards())/2, 230, Integer.toString(GameData.players.get(i).getTotalAmountOfDevCards()), new Color(250, 235, 204));
				playerFont.drawString(playerSideRed.getWidth()/2*Windows.scFactor*0.6f - playerFont.getWidth("" + GameData.players.get(i).resourceAmount)/2, 300, Integer.toString(GameData.players.get(i).resourceAmount), new Color(250, 235, 204));
				playerPlace = 1;
				
			} else if (i != GameData.ownIndex && playerPlace == 1 ){
				if(i == 0){
					playerTopOrange.draw(Windows.scWidth/2-playerTopRed.getWidth()/2*Windows.scFactor*0.6f,0,Windows.scFactor*0.6f);
				} else if(i == 1){
					playerTopBlue.draw(Windows.scWidth/2-playerTopRed.getWidth()/2*Windows.scFactor*0.6f,0,Windows.scFactor*0.6f);
				} else if(i == 2){
					playerTopRed.draw(Windows.scWidth/2-playerTopRed.getWidth()/2*Windows.scFactor*0.6f,0,Windows.scFactor*0.6f);
				} else if(i == 3){
					playerTopGreen.draw(Windows.scWidth/2-playerTopRed.getWidth()/2*Windows.scFactor*0.6f,0,Windows.scFactor*0.6f);
				}
				
				playerFont.drawString(Windows.scWidth/2 - 200, 50, GameData.players.get(i).getName(), new Color(250, 235, 204));
				playerFont.drawString(Windows.scWidth/2 - 60, 50, Integer.toString(GameData.players.get(i).points), new Color(250, 235, 204));
				if(GameData.longestRoad==i){
					playerFont.drawString(Windows.scWidth/2 + 10, 50, "YES", new Color(250, 235, 204));
				} else{
					playerFont.drawString(Windows.scWidth/2 + 10, 50, "NO", new Color(250, 235, 204));
				}
				playerFont.drawString(Windows.scWidth/2 + 90, 50, Integer.toString(GameData.players.get(i).getTotalAmountOfDevCards()), new Color(250, 235, 204));
				playerFont.drawString(Windows.scWidth/2 + 160, 50, Integer.toString(GameData.players.get(i).resourceAmount), new Color(250, 235, 204));
				playerPlace = 2;
				
			} else if (i != GameData.ownIndex && playerPlace == 2 ){
				if(i == 0){
					playerSideOrange.draw(Windows.scWidth-playerSideRed.getWidth()*Windows.scFactor*0.6f,0,Windows.scFactor*0.6f);
				} else if(i == 1){
					playerSideBlue.draw(Windows.scWidth-playerSideRed.getWidth()*Windows.scFactor*0.6f,0,Windows.scFactor*0.6f);
				} else if(i == 2){
					playerSideRed.draw(Windows.scWidth-playerSideRed.getWidth()*Windows.scFactor*0.6f,0,Windows.scFactor*0.6f);
				} else if(i == 3){
					playerSideGreen.draw(Windows.scWidth-playerSideRed.getWidth()*Windows.scFactor*0.6f,0,Windows.scFactor*0.6f);
				}
				playerFont.drawString(Windows.scWidth - playerSideRed.getWidth()/2*Windows.scFactor*0.6f - playerFont.getWidth("" + GameData.players.get(i).getName())/2, 10, GameData.players.get(i).getName(), new Color(250, 235, 204));
				playerFont.drawString(Windows.scWidth -playerSideRed.getWidth()/2*Windows.scFactor*0.6f - playerFont.getWidth("" + GameData.players.get(i).points)/2, 80, Integer.toString(GameData.players.get(i).points), new Color(250, 235, 204));
				if(GameData.longestRoad==i){
					playerFont.drawString(Windows.scWidth -playerSideRed.getWidth()/2*Windows.scFactor*0.6f - playerFont.getWidth("YES")/2, 150, "YES", new Color(250, 235, 204));
				} else{
					playerFont.drawString(Windows.scWidth -playerSideRed.getWidth()/2*Windows.scFactor*0.6f - playerFont.getWidth("NO")/2, 150, "NO", new Color(250, 235, 204));
				}
				playerFont.drawString(Windows.scWidth -playerSideRed.getWidth()/2*Windows.scFactor*0.6f - playerFont.getWidth("" + GameData.players.get(i).getTotalAmountOfDevCards())/2, 230, Integer.toString(GameData.players.get(i).getTotalAmountOfDevCards()), new Color(250, 235, 204));
				playerFont.drawString(Windows.scWidth -playerSideRed.getWidth()/2*Windows.scFactor*0.6f - playerFont.getWidth("" + GameData.players.get(i).resourceAmount)/2, 300, Integer.toString(GameData.players.get(i).resourceAmount), new Color(250, 235, 204));				playerPlace = 0;
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
	
	private void sendTradeRequest(Button button) {
		for (int i = 0; i < sendButtons.length; i++) {
			if (button == sendButtons[i]) {
				TradeObject t = new TradeObject(trading, tradingRec, GameData.ownIndex, i);
				Actions.initiateTrade(t);
				break;
			}
		}
		
		isInitializingTrade = false;
		
		for (int i = 0; i < sendButtons.length; i++) {
			sendButtons[i].remove(true);
		}
		
		for (int i = 0; i < adjustButtons.length; i++) {
			adjustButtons[i].remove(true);
		}
		
		for (int i = 0; i < adjustRecButtons.length; i++) {
			adjustRecButtons[i].remove(true);
		}
		
		cancleButton.remove(true);
		
		tradeBox.deactivate();
	}

	public static void acceptedTrade() {
		tradeAcceptBox.activate();
	}
	
	public static void declinedTrade() {
		tradeDeclineBox.activate();
	}

	public static void chooseTrade() {
		tradeChooseBox.activate();
		tradeChooseBox.addString(GameData.players.get(GameData.tObject.initPlayer).getName() + " wants: ", 200, 125 / 2 + 40);
		tradeChooseBox.addString("You will get: ", 200, 225 / 2 + 40 + 225 + 100);
		isChoosingTrade = true;
		
		acceptTrade = new Button(Windows.scWidth / 2 - 50 - 200, 200 + 100 + Windows.scHeight / 2, 100, 50, 30, "Accept", thisState, true) {
			@Override
			public void isClicked() {
				acceptTrade();
			}
		};
		
		declineTrade = new Button(Windows.scWidth / 2 - 50 + 200, 200 + 100 + Windows.scHeight / 2, 100, 50, 30, "Decline", thisState, true) {
			@Override
			public void isClicked() {
				declineTrade();
			}
		};
	}

	public static void declineTrade() {
		isChoosingTrade = false;
		
		tradeChooseBox.deactivate();
		acceptTrade.remove(true);
		declineTrade.remove(true);
		tradeChooseBox.removeString();
		tradeChooseBox.removeString();
		Actions.declineTrade();
	}

	public static void acceptTrade() {
		isChoosingTrade = false;
		
		tradeChooseBox.deactivate();
		acceptTrade.remove(true);
		declineTrade.remove(true);
		tradeChooseBox.removeString();
		tradeChooseBox.removeString();
		Actions.acceptTrade();
	}
	
	public void cancleTrade() {
		isInitializingTrade = false;
		
		if (sendButtons != null) {
			for (int i = 0; i < sendButtons.length; i++) {
				sendButtons[i].remove(true);
			}
		}
		
		for (int i = 0; i < adjustButtons.length; i++) {
			adjustButtons[i].remove(true);
		}
		
		for (int i = 0; i < adjustRecButtons.length; i++) {
			adjustRecButtons[i].remove(true);
		}
		
		cancleButton.remove(true);
		
		tradeBox.deactivate();
	}
	/**
	 * Method taking care of the dialogue box when the game is finished. 
	 * It lists all victor and all the other players
	 */
	public static void endGame(){
		victoryWarning = new DialogBox(Windows.scWidth/2 - 250, Windows.scHeight/2 - 250, 500, 500, 30, thisState);
		for(int i = 0; i < GameData.players.size(); i ++){
			if(GameData.players.get(i).points == GameData.players.get(i).tempPoints){
					victoryWarning.addString("Victory", Windows.scWidth/2, Windows.scHeight/2 - 200);
					victoryWarning.addString("Congratulation", Windows.scWidth/2, Windows.scHeight/2 - 200 + tempFont.getHeight("IGF"));
			}else {
					victoryWarning.addString("Defeat", Windows.scWidth/2, Windows.scHeight/2 - 200);
					victoryWarning.addString("Condolences", Windows.scWidth/2, Windows.scHeight/2 - 200 + tempFont.getHeight("IGF"));
			}
			victoryWarning.addString("Ranking", Windows.scWidth/2, Windows.scHeight/2 - 200 + (2*tempFont.getHeight("IGF")));	
			victoryWarning.addString("Name: " + GameData.players.get(i).getName() + " Points: " + GameData.players.get(i).points, Windows.scWidth/2, Windows.scHeight/2 - 200 + ((i+3)*tempFont.getHeight("IGF")));
		}
	}
}
