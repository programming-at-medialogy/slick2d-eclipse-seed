package example;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 *  The Lobby Class extends BasicGameState since it is a new state of the program.
 *  The Lobby handles player connections and makes sure that enough people are connected
 *  in order to play the game. The status of the player (ready to play/not ready) is toggled through
 *  the lobby
 */

public class Lobby extends BasicGameState {

    private Image lobbyBackground;

    public Lobby() {
    }

    Player p1, p2, p3, p4;
    Roles role;
    Button[] buttons;
    Button beginGame;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        lobbyBackground = new Image("assets/backgrounds/lobby.png");
        buttons = new Button[4];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button("LOBBY_BUTTON", 43, 70 * i + 130, 0);
        }
        beginGame = new Button("BEGIN_GAME",763, 642, 2);

        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
        p3 = new Player("Player 3");
        p4 = new Player("Player 4");

        role = new Roles("TEST", 3);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].init(gc);
        }
            beginGame.init(gc);
            role.init(gc);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        for (int j = 0; j < buttons.length; j++) {
            if (p1.getPlayerReady()) {
                togglePlayerStatus(gc, buttons, p1, 0);

            } else {
                togglePlayerStatus(gc, buttons, p1, 0);
            }
            if (beginGame.clickWithin(gc) && beginGame.isActive()) {
                sbg.enterState(2);
            }


        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(lobbyBackground, 0, 0);

        for (int j = 0; j < buttons.length; j++) {
            if (p1.getPlayerReady()) {
                buttons[j].render(gc, g);
                role.render(gc, g);
                beginGame.render(gc,g);
                beginGame.setActive(true);
            } else {
                buttons[j].render(gc, g);
                beginGame.setActive(false);
            }
        }

    }

    /**
    *   togglePlayerStatus sees if a player in the player class is ready and
    *   toggles the image of the button (ready/not ready) and sets the playerReady
    *   status to the opposite of what it was
    */
    public void togglePlayerStatus(GameContainer gc, Button[] button, Player player, int buttonNo) {

        Boolean toggle = player.getPlayerReady();

        if (button[buttonNo].clickWithin(gc)) {
            if (!toggle) {
                player.setPlayerReady(true);
                button[buttonNo].setPicIndexNo(1);
            } else {
                player.setPlayerReady(false);
                button[buttonNo].setPicIndexNo(0);

            }
        }
    }


    @Override
    public int getID() {
        return 1;
    }
}
