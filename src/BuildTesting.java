
public class BuildTesting {

	
	
	public static void start() {
		Hexagon.generateMap();
		GameData.initGameData();
		Building.build(Position.assignPosition(1, 4), 0);
		Building.build(Position.assignPosition(2, 7), 1);
	}
}
