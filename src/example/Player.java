package example;

import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The player class
 * Created by TMA on 03-11-2015.
 */
public class Player extends BasicGame {

    private Image[] playerImage = new Image[7];
    private City currentLocation = new City("pandemic", "atlanta", 230, 300, new String[]{"miami", "washington", "chicago"}, 0);
    private Image curLocation;
    private Image othersLocation;
    private Image curCityImg;

    private List<Cards> hand = new ArrayList<Cards>(0);
    private Cards[] playerCards;

    private int xPos;
    private int yPos;

    private int roleP1, roleP2, roleP3, roleP4;

    private Roles role;

    private int playerID;

    private boolean playerReady;

    private boolean moveSelected;


    public Player(String gametitle) {
        super(gametitle);
        playerReady = false;
        currentLocation.setHasResearchSt(true);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {

        playerCards = new Cards[48];

        curLocation = new Image ("assets/guielements/currentlocation.png");
        othersLocation = new Image ("assets/guielements/otherslocation.png");
        curCityImg = new Image ("assets/cities/currentlyincity.png");

        for (int i = 0; i < playerImage.length; i++) {
            playerImage[i] = new Image("assets/guielements/players/" + i + ".png");
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

        if (playerID == 0)
         g.drawImage(playerImage[roleP1], xPos-10, yPos+135);
        else if (playerID == 1)
        g.drawImage(playerImage[roleP2], xPos+35, yPos+135);
        else if (playerID == 2)
        g.drawImage(playerImage[roleP3], xPos-10, yPos+180);
        else
        g.drawImage(playerImage[roleP4], xPos+35, yPos+180);
System.out.println(playerID);
    }

    public boolean checkMovability(City city) {
        if ((getNeighborCityAsList().contains(city.getCityName())) || (currentLocation.isHasResearchSt() && city.isHasResearchSt())) {
            return true;
        }
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

        //IF YOU ARE NEXT TO A CITY OR ON A CITY WITH A RESEARCH STATION
        if (checkMovability(city)) {
            this.currentLocation = city;
        }
        //ONLY IN THE INSTANCE WHERE YOU USE A CARD FOR TRAVEL!
        else if (convertHandToStringList().contains(city.getCityName()) && !checkMovability(city)) {
            this.currentLocation = city;
            hand.remove(city.getPlayerCards());

        }
    }

    public City getPlayerPosition() {
        return currentLocation;
    }

    public List<String> getNeighborCityAsList() {
        return Arrays.asList(currentLocation.getNeighborCities());
    }

    public Roles getRole() {
        return role;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public void setRoleP1(int roleP1) {
        this.roleP1 = roleP1;
    }

    public void setRoleP2(int roleP2) {
        this.roleP2 = roleP2;
    }

    public void setRoleP3(int roleP3) {
        this.roleP3 = roleP3;
    }

    public void setRoleP4(int roleP4) {
        this.roleP4 = roleP4;
    }

    public void displayCurrentLocation(Graphics g) {
        g.drawImage(curLocation,xPos+7,yPos-30);
        if (moveSelected) {
            g.drawImage(curCityImg, xPos, yPos);
        }
    }

    public void displayOthersLocation (Graphics g) {
        g.drawImage(othersLocation,xPos+7,yPos-30);
    }

    public void setMoveSelected(boolean value) {
        this.moveSelected = value;
    }

    public void addCardsToHand (String card) {

        for (int  i = 0; i < playerCards.length; i++) {
            if (card.equals(playerCards[i].getCityName())) {
                hand.add(hand.size(), playerCards[i]);
            }
        }
    }

    public void displayHand(GameContainer gc, Graphics g, int playerHandNo) throws SlickException {

        for (int i = 0; i < hand.size(); i++) {
            hand.get(i).setxPos(25 + playerHandNo*230);
            hand.get(i).setyPos(76+30*i);
            hand.get(i).render(gc, g);
        }
    }

    public void setPlayerCards(City [] cities) {

        for (int i = 0; i < playerCards.length; i++) {
            playerCards[i] = cities[i].getPlayerCards();
        }
    }

    public List<String> convertHandToStringList () {

        List<String> handAsString = new ArrayList<String>();

        for (int i = 0; i < hand.size(); i++) {
            handAsString.add(hand.get(i).getCityName());
        }

        return handAsString;
    }
}
