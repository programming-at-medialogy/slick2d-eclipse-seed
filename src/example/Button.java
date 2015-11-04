package example;

import org.newdawn.slick.*;

/**
 * Created by TMA on 04-11-2015.
 */

public class Button extends BasicGame {

    protected int mouseX, mouseY;
    private Image img, leave, medic;
    Image [] roleArr = new Image[4];
    int imgX;
    int imgY;

    int tempIndexNo;

    boolean playerReady;


    public Button(String title, int x, int y, int indexNo) {
        super(title);

        this.imgX = x;
        this.imgY = y;
        this.tempIndexNo = indexNo;
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        img = new Image("assets/button_players.png");
        leave = new Image("assets/button_players_leave.png");
        medic = new Image("assets/medic.png");

        for (int i = 0; i < roleArr.length; i++) {
            roleArr[i] = new Image("assets/"+i+".png");
        }

        playerReady = false;
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        org.newdawn.slick.Input input = gameContainer.getInput();
        mouseX = input.getMouseX();
        mouseY = input.getMouseY();
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {



        if (playerReady) {
            g.drawImage(leave, imgX,imgY);
            g.drawImage(roleArr[tempIndexNo],43,535);


            //g.drawImage(medic, 43, 535);
        }
        if (!playerReady) {
            g.drawImage(img, imgX, imgY);
        }

    }

    public boolean clickWithin(GameContainer gc) {
        org.newdawn.slick.Input input = gc.getInput();

        if (mouseX > imgX && mouseX < imgX + img.getWidth() && mouseY > imgY && mouseY < imgY + img.getHeight() && input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean getPlayerReady() {
        return playerReady;
    }

    public void setPlayerReady(boolean setPlayerReady) {
        this.playerReady = setPlayerReady;
    }
}
