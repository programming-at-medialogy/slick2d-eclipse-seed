import java.util.ArrayList;

/**
 * Created by kristianhjensen on 02/11/2015.
 */
public class Player  {
	//player name
	private String playerName;
	
	//player number
	public final int NUMBER;
	
	//Buildings
	//private Road[] road;
	
	//ressources
	public int [] resources = new int[5];
	
	int resourceAmount;
	
	//Number of knights
	private int knights;
	
	//Number of points
	private int points;
	
	//How many developmentcards the players has
	private int[] devCard;
	
	//Checks whether the player has to discard resourcecards or not
	//public boolean hasToDiscard;
	
	//Haswon
	private boolean hasWon;





	/**
	 * Constructor for the player classes, this constructor contains all information needed to generate
	 * a new player object for the game.
	 */

	public Player (String playerName, int playerNumber){
		this.playerName = playerName;
		this.NUMBER = playerNumber;
		points = 0;
		int[] resources = new int[5];
		//road = 0;
		knights = 0;

	}
	
	public void setPlayerName(String name) {
		this.playerName = name;
	}

	/**
	 * Collect the resources for a given player.
	 * @param dieRoll The number that was rolled by the die
	 */
	public void collectResources(int dieRoll) {
		for (int i = 0; i <  GameData.buildings.size(); i++) {
			Hexagon[] nearbyHexagons =  GameData.buildings.get(i).POSITION.getNearbyHexagons();
			for (int j = 0; j < nearbyHexagons.length; j++) {
				if (nearbyHexagons[j].NUMBER == dieRoll) {
					if ( GameData.buildings.get(i).isUpgraded()) {
						resources[nearbyHexagons[j].TYPE.toInt()]+=2;
						resourceAmount += 2;
					}
					resources[nearbyHexagons[j].TYPE.toInt()]++;
					resourceAmount++;
				}
				}
			}
		}
	
	


	/**
	 * Method for checking whether the player needs to discard resourcecards
	 */
	public static void setHasToDiscard(){
		boolean hasToDiscard;
		if(GameData.rolledNumber == 7){
			for(int i = 0; i < GameData.players.size(); i++){
				if(GameData.players.get(i).resourceAmount > 7){
					// main.show()
				} 
			}	
		}
	}
	
	public void discard(int[] index) {
		for (int i = 0; i < index.length; i++) {
			resources[index[i]]--;
			resourceAmount--;
		}
	}
	
	
	public void addDevelopmentCard(CardType type) {
		devCard[type.toInt()]++;
	}
	
	public void PlayDevCard(CardType type){
		switch(type) {
		case KNIGHT:
			//Move Robber
			//Take 1 resourceCard
			break;
		case VICTORYPOINT:
			++points;
			break;
		case YEAROFPLENTY:
			//show resource bank
			//addResource(); - method er lavet.
			break;
		case ROADBUILD:
			//Build a road
			//roadBuild();
			break;
		case MONOPOLY:
			//Select a resource whicht the other players must hand over.
			break;
		}
	}
	
	public void addResource(int[] index) {
		for (int i = 0; i < index.length; i++) {
			resources[index[i]]++;
			resourceAmount++;
	}
	}
	
	public void roadBuild (Position startPos, Position endPos) {
		Road.buildRoad(startPos, endPos, NUMBER);
		
	}
	




	public static void main(String[] args) {
		Player player1 = new Player("Lord",2);
		


	}


}