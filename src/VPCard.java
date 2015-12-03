import java.io.IOException;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class VPCard extends Card {
	
	Controller control;
	Game game;
	
	public VPCard (Controller control) throws SlickException{
		super (control);
		cardType = new Image ("images/victorypoint.jpg");
		effectline = new String("drew victory point card");
		this.control = control;
	
	}
	
	@Override
	public void effect () throws IOException{
		control.resources.victoryPoint++;
		game.client.obj.playerVictoryPoints[control.playerNo - 1][0] ++;
		game.client.sendData(game.client.obj);
	}
}