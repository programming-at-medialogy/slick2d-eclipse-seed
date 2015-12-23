import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/* 
 * This gives us the possibility to make one of the special development cards
 * and make sure that we trigger the correct effect when it is drawn.
 */

public class BuildRoadsCard extends Card {

	public BuildRoadsCard (Controller control) throws SlickException{
		
		super(control);
		this.control = control;
		cardType = new Image ("images/buildroads.jpg");
		effectline = new String("drew build roads card");
	}
	
	@Override
	public void effect (){
		
		// should trigger the button for allowing the player to place 2 roads
		game.client.obj.playerResource[control.playerNo-1][3] += 2;
		game.client.obj.playerResource[control.playerNo-1][2] += 2;
		
	}
}
