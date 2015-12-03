import java.io.IOException;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/*
 * Class to manage to dice in the game.
 * Is called with the use of a button and then updates and displays
 * the correct image connected to the random dice roll.
 */
public class DieRoll {
	
	Controller control;
	Game game;
	int roll1;
	int roll2;
	int indexValue;
	int indexer = 0;
	private Random rand = new Random();
	int x,y;
	Dice diceOne, diceTwo;
	
	//Constructor - Initialize variables
	DieRoll(Controller control, int x, int y) throws SlickException{
		this.control = control;
		this.x = x;
		this.y = y;
		roll1 = 1;
		roll2 = 1;
		diceOne = new Dice(10, 600,1);
		diceTwo = new Dice(100,600,1);
	}
	
	//Renders out the dice rolls
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		diceOne.render(gc, g);
		diceTwo.render(gc, g);
	}
	
	/*
	 * When the, Roll Dice, button is clicked a boolean is changed to true
	 * which updates the images on the dice that has to be displayed.
	 * It also sends the dice rolls to every other player in the game.
	 */
	public void update(GameContainer gc, int i) throws SlickException, IOException {
		
		//This makes sure that all clients are being shown the same number on the die;
		diceOne.index = game.client.obj.diceOneValue;
		diceTwo.index = game.client.obj.diceTwoValue;

		///------Sets the new dice values to the shared class and sends it to the server which then broadcasts it to all the clients.------///
		if(game.client.obj.playerTurn == control.playerNo && control.diceButtonClicked == true && control.clickOnce == 0){
			rollDice();
			sendDieData();
			game.client.obj.diceOneValue = roll1; 
			game.client.obj.diceTwoValue = roll2;
			game.client.sendData(game.client.obj);
			game.client.out.flush();
			control.diceButtonClicked = false;
			control.clickOnce = 1; //makes sure you can only roll the dice once every turn
		}
		
		
	}
	
	//Rolls the dice with random numbers
	public int rollDice() throws IOException{
		roll1 = rand.nextInt(6)+1;
		roll2 = rand.nextInt(6)+1;
		int sum = roll1+roll2;
		
		for(int i = 0; i < control.tileNumber.length; i++){
			if(control.tileNumber[i]== sum){
				control.tileResource[indexer] = i; //stores the index of the tile number with the value of the dice.
				System.out.println(i); //prints out the value - for developers to double check
				indexer++; //indexer to increment the initialization of the tile index storage.
			}
		}
		indexer = 0; //sets the indexer to 0 restarting the initialization.
		return sum; //return value of the die.
	}
	
	////calls the functions to distribute the resources to the player with houses on the die number rolled//
	public void sendDieData() throws IOException{
		control.distributeResources();
	}
	
	
	////Function for developer - checks the tile type of the given index and returns it////
	public int getTileType(int index){
		int tileIndex = control.tile[index][2];
		System.out.println("Gettiletype:"+tileIndex);
		return tileIndex;
	}
}