package example;

import java.lang.reflect.Array;
import java.util.*;

import com.sun.org.apache.bcel.internal.classfile.Code;

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

	// The stack of races and abilities
	// ArrayList<Template> pair = new ArrayList<Template>();

	// Template[] pair = new Template[2];
	// ArrayList<Template[]> stack = new ArrayList<Template[]>();

	ArrayList<Pair> pairs = new ArrayList<Pair>();

	// The id of races and abilities
	ArrayList<Integer> raceId = new ArrayList<Integer>();
	ArrayList<Integer> abilityId = new ArrayList<Integer>();

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

	}

	public void initializeStack() {

		for (int i = 0; i < raceDb.size(); i++) {
			raceId.add(i);
		}

		for (int i = 0; i < abilityDb.size(); i++) {
			abilityId.add(i);
		}
	}

	/**
	 * The {@code shuffle()} method is responsible for shuffling the race id's and ability id's before creating the stack.
	 */
	
	public void shuffle() {
		long seed = System.nanoTime();
		Collections.shuffle(raceId, new Random(seed));
		Collections.shuffle(abilityId, new Random(seed));
	}

	public void createStack() {
		if (raceDb.size() > abilityDb.size()) {

			for (int i = 0; i < abilityDb.size(); i++) {
				pairs.add(new Pair(raceDb.get((int) raceId.get(i)), abilityDb.get((int) abilityId.get(i))));
				pairs.add(new Pair(raceDb.get((int) raceId.get(i)), abilityDb.get((int) abilityId.get(i))));
			}

		} else {
			for (int i = 0; i < raceDb.size(); i++) {
				pairs.add(new Pair(raceDb.get((int) raceId.get(i)), abilityDb.get((int) abilityId.get(i))));
				pairs.add(new Pair(raceDb.get((int) raceId.get(i)), abilityDb.get((int) abilityId.get(i))));

			}
		}
	}

	public void debugStack() {
		// list Race-id in the order it appears in the stack
		System.out.println("Race: ");
		for (int i = 0; i < raceDb.size(); i++) {

			System.out.println("Id: " + raceId.get(i) + " Name: " + raceDb.get((int) raceId.get(i)).name);

		}
		// list Ability-id in the order it appears in the stack
		System.out.println("Ability: ");
		for (int i = 0; i < abilityDb.size(); i++) {

			System.out.println("Id: " + abilityId.get(i) + " Name: " + abilityDb.get((int) abilityId.get(i)).name);

		}

	}

}