import java.util.ArrayList;

/**
 * Class describing a building
 * @author Frederik Emil
 *
 */

public class Road {
	
	public final int PLAYER_INDEX;	
	Position start;
	Position end;
	static int roadLength;
	static int tempLength;
	boolean startTouch;
	boolean endTouch;
	boolean visited;
	
	static int longestRoad = 0;
	
	/**
	 * Private constructor. One should use {@link Road#buildRoad(Position, Position, int)} instead, since it contains error checking.
	 * @param startPos The start position of the road
	 * @param endPos The End position of the road
	 * @param playerIndex The player index
	 */
	Road(Position startPos, Position endPos,int playerIndex){
		startTouch = false;
		endTouch = false;
		start = startPos;
		end = endPos;
		PLAYER_INDEX = playerIndex;
		checkEnds(this);
		visited = false;
	}
	
	/**
	 * This method should be used instead of calling the constructor since it contains error checking.
	 * @param startPos The start position of the road
	 * @param endPos The End position of the road
	 * @param playerIndex The player index
	 * @return The constructed Road. Returns null if the road cannot be build
	 */
	public static Road buildRoad(Position startPos, Position endPos, int playerIndex){
		if(Position.getLength(startPos, endPos) == 1){
			System.out.println("Constructing road");
			Road road = new Road(startPos,endPos,playerIndex);
			GameData.roads.add(road);
			longestRoad();
			return road;
		}
		else {
			System.out.println("ERROR");
			System.out.println("Length: " + Position.getLength(startPos, endPos));
			return null;
		}
	}

	/**
	 * Checks which road is the longest by calling roadLength for each end road
	 */
	static void longestRoad(){	
		roadLength = 1;
		int thisRoad = 0;

		for(int i = 0; i<GameData.roads.size(); i++){
			tempLength = 0;
			if(GameData.roads.get(i).PLAYER_INDEX != GameData.ownIndex){
				if(GameData.roads.get(i).endTouch == true){
					thisRoad = roadLength(GameData.roads.get(i),"end");
				}
				else if(GameData.roads.get(i).startTouch == true){
					thisRoad = roadLength(GameData.roads.get(i),"start");
				}
			}	
		}		
		
		if (thisRoad > Road.longestRoad){
			Road.longestRoad = thisRoad;
			GameData.longestRoad = thisRoad; // needs fixing frede
			System.out.println("Longest road is " + thisRoad);
		}
	}
	
	
	/**
	 * Finds the longest branch of road from a given starting road
	 * @param theRoad The starting point of the road
	 * @param endOrStart String that determines if we are going to check the start or the end of the road
	 * @return The length of the longest branch
	 */
	static int roadLength(Road theRoad, String endOrStart){
		ArrayList<Integer> nearbyRoads = new ArrayList<Integer>();
		
		for (int i = 0; i < GameData.roads.size(); i++){
			if (theRoad != GameData.roads.get(i)) {
				
				if (endOrStart == "end") {
					if (Position.comparePosition(theRoad.end, GameData.roads.get(i).start)) {
						nearbyRoads.add(roadLength(GameData.roads.get(i), "end"));
					} else if (Position.comparePosition(theRoad.end, GameData.roads.get(i).end)) {
						nearbyRoads.add(roadLength(GameData.roads.get(i), "start"));
					}
				}
				
				else if (endOrStart == "start") {
					if (Position.comparePosition(theRoad.start, GameData.roads.get(i).start)) {
						nearbyRoads.add(roadLength(GameData.roads.get(i), "end"));
					}
					else if (Position.comparePosition(theRoad.start, GameData.roads.get(i).end)) {
						nearbyRoads.add(roadLength(GameData.roads.get(i), "start"));
					}
				}
			}
		}
		
		// return the length
		if (nearbyRoads.size() == 0)
			return 1;
		if (nearbyRoads.size() == 1)
			return nearbyRoads.get(0) + 1;
		if (nearbyRoads.size() == 2) {
			if (nearbyRoads.get(0) > nearbyRoads.get(1))
				return nearbyRoads.get(0) + 1;
			return nearbyRoads.get(1) + 1;
		}
		
		// error
		System.out.println("Error");
		return 0;
	}
	
	
	/**
	 * Sets endTouch and startTouch for all the roads belonging to the player
	 * @param theRoad Starting point of the function
	 */
	void checkEnds(Road theRoad){
		for(int i = 0; i<GameData.roads.size(); i++){
			if(theRoad != GameData.roads.get(i)){
				if(Position.comparePosition(theRoad.end, GameData.roads.get(i).start)){
					endTouch = true;
					GameData.roads.get(i).startTouch = true;
				}
				if(Position.comparePosition(theRoad.end, GameData.roads.get(i).end)){
					endTouch = true;
					GameData.roads.get(i).endTouch = true;
				}
				if(Position.comparePosition(theRoad.start, GameData.roads.get(i).start)){
					startTouch = true;
					GameData.roads.get(i).startTouch = true;
				}
				if(Position.comparePosition(theRoad.start, GameData.roads.get(i).end)){
					startTouch = true;
					GameData.roads.get(i).endTouch = true;
				}
			}
		}
	}
	
	/**
	 * TODO
	 * @return
	 */
	public static ArrayList<Road> getRoads() {
		return GameData.roads;
	}
	
	/**
	 * Gets the center x coordinate of the road.
	 * @return the center x
	 */
	public float getCenterX() {
		return (start.getX() + end.getX()) / 2;
	}
	
	/**
	 * Gets the center y coordinate of the road.
	 * @return the center x
	 */
	public float getCenterY() {
		return (start.getY() + end.getY()) / 2;
	}
	
	/**
	 * Gets the angle of the road.
	 * @return the angle in degrees
	 */
	public float getAngle() {
		float deltaY = start.getY() - end.getY();
		float deltaX = start.getX() - end.getX();
		return (float)Math.toDegrees(Math.atan2(deltaY, deltaX) + Math.PI / 2);
	}

}
