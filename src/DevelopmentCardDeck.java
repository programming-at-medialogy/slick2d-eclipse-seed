import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a full deck of developmentcards
 */
public class DevelopmentCardDeck  {

    /**
     * Empty developmentcard deck
     */
    //private int[] devCard;
    
    private static ArrayList<CardType> cards;
   
    /**
     * Make a copy of the developmentcardDeck
     */
    public DevelopmentCardDeck(int kn, int vp, int rb, int yp){
    	/*devCard = new int[5];
    	devCard[CardType.KNIGHT.toInt()] = kn;
    	devCard[CardType.VICTORYPOINT.toInt()] = vp;
    	devCard[CardType.ROADBUILD.toInt()] = rb;
    	devCard[CardType.YEAROFPLENTY.toInt()] = yp;*/
    	
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
     * Returns the amount of a desired card.
     * @param age either old or new
     * @param type which type of development card you would like to check for
     * @return the number of the given gard
     */

    public int getAmountOfCards(int type ){
       return cards.indexOf(CardType.fromInteger(type));
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
     * @param age New or Old
     * @param type Type of card, see Interface.
     */
    public void add(int amount, int type) {
        cards.add(amount,CardType.fromInteger(type));
    }

    /**
     * Subtract a specific amount of cards from a specific type.
     * @param amount amount of cards
     * @param age new or old
     * @param type type of card.
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
     * Sets old and new cards to zero
     */
    public void clear(){
    	cards.clear();
   
    }
    
    public static void main(String[] args) {
    	DevelopmentCardDeck dev = new DevelopmentCardDeck(15,15,15,15);
    	System.out.println(dev.BuyCard());
    	System.out.println(dev.BuyCard());
    	System.out.println(dev.BuyCard());


	
}
}

