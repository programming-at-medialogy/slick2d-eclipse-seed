

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Card {
	Image cardType;
	public int x;
	public int y;
	int resCardSize = 50;
	
	public Card () throws SlickException{
		
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
				input[i] = new MonopolyCard();
			} else if (i < 4){
				input[i] = new BuildRoadsCard();
			} else if (i < 6){
				input[i] = new ExcavationCard();
			} else if (i < 21){
				input[i] = new KnightCard();
			} else if (i < 25){
				input[i] = new VPCard();
			}
		}
	}
	
	//Resource piles 
	
	public void createOrePile (Card[] input) throws SlickException{
		for(int i = 0; i < resCardSize; i++){
			input[i] = new CardOre();
		}
	}
	
	public void createWoodPile (Card[] input) throws SlickException{
		for(int i = 0; i < resCardSize; i++){
			input[i] = new CardWood();
		}
	}
	
	public void createWoolPile (Card[] input) throws SlickException{
		for(int i = 0; i < resCardSize; i++){
			input[i] = new CardWool();
		}
	}
	
	public void createWheatPile (Card[] input) throws SlickException{
		for(int i = 0; i < resCardSize; i++){
			input[i] = new CardWheat();
		}
	}
	
	public void createClayPile (Card[] input) throws SlickException{
		for(int i = 0; i < resCardSize; i++){
			input[i] = new CardClay();
		}
	}
}
