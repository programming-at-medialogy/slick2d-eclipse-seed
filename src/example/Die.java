package example;

import java.util.Collections;
import java.util.Random;

public class Die {
	
	
	public static int roll() {
		int[] sides = {0, 0, 0, 1, 2, 3};
		int result = new Random().nextInt(sides.length);
		return sides[result];
	}

	public static void result() {
		System.out.println("You roll the reinforcement die. Number rolled: " + roll());		
	}
	
}
