package example;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * The player class
 * Created by TMA on 03-11-2015.
 */
public class Player extends BasicGame {

    String pos, role;
    boolean turn;
    int actionsLeft;
    private boolean playerReady;


    public Player(String gametitle) {
        super(gametitle);
        playerReady = false;

    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {


    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    /**
     * GETTER and SETTER Methods
     */

    public boolean getPlayerReady() {
        return playerReady;
    }

    public void setPlayerReady(boolean setPlayerReady) {
        this.playerReady = setPlayerReady;
    }

    public void showHand(Graphics g) {

    }




}
