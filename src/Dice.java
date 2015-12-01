public class Dice {

	static int dice1 = 1;
	static int dice2 = 1;
	
static public int RollDice(int index) {
	
	if (index ==1){
		dice1 = (int) (Math.random()*6+1);
		return dice1;
	}
	else if (index ==2){
		dice2 = (int) (Math.random()*6+1);
	}		return dice2;

	}
}
