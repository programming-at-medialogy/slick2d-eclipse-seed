package gameEngine;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Setup{
	ArrayList<String> roleCards;
	ArrayList<String> infectCards;
	ArrayList<String> usedinfecCards;
	ArrayList<String> drawnPlayerCards;
	ArrayList<String> playerDeck;
	
	Setup(){
		roleCards = new ArrayList<>(Arrays.asList("Medic", "Dispatcher","Researcher", "Scientist","Consistency planner", "Operations expert"));
		Collections.shuffle(roleCards);
		//System.out.println(roleCards);
		//creating an array list with  unshuffled cards
		infectCards = new ArrayList<>(Arrays.asList("Seul ","Atlanta","Rihadh",
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
	           "Tokyo","Ho Chi Minh city","San Francisco"));
		//this method shuffles
		 Collections.shuffle(infectCards);
		 
		 // new empty array list where program is going to place drawn cards.
		usedinfecCards = new ArrayList<>(Arrays.asList()); 						
		
		// adds one card from infection deck and place into used cards deck
		usedinfecCards.add(infectCards.get(0)); 
		// removes one card from infection card deck
		infectCards.remove(0);
		// prints one card from the array
		//System.out.println(usedCardsArray.toString()); 
	
		playerDeck = new ArrayList<>(Arrays.asList("Seul","Atlanta","Rihadh",
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
		drawnPlayerCards = new ArrayList<>(Arrays.asList()); 		
		//System.out.println(drawnPlayerCards.toString()); 
		
		System.out.println(usedinfecCards);
						
	}
	//This function should be at the server
	void drawInfectionCards( int amountOfCardsDrawn){
		for (int i=0; i < amountOfCardsDrawn; i++ ){
			
			// we put zero because of the dynamic arraylist
			usedinfecCards.add(infectCards.get(0)); 
			infectCities();
			infectCards.remove(0);
			
		}}
		
void infectCities (){
	usedinfecCards.size();
		
	}

	
	public static void main(String[] args) {
		Setup main = new Setup();
		
	}



	
}
