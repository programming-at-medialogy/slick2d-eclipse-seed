

import java.util.Random;
/*
 * When we get to the part of implementing buttons, this will have to be updated
 * to accommodate the certain action bar.
 * Should also get the information of which player is rolling the dice.
 */
public class DieRoll {
	private int roll1, roll2;
	
	private Random diceRoller = new Random();
	
	public int rollDice(){
		roll1 = diceRoller.nextInt(6)+1;
		roll2 = diceRoller.nextInt(6)+1;
		
		int sum = roll1+roll2;
		return sum;
		
		//System.out.println("'color' Player rolled: " + sum);
	}
}