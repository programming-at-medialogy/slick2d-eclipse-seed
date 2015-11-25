package example;

import org.newdawn.slick.*;

import java.util.Arrays;
import java.util.List;

/**
 * The player class
 * Created by TMA on 03-11-2015.
 */
public class Player extends BasicGame {

    private Image[] playerImage = new Image[7];
    private City currentLocation = new City("pandemic", "atlanta", 230, 300, new String[]{"miami", "washington", "chicago"}, 0);

    private int xPos;
    private int yPos;
    private int relativeXpos;
    private int relativeYpos;

    private int playerRoleNo;

    private int playerID;

    String pos;
    boolean turn;
    int actionsLeft;
    private boolean playerReady;


    public Player(String gametitle) {
        super(gametitle);
        playerReady = false;

    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        for (int i = 0; i < playerImage.length; i++) {
            playerImage[i] = new Image("assets/guielements/players/" + i + ".png"); //PLACEHOLDER IMAGE
        }
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        xPos = currentLocation.getxPos();
        yPos = currentLocation.getyPos();
        if (playerID == 0 || playerID == 2)
            relativeXpos = xPos - 50;
        if (playerID == 1 || playerID == 3)
            relativeXpos = xPos + 20;
        if (playerID == 0 || playerID == 1)
            relativeYpos = yPos + 130;
        if (playerID == 2 || playerID == 3)
            relativeYpos = yPos + 180;




    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {

            g.drawImage(playerImage[playerRoleNo], relativeXpos, relativeYpos);
    }

    public boolean checkMovability(City city) {
        if (getNeighborCityAsList().contains(city.getCityName()))
            return true;
        else
            return false;
    }

    /**
     * GETTER and SETTER Methods
     */

    public boolean getPlayerReady() {
        return playerReady;
    }

    public void setPlayerReady(boolean setPlayerReady) {
        this.playerReady = setPlayerReady;
    }

    public void setPlayerPosition(City city) {

        if (checkMovability(city)) {
            this.currentLocation = city;
            System.out.println(currentLocation.getCityName());
        } else {
            System.out.println("NO");
        }
    }

    public City getPlayerPosition() {
        return currentLocation;
    }

    public void showHand(Graphics g) {
    }

    public List<String> getNeighborCityAsList() {
        return Arrays.asList(currentLocation.getNeighborCities());
    }

    public void setPlayerRoleNo(int index) {
        this.playerRoleNo = index;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int index) {
        this.playerID = index;
    }


}
