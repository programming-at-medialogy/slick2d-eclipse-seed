import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/*
 * This class is used to display the dice inside of the game.
 */
public class Dice {
	
	// Integer to display the image related to the dice roll
	int index;
	
	// Image we want to load after the roll
	Image dice;
	
	// Integers for the dice image position
	int x;
	int y;
	
	Dice(int x, int y, int index){
		this.x = x;
		this.y = y;
		this.index = index;
		
	}

	// Method to render out the image onto the game board
	public void render(GameContainer gc, Graphics g) throws SlickException {
		dice = new Image("images/dice_" + index + ".png");
		g.drawImage(dice, x, y);
	}
	
}
