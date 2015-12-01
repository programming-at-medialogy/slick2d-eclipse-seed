import java.util.ArrayList;

/**
 * Position class used to determine the position of buildings and roads.
 */
public class Position {
	// these variables are declared public final, since they cannot change after being initialized
	public final int DIVISION;
	public final int INDEX;
	
	private float x;
	private float y;
	private Hexagon[] nearbyHexagons;
	private static Position[] positions;
	
	/**
	 * Constructor for a position.
	 * Private since it should only be called from {@link Position#initializePositions()}.
	 * @param division the division
	 * @param index the index
	 */
	private Position(int division, int index) {
		DIVISION = division;
		INDEX = index;
	}
	
	/**
	 * Used to initialize all possible Positions and save them in an array.
	 */
	public static void initializePositions() {
		// initializes the positions
		positions = new Position[6 + 18 + 30];
		for (int i = 0; i < positions.length; i++) {
			if (i < 6)
				positions[i] = new Position(0, i);
			else if (i < 24)
				positions[i] = new Position(1, i - 6);
			else {
				positions[i] = new Position(2, i - 24);
				System.out.println(i - 24);
			}
		}
	}
	
	/**
	 * This should be used instead of a constructor, since all possible Positions are created from {@link Position#initializePositions()}.
	 * @param division the division to retrieve
	 * @param index the index to retrieve
	 * @return the position object with the given division and index
	 */
	public static Position assignPosition(int division, int index) {
		// error checking
		if (positions == null)
			initializePositions();
		if (division == 0)
			return positions[index];
		else if (division == 1)
			return positions[index + 6];
		else
			return positions[index + 24];
	}
	
	/**
	 * Finds the closest position from a given x and y coordinate.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the closest position
	 */
	public static Position findPosition(float x, float y) {
		// error checking
		if (positions == null)
			initializePositions();
		
		// finds the closest position
		float closestX = 0.0f;
		float closestY = 0.0f;
		int closestIndex = -1;
		for (int i = 0; i < positions.length; i++) {
			if ((Math.sqrt(Math.pow(positions[i].getX() - x, 2) + Math.pow(positions[i].getY() - y, 2))) <
				(Math.sqrt(Math.pow(closestX - x, 2) + Math.pow(closestY - y, 2)))) {
				closestX = positions[i].getX();
				closestY = positions[i].getY();
				closestIndex = i;
			}
		}
		
		// returns the closest position
		if (closestIndex < 0 || Math.sqrt(Math.pow(closestX - x,  2) + Math.pow(closestY - y, 2)) > 50)
			return null;
		return positions[closestIndex];
	}
	
	/**
	 * Checks whether two position objects are equal to eachother
	 * @param pos1 the first Position
	 * @param pos2 the second Position
	 * @return true if they are equal; otherwise false
	 */
	public static boolean comparePosition(Position pos1, Position pos2) {
		// checks if division and index are equal
		if (pos1.DIVISION == pos2.DIVISION && pos1.INDEX == pos2.INDEX)
			return true;
		return false;
	}
	
