package example;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class KnightCard extends Card {

	public KnightCard () throws SlickException{
		cardType = new Image ("images/knight.jpg");
	}
	
	@Override
	public void effect (){
		// should trigger the Robber's movement and steal effect
	}
}
