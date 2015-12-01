import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a full deck of developmentcards
 */
public class DevelopmentCardDeck  {

	/**
	 * Arraylist for a developmentdeck
	 */
    private static ArrayList<CardType> cards;
   
  /**
   * Constructor for creating a developmentCard deck, shuffling and add the number of specific cards you would like to have in it 
   * @param kn = number of KNIGHT
   * @param vp = number of VICTORYPOINT
   * @param rb = number of ROADBUILDS
   * @param yp = number of YEAROFPLENTYCARDS
   */
    public DevelopmentCardDeck(int kn, int vp, int rb, int yp){
    	
    	cards = new ArrayList();
    	for (int i = 0; i < kn; i++) {
    		cards.add(CardType.KNIGHT);
    	}
    	for (int i = kn; i < kn + vp; i++) {
    		cards.add(CardType.VICTORYPOINT);
    	}
    	for (int i = kn + vp; i < kn + vp + rb; i++) {
    		cards.add(CardType.ROADBUILD);
    	}
    	for (int i = kn + vp + rb; i < kn + vp + rb + yp; i++) {
    		cards.add(CardType.YEAROFPLENTY);
    	}
    	
    	shuffle(cards, cards.size() - 1);
    }
    
    /**
     * Method for shuffling the deck of developmentcards
     * @param array Specify the array which needs shuffling
     * @param length Length of the array
     */

    private static void shuffle(ArrayList<CardType> array, int length) {
		Random r = new Random();
		for (int i = length - 1; i > 0; i--) {
			int index = r.nextInt(i + 1);
			CardType temp = array.get(index);
			array.set(index, array.get(i));
			array.set(i, temp);
		}
	}

    /**
     * Returns the total number of development cards
     */

    public int getTotal(){
        return cards.size();
    }


    /**
     * Add a specific amount of cards to a specific type
     * @param amount = amount of cards
     * @param type = which type of card you would like to add.
     */
    public void add(int amount, int type) {
        cards.add(amount,CardType.fromInteger(type));
    }


    /**
     * Method for buying a developmentcard
     * @return the card bought
     */
    public static CardType BuyCard() {
    	if(cards.size() != 0 && Player.resources[ResourceType.CORN.toInt()]>=1 && Player.resources[ResourceType.ROCK.toInt()]>=1 && Player.resources[ResourceType.SHEEP.toInt()]>=1){
    	CardType returnCard = cards.get(0);
    	cards.remove(0);
        return returnCard;
    	}
    	System.out.println("No card");
    	return null;
    }

    /**
     * Clears the developmentCard deck.
     */
    public void clear(){
    	cards.clear();
   
    }
    
}

