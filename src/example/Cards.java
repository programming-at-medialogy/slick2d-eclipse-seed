package example;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;

import java.awt.*;

/**
 * Created by TMA on 03-11-2015.
 */
public class Cards extends BasicGame {

    Cards card;
    int cardType;
    Image[] cardImage = new Image[48];
    Image image;

    public Cards(String title, int cardType) {
        super(title);
        this.cardType = cardType;

    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

    }
}
