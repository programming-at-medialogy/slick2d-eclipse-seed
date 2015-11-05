package example;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 * Created by TMA on 03-11-2015.
 */

public class Lobby extends BasicGameState{

    Image lobbyBackground;
    Boolean toggle, playerReady;

    public Lobby()     {    }

    Player p1, p2, p3, p4;
    Roles role;
    Button[] buttons;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        lobbyBackground = new Image("assets/lobby.png");
        buttons = new Button[4];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button("LOBBY BUTTON", 43, 70*i+130, 0);
        }

        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
        p3 = new Player("Player 3");
        p4 = new Player("Player 4");
        playerReady = false;

        role = new Roles("TEST",1);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].init(gc);
        }

        role.init(gc);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        for (int j = 0; j < buttons.length; j++) {
            buttons[j].update(gc, i);
            togglePlayerStatus(gc, buttons, p1);
        }



    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        g.drawImage(lobbyBackground, 0, 0);

        for (int j = 0; j < buttons.length; j++) {
            buttons[j].render(gc, g);
            if (p1.getPlayerReady()) {
                role.render(gc, g);
            }
        }

    }

    public void togglePlayerStatus(GameContainer gc, Button[] button, Player player) {

        for (int i = 0; i < button.length; i++) {
            toggle = player.getPlayerReady();

            if (button[i].clickWithin(gc)) {
                if (!toggle) {
                    player.setPlayerReady(true);
                    button[i].setPicIndexNo(1);
                } else {
                    player.setPlayerReady(false);
                    button[i].setPicIndexNo(0);

                }
            }
        }
    }


    @Override
    public int getID() {
        return 1;
    }
}
