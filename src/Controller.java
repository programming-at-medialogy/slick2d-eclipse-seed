import java.io.IOException;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Controller {
	
	//Int to set the player number
	int playerNo;
	
	Game game;
	Resource resources;
	
	int receivedExCard = 0; //Int to control if a player gets an excavation card
	boolean receivedMonoCard = false; //boolean to control if a player gets a monopoly card
	boolean devCardButtonClicked = false; //checks if a player have bought a card
	
	int [][] tile = new int[19][3]; //2D array to store middle position
	int [] tileNumber = new int[19];
	int [][] settlement = new int[54][2];
	int dieNumber = 0;
	int [] tileResource = new int[2];
	boolean diceButtonClicked = false; //boolean to control if the dices have been clicked
	int testint = 0;
	int clickOnce = 0; //int to make sure the endturn button is only clicked once
	int [][] settlementData = new int[54][3];
	boolean deselectButtonControl; //boolean to the deselect button
	boolean tradeButtonControl;
	Random rand = new Random();
	int armyCounter = 0;
	int roadCounter = 0;
	
	Controller() throws SlickException{
		resources = new Resource(900,10,playerNo);
		playerNo = game.client.obj.playerNumber; //Set player number
		tileNumber = game.client.obj.SOCnumberIndex;
	}
	
	public void update(GameContainer gc, int i) throws SlickException, IOException {
		
		resources.oreResource = game.client.obj.playerResource[playerNo-1][0]; //ORE
		resources.wheatResource = game.client.obj.playerResource[playerNo-1][1]; //WHEAT
		resources.woodResource= game.client.obj.playerResource[playerNo-1][2]; //WOOD
		resources.clayResource = game.client.obj.playerResource[playerNo-1][3]; //CLAY
		resources.woolResource = game.client.obj.playerResource[playerNo-1][4]; //WOOL

		checkArmySize();
		checkRoadSize();
		
		
		for(int j = 0; j < settlementData.length; j++){
			settlementData[j][0] = game.client.obj.settlementPos[j][0];
			settlementData[j][1] = game.client.obj.settlementPos[j][1];
			settlementData[j][2] = game.client.obj.settlementPos[j][2];
		}
		
		
		//Code for most roads...
		/*for(int j = 0; j < game.client.obj.playerRoadCount.length;j++){
			if(game.client.obj.playerRoadCount[playerNo-1][0]>game.client.obj.playerRoadCount[j][0]){
				resources.victoryPoint++;
				break;
			}
		}*/
	}
	
	public void distributeResources() {
		

		for (int i = 0; i < settlementData.length; i++) {
			if (settlementData[i] != null) {
				distance(settlementData[i][0], settlementData[i][1], tile[tileResource[0]][0],
						tile[tileResource[0]][1], tile[tileResource[0]][2],settlementData[i][2]);
				
			}
		}

		
		for (int i = 0; i < settlementData.length; i++) {
			if (settlementData[i] != null) {
				distance(settlementData[i][0], settlementData[i][1], tile[tileResource[1]][0],
						tile[tileResource[1]][1], tile[tileResource[1]][2],settlementData[i][2]);
				
			}
		}

		
		for (int i = 0; i < tileResource.length; i++){
			tileResource[i] = 0;
		}

		
	}
	
	/*
	 * A method to measure the distance between the middle point of a tile and a settlement
	 * Checks the index of the tile to know what resource must be distributed
	 * and the playerIndex tells who needs to get the resource.
	 */
	public void distance(int x, int y, int x2, int y2, int tileIndex, int playerIndex) {
		int i = 0;
		int minDist = 60;
		i = (int) Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2));
		
		if (i < minDist && tileIndex == 0) {
			game.client.obj.playerResource[playerIndex][0]++;
			System.out.println("ore");
		}
		if (i < minDist && tileIndex == 1) {
			game.client.obj.playerResource[playerIndex][1]++;
			System.out.println("wheat");
		}
		if (i < minDist && tileIndex == 2) {
			game.client.obj.playerResource[playerIndex][rand.nextInt(5)]++;
		}
		if (i < minDist && tileIndex == 3) {
			game.client.obj.playerResource[playerIndex][2]++;
			System.out.println("wood");
		}
		if (i < minDist && tileIndex == 4) {
			game.client.obj.playerResource[playerIndex][3]++;
			System.out.println("clay");
		}
		if (i < minDist && tileIndex == 5) {
			game.client.obj.playerResource[playerIndex][4]++;
			System.out.println("wool");
		}
		
	}
	
	
	public void checkArmySize() throws IOException{
		if(armyCounter == 5){
			game.client.obj.playerVictoryPoints[playerNo-1][0]++;
			game.client.sendData(game.client.obj);
			resources.victoryPoint++;
			armyCounter = 0;
		}
	}
	
	public void checkRoadSize() throws IOException{
		if(roadCounter == 10){
			game.client.obj.playerVictoryPoints[playerNo-1][0]++;
			game.client.sendData(game.client.obj);
			resources.victoryPoint++;
			roadCounter = 0;
		}
	}

}