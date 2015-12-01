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

    private int cardType;
    private int cardIdentifier;
    private Image cardImage;

    private String cityName;

    private int xPos;
    private int yPos;

    public Cards(String title, int cardType, String cityName, int cardIdentifier) {
        super(title);
        this.cardType = cardType;
        this.cityName = cityName;
        this.cardIdentifier = cardIdentifier;
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        cardImage = new Image("assets/cards/" + cityName + ".png");
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        if (cardIdentifier == 0)
            g.drawImage(cardImage, xPos, yPos);
    }


    /**
     * GETTER AND SETTER METHODS
     */

    public String getCityName() {
        return cityName;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getCardType() {
        return cardType;
    }
}
