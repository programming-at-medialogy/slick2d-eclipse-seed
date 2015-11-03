

import java.util.ArrayList;

/**
 * Position class used to determine the position of buildings and roads.
 * @author Anders Elmholdt
 */
public class Position {
	private int division;
	private int index;
	
	/**
	 * TODO
	 * @param division
	 * @param index
	 */
	Position(int division, int index) {
		this.division = division;
		this.index = index;
	}
	
	/**
	 * TODO
	 * @param startPos
	 * @param endPos
	 * @return
	 */
	public static int getLength(Position startPos, Position endPos){
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(startPos);
		return getLength(positions, endPos);
	}
	
	/**
	 * TODO
	 * @param positions
	 * @param inPos
	 * @return
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
		
		// check if a building is at the same position
		if (Building.getByPosition(inPos) != null) {
			return 0;
		}
				
		// check for nearby buildings using index
		if (Building.getByPosition(new Position(inPos.getDivision(), (inPos.getIndex() + 1) % maxIndex)) != null ||
			Building.getByPosition(new Position(inPos.getDivision(), (inPos.getIndex() + maxIndex - 1) % maxIndex)) != null)
			return 1;
		
		// get nearby hexagons
		Hexagon[] nearbyHexagons = Building.getNearbyHexagons(inPos);
		
		// comment
		ArrayList<Hexagon> upwardHexagons = new ArrayList<Hexagon>();
		ArrayList<Hexagon> downwardHexagons = new ArrayList<Hexagon>();
		for (int i = 0; i < nearbyHexagons.length; i++) {
			if (nearbyHexagons[i].getDivision() > inPos.getDivision())
				upwardHexagons.add(nearbyHexagons[i]);
			else
				downwardHexagons.add(nearbyHexagons[i]);
		}
		
		
		// comment
		if (upwardHexagons.size() + downwardHexagons.size() != 1) {
			if ((upwardHexagons.size() > downwardHexagons.size() && Hexagon.hasSharedPositions(upwardHexagons, positions, inPos)) ||
				(upwardHexagons.size() < downwardHexagons.size() && Hexagon.hasSharedPositions(downwardHexagons, positions, inPos))) {
				return 1;
			} 
		}
		
		return 2;
	}
	
	/**
	 * Finds the nearby hexagons.
	 * @param hexagons the hexagon array to search
	 * @return the found hexagons
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
	 * TODO
	 * @return
	 */
	public Hexagon[] getNearbyHexagons() {
		return getNearbyHexagons(this);
	}
	
	/**
	 * Checks if a single hexagon is near the building.
	 * Uses {@link Building#getNearbyHexagons(Hexagon[])} to calculate this.
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
	 * 
	 * @param pos1
	 * @param pos2
	 * @return
	 */
	static public boolean comparePosition(Position pos1, Position pos2) {
		if (pos1.division == pos2.division && pos1.index == pos2.index)
			return true;
		return false;
	}
	
	/**
	 * TODO
	 * @return
	 */
	public int getDivision() {
		return division;
	}
	
	/**
	 * TODO
	 * @return
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * TODO
	 * @param division
	 */
	public void setDivision(int division) {
		this.division = division;
	}
	
	/**
	 * TODO
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * TODO
	 */
	@Override
	public String toString() {
		return this.getDivision() + ", " + this.getIndex();
	}
}