	/**
	 * Method to calculate the shortest distance between a Position object and an ArrayList of other Positions.
	 * @param positions the ArrayList of Positions
	 * @param inPos the Position
	 * @return 0 if they are the same position; 1 if the distance is equal to 1; 2 otherwise.
	 */
	public static int getLength(ArrayList<Position> positions, Position inPos) {
		/* MaxIndex is the maximum index in a division.
		 * It varies with division, hence a switch is used to compute it. */
		int maxIndex;
		switch(inPos.DIVISION) {
			case 0:
				maxIndex = 6;
				break;
			case 1:
				maxIndex = 18;
				break;
			case 2:
				maxIndex = 30;
				break;
			default:
				System.out.println("Error in division.. Setting maxIndex to 6");
				maxIndex = 6;
		}
		
		// check for all positions
		for (int i = 0; i < positions.size(); i++) {
			// check if another Position object is a the same position
			if (Position.comparePosition(positions.get(i), inPos)) {
				return 0;
			}
			
			/* Check if distance is 1.
			 * NOTE: this only checks for 2/3 possible nearby Positions with distance = 1.
			 * The last 1/3 is found in the lines below. */
			if (Position.comparePosition(new Position(inPos.DIVISION, (inPos.INDEX + 1) % maxIndex), positions.get(i)) ||
				Position.comparePosition(new Position(inPos.DIVISION, (inPos.INDEX + maxIndex - 1) % maxIndex), positions.get(i)))
				return 1;
		}
		
		// get nearby hexagons
		Hexagon[] nearbyHexagons = getNearbyHexagons(inPos);
		
		/* Categorizes the nearby hexagons into groups.
		 * 1. group for hexagons that are closer to the center relative to the position.
		 * 2. group for hexagons that are not. */
		ArrayList<Hexagon> upwardHexagons = new ArrayList<Hexagon>();
		ArrayList<Hexagon> downwardHexagons = new ArrayList<Hexagon>();
		for (int i = 0; i < nearbyHexagons.length; i++) {
			if (nearbyHexagons[i].getDivision() > inPos.DIVISION)
				upwardHexagons.add(nearbyHexagons[i]);
			else
				downwardHexagons.add(nearbyHexagons[i]);
		}
		
		/* If two of the nearby hexagons has a shared position that is included
		 * in the ArrayList of Positions, then the distance is equal to 1. */
		if (upwardHexagons.size() + downwardHexagons.size() != 1) {
			if ((upwardHexagons.size() > downwardHexagons.size() && Hexagon.hasSharedPositions(upwardHexagons, positions, inPos)) ||
				(upwardHexagons.size() < downwardHexagons.size() && Hexagon.hasSharedPositions(downwardHexagons, positions, inPos))) {
				return 1;
			} 
		}
		
		// if none of the above code has executed, the smallest distance is greater than 1
		return 2;
	}
	
	/**
	 * Overloaded method of {@link Position#getLength(ArrayList, Position)}, which allows to get the length of two positions.
	 * @param startPos the first Position object
	 * @param endPos the second Position object
	 * @return 0 if they are the same position; 1 if the distance is equal to 1; 2 otherwise.
	 */
	public static int getLength(Position startPos, Position endPos){
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(startPos);
		return getLength(positions, endPos);
	}
	
	/**
	 * Finds the nearby hexagons.
	 * @param hexagons the hexagon array to search
	 * @return the nearby hexagons as an array
	 */
	public static Hexagon[] getNearbyHexagons(Position inPos) {
		// only calculate the nearby hexagons if they have not already been calculated previously
		if (inPos.nearbyHexagons == null) {
			ArrayList<Hexagon> foundHexagons = new ArrayList<Hexagon>();
			
			for (int i = 0; i < Hexagon.getHexagons().length; i++) {
				// check division
				int hexDivision = Hexagon.getHexagons()[i].getDivision();
				if (hexDivision == inPos.DIVISION || hexDivision == inPos.DIVISION + 1) {
					// get maxPositionIndex
					int maxPositionIndex;
					switch(inPos.DIVISION) {
						case 0:
							maxPositionIndex = 6;
							break;
						case 1:
							maxPositionIndex = 18;
							break;
						case 2:
							maxPositionIndex = 30;
							break;
						default:
							System.out.println("Error in Position division");
							maxPositionIndex = 1;
					}
					
					// get maxHexIndex
					int maxHexIndex;
					switch(hexDivision) {
						case 0:
							maxHexIndex = 1;
							break;
						case 1:
							maxHexIndex = 6;
							break;
						case 2:
							maxHexIndex = 12;
							break;
						default:
							System.out.println("Error in hex division: " + hexDivision);
							maxHexIndex = 1;
					}
					
					float dividor = maxPositionIndex / (float)maxHexIndex;
					
					// check if same position
					if ((int)(inPos.INDEX / dividor) == Hexagon.getHexagons()[i].getIndex()) {
						foundHexagons.add(Hexagon.getHexagons()[i]);
					} else if (Math.round(inPos.INDEX / dividor) % maxHexIndex == Hexagon.getHexagons()[i].getIndex()) {
						foundHexagons.add(Hexagon.getHexagons()[i]);
					} else if ((inPos.INDEX*2) % (int)(dividor*2) == 0 && hexDivision > inPos.DIVISION && (int)(inPos.INDEX / dividor) == (Hexagon.getHexagons()[i].getIndex() + 1) % maxHexIndex) {
						foundHexagons.add(Hexagon.getHexagons()[i]);
					}
				}
			}
			
			// copy arrayList into array
			inPos.nearbyHexagons = new Hexagon[foundHexagons.size()];
			for (int i = 0; i < inPos.nearbyHexagons.length; i++) {
				inPos.nearbyHexagons[i] = foundHexagons.get(i);
			}
		}
		return inPos.nearbyHexagons;
	}
	
