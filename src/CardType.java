
/**
 * This is an enum 
 */
public enum CardType {
	//TUTORIAL:
	/*
	 * declare int array:
	 * int[] Card = new int[5];
	 * 
	 * to add spaceDebris into the array:
	 * resources[CardType.KNIGHT.toInt()]++;
	 * 
	 * to check how much mars sand a user has:
	 * resources[CardType.YEAROFPLENTY.toInt()];
	 */
	
	KNIGHT,
	VICTORYPOINT,
	ROADBUILD,
	MONOPOLY,
	YEAROFPLENTY;
	
	/**
	 * Initializes a CardType from an integer
	 * @param i input integer
	 * @return the ResourceType enum
	 */
	
	
	public static CardType fromInteger(int i) {
    switch(i){
    case 0 :
    	return KNIGHT;
    case 1:
    	return VICTORYPOINT;
    case 2:
    	return ROADBUILD;
    case 3:
    	return MONOPOLY;
    case 4:
    	return YEAROFPLENTY;
    }
    return null;
  }

	public int toInt () {
		switch(this) {
		case KNIGHT:
			return 0;
		case VICTORYPOINT:
			return 1;
		case ROADBUILD:
			return 2;
		case MONOPOLY:
			return 3;
		case YEAROFPLENTY:
			return 4;
		}
		return 5;
}
	}
