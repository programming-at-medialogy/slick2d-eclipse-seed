package oose2015.components;

import org.newdawn.slick.Input;

/**
 * Created by @Kasper on 16/03/2015
 * <p/>
 * Description:
 * keeps data about keys and has booleans for each key that hold whether or not it has been pressed. the keys can be set in the constructor or using setKeys() method
 * <p/>
 * Usage:
 * Must, like all components, be added to an Entity
 */

public class PlayerComponent extends BodyComponent {

    private int leftKey, rightKey, upKey, downKey;
    public boolean leftDown, rightDown, upDown, downDown;

    /**
     * Set the keys for the player
     * @param left left key
     * @param right right key
     * @param up up key
     * @param down down key
     */
    public PlayerComponent(int left, int right, int up, int down){
        setKeys(left,right,up,down);
    }

    /**
     * Set the keys for the player
     * @param left left key
     * @param right right key
     * @param up up key
     * @param down down key
     */
    public void setKeys(int left, int right, int up, int down) {
        leftKey = left;
        rightKey = right;

        upKey = up;
        downKey = down;
    }

    public void updateKeys(Input input){
        leftDown = input.isKeyDown(leftKey);
        rightDown = input.isKeyDown(rightKey);

        upDown = input.isKeyDown(upKey);
        downDown = input.isKeyDown(downKey);
    }

}
