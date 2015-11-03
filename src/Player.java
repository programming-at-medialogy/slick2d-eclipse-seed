import java.awt.Color;
import java.util.ArrayList;

public class Player {
	Color color;
	ArrayList<Building> buildings; //(both settlements and cities)
	ArrayList<Road> roads;
	ArrayList<Ressource> ressources;
	ArrayList<devCard> devCard;
	int points;
	int turn;
	int knights;
	boolean hasWon;
}
