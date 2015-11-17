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
	static ArrayList<Road> roads = new ArrayList<Road>();
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
			roads.add(road);
			longestRoad();
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

		for(int i = 0; i<Road.roads.size(); i++){
			tempLength = 0;
			if(Road.roads.get(i).player == player){ // we only want to look at this players roads
				if(Road.roads.get(i).endTouch == true){
					thisRoad = roadLength(Road.roads.get(i),"end");
				}
				else if(Road.roads.get(i).startTouch == true){
					thisRoad = roadLength(Road.roads.get(i),"start");
				}
			}	
		}		
		
		if (thisRoad > Road.longestRoad){
			Road.longestRoad = thisRoad;
			Main.longestRoad = player;
			System.out.println(thisRoad);
		}
	}
	
	
	
	static int roadLength(Road theRoad, String endOrStart){
		for (int i = 0; i < Road.roads.size(); i++){
			if (theRoad != Road.roads.get(i)){ 
				
				if(endOrStart == "end"){
					if(Position.comparePosition(theRoad.end, Road.roads.get(i).start)){
						tempLength ++;
						roadLength(Road.roads.get(i), "en");
					}
					if(Position.comparePosition(theRoad.end, Road.roads.get(i).end)){
						tempLength ++;
						roadLength(Road.roads.get(i), "start");
					}
					else{
						if (tempLength >= roadLength){
							roadLength = tempLength;
							System.out.println(roadLength);
						}
						tempLength --;
					}
				}
				
				if(endOrStart == "start"){
					if(Position.comparePosition(theRoad.start, Road.roads.get(i).start)){
						tempLength ++;
						roadLength(Road.roads.get(i), "end");
					}
					if(Position.comparePosition(theRoad.start, Road.roads.get(i).end)){
						tempLength ++;
						roadLength(Road.roads.get(i), "start");
					}
					}
					else{
						if (tempLength >= roadLength){
							roadLength = tempLength;
							System.out.println(roadLength);
						}
						tempLength --;
					}
				}
					
					
		}
		return roadLength;
	}
	
	
	
	void checkEnds(Road theRoad){
		for(int i = 0; i<Road.roads.size(); i++){
			if(theRoad != Road.roads.get(i)){
				if(Position.comparePosition(theRoad.end, Road.roads.get(i).start)){
					endTouch = true;
					Road.roads.get(i).startTouch = true;
				}
				if(Position.comparePosition(theRoad.end, Road.roads.get(i).end)){
					endTouch = true;
					Road.roads.get(i).endTouch = true;
				}
				if(Position.comparePosition(theRoad.start, Road.roads.get(i).start)){
					startTouch = true;
					Road.roads.get(i).startTouch = true;
				}
				if(Position.comparePosition(theRoad.start, Road.roads.get(i).end)){
					startTouch = true;
					Road.roads.get(i).endTouch = true;
				}
			}
		}
	}
	
	/**
	 * TODO
	 * @return
	 */
	public static ArrayList<Road> getRoads() {
		return roads;
	}

}
