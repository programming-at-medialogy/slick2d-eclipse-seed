import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Dice {
	
	int index;
	Image dice;
	int x;
	int y;
	
	Dice(int x, int y, int index){
		this.x = x;
		this.y = y;
		this.index = index;
		
	}

	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		dice = new Image("images/dice_" + index + ".png");
		g.drawImage(dice, x, y);
	}
	
}
