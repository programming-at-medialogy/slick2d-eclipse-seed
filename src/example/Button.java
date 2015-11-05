package example;

import org.newdawn.slick.*;

/**
 * Created by TMA on 04-11-2015.
 */

public class Button extends BasicGame {

    protected int mouseX, mouseY;
    Image[] images;
    int imgX;
    int imgY;

    int picIndexNo;


    boolean playerReady;


    public Button(String title, int x, int y, int picIndexNo) {
        super(title);
        this.imgX = x;
        this.imgY = y;
        this.picIndexNo = picIndexNo;
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        images = new Image[2];
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image("assets/button" +i+ ".png");
        }

        playerReady = false;
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {

        g.drawImage(images[picIndexNo], imgX,imgY);

    }

    public boolean clickWithin(GameContainer gc) {
        org.newdawn.slick.Input input = gc.getInput();
        mouseX = input.getMouseX();
        mouseY = input.getMouseY();

        if (mouseX > imgX && mouseX < imgX + images[picIndexNo].getWidth() && mouseY > imgY && mouseY < imgY + images[picIndexNo].getHeight() && input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {
            return true;
        } else {
            return false;
        }

    }

    public int getPicIndexNo() {
        return picIndexNo;
    }

    public void setPicIndexNo(int picIndexNo) {
        this.picIndexNo = picIndexNo;
    }
}
