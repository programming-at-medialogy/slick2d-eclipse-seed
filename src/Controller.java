import org.newdawn.slick.SlickException;

public class Controller {
	int playerNo;
	Game game;
	Resource resources;
	
	int receivedExCard = 0;
	
	Controller() throws SlickException{
		resources = new Resource(900,10);
		playerNo = game.client.obj.playerNumber; //Set player number
	}

}