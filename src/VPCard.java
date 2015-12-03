

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/*This class stores the image and a string for the Victory Point card.
 * The effect increments the integer victoryPoint with 1, which is displayed in the Resource class.
 * Image is not displayed in this version.
 */

public class VPCard extends Card {
	
	Controller control;
	
	public VPCard (Controller control) throws SlickException{
		super (control);
		this.control = control;
		
		cardType = new Image ("images/victorypoint.jpg");
		effectline = new String("drew victory point card");
	}
	
	@Override
	public void effect (){
		control.resources.victoryPoint++; //the player gets a victory point!
	}
}
