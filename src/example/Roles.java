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
    private int gameState;

    public Roles(String title, int gameState) {
        super(title);
        /*
        //Encapsulation of the indexNo so that it can never be outside of the bounds of the image array
        if (indexNo >= 0 && indexNo <= roleImg.length -1) {
        } else {
            this.indexNo = 0;
            System.out.println("Index number is higher than the length of the array it is used to access. Highest value it can be is " + (roleImg.length - 1));
        }
        this.gameState = gameState;*/
    }


    //SLICK2D METHODS
    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        if (gameState == 0) {
            for (int i = 0; i < roleImg.length; i++) {
                roleImg[i] = new Image("assets/roles/" + i + ".png");
            }
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

