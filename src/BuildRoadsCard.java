

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BuildRoadsCard extends Card {

	public BuildRoadsCard (Controller control) throws SlickException{
		super(control);
		cardType = new Image ("images/buildroads.jpg");
		
		effectline = new String("drew build roads card");
	}
	
	@Override
	public void effect (){
		// should trigger the button for allowing the player to place 2 roads
		System.out.println("testGithub");
	}
}
