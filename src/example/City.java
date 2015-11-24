package example;

import org.lwjgl.Sys;
import org.newdawn.slick.*;

/**
 * Created by Marianne on 03-11-2015.
 */
public class City extends BasicGame {

    /**
     * Declaring the variables used in the City class. String cityName:
     * The name of ny given city on the game map stored as a String.
     * xPos and yPos are both integers that refer to the x and why coordinates respectively.
     * They are used to define the position of each city on the game map.
     * The integers cubeBlue, cubeYellow, cubeBlack and cubeRed are used:
     * To represent the number of cubes of each individual color present at any given city, starting with a value of 0.
     * The boolean hadResearchSt is used to return either true or false depending
     * on whether or not a research station is present at a given city.
     * The String array neighborCities defines the neighbor-cities of a any given city as a list of Strings
     * with the names of the neighbor cities.
     * The integer color is used to represent the colors: blue, yellow, back and red.
     * Button calls an instance of the button class, it is used to make the cities buttons that react to mouse click.
     */

    String cityName;

    int xPos;

    int yPos;

    int cubeYellow = 0;

    int cubeBlue = 0;

    int cubeRed = 0;

    int cubeBlack = 0;

    boolean hasResearchSt;

    String[] neighborCities;

    int color;

    Button button;

    Image[] cityImage = new Image[48];
    Image image;

    /**
     * The constructor for the city class, defining the parameters needed to create a city object
     * in the Gameboard class.
    **/
    public City(String title, String cityName, int xPos, int yPos, String[] neighborCities, int color) {
        super(title);
        this.cityName = cityName;
        this.xPos = xPos;
        this.yPos = yPos;
        this.neighborCities = neighborCities;
        this.color = color;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        /**
         *
        **/

        if (color == 0) {
            button = new Button("cityButton", xPos, yPos, 19);

        } else if (color == 1) {

            button = new Button("cityButton", xPos, yPos, 20);
        }
        else if (color == 2) {

            button = new Button("cityButton", xPos, yPos, 21);
        }
        else {

            button = new Button("cityButton", xPos, yPos, 22);
        }

        button.init(gc);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        org.newdawn.slick.Input input = gc.getInput();
        System.out.println("X : " + input.getMouseX());
        System.out.println("Y : " + input.getMouseY());


    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {

        //g.drawImage(image, xPos, yPos);
        button.render(gc,g);
        g.setColor(Color.white);
        g.drawString(cityName, xPos, yPos + 25);

        //g.drawImage(image, xPos, yPos);
        button.render(gc,g);
        g.setColor(Color.white);
        g.drawString(cityName, xPos, yPos + 25);


        if(button.clickWithin(gc)) {
            System.out.println("NU ER DET EN KNAP");
        }

        if (cubeBlue == 1){
            g.setColor(Color.blue);
            g.fillRect(xPos-20, yPos+15, 15,15);
        } else if(cubeBlue == 2){
            g.setColor(Color.blue);
            g.fillRect(xPos-20, yPos+15, 15, 15);
            g.setColor(Color.white);
            g.drawString("2", xPos-20, yPos+15);
        } else if(cubeBlue == 3){
            g.setColor(Color.blue);
            g.fillRect(xPos-20, yPos+15, 15, 15);
            g.setColor(Color.white);
            g.drawString("3", xPos-20, yPos+15);
        }

        if (cubeYellow == 1){
            g.setColor(Color.yellow);
            g.fillRect(xPos-20, yPos+15, 15, 15);
        } else if(cubeYellow == 2){
            g.setColor(Color.yellow);
            g.fillRect(xPos-20, yPos+15, 15, 15);
            g.setColor(Color.white);
            g.drawString("2", xPos-20, yPos+15);
        } else if(cubeYellow == 3){
            g.setColor(Color.yellow);
            g.fillRect(xPos-20, yPos+15, 15, 15);
            g.setColor(Color.white);
            g.drawString("3", xPos-20, yPos+15);
        }

        if (cubeBlack == 1){
            g.setColor(Color.black);
            g.fillRect(xPos-20, yPos+15, 15, 15);
        } else if(cubeBlack == 2){
            g.setColor(Color.black);
            g.fillRect(xPos-20, yPos+15, 15, 15);
            g.setColor(Color.white);
            g.drawString("2", xPos-20, yPos+15);
        } else if(cubeRed == 3){
            g.setColor(Color.black);
            g.fillRect(xPos-20, yPos+15, 15, 15);
            g.setColor(Color.white);
            g.drawString("3", xPos-20, yPos+15);
        }

        if (cubeRed == 1){
            g.setColor(Color.red);
            g.fillRect(xPos-20, yPos+15, 15, 15);
        } else if(cubeRed == 2){
            g.setColor(Color.red);
            g.fillRect(xPos-20, yPos+15, 15, 15);
            g.setColor(Color.white);
            g.drawString("2", xPos-20, yPos+15);
        } else if(cubeRed == 3){
            g.setColor(Color.red);
            g.fillRect(xPos-20, yPos+15, 15, 15);
            g.setColor(Color.white);
            g.drawString("3", xPos-20, yPos+15);
        }
    }


    //functions:
    //placeResearchSt ()
    public void placeResearchSt() {
        hasResearchSt = true;
    }

    //removeResearchSt ()
    public void removeResearchSt() {
        hasResearchSt = false;
    }

    //placeCubeY(color: string, amount: int)
    public void placeCube(String color, int amount) {
        if (color == "yellow") {
            this.cubeYellow += amount;
        } else if (color == "blue") {
            this.cubeBlue += amount;
        } else if (color == "red") {
            this.cubeRed += amount;
        } else if (color == "black") {
            this.cubeBlack += amount;
        }
    }

    //removeCube(color: string, amount: int)
    public void removeCube(String color, int amount) {
        if (color == "yellow") {
            cubeYellow -= amount;
        } else if (color == "blue") {
            cubeBlue -= amount;
        } else if (color == "red") {
            cubeRed -= amount;
        } else if (color == "black") {
            cubeBlack -= amount;
        }
    }

    public int getCubeYellow() {
        return cubeYellow;
    }

    public void setCubeYellow(int cubeYellow) {
        this.cubeYellow = cubeYellow;
    }

    public int getCubeBlue() {
        return cubeBlue;
    }

    public void setCubeBlue(int cubeBlue) {
        this.cubeBlue = cubeBlue;
    }

    public int getCubeRed() {
        return cubeRed;
    }

    public void setCubeRed(int cubeRed) {
        this.cubeRed = cubeRed;
    }

    public int getCubeBlack() {
        return cubeBlack;
    }

    public void setCubeBlack(int cubeBlack) {
        this.cubeBlack = cubeBlack;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public boolean isHasResearchSt() {
        return hasResearchSt;
    }

    public void setHasResearchSt(boolean hasResearchSt) {
        this.hasResearchSt = hasResearchSt;
    }

    public String[] getNeighborCities() {
        return neighborCities;
    }

    public void setNeighborCities(String[] neighborCities) {
        this.neighborCities = neighborCities;
    }
}


