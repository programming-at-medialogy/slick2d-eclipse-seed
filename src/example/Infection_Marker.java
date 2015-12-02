package example;

import org.newdawn.slick.*;

/**
 * Created by Marianne on 03-11-2015.
 */
public class Infection_Marker extends BasicGame {

    private int count;
    private int xPos = 795;
    private int yPos = 670;
    Image ratemarker;

    public Infection_Marker(String title, int count){
        super(title);
        this.count = count;
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        ratemarker = new Image("assets/guielements/ratemarker.png");
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

       xPos=795+count*40;

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        graphics.drawImage(ratemarker,xPos,yPos);
    }
}
