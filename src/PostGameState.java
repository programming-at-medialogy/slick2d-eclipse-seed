import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PostGameState extends BasicGameState implements KeyListener {
	
	private Image tableImg;

	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
		tableImg = new Image("resources/tableImg.png"); // insert table image
		
		Button playAgain = new Button(Windows.scWidth / 2, Windows.scHeight / 2, 200, 100, 20, "_test", this) {
			
			@Override
			public void isClicked() {
				
			}
		};
	}

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		Color backgroundColor = Color.decode("#cc3333");
		g.setBackground(backgroundColor);
		
		tableImg.draw();
		Button.draw(g, this);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		Button.update(this);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return States.PostGameState;
	}

}
