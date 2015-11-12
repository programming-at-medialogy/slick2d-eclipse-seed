package example;

import java.io.IOException;
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

        //this.addState(new InputNameScreen());
        //this.addState(new Lobby(gsc));
        this.addState(new GameBoard(gsc));

    }


    //The main
    public static void main(String[] args) {

        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new GameClient("Pandemic"));
            appgc.setShowFPS(false);
            appgc.setTargetFrameRate(10);
            appgc.setDisplayMode(1366, 768, true); //True for fullscreen goodness, kept false for testing
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
        }




        System.exit(0);


    }

}