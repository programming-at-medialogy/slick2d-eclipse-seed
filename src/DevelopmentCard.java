
/**
 * This class represents a full deck of developmentcards
 */
public class DevelopmentCard implements DevelopmentCardIF {

    /**
     * OLD = old card, NEW = new card
     */

    private static final int OLD = 0;
    private static final int NEW = 1;


    /**
     * Empty developmentcard deck
     */

    public int [][] devCard;

    public DevelopmentCard() {
        devCard = new int[2][DevelopmentCardIF.Max];
        clear();

    }


    /**
     * Make a copy of the developmentcardDeck
     */

    public DevelopmentCard(DevelopmentCard set){
        for (int i = DevelopmentCard.Min;
             i < DevelopmentCard.Max; i++){
            devCard[OLD][i] = set.devCard[OLD][i];
            devCard[NEW][i] = set.devCard[NEW][i];
        }
    }

    /**
     * Returns the amount of a desired card.
     * @param age either old or new
     * @param type which type of development card you would like to check for
     * @return the number of the given gard
     */

    public int getAmountOfCards(int age, int type ){
        return devCard[age][type];
    }

    /**
     * Returns the total number of development cards
     */

    public int getTotal(){
        int sum = 0;
        for (int i = DevelopmentCard.Min;
             i < DevelopmentCard.Max; i++)
        {
            sum += (devCard[OLD][i] + devCard[NEW][i]);
        }

        return sum;
    }

    /**
     * Set the amount of a type of card
     * @param amount Amount of cards
     * @param type Type of card
     * @param age New or Old
     */
    public void setAmount(int amount, int type, int age) {
        devCard[age][type] = amount;
    }

    /**
     * Add a specific amount of cards to a specific type
     * @param amount = amount of cards
     * @param age New or Old
     * @param type Type of card, see Interface.
     */
    public void add(int amount, int age, int type) {
        devCard[age][type] += amount;
    }

    /**
     * Subtract a specific amount of cards from a specific type.
     * @param amount amount of cards
     * @param age new or old
     * @param type type of card.
     */

    public void subtract(int amount, int age, int type) {
        if (amount <= devCard[age][type]) {
            devCard[age][type] -= amount;
        } else {
            devCard[age][type] = 0;
            devCard[age][DevelopmentCard.Unknown] -= amount;
        }
    }

    /**
     * Some cards stay in hand, this method counts both old and new
     * @return
     */

    public int getUnplayed() {
        int sum = 0;
        sum += devCard[OLD][DevelopmentCardIF.Blackhole];
        sum += devCard[OLD][DevelopmentCardIF.Road];
        sum += devCard[OLD][DevelopmentCardIF.Mono];
        sum += devCard[OLD][DevelopmentCardIF.Unknown];
        sum += devCard[NEW][DevelopmentCardIF.Blackhole];
        sum += devCard[NEW][DevelopmentCardIF.Road];
        sum += devCard[NEW][DevelopmentCardIF.Mono];
        sum += devCard[NEW][DevelopmentCardIF.Unknown];
        return sum;
    }

    public void newToOld() {
        for (int i = DevelopmentCardIF.Min;
             i < DevelopmentCardIF.Max; i++) {
            devCard[OLD][i] += devCard[NEW][i];
            devCard[NEW][i] = 0;
        }
    }


    /**
     * Sets old and new cards to zero
     */
    public void clear()
    {
        for (int i = DevelopmentCard.Min;
             i < DevelopmentCard.Max; i++) {
            devCard[OLD][i] = 0;
            devCard[NEW][i] = 0;
        }
    }

}
