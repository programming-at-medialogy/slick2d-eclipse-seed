package example;

import org.newdawn.slick.*;

/**
 * The roles class
 * Created by TMA on 05-11-2015.
 */

public class Roles extends BasicGame {

    /**
    *   roleImg stores the different graphical elements that display the players role, indexNo is used for access
    *            Index 0: Medic
    *            Index 1: Dispatcher
    *            Index 2: Quarantine Specialist
    *            Index 3: Contingency Planner
    *            Index 4: Researcher
    *            Index 5: Scientist
    *           Index 6: OperationsExpert
    */
    private Image[] roleImg = new Image[7];
    private int indexNo = 0;

    public Roles(String title, int gameState) {
        super(title);
    }


    //SLICK2D METHODS
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

    public void setIndexNo(int index) { this.indexNo = index; }

    public int getRoleNumber () {
        return indexNo;
    }


}

