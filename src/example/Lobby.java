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

    public Lobby() {
    }

    Player p1, p2, p3, p4;
    Roles role;
    Button readyToggle;
    Button beginGame;
    List<Player> players;
    String inputStream, playerStatusOut;
    int playerno = 0;
    boolean curPlayStatus;

    Image waitingForPlayers, playersConnected;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        lobbyBackground = new Image("assets/backgrounds/lobby.png");
        readyToggle = new Button("READY", 43, 130, 0);
        beginGame = new Button("BEGIN_GAME", 763, 642, 2);

        waitingForPlayers = new Image("assets/buttons/button4.png");
        playersConnected = new Image("assets/buttons/button5.png");


        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
        p3 = new Player("Player 3");
        p4 = new Player("Player 4");

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
            Socket s = new Socket("localhost", 1234);

            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            out.println("ASSIGN_PLAYER_ID");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            inputStream = in.readLine();
            playerno = Integer.valueOf(inputStream);
            System.out.println(inputStream);

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

        readyToggle.setImgY(playerno*70+130);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {


        try {

            if (players.get(playerno).getPlayerReady()) {
                togglePlayerStatus(gc, readyToggle, players, playerno);

            } else {
                togglePlayerStatus(gc, readyToggle, players, playerno);
            }
            if (beginGame.clickWithin(gc) && beginGame.isActive()) {
                System.out.println("PLAYER WITH ID " + playerno + " IS NOW ACTIVE: " + getPlayerStatusOnServer(playerno) + ". FROM A BOOL METHOD");
                sbg.enterState(2);
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(lobbyBackground, 0, 0);


            if (players.get(playerno).getPlayerReady()) {
                readyToggle.render(gc, g);
                role.render(gc, g);
                beginGame.render(gc, g);
                beginGame.setActive(true);
            } else {
                readyToggle.render(gc,g);
                beginGame.setActive(false);
            }

        try {
            if (getPlayerStatusOnServer(0) && playerno!= 0) {
                g.drawImage(waitingForPlayers, 43, 130);
            } else if (!getPlayerStatusOnServer(0) && playerno!= 0) {
                g.drawImage(playersConnected, 43, 130);
            }
            if (getPlayerStatusOnServer(1) && playerno!= 1) {
                g.drawImage(waitingForPlayers, 43, 200);
            } else if (!getPlayerStatusOnServer(1) && playerno!= 1) {
                g.drawImage(playersConnected, 43, 200);
            }
            if (getPlayerStatusOnServer(2) && playerno!= 2) {
                g.drawImage(waitingForPlayers, 43, 270);
            } else if (!getPlayerStatusOnServer(2) && playerno!= 2) {
                g.drawImage(playersConnected, 43, 270);
            }
            if (getPlayerStatusOnServer(3) && playerno!= 3) {
                g.drawImage(waitingForPlayers, 43, 340);
            } else if (!getPlayerStatusOnServer(3) && playerno!= 3) {
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

    public int returnPlayerNo() {
        return playerno;
    }

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


    /**
     *
     * @param index sets the GET function to the specific player ID
     * @return the current status of the player (if he is ready or not)
     * @throws IOException
     */
    public boolean getPlayerStatusOnServer(int index) throws IOException {

        boolean gO;
        String oPSO;

        Socket gP = new Socket("localhost", 1234);
        PrintWriter gPs = new PrintWriter(gP.getOutputStream(), true);
        gPs.println("GET_PLAYER_STATUS: "+index);

        BufferedReader gettingThePlayerStatus = new BufferedReader(new InputStreamReader(gP.getInputStream()));
        oPSO = gettingThePlayerStatus.readLine();
        gO = Boolean.valueOf(oPSO);
        //System.out.println("FROM GET FUNCTION : Player With ID " + index + " is active " + gO);
        return gO;
    }
}
