import java.util.ArrayList;

public class Road {
	
	/* Again, just like in the building class, the player should not be static.
	 * However, if i change it to non static i screw up the code. The code therefore needs to be rewritten a bit.
	 * See building class for elaboration. */
	static int player;	
	Position start;
	Position end;
	static int roadLength;
	static int tempLength;
	//static ArrayList<Road> roads = new ArrayList<Road>();
	boolean startTouch;
	boolean endTouch;
	boolean visited;
	
	static int longestRoad = 0;
	
	Road(Position startPos, Position endPos,int playerIndex){
		startTouch = false;
		endTouch = false;
		start = startPos;
		end = endPos;
		player = playerIndex;
		checkEnds(this);
		visited = false;
	}
	
	public static void buildRoad(Position startPos, Position endPos,int playerIndex){
		if(Position.getLength(startPos, endPos) == 1){
			System.out.println("Constructing road");
			Road road = new Road(startPos,endPos,playerIndex);
			GameData.roads.add(road);
			longestRoad();
		}
		else {
			System.out.println("ERROR");
			System.out.println("Length: " + Position.getLength(startPos, endPos));
		}
	}
	
	/**
	 * Checks if the road can be build at the requested position
	 * @param Division The division
	 * @param startIndex The start of the road
	 * @param endIndex The end of the road
	 * @return Boolean
	 */
	
	static void longestRoad(){	
		roadLength = 1;
		int thisRoad = 0;

		for(int i = 0; i<GameData.roads.size(); i++){
			tempLength = 0;
			if(GameData.roads.get(i).player == player){ // we only want to look at this players roads
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
			Main.longestRoad = player;
			System.out.println("Longest road is " + thisRoad);
		}
	}
	
	
	
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
