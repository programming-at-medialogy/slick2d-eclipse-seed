import java.util.ArrayList;

public class Road {
	
	int player;	
	Position start;
	Position end;
	static int roadLength;
	static int tempLength;
	static ArrayList<Road> roads;
	static int longestRoad = 0;
	
	Road(Position startPos, Position endPos,int playerIndex){
		start = startPos;
		end = endPos;
		player = playerIndex;
	}
	
	public static void buildRoad(Position startPos, Position endPos,int playerIndex){
		if(Position.getLength(startPos, endPos) == 1){
			Road road = new Road(startPos,endPos,playerIndex);
			longestRoad(road);
			roads.add(road);
		}
	}
	
	/**
	 * Checks if the road can be build at the requested position
	 * @param Division The division
	 * @param startIndex The start of the road
	 * @param endIndex The end of the road
	 * @return Boolean
	 */
	static boolean canBuild(int Division, int startIndex, int endIndex){
		return true;
	}
	
	static void longestRoad(Road theRoad){	
		roadLength = 0;
		int thisRoad = roadLength(theRoad);
		if (thisRoad > Road.longestRoad){
			Road.longestRoad = thisRoad;
			Main.longestRoad = theRoad.player;
		}
	}
	
	
	static int roadLength(Road theRoad){
		for (int i = 0; i < Road.roads.size(); i++){
			tempLength = 0;
			if(theRoad.player == Road.roads.get(i).player){ // we only want to look at this players roads
				if(theRoad.end == Road.roads.get(i).start || theRoad.end == Road.roads.get(i).end ||
				   theRoad.start == Road.roads.get(i).start || theRoad.start == Road.roads.get(i).end){
					tempLength ++;
					Road.longestRoad(Road.roads.get(i));
				} 
				else if (tempLength > roadLength){
					roadLength = tempLength;
				}
			}
		}
		return longestRoad;
	}

}
