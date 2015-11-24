import java.util.ArrayList;

/**
 * Created by kristianhjensen on 02/11/2015.
 */
<<<<<<< HEAD
<<<<<<< HEAD
public class Player {
	//This particular clients index in the player-array
	public final int ownIndex;
	
=======
public class Player implements DevelopmentCardIF {
>>>>>>> origin/master
=======
public class Player implements DevelopmentCardIF {
	public final int ownIndex; //This particular clients index in the player-array
>>>>>>> parent of 931dcbe... Added Resource deck, updated Development deck+enum, and corrected all names of resources.
	//player name
	private String playerName;
	//player number
	private float playerNumber;
	//Buildings
	//private Road[] road;
	//ressources
	static int [] resources = new int[5];
	//Number of knights
	private int knights;
	//Number of points
	private int points;
	//How many developmentcards the players has
	private DevelopmentCard devCard;
	//Checks whether the player has to discard resourcecards or not
	private boolean hasToDiscard;
	//Haswon
	private boolean hasWon;

	DevelopmentCard developmentCard = new DevelopmentCard();



	/**
	 * Constructor for the player classes, this constructor contains all information needed to generate
	 * a new player object for the game.
	 */

	public Player (String playerName, int playerNumber){
		this.playerName = playerName;
		this.playerNumber = playerNumber;
		points = 0;
		int[] resources = new int[5];

		//road = 0;
		knights = 0;
		hasToDiscard = false;

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
					if ( GameData.buildings.get(i).isUpgraded())
						resources[nearbyHexagons[j].TYPE.toInt()]+=2;
					resources[nearbyHexagons[j].TYPE.toInt()]++;
				}
			}
		}
	}

	/**
	 * Method for drawing a resourcecard
	 */

	public void drawResourceCard(){

		resources[ResourceType.SPACEDEBRIS.toInt()]++;
		System.out.println(resources[ResourceType.SPACEDEBRIS.toInt()]);
	}

	/**
	 * Method for checking whether the player needs to discard resourcecards
	 */
	public void setHasToDiscard(){
		if(resources.length < 7 ){

		}

	}




	public static void main(String[] args) {
		Player player = new Player("LordHagen",5);


	}


}