
public enum ResourceType {
	
	//TUTORIAL:
	/*
	 * declare int array:
	 * int[] resources = new int[5];
	 * 
	 * to add spaceDebris into the array:
	 * resources[ResourceType.SPACEDEBRIS]++;
	 * 
	 * to check how much mars sand a user has:
	 * resources[ResourceType.MARSSAND];
	 */
	
	SPACEDEBRIS,
	MOONWATER,
	ALIENDNA,
	SPACEHAY,
	MARSSAND,
	ANTIMATTER;
	
	/**
	 * Initializes a ResourceType from an integer
	 * @param i input integer
	 * @return the ResourceType enum
	 */
	public static ResourceType fromInteger(int i) {
		switch(i) {
		case 0:
			return SPACEDEBRIS;
		case 1:
			return MOONWATER;
		case 2:
			return ALIENDNA;
		case 3:
			return SPACEHAY;
		case 4:
			return MARSSAND;
		case 5:
			return ANTIMATTER;
		}
		return null;
	}
}
