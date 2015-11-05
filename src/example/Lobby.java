package example;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 * Created by TMA on 03-11-2015.
 */

public class Lobby extends BasicGameState{

    Image lobbyBackground;
    Button b1, b2, b3, b4;
    Boolean toggle, playerReady;

    public Lobby()     {    }

    Player p1, p2, p3, p4;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        lobbyBackground = new Image("assets/lobby.png");
        b1 = new Button("button1", 43, 130,0);
        b2 = new Button("button2", 43, 200,1);
        b3 = new Button("button3", 43, 270,2);
        b4 = new Button("button4", 43, 340,3);
        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
        p3 = new Player("Player 3");
        p4 = new Player("Player 4");
        playerReady = false;

        b1.init(gc);

        b2.init(gc);
        b3.init(gc);
        b4.init(gc);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        b1.update(gc, i);
        togglePlayerStatus(gc, b1, p1);
        b2.update(gc, i);
        togglePlayerStatus(gc, b2, p2);
        b3.update(gc, i);
        togglePlayerStatus(gc, b3, p3);
        b4.update(gc, i);
        togglePlayerStatus(gc, b4, p4);




    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        g.drawImage(lobbyBackground, 0, 0);


        b1.render(gc, g);
        b2.render(gc, g);
        b3.render(gc, g);
        b4.render(gc, g);

        g.drawString(NameScreen.tmpName,50,140);


    }

    public void togglePlayerStatus(GameContainer gc, Button button, Player player) {

        toggle = button.getPlayerReady();

        if (button.clickWithin(gc)) {
            if (!toggle) {
                button.setPlayerReady(true);
            } else {
                button.setPlayerReady(false);
            }
        }
    }


    @Override
    public int getID() {
        return 1;
    }
}
