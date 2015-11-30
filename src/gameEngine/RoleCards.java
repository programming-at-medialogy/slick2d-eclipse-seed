package gameEngine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RoleCards {

	public static void main(String[] args) {
		ArrayList<String> roleCards = new ArrayList<>(Arrays.asList("Medic", "Dispatcher","Researcher",
				"Scientist","Consistency planner", "Operations expert"));
		Collections.shuffle(roleCards);
		System.out.println(roleCards);

	}

}
