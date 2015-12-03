

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/* This class stores the image and string for the Excavation card.
 * The effect sets the integer receivedExCard to 2, which is used in the OnScreenButton class
 * which then handles the effect for this card.
 * Image is not displayed in this version.
 */

public class ExcavationCard extends Card{

	Controller control;
	
	public ExcavationCard (Controller control) throws SlickException {
		super (control);
		this.control = control;
		
		cardType = new Image ("images/excavation.jpg");
		effectline = new String("drew excavation card");
	}
	
	@Override
	public void effect (){
		control.receivedExCard = 2; //The player may now choose two resources.
	}
}
