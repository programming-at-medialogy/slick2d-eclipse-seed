import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/* 
 * This is the super class for all of the cards,
 * which is all of the development cards,
 * it contains the information which every subclass uses.
 * Though the render function isn't used now it was the idea to have them displayed.
 */
public class Card {
	Image cardType;
	public int x;
	public int y;
	String effectline;
	
	Controller control;
	Game game;
	
	public Card (Controller control) throws SlickException{
		this.control = control;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawImage(cardType,x,y);	
	}
	
	public void effect (){
		
	}
	
	/* 
	 * This method creates the players development pile.
	 * This makes sure that we have the same percentage amount on drawing certain cards
	 * as we would have in the board game.
	 */
	public void createDevPile (Card[] input) throws SlickException{
		for (int i = 0; i < 25; i++){
			if (i < 2){
				input[i] = new MonopolyCard(control);
			} else if (i < 4){
				input[i] = new BuildRoadsCard(control);
			} else if (i < 6){
				input[i] = new ExcavationCard(control);
			} else if (i < 20){
				input[i] = new KnightCard(control);
			} else if (i < 25){
				input[i] = new VPCard(control);
			}
		}
	}
	// Checks that the player can afford a development card
	public boolean checkDevCardCost (){
		boolean isTrue = false;
		if (game.client.obj.playerResource [control.playerNo-1][1] >= 1 && game.client.obj.playerResource [control.playerNo-1][4] >= 1 
				&& game.client.obj.playerResource [control.playerNo-1][0] >= 1){
			isTrue = true;
		}else {
			isTrue = false;
		}
		return isTrue;
	}
	
	// Removes resources if the player buys a card
	public void buyCard(){
		game.client.obj.playerResource [control.playerNo-1][0]--;
		game.client.obj.playerResource [control.playerNo-1][1]--;
		game.client.obj.playerResource [control.playerNo-1][4]--;
	}
}
