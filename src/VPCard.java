

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

// Victory point development card class
public class VPCard extends Card {
	public int increaseVP = 1;
	public int playerScore; 
	/* right now we don't know how to get the information,
	 * but when we do we need to change the class
	 */
	
	public VPCard (Controller control) throws SlickException{
		super (control);
		cardType = new Image ("images/victorypoint.jpg");
		
	}
	
	@Override
	public void effect (){
		playerScore += increaseVP;
	}
}
