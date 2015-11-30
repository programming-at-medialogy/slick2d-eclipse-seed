package gameEngine;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Collections;

public class PlayerCardsDeck {

	public static void main(String[] args) {
	
		ArrayList<String> playerDeck = new ArrayList<>(Arrays.asList("Seul","Atlanta","Rihadh",
		           "Teheran","Baghdad","Sao Paulo",
		           "Bogota","Buenos Aires","Montreal",
		           "Miami","St.Petersburg","Mumbai",
		           "Manila","Cairo","Hong Kong",
		           "Shanghai","Algiers","Mexico city",
		           "Sydney","Madrid","Lima",
		           "Lagos","Karachi","Osaka",
		           "Moscow","Delhi","Washington",
		           "Istambul","Jakarta","Bejing",
		           "Kolkata","Bangkok","London",
		           "New York","Kinshasa","Chicago",
		           "Paris","Taipei","Milan",
		           "Santiago","Johannesburg","Essen",
		           "Khartoum","Chennai","Los Angeles",
		           "Tokyo","Ho Chi Minh city","San Francisco",
		           "Epidemic card","Epidemic card","Epidemic card",
		           "Epidemic card","Epidemic card","Epidemic card"));
		 Collections.shuffle( playerDeck);
		 
		// new empty array list where program is going to place drawn cards.
					ArrayList<String> drawnPlayerCards = new ArrayList<>(Arrays.asList()); 						
				
					
					// adds one card from player deck and place into used cards deck
					drawnPlayerCards.add(playerDeck.get(0)); 
					// removes one card from player card deck
					playerDeck.remove(0);
					// prints one card from the array
					System.out.println(drawnPlayerCards.toString()); 
	}

}
