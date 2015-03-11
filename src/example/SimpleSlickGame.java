package example;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;

public class SimpleSlickGame extends BasicGame
{
    private double x,y;
    private float a = 0;

	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
        a += 0.005f * delta;

        x = Math.cos(a)*50;
        y = Math.sin(a)*50;
    }

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
        g.setColor(new Color(0xFFFFFF));
		g.drawString("Hello World!", 250, 200);
        g.drawString("Commit test", 250, 100);
        g.setColor(new Color(0xBADA55));
        g.fillOval(320 + (float) x, 240 + (float) y, 10, 10, 10);
        g.rotate(320, 240, a * 10);
        g.fillOval(420 + (float) x, 240 + (float) y, 10, 10, 3);
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}