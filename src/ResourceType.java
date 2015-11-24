
public enum ResourceType {
	
	//TUTORIAL:
	/*
	 * declare int array:
	 * int[] resources = new int[5];
	 * 
	 * to add spaceDebris into the array:
	 * resources[ResourceType.SPACEDEBRIS.toInt()]++;
	 * 
	 * to check how much mars sand a user has:
	 * resources[ResourceType.MARSSAND.toInt()];
	 */
	
	ROCK,
	BRICK,
	TREE,
	SHEEP,
	CORN,
	DESERT;
	
	/**
	 * Initializes a ResourceType from an integer
	 * @param i input integer
	 * @return the ResourceType enum
	 */
	public static ResourceType fromInteger(int i) {
		switch(i) {
		case 0:
			return ROCK;
		case 1:
			return BRICK;
		case 2:
			return TREE;
		case 3:
			return SHEEP;
		case 4:
			return CORN;
		case 5:
			return DESERT;
		}
		return null;
	}
	
	public int toInt() {
		switch(this) {
		case ROCK:
			return 0;
		case BRICK:
			return 1;
		case TREE:
			return 2;
		case SHEEP:
			return 3;
		case CORN:
			return 4;	
		case DESERT:
			return 5;
		}
		return 6;
	}
}
