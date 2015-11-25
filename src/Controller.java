

/*
 * General controller class to control different things in the game
 */

public class Controller {
	
	Game game;

	int playerNo = game.client.obj.playerNumber; //Set player number
	
	boolean isPlayerTurn; //Boolean to see if it is the players turn

	int placeHouseAmount; //Int to set the amount of houses the player is allowed to put
	int placeRoadAmount; //Int to set the amount of roads the player is allowed to put
	
	Controller() {
		isPlayerTurn = true; //Set the boolean to default true
		placeHouseAmount = 2; //set the amount of houses to default 2
		placeRoadAmount = 2; //set the amount of roads to default 2
		
		//if statement to reset to 2 houses if the player for some reason gets allowed to place more than 2 houses/roads
		//not sure if we will be needing this though-
		if(placeHouseAmount > 2 || placeRoadAmount > 2) {
			placeHouseAmount = 2;
			placeRoadAmount = 2;
		}	
	}
	
	//method to reduce the amount of houses if the player places a house
	public int reduceHouseAmount() {
		return placeHouseAmount -= 1;
	}

	//method to reduce the amount of roads if the player places a road
	public int reduceRoadAmount() {
		return placeRoadAmount -= 1;
	}
	
	//This method can be called to refill the amount of houses the player have
	//idea is that it will be called upon next turn.
	public int refilHouses () {
		return placeHouseAmount = 2;
	}
	
	//Same, just for roads
	public int refilRoads () {
		return placeRoadAmount = 2;
	}
	
	//Method to be called if the player press ends turn button (not implemented yet)
	public boolean endPlayerTurn() {
		return isPlayerTurn = false;
	}
}
