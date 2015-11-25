package example;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ExcavationCard extends Card{

	public ExcavationCard () throws SlickException {
		cardType = new Image ("images/excavation.jpg");
	}
	
	@Override
	public void effect (){
		/*
		 * When using this card the player can draw 2 resource cards of any type, by the player's choice
		 */
	}
}
