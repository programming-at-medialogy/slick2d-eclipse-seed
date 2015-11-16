import java.util.ArrayList;

/**
 * Class describing a Building.
 * @author Anders Frederik Elmholdt
 */

public class Building {
	
	private boolean upgraded;
	Position position;
	static ArrayList<Building> buildings = new ArrayList<Building>();
	
	private Building(Position inPos) {
		position = inPos;
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
	 * Finds the nearby hexagons.
	 * @return The nearby hexagons as an array
	 */
	public Hexagon[] getNearbyHexagons() {
		return getNearbyHexagons(position);
	}
	
	/**
	 * Checks if a single hexagon is near the building.
	 * Uses {@link Building#getNearbyHexagons(Hexagon[])} to calculate this.
	 * @param hexagon the hexagon
	 * @return true if the hexagon is near the building; otherwise false
	 */
	public boolean isNearby(Hexagon hexagon) {
		Hexagon[] hexArray = new Hexagon[1];
		hexArray[0] = hexagon;
		hexArray = getNearbyHexagons();
		
		if (hexArray.length == 0)
			return false;
		return true;
	}

	/**
	 * Upgrades a building.
	 * Contains error checking to check if the building is already upgraded.
	 * @return true if successfully upgraded; otherwise false
	 */
	public boolean upgrade() {
		if (!upgraded) {
			upgraded = true;
			return true;
		}
		else {
			System.out.println("Building is already upgraded!");
			return false;
		}
	}
	
	/**
	 * This method should be used instead of calling the constructor since it contains error checking.
	 * @param division the division
	 * @param index the index
	 * @return the constructed building; returns null if no building can be built
	 */
	public static Building build(Position inPos) {
		// error checking
		if (Position.getLength(getBuildingPositions(), inPos) == 2) {
			System.out.println("Building here:    " + inPos.getDivision() + ", " + inPos.getIndex());
			Building building = new Building(inPos);
			buildings.add(building);
			// check longest road
			return building;
		}
		
		System.out.println("Can't build here: " + inPos.getDivision() + ", " + inPos.getIndex());
		return null;
	}
	
	/**
	 * Finds a building by a given division and index
	 * @param division the division
	 * @param index the index
	 * @return the found building; null if no building is found
	 */
	public static Building getByPosition(Position inPos) {
		for (int i = 0; i < buildings.size(); i++) {
			if (buildings.get(i).getDivision() == inPos.getDivision() && buildings.get(i).getIndex() == inPos.getIndex())
				return buildings.get(i);
		}
		
		return null;
	}
	
	/**
	 * @return upgraded
	 */
	public boolean isUpgraded() {
		return upgraded;
	}

	/**
	 * @return the division of the building
	 */
	public int getDivision() {
		return position.getDivision();
	}
	
	/**
	 * @return the index of the building
	 */
	public int getIndex() {
		return position.getIndex();
	}
	
	/**
	 * Sets the division
	 * @param division the division
	 */
	public void setDivision(int division) {
		position.setDivision(division);
	}
	
	/**
	 * Sets the index
	 * @param index the index
	 */
	public void setIndex(int index) {
		position.setIndex(index);
	}
	
	/**
	 * Gets the current buildings.
	 * @return The buildings as an ArrayList
	 */
	public static ArrayList<Building> getBuildings() {
		return buildings;
	}
	
	/**
	 * Gets the position of a building.
	 * @return The position
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Gets the position of all buildings.
	 * @return The positions as an ArrayList
	 */
	public static ArrayList<Position> getBuildingPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		for (int i = 0; i < buildings.size(); i++) {
			positions.add(buildings.get(i).getPosition());
		}
		return positions;
	}
}
