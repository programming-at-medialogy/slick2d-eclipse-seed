

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MonopolyCard extends Card{
	/* The below variables should be used for buttons to confirm which resource is selected.
	private boolean oreType = false;
	private boolean woolType = false;
	private boolean woodType = false;
	private boolean clayType = false;
	private boolean wheatType = false;
	*/
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
