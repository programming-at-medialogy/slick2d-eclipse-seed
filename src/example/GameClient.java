package example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


/**
*   Uses SLICK2D StateBasedGame in order to easily jump between states
*/
public class GameClient extends StateBasedGame
{




    //Constructor
    public GameClient(String gamename) {
        super(gamename);
    }

    /**
    *   SLICK2D StateBasedGame initializer for the different states of the client.
    *   Index list:
    *            Index 0: InputNameScreen
    *            Index 1: Lobby
     *           Index 2: PlayState
    */
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {

        GameStateCommons gsc = new GameStateCommons();

        //CREATE THE PLAYERS
        List<Player> players = new ArrayList<Player>(4);
        players.add(new Player("1"));
        players.add(new Player("2"));
        players.add(new Player("3"));
        players.add(new Player("4"));

        //this.addState(new InputNameScreen(gsc));
        //this.addState(new Lobby(gsc, players));
        this.addState(new GameBoard(gsc, players));

    }


    //The main
    public static void main(String[] args) {

        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new GameClient("Pandemic"));
            appgc.setShowFPS(false);
            appgc.setTargetFrameRate(10);
            appgc.setDisplayMode(1366, 768, false); //True for fullscreen goodness, kept false for testing
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
        }




        System.exit(0);


    }

}