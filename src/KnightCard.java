

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/* This class stores the image and string for the Knight card. 
 * The effect increases the integer armySize with 1, which is displayed in the Resource class.
 * Image is not displayed in this version.
 */

public class KnightCard extends Card {

	Controller control;
	
	public KnightCard (Controller control) throws SlickException{
		super (control);
		this.control = control;
		
		cardType = new Image ("images/knight.jpg");
		effectline = new String("drew knight card");
	}
	
	@Override
	public void effect (){
		control.resources.armySize++;
	}
}
