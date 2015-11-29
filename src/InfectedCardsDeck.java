import java.util.Arrays;
		import java.util.ArrayList;
		import java.util.Collections;
public class InfectedCardsDeck {
	public static void main(String[] args) {
		//creating an array list with  unshuffled cards
		ArrayList<String> infecArray = new ArrayList<>(Arrays.asList("Seul","Atlanta","Rihadh",
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
		 Collections.shuffle(infecArray);
		 
		// new empty array list where program is going to place drawn cards.
			ArrayList<String> usedCardsArray = new ArrayList<>(Arrays.asList()); 						
		
			
			// adds one card from infection deck and place into used cards deck
			usedCardsArray.add(infecArray.get(0)); 
			// removes one card from infection card deck
			infecArray.remove(0);
			// prints one card from the array
			System.out.println(usedCardsArray.toString()); 
			
			
			
		

					
		}
	}


