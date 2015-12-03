import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class KnightCard extends Card {

	Controller control;
	
	public KnightCard (Controller control) throws SlickException{
		super (control);
		cardType = new Image ("images/knight.jpg");
		effectline = new String("drew knight card");
		this.control = control;
	}
	
	@Override
	public void effect (){
		control.armyCounter++;
		control.resources.armySize++;
	}
}