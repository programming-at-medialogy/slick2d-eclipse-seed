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
    private int relativeXOffset;
    private int relativeYOffset;

    private Roles role;


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
    public void init(GameContainer gc) throws SlickException {

        for (int i = 0; i < playerImage.length; i++) {
            playerImage[i] = new Image("assets/guielements/players/" + i + ".png"); //PLACEHOLDER IMAGE
        }

        role = new Roles("Role", 0);
        role.init(gc);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

        role.update(gc,i);

        xPos = currentLocation.getxPos();
        yPos = currentLocation.getyPos();





    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
         g.drawImage(playerImage[0], relativeXOffset, relativeYOffset);
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

    public int getPlayerRoleNo() { return playerRoleNo; }


    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int index) {
        this.playerID = index;
    }

    public void setRelativeXOffset(int offset) {
        this.relativeXOffset = xPos+offset;
    }

    public void setRelativeYOffset(int offset) {
        this.relativeYOffset = yPos+offset;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
