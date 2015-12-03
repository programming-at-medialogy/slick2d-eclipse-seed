import java.io.IOException;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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
	
	DieRoll(Controller control, int x, int y) throws SlickException{
		this.control = control;
		this.x = x;
		this.y = y;
		roll1 = 1;
		roll2 = 1;
		diceOne = new Dice(10, 600,1);
		diceTwo = new Dice(100,600,1);
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{

		diceOne.render(gc, g);
		diceTwo.render(gc, g);

	}
	
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
	
	
	public int rollDice() throws IOException{
		roll1 = rand.nextInt(6)+1;
		roll2 = rand.nextInt(6)+1;
		int sum = roll1+roll2;
		
		for(int i = 0; i < control.tileNumber.length; i++){
			if(control.tileNumber[i]== sum){
				//control.dieNumber = i;
				control.tileResource[indexer] = i;
				System.out.println(i);
				indexer++;
				//break;
			}
		}
		//control.dieNumber = indexValue;
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