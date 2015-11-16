package com.company;


import java.util.ArrayList;

/**
 * Created by kristianhjensen on 02/11/2015.
 */
public class Player implements DevelopmentCardIF {
	//player name
	private String playerName;
	//player number
	private float playerNumber;
	//Buildings
	private ArrayList<Building> buildings;
	//private Road[] road;
	//ressources
	private int [] resources = new int[5];
	//Number of knights
	private int knights;
	//Number of points
	private int points;
	//turn
	private static int turn;
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
		buildings = new ArrayList<>();
		int[] resources = new int[5];

		//road = 0;
		knights = 0;
		turn = 0;
		hasToDiscard = false;

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

	public void addBuilding(Position position) {
		buildings.add(Building.build(position));
	}




	public static void main(String[] args) {
		Player player = new Player("LordHagen",5);


	}


}