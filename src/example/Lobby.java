package example;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * The Lobby Class extends BasicGameState since it is a new state of the program.
 * The Lobby handles player connections and makes sure that enough people are connected
 * in order to play the game. The status of the player (ready to play/not ready) is toggled through
 * the lobby
 * Created by TMA.
 */

public class Lobby extends BasicGameState {

    private Image lobbyBackground;
    private Roles role;
    private Button readyToggle;
    private Button beginGame;
    private List<Player> players;
    private int playerno = 0;
    private int roleNo = 0;

    private Image waitingForPlayers, playersConnected;
    private Image[] team, gameBeginning;
    Animation animation;
    int counter;
    boolean gameStateControl;

    public Lobby() {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        lobbyBackground = new Image("assets/backgrounds/lobby.png");
        readyToggle = new Button("READY", 43, 130, 0);
        beginGame = new Button("BEGIN_GAME", 763, 642, 2);

        playersConnected = new Image("assets/buttons/button4.png");
        waitingForPlayers = new Image("assets/buttons/button5.png");
        team = new Image[7];
        for (int i = 0; i < team.length; i++) {
            team[i] = new Image("assets/roles/team/" + i + ".png");
        }
        gameBeginning = new Image[5];
        for (int i = 0; i < gameBeginning.length; i++) {
            gameBeginning[i] = new Image("assets/animation/" + i + ".png");
        }

        counter = 0;
        gameStateControl = false;

        beginGame.init(gc);
        readyToggle.init(gc);

        try {
            playerno = ServerCalls.assignPlayerID();
            roleNo = ServerCalls.setPlayerRole(playerno);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

        players = new ArrayList<Player>(4);
        players.add(new Player("1"));
        players.add(new Player("2"));
        players.add(new Player("3"));
        players.add(new Player("4"));

        readyToggle.setImgY(playerno * 70 + 130);
        role = new Roles("PlayerRole", roleNo);
        role.init(gc);

        animation = new Animation(gameBeginning, 1000);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        //TOGGLES YOUR STATUS BETWEEN TRUE AND FALSE
        //IF BEGIN GAME IS ACTIVE (ALL PLAYERS HAVE JOINED) PRESS THE BUTTON TO ENTER THE GAME
        togglePlayerStatus(gc, readyToggle, players, playerno);
        if (beginGame.isActive() && beginGame.clickWithin(gc)) {
            ServerCalls.setAnimationStatusTrue();
        }

        if (gameStateControl) {
            sbg.enterState(2);
            ServerCalls.beginGame();
        }

        if (playerno != 0) {
            try {
                int gs = ServerCalls.hasGameBegun();
                sbg.enterState(gs);

            } catch (IOException IOex) {
                IOex.printStackTrace();
            }

        }


    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(lobbyBackground, 0, 0);
        readyToggle.render(gc, g);
        try {

            //IF YOU ARE ACTIVE RENDER YOUR ASSIGNED ROLE
            if (ServerCalls.getPlayerStatusOnServer(playerno)) {
                role.render(gc, g);
            }

            //IF ALL PEOPLE ARE READY RENDER THE BEGIN GAME BUTTON AND SET IT TO ACTIVE
            if (ServerCalls.getPlayerStatusOnServer(0) && ServerCalls.getPlayerStatusOnServer(1) && ServerCalls.getPlayerStatusOnServer(2) && ServerCalls.getPlayerStatusOnServer(3) && !ServerCalls.getAnimationStatus()) {
                beginGame.render(gc, g);
                beginGame.setActive(true);
            } else {
                beginGame.setActive(false);
            }

            if (ServerCalls.getAnimationStatus()) {
                g.drawAnimation(animation, 763, 642);
                counter++;
                if (counter > 48) {
                    gameStateControl = true;
                }
            }

            //UPDATES THE VISUAL STATUS OF THE OTHER PLAYERS IN THE LOBBY FOR YOUR GAME CLIENT
            if (!ServerCalls.getPlayerStatusOnServer(0) && playerno != 0) {
                g.drawImage(waitingForPlayers, 43, 130);
            } else if (ServerCalls.getPlayerStatusOnServer(0) && playerno != 0) {
                g.drawImage(playersConnected, 43, 130);
                g.drawImage(team[ServerCalls.getPlayerRole(0)], 851, 163);


            }
            if (!ServerCalls.getPlayerStatusOnServer(1) && playerno != 1) {
                g.drawImage(waitingForPlayers, 43, 200);
            } else if (ServerCalls.getPlayerStatusOnServer(1) && playerno != 1) {
                g.drawImage(playersConnected, 43, 200);
                g.drawImage(team[ServerCalls.getPlayerRole(1)], 1033, 163);
            }
            if (!ServerCalls.getPlayerStatusOnServer(2) && playerno != 2) {
                g.drawImage(waitingForPlayers, 43, 270);
            } else if (ServerCalls.getPlayerStatusOnServer(2) && playerno != 2) {
                g.drawImage(playersConnected, 43, 270);
                g.drawImage(team[ServerCalls.getPlayerRole(2)], 851, 335);
            }
            if (!ServerCalls.getPlayerStatusOnServer(3) && playerno != 3) {
                g.drawImage(waitingForPlayers, 43, 340);
            } else if (ServerCalls.getPlayerStatusOnServer(3) && playerno != 3) {
                g.drawImage(playersConnected, 43, 340);
                g.drawImage(team[ServerCalls.getPlayerRole(3)], 1033, 335);
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

    }

    /**
     * @param gc      the game container passed through every instance of SLICK2D
     * @param button  - The button that is supposed to be toggled
     * @param list    - the Player
     * @param indexNo - the position of the player within the array
     *                togglePlayerStatus sees if a player in the player class is ready and
     *                toggles the image of the button (ready/not ready) and sets the playerReady
     *                status to the opposite of what it was
     */
    public void togglePlayerStatus(GameContainer gc, Button button, List<Player> list, int indexNo) {

        Boolean toggle = list.get(indexNo).getPlayerReady();

        if (button.clickWithin(gc)) {
            if (!toggle) {
                list.get(indexNo).setPlayerReady(true);
                ServerCalls.setPlayerStatusOnServer(players, playerno);

                button.setPicIndexNo(1);
            } else {
                list.get(indexNo).setPlayerReady(false);
                button.setPicIndexNo(0);
                ServerCalls.setPlayerStatusOnServer(players, playerno);


            }
        }
    }


    @Override
    public int getID() {
        return 1;
    }

}
