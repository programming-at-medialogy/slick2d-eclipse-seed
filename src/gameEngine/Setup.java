package gameEngine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random; 
public class Setup{
	ArrayList<String> roleCards;
	ArrayList<City> infectCards;
	ArrayList<City> usedInfecCards;
	ArrayList<City> drawnPlayerCards;
	ArrayList<City> playerDeck;
	ArrayList<City> infectionDeck;
	Random ran;
	int x;
	
	Setup(){
	
		ran = new Random();
		
		roleCards = new ArrayList<>(Arrays.asList("Medic", "Dispatcher","Researcher", "Scientist","Consistency planner", "Operations expert"));
		Collections.shuffle(roleCards);
		infectionDeck = new ArrayList<City>();
		infectionDeck.add(new City("Seul",100,100,0 ));
		infectionDeck.add(new City("Atlanta",200,200,0 ));
		infectionDeck.add(new City("Rihadh",300,300,0 ));
		infectionDeck.add(new City( "Teheran",100,100,0 ));
		 infectionDeck.add(new City("San Francisco",200,200,0 ));
		 infectionDeck.add(new City("Washington",300,300,0 ));
		 infectionDeck.add(new City("Hong Kong",100,100,0 ));
		 infectionDeck.add(new City("Madrid",200,200,0 ));
		 infectionDeck.add(new City("Moscow",300,300,0 ));
		 infectionDeck.add(new City("Algiers",100,100,0 ));
		 infectionDeck.add(new City("Chicago",200,200,0 ));
		 infectionDeck.add(new City("London",300,300,0 ));
		 infectionDeck.add(new City("Paris",100,100,0 ));
		 infectionDeck.add(new City("New York",200,200,0 ));
		 infectionDeck.add(new City("Kolkata",300,300,0 ));
		 infectionDeck.add(new City("Tokyo",300,300,0 ));
		 Collections.shuffle(infectionDeck);
		 x = infectionDeck.size();
		 for(City str: infectionDeck){
			//System.out.println(str.toString());
		}
		 

		usedInfecCards = new ArrayList<>(Arrays.asList()); 						
		
		// adds one card from infection deck and place into used cards deck
		//usedInfecCards.add(infectCards.get(0)); 
		// removes one card from infection card deck
		// prints one card from the array
		//System.out.println(usedCardsArray.toString()); 
	
		ArrayList<City> playerDeck = new ArrayList<City>();
		playerDeck.add(new City("Seul",100,100,0 ));
		playerDeck.add(new City("Atlanta",200,200,0 ));
		playerDeck.add(new City("Rihadh",300,300,0 ));
		playerDeck.add(new City( "Teheran",100,100,0 ));
		playerDeck.add(new City("San Francisco",200,200,0 ));
		playerDeck.add(new City("Washington",300,300,0 ));
		playerDeck.add(new City("Hong Kong",100,100,0 ));
		playerDeck.add(new City("Madrid",200,200,0 ));
		playerDeck.add(new City("Moscow",300,300,0 ));
		playerDeck.add(new City("Algiers",100,100,0 ));
		playerDeck.add(new City("Chicago",200,200,0 ));
		playerDeck.add(new City("London",300,300,0 ));
		playerDeck.add(new City("Paris",100,100,0 ));
		playerDeck.add(new City("New York",200,200,0 ));
		playerDeck.add(new City("Kolkata",300,300,0 ));
		playerDeck.add(new City("Tokyo",300,300,0 ));
		
		Collections.shuffle( playerDeck);
			// new empty array list where program is going to place drawn cards.
		drawnPlayerCards = new ArrayList<>(Arrays.asList()); 		
		//System.out.println(drawnPlayerCards.toString()); 
		
		drawInfectionCards(1);

		
	}
	//This function should be at the server
	public void drawInfectionCards( int amountOfCardsDrawn){
		for (int i=0; i < amountOfCardsDrawn; i++ ){
			//int ran= Math.random()*0+infectionDeck.size();	
			// we put zero because of the dynamic arraylist
			

			usedInfecCards.add(infectionDeck.get(0));
			//String drawnCityName = usedInfecCards.getcityName();
			infectCities();
			infectCards.remove(0);
			
		}}
		

public void infectCities (){
	int cardsDrawn = usedInfecCards.size();
    usedInfecCards.get(0).setInfectionRate(usedInfecCards.get(0).getInfectionRate()+1);
    
    //System.out.println(usedInfecCards.get(0).getInfectionRate());
    System.out.println(usedInfecCards.size());
	}

public void asignRoles () {
	
}	

public void asignPlayerCards () {
	
}


	public static void main(String[] args) {
		Setup main = new Setup();
		
		
	}
	
}



	

