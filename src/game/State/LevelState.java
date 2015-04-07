package game.State;
import game.SimpleSlickGame;
import Level.Level;
 
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
public class LevelState extends BasicGameState {

	Level level;
	String firstLevel;
	public LevelState ( String firstLevel){
		this.firstLevel = firstLevel;
			}
	public void init(GameContainer container, StateBasedGame sbg)throws SlickException{
		
		level = new Level (firstLevel);
	}

	public void update (GameContainer container,  StateBasedGame  sbg, int delta) throws SlickException{
		
	}
	public void render (GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException{
		g.scale(game.SCALE, game.SCALE);
		level.render();
	}
	public void KeyPressed ( int key, char code){
		if (key == Input.KEY_ESCAPE){
			System.exit(0);
		}
		
	}

}
