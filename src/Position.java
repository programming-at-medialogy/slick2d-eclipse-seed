

import java.util.ArrayList;

/**
 * Position class used to determine the position of buildings and roads.
 * @author Anders Elmholdt
 */
public class Position {
	private int division;
	private int index;
	private float x;
	private float y;
	
	/**
	 * Constructor for a position.
	 * @param division the division
	 * @param index the index
	 */
	Position(int division, int index) {
		this.division = division;
		this.index = index;
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
		switch(inPos.getDivision()) {
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
			if (Position.comparePosition(new Position(inPos.getDivision(), (inPos.getIndex() + 1) % maxIndex), positions.get(i)) ||
				Position.comparePosition(new Position(inPos.getDivision(), (inPos.getIndex() + maxIndex - 1) % maxIndex), positions.get(i)))
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
			if (nearbyHexagons[i].getDivision() > inPos.getDivision())
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
		ArrayList<Hexagon> foundHexagons = new ArrayList<Hexagon>();
		
		for (int i = 0; i < Hexagon.getHexagons().length; i++) {
			// check division
			int hexDivision = Hexagon.getHexagons()[i].getDivision();
			if (hexDivision == inPos.getDivision() || hexDivision == inPos.getDivision() + 1) {
				// get maxBuildingIndex
				int maxBuildingIndex;
				switch(inPos.getDivision()) {
					case 0:
						maxBuildingIndex = 6;
						break;
					case 1:
						maxBuildingIndex = 18;
						break;
					case 2:
						maxBuildingIndex = 30;
						break;
					default:
						System.out.println("Error in building division");
						maxBuildingIndex = 1;
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
				
				float dividor = maxBuildingIndex / (float)maxHexIndex;
				
				// check if same position
				if ((int)(inPos.getIndex() / dividor) == Hexagon.getHexagons()[i].getIndex()) {
					foundHexagons.add(Hexagon.getHexagons()[i]);
				} else if (Math.round(inPos.getIndex() / dividor) % maxHexIndex == Hexagon.getHexagons()[i].getIndex()) {
					foundHexagons.add(Hexagon.getHexagons()[i]);
				} else if ((inPos.getIndex()*2) % (int)(dividor*2) == 0 && hexDivision > inPos.getDivision() && (int)(inPos.getIndex() / dividor) == (Hexagon.getHexagons()[i].getIndex() + 1) % maxHexIndex) {
					foundHexagons.add(Hexagon.getHexagons()[i]);
				}
			}
		}
		
		// copy arrayList into array
		Hexagon[] returnHexagons = new Hexagon[foundHexagons.size()];
		for (int i = 0; i < returnHexagons.length; i++) {
			returnHexagons[i] = foundHexagons.get(i);
		}
		
		return returnHexagons;
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
	 * @return true if the hexagon is near the building; otherwise false
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
	 * Checks whether two position objects are equal to eachother
	 * @param pos1 the first Position
	 * @param pos2 the second Position
	 * @return true if they are equal; otherwise false
	 */
	static public boolean comparePosition(Position pos1, Position pos2) {
		// checks if division and index are equal
		if (pos1.division == pos2.division && pos1.index == pos2.index)
			return true;
		return false;
	}
	
	/**
	 * Gets the division
	 * @return the division
	 */
	public int getDivision() {
		return division;
	}
	
	/**
	 * Gets the index
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Sets the division
	 * @param division the new division
	 */
	public void setDivision(int division) {
		this.division = division;
	}
	
	/**
	 * Sets the index
	 * @param index the new index
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * Gets the x and y position of a Position
	 * @return a float array containing the x position as index 0; y position as index 1
	 */
	public float[] getXY() {
		Hexagon positionHexagon = null;
		int hexOffsetIndex = 0;
		
		// if the Position has division 0, then it should just use its index as the hexOffsetIndex
		if (division == 0) {
			hexOffsetIndex = index;
			
			// just chose the center hexagon to be the reference for positions on division 0
			positionHexagon = Hexagon.getHexagonByDivision(0, 0, Hexagon.getHexagons()); // update this method
		}
		else {
			// get nearby hexagons
			Hexagon[] nearbyHexagons = getNearbyHexagons();
			
			/* Chose a random hexagon at the same division
			 * This hexagon is used as a relative position to describe the Position objects position */
			for (int i = 0; i < nearbyHexagons.length; i++) {
				if (nearbyHexagons[i].getDivision() == division) {
					positionHexagon = nearbyHexagons[i];
					break;
				}
			}
			
			// calculate hexOffsetIndex
			if (positionHexagon != null)
				hexOffsetIndex = (index - positionHexagon.getIndex() * 2) % 6;
			else
				System.out.println("getVector ERROR");
		}
		
		/* Calculate the angle at which the position is relative to the hexagon center.
		 * 60 is used since 360/6 = 60. 
		 * The offsetIndex is shifted once by adding 5 and using modulos 6 (amount of corners on hex) */
		hexOffsetIndex = (hexOffsetIndex + 5) % 6;
		int degreesTurn = 60 * hexOffsetIndex;
		
		// calculate the offset
		float offsetX = (float) Math.sin(Math.toRadians(degreesTurn)) * Main.hexHeight / 2 * Main.scFactor;
		float offsetY = (float) Math.cos(Math.toRadians(degreesTurn)) * Main.hexHeight / 2 * Main.scFactor;
		
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
		return this.getDivision() + ", " + this.getIndex();
	}
}
