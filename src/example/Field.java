package example;

import java.util.ArrayList;

public class Field {

	// The innate defence value of a field. Starts at 2, which is set in the constructor below. 
	// The idea is that this value will always equal itself + the size of the arraylist of units, 
	// and can be modified by other methods as required.
	private int defenceValue;

	// booleans to check if a field contains any of the the three innate resources
	private boolean containingMagic = false;
	private boolean containingMines = false;
	private boolean containingMountains = false;
	
	// boolean to check if a field is adjacent to border of map. BorderPosition is 
	// set to false by default.
	private boolean borderPosition = false;
	
	// Conquerable is true by default and must be set to false for water fields
	private boolean conquerable = true;

	// A string containing the terrain type of a field - forest, hill, water, etc. This is set 
	// using an enumerator with a switch upon object construction to ensure that people can't 
	// fuck around with it!
	private String terrainType;

	// The idea is that this ArrayList of fields will contain all the fields adjacent to this 
	// given field, as per Paolo's chart instructions
	ArrayList<Field> adjacencies = new ArrayList<Field>();

	// This array is supposed to be an array of units, we used strings as we don't have the 
	// unit class yet
	ArrayList<String> amountOfUnits = new ArrayList<String>();

	// Constructor
	public Field(TerrainType terrainType) {
		setDefenceValue(2);

		setTerrainType(terrainType);
		
		// If statements which sets conquerable to true if field is water
		if (this.terrainType.equals("Water")) {
			setConquerable(false);
		}

	}

	// Method that uses a switch and the construction parameters to set the terrain type for 
	// the field via our enumerator
	private void setTerrainType(TerrainType terrainType) {
		switch (terrainType) {
		case WATER:
			this.terrainType = "Water";
		case FOREST:
			this.terrainType = "Forest";
		case HILL:
			this.terrainType = "Hill";
		case SWAMP:
			this.terrainType = "Swamp";
		case MOUNTAINLAND:
			this.terrainType = "Mountainland";
		case FARMLAND:
			this.terrainType = "Farmland";


		}
	}

	// Method which checks if the field is adjacent to another field
	public boolean isAdjacentTo(Field otherField) {
		if (adjacencies.contains(otherField)) return true;
		else return false;
	}

	// Everything below this line is just encapsulation methods for the various variables for
	// maximum object-orientation

	int getDefenceValue() {
		return defenceValue;
	}

	void setDefenceValue(int defenceValue) {
		this.defenceValue = defenceValue;
	}

	// Notice lack of set method for terrainType - it's not needed, as it's always locked in 
	// upon instantiation
	String getTerrainType() {
		return terrainType;
	}

	boolean isContainsMagic() {
		return containingMagic;
	}

	void setContainsMagic(boolean containingMagic) {
		this.containingMagic = containingMagic;
	}

	boolean isContainsMines() {
		return containingMines;
	}

	void setContainsMines(boolean containingMines) {
		this.containingMines = containingMines;
	}

	boolean isContainsMountains() {
		return containingMountains;
	}

	void setContainsMountains(boolean containingMountains) {
		this.containingMountains = containingMountains;
	}

	boolean isBorderPosition() {
		return borderPosition;
	}

	void setBorderPosition(boolean borderPosition) {
		this.borderPosition = borderPosition;
	}

	boolean isConquerable() {
		return conquerable;
	}

	// This method is private as conquerable is defined only in constructor
	private void setConquerable(boolean conquerable) {
		this.conquerable = conquerable;
	}

}
