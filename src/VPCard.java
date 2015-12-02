

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class VPCard extends Card {
	
	Controller control;
	
	public VPCard (Controller control) throws SlickException{
		super (control);
		cardType = new Image ("images/victorypoint.jpg");
		effectline = new String("drew victory point card");
		this.control = control;
	
	}
	
	@Override
	public void effect (){
		control.resources.victoryPoint++;
	}
}
