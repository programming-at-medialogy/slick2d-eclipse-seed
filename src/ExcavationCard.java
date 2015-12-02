

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ExcavationCard extends Card{

	public ExcavationCard (Controller control) throws SlickException {
		super (control);
		cardType = new Image ("images/excavation.jpg");
		
		effectline = new String("drew excavation card");
	}
	
	@Override
	public void effect (){
		/*
		 * When using this card the player can draw 2 resource cards of any type, by the player's choice
		 */
	}
}
