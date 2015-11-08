package example;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The play state of the game. The player enters this state when all 4 players are connected and have toggled their
 * status to ready.
 * Created by TMA.
 */
public class PlayState extends BasicGameState {

    private Image gameBoard;


    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        gameBoard = new Image("assets/backgrounds/bg.png");
    }


    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

    }


    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        g.drawImage(gameBoard, 0,0);
    }

    @Override
    public int getID() {
        return 2;
    }
}
