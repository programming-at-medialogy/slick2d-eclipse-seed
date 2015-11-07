package example;

import org.newdawn.slick.*;

/**
 * Button class that holds all different clickables in the game
 * Created by TMA on 04-11-2015.
 */

public class Button extends BasicGame {

    /**
    *   mouseX and mouseY stores the values of the mouse on the screen
    *   images array stores graphical representation of different buttons, picIndexNo is used to locate a specific index in the array
    *        Index 0: Click to join button
    *        Index 1: Ready - Click to leave
    *        Index 2: Enter PlayState
    */

    private int imgX;
    private int imgY;
    private int picIndexNo;

    private boolean isActive;

    private Image[] images = new Image[4];


    public Button(String title, int x, int y, int picIndexNo) {
        super(title);
        this.imgX = x;
        this.imgY = y;
        isActive = false;

        //Encapsulation of the indexNo used to access specific images in the images[] array
        if (picIndexNo >= 0 && picIndexNo <= images.length - 1) {
        this.picIndexNo = picIndexNo;
        } else {
            this.picIndexNo = 0;
            System.out.println("Index number is higher than the length of the array it is used to access. Highest value it can be is " + (images.length - 1));
        }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image("assets/buttons/button" + i + ".png");
        }

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {

        g.drawImage(images[picIndexNo], imgX, imgY);

    }

    /**
     * Simple collision test. If the mouse is pressed within the confounds of the button the method returns true.
     * Otherwise returns false. Takes GameContainer argument to use SLICK2D's Input function.
     * @param gc the SlICK 2D game container
     * @return boolean
     */
    public boolean clickWithin(GameContainer gc) {
        org.newdawn.slick.Input input = gc.getInput();
        float mouseX = input.getMouseX();
        float mouseY = input.getMouseY();

        if (mouseX > imgX && mouseX < imgX + images[picIndexNo].getWidth() && mouseY > imgY && mouseY < imgY + images[picIndexNo].getHeight() && input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {
            return true;
        } else {
            return false;
        }

    }


    /**
     * GETTER and SETTER methods
     */

    public int getPicIndexNo() {
        return picIndexNo;
    }

    public void setPicIndexNo(int picIndexNo) {
        this.picIndexNo = picIndexNo;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean setActive) {
        this.isActive = setActive;
    }

    public int getImgY() { return imgY; }

    public void setImgY(int i) { this.imgY = i; }
}
