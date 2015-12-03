import java.util.ArrayList;

/**
 * Class describing a Building.
 */
public class Building {
	
	// these variables are declared public final, since they cannot change after being initialized
	public final Position POSITION;
	public final int PLAYER;
	
	private boolean upgraded;
	/* I don't where this is used, but the player should not be static.
	 * By making the player static every single building will share the
	 * same player, which is certainly not what is intended.
	 * I changed it, so some of the code might need to be rewritten now.
	 * Same goes for the boolean upgraded. */
	//private static ArrayList<Building> buildings = new ArrayList<Building>();
	
	/**
	 * Private constructor. One should use {@link Building#build(Position, int)} instead, since it contains error checking.
	 * @param inPos the Position to place the building at
	 * @param player the player index
	 */
	private Building(Position inPos, int player) {
		POSITION = inPos;
		PLAYER = player;
	}
	
	/**
	 * This method should be used instead of calling the constructor since it contains error checking.
	 * @param division the division
	 * @param index the index
	 * @return the constructed building; returns null if no building can be built
	 */
	public static Building build(Position inPos, int player) {
		// error checking
		if (canBuild(inPos)) {
			System.out.println("Building here:    " + inPos.DIVISION + ", " + inPos.INDEX);
			Building building = new Building(inPos, player);
			GameData.buildings.add(building);
			Road.longestRoad();
			GameData.players.get(player).addPoint();
			System.out.println("Player has " + GameData.players.get(player).points + " points");
			return building;
		}
		
		System.out.println("Can't build here: " + inPos.DIVISION + ", " + inPos.INDEX);
		return null;
	}
	
	/**
	 * Gets the Position of all built buildings.
	 * @return The positions as an ArrayList
	 */
	public static ArrayList<Position> getBuildingPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		for (int i = 0; i < GameData.buildings.size(); i++) {
			positions.add(GameData.buildings.get(i).POSITION);
		}
		return positions;
	}
	
	/**
	 * Checks whether it is legal to place a building at a specific position.
	 * @param inPos the position to be checked
	 * @return true if a building can be built; otherwise false
	 */
	public static boolean canBuild(Position inPos) {
		if (getBuildingPositions() != null && Position.getLength(getBuildingPositions(), inPos) < 2)
			return false;
		return true;
	}
	
	/**
	 * Finds a building by a given division and index
	 * @param division the division
	 * @param index the index
	 * @return the found building; null if no building is found
	 */
	public static Building getByPosition(Position inPos) {
		for (int i = 0; i < GameData.buildings.size(); i++) {
			if (Position.comparePosition(GameData.buildings.get(i).POSITION, inPos))
				return GameData.buildings.get(i);
		}
		return null;
	}
	
	/**
	 * Gets the built buildings.
	 * @return The buildings as an ArrayList
	 */
	public static ArrayList<Building> getBuildings() {
		return GameData.buildings;
	}

	/**
	 * Upgrades a building.
	 * Contains error checking to check if the building is already upgraded.
	 */
	public boolean upgrade() {
		if (!upgraded) {
			upgraded = true;
			GameData.players.get(PLAYER).addPoint();
			System.out.println("Player has " + GameData.players.get(PLAYER).points + " points");
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the division of the buildings position.
	 * @return the division of the building
	 */
	public int getDivision() {
		return POSITION.DIVISION;
	}
	
	/**
	 * Gets the index of the buildings position.
	 * @return the index of the building
	 */
	public int getIndex() {
		return POSITION.INDEX;
	}
	
	/**
	 * Checks whether a building is upgraded.
	 * @return true if it is upgraded; otherwise false
	 */
	public boolean isUpgraded() {
		return upgraded;
	}
}