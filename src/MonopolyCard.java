

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/* This class stores the image and string for the Monopoly card.
 * The effect turns a boolean, receivedMonoCard to true, 
 * which is used in the OnScreenButton class, which handles the effect.
 * Image is not displayed in this version.
 */

public class MonopolyCard extends Card{

	Controller control;
	
	public MonopolyCard(Controller control) throws SlickException {
		super (control);
		this.control = control;
		
		cardType = new Image ("images/monopoly.jpg");
		effectline = new String("Drew monopoly card");
	}
	
	@Override
	public void effect (){
		control.receivedMonoCard = true; //the player receives a monopoly card.
	}
}
