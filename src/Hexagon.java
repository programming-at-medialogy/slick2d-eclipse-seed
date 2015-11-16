import java.util.ArrayList;
import java.util.Random;

/**
 * Class describing a Hexagon for the game Settlers.
 * @author Anders Frederik Elmholdt
 */
public class Hexagon {
	
	private int indexInArray;
	private int number;
	private static Hexagon[] hexagons;
	private ResourceType type;
	private boolean robbed;

	/**
	 * Constructor for a Hexagon.
	 * The boolean hasRobber is inferred from the type of the resource.
	 * @param resourceType the type of resource the hexagon contains
	 * @param position the position of the hexagon
	 * @param number the number that has to be rolled for the hexagon to yield resources
	 */
	Hexagon(ResourceType resourceType, int number) {
		this.type = resourceType;
		this.number = number;
		
		// the robber always starts on the antimatter block
		if (type == ResourceType.ANTIMATTER)
			this.robbed = true;
		else
			this.robbed = false;
	}
	
	/**
	 * Creates a random generated map.
	 * @return an array containing the hexagons for the map
	 */
	public static Hexagon[] generateMap() {
		final int HEXAMOUNT = 19;
		
		// initialize numbers
		int[] numbers = new int[HEXAMOUNT];
		for (int i = 0; i < HEXAMOUNT / 2; i++) {
			numbers[i] = 2 + (i + 1) / 2;
			numbers[HEXAMOUNT - i - 2] = 12 - (i + 1) / 2;
		}
		numbers[HEXAMOUNT - 1] = 7;
		
		// shuffle numbers
		shuffle(numbers, HEXAMOUNT - 1);
		
		// instantiate hex array
		Hexagon[] hexagons = new Hexagon[HEXAMOUNT];
		for (int i = 0; i < HEXAMOUNT - 1; i++) {
			hexagons[i] = new Hexagon(ResourceType.fromInteger(i % 5), numbers[i]);
		}
		hexagons[HEXAMOUNT - 1] = new Hexagon(ResourceType.ANTIMATTER, numbers[HEXAMOUNT - 1]);
		
		// shuffle hexagons
		shuffle(hexagons, HEXAMOUNT);
		
		// set indexes
		for (int i = 0; i < hexagons.length; i++) {
			hexagons[i].setIndexInArray(i);
		}
		
		Hexagon.hexagons = hexagons;
		
		return hexagons;
	}
	
	/**
	 * Returns a hexagon at a specific division and index.
	 * @param division the division
	 * @param index the index
	 * @param hexagons the hexagon array to use
	 * @return the hexagon at (division, index)
	 */
	public static Hexagon getHexagonByDivision(int division, int index, Hexagon[] hexagons) {
		switch(division) {
			case 0:
				return hexagons[0];
			case 1:
				return hexagons[index + 1];
			case 2:
				return hexagons[index + 7];
		}
		
		// print error
		System.out.println("Invalid division for method: getHexagon(int, int, Hexagon[]");
		return null;
	}
	
	/**
	 * Gets the hexagon(s) with a specific number.
	 * @param number the number
	 * @param hexagons the hexagon array to use
	 * @return the hexagon(s) with the specific number
	 */
	public static Hexagon[] getHexagonByNumber(int number) {
		// find the hexagons
		Hexagon[] foundHexagons = new Hexagon[2];
		int index = 0;
		for (int i = 0; i < hexagons.length; i++) {
			if (hexagons[i].getNumber() == number) {
				foundHexagons[index++] = hexagons[i];
				if (index > 1)
					break;
			}
		}
		
		// convert size of array to one if only one hexagon was found
		if (index == 1) {
			Hexagon[] foundHexagon = new Hexagon[1];
			foundHexagon[0] = foundHexagons[0];
			return foundHexagon;
		}
		
		return foundHexagons;
	}
	