	/**
	 * Overloaded method of {@link Position#getNearbyHexagons(Position)}, that allows it to be called on an instance.
	 * @return the nearby hexagons as an array
	 */
	public Hexagon[] getNearbyHexagons() {
		return getNearbyHexagons(this);
	}
	
	/**
	 * Checks if a single hexagon is near the position.
	 * Uses {@link Position#getNearbyHexagons()} to calculate this.
	 * @param hexagon the hexagon
	 * @return true if the hexagon is near the Position; otherwise false
	 */
	public boolean isNearby(Hexagon hexagon) {
		Hexagon[] hexArray = getNearbyHexagons();
		for (int i = 0; i < hexArray.length; i++) {
			if (hexArray[i] == hexagon)
				return true;
		}
		return false;
	}
	
	/**
	 * Gets the x and y position of a Position
	 * @return a float array containing the x position as index 0; y position as index 1
	 */
	private float[] getXY() {
		Hexagon positionHexagon = null;
		int hexOffsetIndex = 0;
		
		// if the Position has division 0, then it should just use its index as the hexOffsetIndex
		if (DIVISION == 0) {
			hexOffsetIndex = INDEX;
			
			// just chose the center hexagon to be the reference for positions on division 0
			positionHexagon = Hexagon.getHexagonByDivision(0, 0);
		} else {
			// get nearby hexagons
			Hexagon[] nearbyHexagons = getNearbyHexagons();
			
			/* Chose a random hexagon at the same division
			 * This hexagon is used as a relative position to describe the Position objects position */
			for (int i = 0; i < nearbyHexagons.length; i++) {
				if (nearbyHexagons[i].getDivision() == DIVISION) {
					positionHexagon = nearbyHexagons[i];
					break;
				}
			}
			
			// calculate hexOffsetIndex
			if (positionHexagon != null)
				hexOffsetIndex = (INDEX - positionHexagon.getIndex() * 2) % 6;
			else
				System.out.println("getVector ERROR");
		}
		
		/* Calculate the angle at which the position is relative to the hexagon center.
		 * 60 is used since 360/6 = 60. 
		 * The offsetIndex is shifted once by adding 5 and using modulos 6 (amount of corners on hex) */
		hexOffsetIndex = (hexOffsetIndex + 5) % 6;
		int degreesTurn = 60 * hexOffsetIndex;
		
		// calculate the offset
		float offsetX = (float) Math.sin(Math.toRadians(degreesTurn)) * GameState.hexHeight / 2 * Windows.scFactor;
		float offsetY = (float) Math.cos(Math.toRadians(degreesTurn)) * GameState.hexHeight / 2 * Windows.scFactor;
		
		// store position in array
		float[] position = new float[2];
		position[0] = offsetX + positionHexagon.getX();
		position[1] = - offsetY + positionHexagon.getY();
		x = position[0];
		y = position[1];
		
		return position;
	}
	
	/**
	 * Gets the x position of a Position object.
	 * Uses {@link Position#getXY()} to do this.
	 * @return the x position as a float
	 */
	public float getX() {
		if (x == 0)
			return getXY()[0];
		return x;
	}
	
	/**
	 * Gets the y position of a Position object.
	 * Uses {@link Position#getXY()} to do this.
	 * @return the y position as a float
	 */
	public float getY() {
		if (y == 0)
			return getXY()[1];
		return y;
	}
	
	/**
	 * Override of the {@link Object#toString()} method that allows this class to be printed.
	 * Prints the Position in the following manner: "division, index".
	 */
	@Override
	public String toString() {
		return DIVISION + ", " + INDEX;
	}
}
