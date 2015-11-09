package example;

import org.newdawn.slick.*;

/**
 * Created by TMA on 05-11-2015.
 */

public class Roles extends BasicGame {

    /**
    *   roleImg stores the different graphical elements that display the players role, indexNo is used for access
    *            Index 0: Medic
    *            Index 1: Dispatcher
    *            Index 2: Quarantine Specialist
    *            Index 3: Contingency Planner
    */
    private Image[] roleImg = new Image[4];
    private int indexNo;

    public Roles(String title, int indexNo) {
        super(title);

        //Encapsulation of the indexNo so that it can never be outside of the bounds of the image array
        if (indexNo >= 0 && indexNo <= roleImg.length -1) {
            this.indexNo = indexNo;
        } else {
            this.indexNo = 0;
            System.out.println("Index number is higher than the length of the array it is used to access. Highest value it can be is " + (roleImg.length - 1));
        }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        for (int i = 0; i < roleImg.length; i++) {
            roleImg[i] = new Image("assets/roles/" + i + ".png");
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
