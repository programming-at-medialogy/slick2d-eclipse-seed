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
	boolean testBool = false;
	int test = 0;
	
	//Constructor
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
		
		diceOne.index = game.client.obj.diceOneValue;
		diceTwo.index = game.client.obj.diceTwoValue;

		if(game.client.obj.diceClick == true && game.client.obj.playerTurn == control.playerNo && control.diceButtonClicked == true && control.clickOnce == 0){
			rollDice();
			sendDieData();
			game.client.obj.diceOneValue = roll1;
			game.client.obj.diceTwoValue = roll2;
			game.client.sendData(game.client.obj);
			game.client.out.flush();
			control.diceButtonClicked = false;
			control.clickOnce = 1;
		}
		
		
	}
	
	//Rolls the dice with random numbers
	public int rollDice() throws IOException{
		roll1 = rand.nextInt(6)+1;
		roll2 = rand.nextInt(6)+1;
		int sum = roll1+roll2;
		
		for(int i = 0; i < control.tileNumber.length; i++){
			if(control.tileNumber[i]== sum){
				control.tileResource[indexer] = i;
				System.out.println(i);
				indexer++;
			}
		}
		indexer = 0;
		return sum;
	}
	
	public void sendDieData() throws IOException{
		getTileType(control.dieNumber);
		control.distributeResources();
	}
	
	public int getTileType(int index){
		int tileIndex = control.tile[index][2];
		System.out.println("Gettiletype:"+tileIndex);
		return tileIndex;
	}
}