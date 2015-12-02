package gameEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Setup {
	ArrayList<String> roleCards;
	ArrayList<String> infectionDeck;
	ArrayList<String> usedInfecCards;
	ArrayList<String> drawnPlayerCards;
	static ArrayList<String> playerDeck;
	ArrayList<City> citiesOnBoard;

	Random ran;
	int x;
	int initialPos;
	int foundMatchingCity;

	Setup() {

		ran = new Random();

		roleCards = new ArrayList<>(Arrays.asList("Medic", "Dispatcher", "Researcher", "Scientist",
				"Consistency planner", "Operations expert"));
		Collections.shuffle(roleCards);

		infectionDeck = new ArrayList<>(Arrays.asList("Sydney", "Atlanta", "Hong Kong",
				"Johannesburg", "Algiers", "Moscow", "Washington", "Kolkata", "London", "Bejing", "New York", "Chicago",
				"Tromso", "Paris", "Tokyo", "San Francisco"));
		// this method shuffles
		Collections.shuffle(infectionDeck);

		usedInfecCards = new ArrayList<>(Arrays.asList());

		// adds one card from infection deck and place into used cards deck
		// usedInfecCards.add(infectCards.get(0));
		// removes one card from infection card deck
		// prints one card from the array
		// System.out.println(usedCardsArray.toString());

		ArrayList<String> playerDeck = new ArrayList<>(Arrays.asList("Sydney", "Atlanta", "Hong Kong", "Johannesburg",
				"Algiers", "Moscow", "Washington", "Kolkata", "London", "Bejing", "New York", "Chicago", "Tromso",
				"Paris", "Tokyo", "San Francisco"));
		Collections.shuffle(playerDeck);

		citiesOnBoard = new ArrayList<City>();
		citiesOnBoard.add(new City("Sydney", 100, 100, 0));
		citiesOnBoard.add(new City("Johannesburg", 300, 300, 0));
		citiesOnBoard.add(new City("Beijing", 100, 100, 0));
		citiesOnBoard.add(new City("San Francisco", 200, 200, 0));
		citiesOnBoard.add(new City("Washington", 300, 300, 0));
		citiesOnBoard.add(new City("Hong Kong", 100, 100, 0));
		citiesOnBoard.add(new City("Tromso", 200, 200, 0));
		citiesOnBoard.add(new City("Moscow", 300, 300, 0));
		citiesOnBoard.add(new City("Algiers", 100, 100, 0));
		citiesOnBoard.add(new City("Chicago", 200, 200, 0));
		citiesOnBoard.add(new City("London", 300, 300, 0));
		citiesOnBoard.add(new City("Paris", 100, 100, 0));
		citiesOnBoard.add(new City("New York", 200, 200, 0));
		citiesOnBoard.add(new City("Kolkata", 300, 300, 0));
		citiesOnBoard.add(new City("Atlanta", 200, 200, 0));
		citiesOnBoard.add(new City("Tokyo", 300, 300, 0));

		// new empty array list where program is going to place drawn cards.
		drawnPlayerCards = new ArrayList<>(Arrays.asList());
		// System.out.println(drawnPlayerCards.toString());

		drawInfectionCards(9);
		//asignPlayerCards(4);


	}

	// This function should be at the server
	// Draws number of city cards and calls infect function on them
	public void drawInfectionCards(int amountOfCardsDrawn) {
		for (int i = 0; i < amountOfCardsDrawn; i++) {
			usedInfecCards.add(infectionDeck.get(0));
			infectCities();
			infectionDeck.remove(0);
		}
	}

	public void infectCities() {
		int totalCardsDrawn = usedInfecCards.size();
		//usedInfecCards.get(usedInfecCards.size() - 1).setInfectionRate(usedInfecCards.get(usedInfecCards.size() - 1).findCity());
		citiesOnBoard.get(totalCardsDrawn).setInfectionRate(+1);
		
		System.out.println(citiesOnBoard.get(totalCardsDrawn).getcityName()+ " was infected. It's current infection rate is " + citiesOnBoard.get(totalCardsDrawn).getInfectionRate());
		

		//System.out.println(usedInfecCards.get(usedInfecCards.size() - 1).getInfectionRate());
		// System.out.println(usedInfecCards.size());
	}

	public void asignRoles() {

	}

	public void asignPlayerCards(int amountOfPlayerCards) {
		for (int i = 0; i < amountOfPlayerCards; i++) {
			drawnPlayerCards.add(playerDeck.get(0));
			playerDeck.remove(0);
		}
	}
	// Finds city index from citiesOnBoard array according input String city 
	public int findCity(String city) {
		for (int i = 0; i < citiesOnBoard.size(); i++) {
			if (city == citiesOnBoard.get(i).getcityName()) {
				int foundMatchingCity = i;
				System.out.println(city+" is index " + foundMatchingCity + " in citiesOnBoard array.");
			}
		}
		return foundMatchingCity;
	}

	public static void main(String[] args) {
		Setup main = new Setup();

	}

}
