package example;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

/**
 * Created by TMA on 03-11-2015.
 */
public class Cards extends BasicGame {

    int cardType;
    Image[] cardImage = new Image[48];

    String cityName;

    public Cards(String title, int cardType, String cityName) {
        super(title);
        this.cardType = cardType;
        this.cityName = cityName;
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        for (int i = 0; i < cardImage.length; i++) {
            cardImage[i] = new Image ("assets/cards/"+cityName+".png");
        }

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        if (cardType == 0)
    g.drawImage(cardImage[1], 200,200);
    }
}
