package example;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;


public class GameClient extends BasicGame //My first comment
{

    Image bg;
	Lobby lobby;

	public GameClient(String gamename)
	{
		super(gamename);
		lobby = new Lobby(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		lobby.init(gc);
        bg = new Image("assets/bg.png");
    }//Comment 2

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
        lobby.update(gc,i);
    }

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		//g.drawString("Pandemic!",300 , 200);
        //g.drawImage(bg,0,0);
		lobby.render(gc, g);
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new GameClient("Pandemic"));
			appgc.setShowFPS(false);
			appgc.setDisplayMode(1366, 768, true);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}