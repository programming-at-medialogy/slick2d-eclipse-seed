package example;

import org.newdawn.slick.*;

/**
 * Created by TMA on 05-11-2015.
 */
public class Roles extends BasicGame {

    Image[] roleImg;
    int indexNo;

    public Roles(String title, int indexNo) {
        super(title);
        this.indexNo = indexNo;
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        roleImg = new Image[4]; //SHOULD BE 7
        for (int i = 0; i < roleImg.length; i++) {
            roleImg[i] = new Image("assets/" + i + ".png");
        }
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics g) throws SlickException {
            g.drawImage(roleImg[indexNo], 43, 535);

    }
}
