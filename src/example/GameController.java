package example;

import java.util.*;

/**
 *
 * The {@code GameController} is responsible for handling events during the game
 * session e.g. keeping track of the card stack.
 *
 */
public class GameController {

	// Databases for races and abilities
	ArrayList<Race> raceDb = new ArrayList<Race>();
	ArrayList<Ability> abilityDb = new ArrayList<Ability>();

	// Pair consisting of a Race and an Ability
	ArrayList<Pair> pairs = new ArrayList<Pair>();

	// The id of races and abilities
	ArrayList<Race> raceStack = new ArrayList<Race>();
	ArrayList<Ability> abilityStack = new ArrayList<Ability>();

	public void initDb() {

		// Initialize races
		raceDb.add(new Race("Race 1", 8, "backup", "conquer")); 
		raceDb.add(new Race("Race 2", 8, "backup", "conquer")); 
		raceDb.add(new Race("Race 3", 8, "backup", "conquer")); 
		raceDb.add(new Race("Race 4", 8, "backup", "conquer")); 

		// Initialize abilities
		abilityDb.add(new Ability("Ability 1", 5, "fly", "none"));
		abilityDb.add(new Ability("Ability 2", 5, "fly", "none"));
		abilityDb.add(new Ability("Ability 3", 5, "fly", "none"));
		abilityDb.add(new Ability("Ability 4", 5, "fly", "none"));
		abilityDb.add(new Ability("Ability 5", 5, "fly", "none"));
		abilityDb.add(new Ability("Ability 6", 5, "fly", "none"));
		abilityDb.add(new Ability("Ability 7", 5, "fly", "none"));
		abilityDb.add(new Ability("Ability 8", 5, "fly", "none"));

	}

	public void initializeStack() {

		for (int i = 0; i < raceDb.size(); i++) {
			raceStack.add(raceDb.get(i));
		}

		for (int i = 0; i < abilityDb.size(); i++) {
			abilityStack.add(abilityDb.get(i));
		}
	}

	/**
	 * The {@code shuffle()} method is responsible for shuffling the race id's
	 * and ability id's before creating the stack.
	 */
	public void shuffleStack() {

		Random r1 = new Random(Double.doubleToLongBits(Math.random()));
		Random r2 = new Random(Double.doubleToLongBits(Math.random()));

		Collections.shuffle(raceStack, r1);
		Collections.shuffle(abilityStack, r2);
	}
	
	public void addPair(Race race, Ability ability) {
		raceStack.add(race);
		abilityStack.add(ability);
	}
	
	public void removePair() {
		raceStack.remove(0);
		abilityStack.remove(0);
	}

	public void debugStack() {
		// list Race-id in the order it appears in the stack
		System.out.println("Race: ");
		for (int i = 0; i < raceStack.size(); i++) {

			System.out.println("Id: " + i + " Name: " + raceStack.get(i).name);

		}
		// list Ability-id in the order it appears in the stack
		System.out.println("Ability: ");
		for (int i = 0; i < abilityStack.size(); i++) {
			System.out.println("Id: " + i + " Name: " + abilityStack.get(i).name);
		}
	}
}