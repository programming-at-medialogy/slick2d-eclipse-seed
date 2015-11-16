
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



public class IntroState extends BasicGameState{

	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {		
		Ressource.getImage("resources/settlers").draw();
		g.drawString("WELCOME TO SETTLERS, THE GOOD EDITION", Windows.scWidth/3, Windows.scHeight/3);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {		
		
		//if(gc.getInput().isKeyPressed(Input.KEY_ENTER)) s.enterState(States.gameState);
		
	
	}

	@Override
	//controls the state of the program (menu, chat etc)
	public int getID() {
		return States.IntroState;
	}
}
