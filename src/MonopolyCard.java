

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MonopolyCard extends Card{
	
	Controller control;
	
	public MonopolyCard(Controller control) throws SlickException {
		super (control);
		cardType = new Image ("images/monopoly.jpg");
		
		effectline = new String("drew monopoly card");
	}
	
	@Override
	public void effect (){
		control.receivedMonoCard = true;
	}
}
