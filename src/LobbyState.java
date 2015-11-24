import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LobbyState extends BasicGameState{
	Image background;
	
	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
		background = new Image("resources/background.png");

	}

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0, Windows.scWidth, Windows.scHeight, 0, 0, 1366, 768);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return States.LobbyState;
	}

}
