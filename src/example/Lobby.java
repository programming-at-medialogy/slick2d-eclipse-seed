package example;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;


/**
 * Created by TMA on 03-11-2015.
 */

public class Lobby extends BasicGame{

    Image lobbyBackground;
    Button b1, b2, b3, b4;

    public Lobby(String gamename)
    {
        super(gamename);
    }

    //Player player1, player2, player3, player4;

    public void init(GameContainer gc) throws SlickException {
        lobbyBackground = new Image("assets/lobby.png");
        b1 = new Button("button1", 60, 150);
        b2 = new Button("button1", 60, 300);
        b3 = new Button("button1", 60, 450);
        b4 = new Button("button1", 60, 600);

        b1.init(gc);
        b2.init(gc);
        b3.init(gc);
        b4.init(gc);
    }

    public void update(GameContainer gc, int i) throws SlickException {
        b1.update(gc, i);
        b2.update(gc, i);
        b3.update(gc, i);
        b4.update(gc, i);

    }

    public void render(GameContainer gc, Graphics g) throws SlickException{
        g.drawImage(lobbyBackground, 0, 0);
        b1.render(gc, g);
        b2.render(gc, g);
        b3.render(gc, g);
        b4.render(gc, g);
    }


}
