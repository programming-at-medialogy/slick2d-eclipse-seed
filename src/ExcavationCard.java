

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ExcavationCard extends Card{

	Controller control;
	
	public ExcavationCard (Controller control) throws SlickException {
		super (control);
		cardType = new Image ("images/excavation.jpg");
		
		effectline = new String("drew excavation card");
	}
	
	@Override
	public void effect (){
		control.receivedExCard = 2;
	}
}