	/**
	 * Gets the division of a hexagon
	 * @return the division
	 */
	public int getDivision() {
		if (indexInArray == 0) {
			return 0;
		} else if (indexInArray < 7) {
			return 1;
		}
		return 2;
	}
	
	/**
	 * Gets the index of the current hexagon.
	 * @return the index
	 */
	public int getIndex() {
		switch(getDivision()) {
			case 0:
				return 0;
			case 1: 
				return indexInArray - 1;
			case 2:
				return indexInArray - 7;
		}
		
		// this should never happen since the code is pretty error proof... but just in case
		System.out.println("Error occured!");
		return 0;
	}
	
	/**
	 * Method used to retrieve the current hexagon's position in the array
	 * @return the index
	 */
	public int getIndexInArray() {
		return indexInArray;
	}
	
	/**
	 * Method used to set the current hexagon's position in the array
	 * @param index the index
	 */
	private void setIndexInArray(int index) {
		this.indexInArray = index;
	}
	
	/**
	 * Shuffles an array using the Fisher-Yates shuffle.
	 * @param array the array to be shuffled
	 * @param length the amount of numbers to be shuffled
	 */
	private static void shuffle(int[] array, int length) {
		Random r = new Random();
		for (int i = length - 1; i > 0; i--) {
			int index = r.nextInt(i + 1);
			int temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}
	
	/**
	 * Works just like {@link Hexagon#shuffle(int[], int)} except the array is of type Hexagon.
	 * @see Hexagon#shuffle(int[], int)
	 */
	private static void shuffle(Hexagon[] array, int length) {
		Random r = new Random();
		for (int i = length - 1; i > 0; i--) {
			int index = r.nextInt(i + 1);
			Hexagon temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}
	
	/**
	 * Finds the shared positions of two hexagons, if any.
	 * @param hexagonList The two hexagons to use
	 * @param positions The positions to look at
	 * @param excludePosition A position chosen to be excluded
	 * @return true if they contain a shared position; false otherwise
	 */
	public static boolean hasSharedPositions(ArrayList<Hexagon> hexagonList, ArrayList<Position> positions, Position excludePosition) {
		if (hexagonList.size() == 2) {
			for (int i = 0; i < positions.size(); i++) {
				if (positions.get(i).isNearby(hexagonList.get(0)) && positions.get(i).isNearby(hexagonList.get(1))) {
					if (!Position.comparePosition(excludePosition, positions.get(i))) {
						return true;
					}
				}
			}
		}
		else {
			System.out.println("ArrayList needs to contain 2 objects");
			System.out.println("Currently containing: \n" + hexagonList.get(0).getDivision() + ", " + hexagonList.get(0).getIndex());
			return true;
		}
		return false;
	}

	/**
	 * @return the id
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @return the type
	 */
	public ResourceType getType() {
		return type;
	}

	/**
	 * @return true if robbed, false otherwise
	 */
	public boolean isRobbed() {
		return robbed;
	}
	
	/**
	 * Get the hexagon array.
	 * @return the hexagon array
	 */
	public static Hexagon[] getHexagons() {
		return hexagons;
	}
	
	/**
	 * Gets the x position of the hexagon. Used to draw it.
	 * @return the x coordinate
	 */
	public float getX() {
		return (float)Math.cos(angle());
	}
	
	/**
	 * Gets the y position of the hexagon. Used to draw it.
	 * @return the y coordinate
	 */
	public float getY() {
		return (float)Math.sin(angle());
	}
	
	/**
	 * Function to calculate angle for each polygon
	 * @return the angle in radians
	 */
	private float angle(){
		float angle;
		if (indexInArray <=6) 							// for inner
			angle  = (indexInArray + 3) * 1.0472f; 		// 1.05f = 360/6 is specific angle for all polygons up to 7th.
		else 											// for outer
			angle  = (indexInArray + 1) * 0.523599f; 	// 0.523599f = 360/12 for hexagons from 7th
		return angle;
	}
}
