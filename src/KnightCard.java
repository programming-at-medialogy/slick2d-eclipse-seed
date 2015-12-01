

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class KnightCard extends Card {

	public KnightCard (Controller control) throws SlickException{
		super (control);
		cardType = new Image ("images/knight.jpg");
	}
	
	@Override
	public void effect (){
		// should trigger the Robber's movement and steal effect
	}
}
