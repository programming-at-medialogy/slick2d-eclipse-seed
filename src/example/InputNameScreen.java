package example;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by TMA on 05-11-2015.
 */
public class InputNameScreen extends BasicGameState {

    Image bg;
    Button b;

    public InputNameScreen() {
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        bg = new Image("assets/backgrounds/namescreen.png");
        b = new Button("ENTER_LOBBY", 400, 642, 3);
        b.init(gc);

    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

        g.drawImage(bg, 0, 0);
            b.render(gc, g);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        b.update(gc, i);
        if (b.clickWithin(gc)) {
            sbg.enterState(1);
        }

    }
}
