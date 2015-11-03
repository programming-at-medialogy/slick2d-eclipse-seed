package example;

public class MapTwoPlayer extends Map {
	
	Field field1pt1 = new Field(TerrainType.WATER);
	Field field1pt2 = new Field(TerrainType.FARMLAND);
	Field field1pt3 = new Field(TerrainType.FOREST);	
	Field field1pt4 = new Field(TerrainType.SWAMP);	
	Field field1pt5 = new Field(TerrainType.HILL);
		
	Field field2pt1 = new Field(TerrainType.MOUNTAINLAND);	
	Field field2pt2 = new Field(TerrainType.HILL);
	Field field2pt3 = new Field(TerrainType.WATER);
	Field field2pt4 = new Field(TerrainType.MOUNTAINLAND);	
	Field field2pt5 = new Field(TerrainType.FARMLAND);
	Field field2pt6 = new Field(TerrainType.FOREST);
	
	Field field3pt1 = new Field(TerrainType.FARMLAND);
	Field field3pt2 = new Field(TerrainType.FOREST);
	Field field3pt3 = new Field(TerrainType.FARMLAND);
	Field field3pt4 = new Field(TerrainType.HILL);
	Field field3pt5 = new Field(TerrainType.MOUNTAINLAND);
	
	Field field4pt1 = new Field(TerrainType.SWAMP);
	Field field4pt2 = new Field(TerrainType.HILL);
	Field field4pt3 = new Field(TerrainType.SWAMP);
	Field field4pt4 = new Field(TerrainType.MOUNTAINLAND);
	Field field4pt5 = new Field(TerrainType.SWAMP);
	Field field4pt6 = new Field(TerrainType.FOREST);
	Field field4pt7 = new Field(TerrainType.WATER);
	
	public void createTwoPlayerMap(){		
		
		defineAdjacencies();

		field1pt1.setBorderPosition(true);
		
	}

	private void defineAdjacencies() {
		field1pt1.adjacencies.add(field1pt2);
		field1pt1.adjacencies.add(field2pt1);
		
		field1pt2.adjacencies.add(field1pt1);
		field1pt2.adjacencies.add(field2pt1);
		field1pt2.adjacencies.add(field2pt2);
		field1pt2.adjacencies.add(field1pt3);
		
		field1pt3.adjacencies.add(field1pt2);
		field1pt3.adjacencies.add(field2pt2);
		field1pt3.adjacencies.add(field2pt3);
		field1pt3.adjacencies.add(field2pt4);
		field1pt3.adjacencies.add(field1pt4);
		
		field1pt4.adjacencies.add(field1pt3);
		field1pt4.adjacencies.add(field2pt4);
		field1pt4.adjacencies.add(field2pt5);
		field1pt4.adjacencies.add(field1pt5);
		
		field1pt5.adjacencies.add(field1pt4);
		field1pt5.adjacencies.add(field2pt5);
		field1pt5.adjacencies.add(field2pt6);
		
		
		field2pt1.adjacencies.add(field1pt1);
		field2pt1.adjacencies.add(field1pt2);
		field2pt1.adjacencies.add(field2pt2);
		field2pt1.adjacencies.add(field3pt1);
		
		field2pt2.adjacencies.add(field2pt1);
		field2pt2.adjacencies.add(field1pt2);
		field2pt2.adjacencies.add(field1pt3);
		field2pt2.adjacencies.add(field2pt3);
		field2pt2.adjacencies.add(field3pt2);
		field2pt2.adjacencies.add(field3pt1);
		
		field2pt3.adjacencies.add(field1pt3);
		field2pt3.adjacencies.add(field2pt4);
		field2pt3.adjacencies.add(field3pt3);
		field2pt3.adjacencies.add(field3pt2);
		field2pt3.adjacencies.add(field2pt2);
		
		field2pt4.adjacencies.add(field1pt4);
		field2pt4.adjacencies.add(field2pt5);
		field2pt4.adjacencies.add(field3pt4);
		field2pt4.adjacencies.add(field3pt5);
		field2pt4.adjacencies.add(field2pt3);
		field2pt4.adjacencies.add(field1pt3);
		
		field2pt5.adjacencies.add(field1pt4);
		field2pt5.adjacencies.add(field1pt5);
		field2pt5.adjacencies.add(field2pt6);
		field2pt5.adjacencies.add(field3pt4);
		field2pt5.adjacencies.add(field2pt4);
		
		
		field3pt1.adjacencies.add(field2pt1);
		field3pt1.adjacencies.add(field2pt2);
		field3pt1.adjacencies.add(field3pt2);
		field3pt1.adjacencies.add(field4pt2);
		field3pt1.adjacencies.add(field4pt1);
		
		field3pt2.adjacencies.add(field2pt2);
		field3pt2.adjacencies.add(field2pt3);
		field3pt2.adjacencies.add(field3pt3);
		field3pt2.adjacencies.add(field4pt3);
		field3pt2.adjacencies.add(field4pt2);
		field3pt2.adjacencies.add(field3pt1);
		
		field3pt3.adjacencies.add(field2pt3);
		field3pt3.adjacencies.add(field2pt4);
		field3pt3.adjacencies.add(field3pt4);
		field3pt3.adjacencies.add(field4pt5);
		field3pt3.adjacencies.add(field4pt4);
		field3pt3.adjacencies.add(field4pt3);
		field3pt3.adjacencies.add(field3pt2);
		
		field3pt4.adjacencies.add(field2pt4);
		field3pt4.adjacencies.add(field2pt5);
		field3pt4.adjacencies.add(field2pt6);
		field3pt4.adjacencies.add(field3pt5);
		field3pt4.adjacencies.add(field4pt6);
		field3pt4.adjacencies.add(field4pt5);
		field3pt4.adjacencies.add(field3pt3);
		
		field3pt5.adjacencies.add(field2pt6);
		field3pt5.adjacencies.add(field4pt7);
		field3pt5.adjacencies.add(field4pt6);
		field3pt5.adjacencies.add(field3pt4);
		
		field4pt1.adjacencies.add(field3pt1);
		field4pt1.adjacencies.add(field4pt2);
		
		field4pt2.adjacencies.add(field4pt1);
		field4pt2.adjacencies.add(field3pt1);
		field4pt2.adjacencies.add(field3pt2);
		field4pt2.adjacencies.add(field4pt3);
		
		field4pt3.adjacencies.add(field4pt2);
		field4pt3.adjacencies.add(field3pt2);
		field4pt3.adjacencies.add(field3pt3);
		field4pt3.adjacencies.add(field4pt4);

		field4pt4.adjacencies.add(field4pt3);
		field4pt4.adjacencies.add(field3pt3);
		field4pt4.adjacencies.add(field4pt5);
		
		field4pt5.adjacencies.add(field4pt4);
		field4pt5.adjacencies.add(field3pt3);
		field4pt5.adjacencies.add(field3pt4);
		field4pt5.adjacencies.add(field4pt6);
		field4pt5.adjacencies.add(field4pt7);
		
		field4pt6.adjacencies.add(field3pt4);
		field4pt6.adjacencies.add(field3pt5);
		field4pt6.adjacencies.add(field4pt7);
		field4pt6.adjacencies.add(field4pt5);
		
		field4pt7.adjacencies.add(field4pt5);
		field4pt7.adjacencies.add(field4pt6);
		field4pt7.adjacencies.add(field3pt5);
	}

}
