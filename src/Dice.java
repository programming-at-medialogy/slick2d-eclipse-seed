

public class Dice {

	static int dice1 = 0;
	static int dice2 = 0;
	
static public int RollDice() {
	
	dice1 = (int) (Math.random()*6+1);
	dice2 = (int) (Math.random()*6+1);
	return dice1 + dice2;
	
}
}
