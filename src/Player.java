import java.util.ArrayList;

/**
 * Created by kristianhjensen on 02/11/2015.
 */
public class Player {
	//This particular clients index in the player-array
	public final int ownIndex;
	
	//player name
	private String playerName;
	
	//player number
	private float playerNumber;
	
	//Buildings
	//private Road[] road;
	
	//ressources
	int [] resources = new int[5];
	
	int resourceAmount;
	
	//Number of knights
	private int knights;
	
	//Number of points
	private int points;
	
	//How many developmentcards the players has
	private DevelopmentCard devCard;
	
	//Checks whether the player has to discard resourcecards or not
	boolean hasToDiscard;
	
	//Haswon
	private boolean hasWon;





	/**
	 * Constructor for the player classes, this constructor contains all information needed to generate
	 * a new player object for the game.
	 */

	public Player (String playerName, int playerNumber, int ownIndex){
		this.ownIndex = ownIndex;
		this.playerName = playerName;
		this.playerNumber = playerNumber;
		points = 0;
		int[] resources = new int[5];
		//road = 0;
		knights = 0;
		hasToDiscard = false;

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
	public void setHasToDiscard(){
		//Needs a resource class to be created.
		}
	





	public static void main(String[] args) {
		Player player1 = new Player("Lord",2,2);
		


	}


}