package oose2015;
import java.util.logging.Level;
import java.util.logging.Logger;

import oose2015.states.GameplayState;
import oose2015.states.MainMenuState;
import org.newdawn.slick.*;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class SimpleSlickGame extends StateBasedGame {
	public SimpleSlickGame(String gamename){
		super(gamename);
	}


    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        //creates gamestates
        GameState mainMenu = new MainMenuState();
        GameState gameRunning = new GameplayState();

        //add the states
        addState(mainMenu);
        addState(gameRunning);
    }

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Rouge Gauntlet"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}