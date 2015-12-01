

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Card {
	Image cardType;
	public int x;
	public int y;
	
	Controller control;
	
	public Card (Controller control) throws SlickException{
		this.control = control;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawImage(cardType,x,y);	
	}
	
	public void effect (){
		
	}
	
	
	public void createDevPile (Card[] input) throws SlickException{
		for (int i = 0; i < 25; i++){
			if (i < 2){
				input[i] = new MonopolyCard(control);
			} else if (i < 4){
				input[i] = new BuildRoadsCard(control);
			} else if (i < 6){
				input[i] = new ExcavationCard(control);
			} else if (i < 21){
				input[i] = new KnightCard(control);
			} else if (i < 25){
				input[i] = new VPCard(control);
			}
		}
	}
	
	public boolean checkDevCardCost (){
		boolean isTrue = false;
		if (control.resources.wheatResource >= 1 && control.resources.woolResource >= 1 
				&& control.resources.oreResource >= 1){
			isTrue = true;
		}else {
			isTrue = false;
		}
		return isTrue;
	}
	public void buyCard(){
		control.resources.wheatResource -= 1;
		control.resources.woolResource -= 1;
		control.resources.oreResource -= 1;
	}
}
