package example;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



/**
 * The Lobby Class extends BasicGameState since it is a new state of the program.
 * The Lobby handles player connections and makes sure that enough people are connected
 * in order to play the game. The status of the player (ready to play/not ready) is toggled through
 * the lobby
 */

public class Lobby extends BasicGameState {

    private Image lobbyBackground;
    private Roles role;
    private Button readyToggle;
    private Button beginGame;
    private List<Player> players;
    private String playerStatusOut;
    private int playerno = 0;
    private boolean curPlayStatus;

    private Image waitingForPlayers, playersConnected;

    public Lobby() {
    }



    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        lobbyBackground = new Image("assets/backgrounds/lobby.png");
        readyToggle = new Button("READY", 43, 130, 0);
        beginGame = new Button("BEGIN_GAME", 763, 642, 2);

        waitingForPlayers = new Image("assets/buttons/button4.png");
        playersConnected = new Image("assets/buttons/button5.png");

        role = new Roles("TEST", 3);

        beginGame.init(gc);
        readyToggle.init(gc);
        role.init(gc);

        players = new ArrayList<Player>(4);
        players.add(new Player("1"));
        players.add(new Player("2"));
        players.add(new Player("3"));
        players.add(new Player("4"));

        try {
            playerno = ServerCalls.assignPlayerID();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

        readyToggle.setImgY(playerno*70+130);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

            //TOGGLES YOUR STATUS BETWEEN TRUE AND FALSE
            //IF BEGIN GAME IS ACTIVE (ALL PLAYERS HAVE JOINED) PRESS THE BUTTON TO ENTER THE GAME
            togglePlayerStatus(gc,readyToggle,players,playerno);
            if (beginGame.isActive() && beginGame.clickWithin(gc)) {
                sbg.enterState(2);
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
            if (ServerCalls.getPlayerStatusOnServer(0) && ServerCalls.getPlayerStatusOnServer(1) && ServerCalls.getPlayerStatusOnServer(2) && ServerCalls.getPlayerStatusOnServer(3)) {
                beginGame.render(gc,g);
                beginGame.setActive(true);
            } else {
                beginGame.setActive(false);
            }

            //UPDATES THE VISUAL STATUS OF THE OTHER PLAYERS IN THE LOBBY FOR YOUR GAME CLIENT
            if (ServerCalls.getPlayerStatusOnServer(0) && playerno!= 0) {
                g.drawImage(waitingForPlayers, 43, 130);
            } else if (!ServerCalls.getPlayerStatusOnServer(0) && playerno!= 0) {
                g.drawImage(playersConnected, 43, 130);
            }
            if (ServerCalls.getPlayerStatusOnServer(1) && playerno!= 1) {
                g.drawImage(waitingForPlayers, 43, 200);
            } else if (!ServerCalls.getPlayerStatusOnServer(1) && playerno!= 1) {
                g.drawImage(playersConnected, 43, 200);
            }
            if (ServerCalls.getPlayerStatusOnServer(2) && playerno!= 2) {
                g.drawImage(waitingForPlayers, 43, 270);
            } else if (!ServerCalls.getPlayerStatusOnServer(2) && playerno!= 2) {
                g.drawImage(playersConnected, 43, 270);
            }
            if (ServerCalls.getPlayerStatusOnServer(3) && playerno!= 3) {
                g.drawImage(waitingForPlayers, 43, 340);
            } else if (!ServerCalls.getPlayerStatusOnServer(3) && playerno!= 3) {
                g.drawImage(playersConnected, 43, 340);
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

    }

    /**
     * togglePlayerStatus sees if a player in the player class is ready and
     * toggles the image of the button (ready/not ready) and sets the playerReady
     * status to the opposite of what it was
     */
    public void togglePlayerStatus(GameContainer gc, Button button, List<Player> list, int indexNo) {

        Boolean toggle = list.get(indexNo).getPlayerReady();

        if (button.clickWithin(gc)) {
            if (!toggle) {
                list.get(indexNo).setPlayerReady(true);
                setPlayerStatusOnServer();

                button.setPicIndexNo(1);
            } else {
                list.get(indexNo).setPlayerReady(false);
                button.setPicIndexNo(0);
                setPlayerStatusOnServer();


            }
        }
    }


    @Override
    public int getID() {
        return 1;
    }


    //SHOULD BE MOVED TO SERVERCALL CLASS AND MADE STATIC
    /**
     *  Opens a connection to the server and sets the ready status of the player
     */

    public void setPlayerStatusOnServer() {
        try {
            Socket s = new Socket("localhost", 1234);
            PrintWriter setPlayerStatus = new PrintWriter(s.getOutputStream(), true);
            curPlayStatus = players.get(playerno).getPlayerReady();
            setPlayerStatus.println("SET_PLAYER_STATUS: " + curPlayStatus + " " + playerno);

            BufferedReader getPlayerStatus = new BufferedReader(new InputStreamReader(s.getInputStream()));
            playerStatusOut = getPlayerStatus.readLine();
            curPlayStatus = Boolean.valueOf(playerStatusOut);
            System.out.println("FROM SET PLAY FUNCTION : Player With ID " + playerno + " is now active" + curPlayStatus);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

    }
}
