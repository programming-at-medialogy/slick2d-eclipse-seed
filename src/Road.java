import java.util.ArrayList;

public class Road {
	
	static int player;	
	Position start;
	Position end;
	static int roadLength;
	static int tempLength;
	static ArrayList<Road> roads = new ArrayList<Road>();
	boolean startTouch;
	boolean endTouch;
	static int longestRoad = 0;
	
	Road(Position startPos, Position endPos,int playerIndex){
		startTouch = false;
		endTouch = false;
		start = startPos;
		end = endPos;
		player = playerIndex;
		checkEnds();
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
			tempLength = 1;
			if(Road.roads.get(i).player == player){ // we only want to look at this players roads
				if(Road.roads.get(i).endTouch == false){
					thisRoad = roadLength(Road.roads.get(i),"start");
				}
				if(Road.roads.get(i).startTouch == false){
					thisRoad = roadLength(Road.roads.get(i),"end");
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
						System.out.println("test");
						tempLength ++;
						roadLength(Road.roads.get(i),"end");
					}
					else if(Position.comparePosition(theRoad.end, Road.roads.get(i).end)){
						System.out.println("test");
						tempLength ++;
						roadLength(Road.roads.get(i),"start");
					}
					else if (tempLength > roadLength){
						roadLength = tempLength;
					}
				}
				
				else if(endOrStart == "start"){
					if(Position.comparePosition(theRoad.start, Road.roads.get(i).start)){
						System.out.println("test");
						tempLength ++;
						roadLength(Road.roads.get(i),"end");
					}
					if(Position.comparePosition(theRoad.start, Road.roads.get(i).end)){
						System.out.println("test");
						tempLength ++;
						roadLength(Road.roads.get(i),"start");
						//System.out.println(tempLength);
					}
					else if (tempLength > roadLength){
						roadLength = tempLength;
						//System.out.println(roadLength);
					}
				}
			}
		}
		return roadLength;
	}
	
	
	void checkEnds(){
		for(int i = 0; i<Road.roads.size(); i++){
			if(end == Road.roads.get(i).start){
				endTouch = true;
				Road.roads.get(i).startTouch = true;
			}
			if(end == Road.roads.get(i).end){
				endTouch = true;
				Road.roads.get(i).endTouch = true;
			}
			if(start == Road.roads.get(i).start){
				startTouch = true;
				Road.roads.get(i).startTouch = true;
			}
			if(start == Road.roads.get(i).end){
				startTouch = true;
				Road.roads.get(i).endTouch = true;
			}
			
		}
	}

}
