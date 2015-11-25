

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BuildRoadsCard extends Card {

	public BuildRoadsCard () throws SlickException{
		cardType = new Image ("images/buildroads.jpg");
	}
	
	@Override
	public void effect (){
		// should trigger the button for allowing the player to place 2 roads
	}
}
