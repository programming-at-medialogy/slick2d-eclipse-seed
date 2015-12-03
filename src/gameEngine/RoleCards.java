package gameEngine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RoleCards {
	ArrayList<String> roleCards;
	
	RoleCards () {
		roleCards = new ArrayList<>(Arrays.asList("Medic", "Dispatcher","Researcher",
				"Scientist","Consistency planner", "Operations expert"));
		Collections.shuffle(roleCards);
		System.out.println(roleCards);	
	}
	
	
	public static void main(String[] args) {
	RoleCards roleCards= new RoleCards();

	}

}
