package example;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class GameClient extends StateBasedGame //My first comment
{

	public GameClient(String gamename)
	{
		super(gamename);
	}


    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new Lobby());

		this.addState(new NameScreen());



    }

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new GameClient("Pandemic"));
			appgc.setShowFPS(false);
			appgc.setDisplayMode(1366, 768, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}