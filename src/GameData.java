import java.util.ArrayList;
/**
 * Class containing all game data
 * @author Frederik Emil
 *
 */
public class GameData {
	public static ArrayList<Building> buildings; 
	public static ArrayList<Road> roads;
	public static ArrayList<Player> players;
	public static TradeObject tObject;
	public static int turn;
	public static int rolledNumber; //The number rolled by the dice
	static int longestRoad; //the index of the player in the player-ArraList with the longest road
	int mostKnights; //the index of the player in the player-ArraList with the most knights
	int thief; //the placement of the thief
	static int ownIndex;
	
	static void initGameData(){
		buildings = new ArrayList<Building>(); 
		roads = new ArrayList<Road>();
		players = new ArrayList<Player>();
	}
}
