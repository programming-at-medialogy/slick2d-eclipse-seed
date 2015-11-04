package example;

import org.newdawn.slick.*;

/**
 * Created by TMA on 04-11-2015.
 */

public class Button extends BasicGame {

    protected int mouseX,mouseY;
    private Image img, accept, decline;
    int imgX;
    int imgY;

    boolean playerReady;

    public Button(String title, int x, int y) {
        super(title);

        this.imgX = x;
        this.imgY = y;
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        img = new Image("assets/button_players.png");
        accept = new Image("assets/accept.png");
        decline = new Image("assets/decline.png");
        playerReady = false;
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        org.newdawn.slick.Input input = gameContainer.getInput();
        mouseX = input.getMouseX();
        mouseY = input.getMouseY();
        System.out.println();
        clickWithin(input);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {

        g.drawImage(img, imgX, imgY);


        if (playerReady) {
            g.drawImage(accept, imgX+img.getWidth()+20,imgY);
            update(gc,1);
        }
        if (!playerReady) {
            g.drawImage(decline, imgX+img.getWidth()+20,imgY);
        }

    }

    public void clickWithin(Input input) {
        if (mouseX > imgX && mouseX < imgX+img.getWidth() && mouseY > imgY && mouseY < imgY+img.getHeight() && input.isMousePressed(input.MOUSE_LEFT_BUTTON) && !playerReady) {
            System.out.println("IS WITHIN");
            playerReady = true;
        }
        }

}
