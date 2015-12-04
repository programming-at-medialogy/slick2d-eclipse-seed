
package gameEngine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
// Server
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Server {
	//Server
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static BufferedReader bufferedReader;
	private static String inputLine;
	//Server end
	ArrayList<String> roleCards;
	ArrayList<String> infectionDeck;
	ArrayList<String> usedInfecCards;
	ArrayList<String> drawnPlayerCards;
	ArrayList<String> playerDeck;
	ArrayList<City> citiesOnBoard;

	Random ran;
	int x;
	int initialPos;

	public int totalCardsDrawn;

	Server() {

		ran = new Random();
		roleCards = new ArrayList<>(Arrays.asList("Medic", "Dispatcher", "Researcher", "Scientist","Consistency planner", "Operations expert"));
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

		playerDeck = new ArrayList<>(Arrays.asList("Sydney", "Atlanta", "Hong Kong", "Johannesburg",
				"Algiers", "Moscow", "Washington", "Kolkata", "London", "Bejing", "New York", "Chicago", "Tromso",
				"Paris", "Tokyo", "San Francisco"));
		Collections.shuffle(playerDeck);
		
		citiesOnBoard = new ArrayList<City>();
		citiesOnBoard.add(new City("Sydney", 1688, 906, 0));
		citiesOnBoard.add(new City("Johannesburg", 1062, 860, 0));
		citiesOnBoard.add(new City("Beijing", 1540, 282, 0));
		citiesOnBoard.add(new City("San Francisco", 324, 402, 0));
		citiesOnBoard.add(new City("Washington", 592, 490, 0));
		citiesOnBoard.add(new City("Hong Kong", 1502, 582, 0));
		citiesOnBoard.add(new City("Tromso", 1038, 214, 0));
		citiesOnBoard.add(new City("Moscow", 1168, 354, 0));
		citiesOnBoard.add(new City("Algiers", 958, 514, 0));
		citiesOnBoard.add(new City("Chicago", 460, 340, 0));
		citiesOnBoard.add(new City("London", 918, 322, 0));
		citiesOnBoard.add(new City("Paris", 1018, 380, 0));
		citiesOnBoard.add(new City("New York", 574, 384, 0));
		citiesOnBoard.add(new City("Kolkata", 1426, 490, 0));
		citiesOnBoard.add(new City("Atlanta", 428, 400, 0));
		citiesOnBoard.add(new City("Tokyo", 1676, 368, 0));

		// new empty array list where program is going to place drawn cards.
		drawnPlayerCards = new ArrayList<>(Arrays.asList());
		// System.out.println(drawnPlayerCards.toString());
		System.out.println(citiesOnBoard.get(13).getcityPosX());
		//draws and infects amount of cards
		drawInfectionCards(5);
	}

	// This function should be at the server
	// Draws number of city cards and calls infect function on them
	public void drawInfectionCards(int amountOfCardsDrawn) {
		System.out.println("Cards drawn: " + amountOfCardsDrawn);
		for (int i = 0; i < amountOfCardsDrawn; i++) {
			usedInfecCards.add(infectionDeck.get(0));
			infectCities();
			infectionDeck.remove(0);
		}
	}

	public void infectCities() {
		totalCardsDrawn = usedInfecCards.size();
		int totalCardsDrawn = usedInfecCards.size();
		citiesOnBoard.get(totalCardsDrawn).setInfectionRate(+1);
		//System.out.println(citiesOnBoard.get(totalCardsDrawn).getcityName()+ " was infected. It's current infection rate is " + citiesOnBoard.get(totalCardsDrawn).getInfectionRate());
	}

	
	public String assignRole(){
		String playerRole;
		playerRole = roleCards.get(0);
		roleCards.remove(0);
		return playerRole;
	}

	public void asignPlayerCards(int amountOfPlayerCards) {
		for (int i = 0; i < amountOfPlayerCards; i++) {
			drawnPlayerCards.add(playerDeck.get(0));
			playerDeck.remove(0);
		}
	}
	// Finds city index from citiesOnBoard array according input String city 
	public int findCity(String city) {
		int foundMatchingCity = 0;
		for (int i = 0; i < citiesOnBoard.size(); i++) {
			if (city == citiesOnBoard.get(i).getcityName()) {
				foundMatchingCity = i;
			}
		}
		//System.out.println(city+" is index " + foundMatchingCity + " in citiesOnBoard array.");
		return foundMatchingCity;
	}

	public static void main(String[] args) {
		Server server = new Server ();
		try
		{
			serverSocket = new ServerSocket(9000);
			System.out.println("Server is running");
			clientSocket = serverSocket.accept();
			
			// Create a reader
			bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			// Get the client message
			while((inputLine = bufferedReader.readLine()) != null)
			System.out.println(inputLine);

			
		}
		catch(IOException e)
		{
			System.out.println(e);
		}

	}

}
