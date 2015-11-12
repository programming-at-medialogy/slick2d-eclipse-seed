package example;

import org.newdawn.slick.*;

/**
 * Created by Marianne on 03-11-2015.
 */
public class City extends BasicGame {

    //String city-name
    String cityName;
    //int xpos
    int xPos;
    //int ypos
    int yPos;
    //int cubesYellow
    int cubeYellow;
    //int cubesBlue
    int cubeBlue;
    //int cubesRed
    int cubeRed;
    //int cubesBlack
    int cubeBlack;
    //bool hasResearchSt
    boolean hasResearchSt;
    //String neighborCities
    String[] neighborCities;

    int color;

    Image[] cityImage = new Image[48];
    Image image;

    public City(String title, String cityName, int xPos, int yPos, String[] neighborCities, int color) {
        super(title);
        this.cityName = cityName;
        this.xPos = xPos;
        this.yPos = yPos;
        this.neighborCities = neighborCities;
        this.color = color;
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        if (color == 0)
            image = new Image("assets/cities/bluecities.png");
        else if (color == 1)
            image = new Image("assets/cities/yellowcities.png");
        else if (color == 2)
            image = new Image("assets/cities/blackcities.png");
        else
            image = new Image("assets/cities/redcities.png");


/*
        for (int i = 0; i < 12; i++) {
            cityImage[i] = new Image("assets/cities/bluecities.png");
        }
        for (int j = 12; j < 24; j++){
            cityImage[j] = new Image("assets/cities/yellowcities.png");
        }
        for (int i = 24; i < 36; i++){
            cityImage[i] = new Image("assets/cities/blackcities.png");
        }
        for (int i = 36; i < 48; i++){
            cityImage[i] = new Image("assets/cities/redcities.png");
        }

        /*
        for (int i = 0; i < cityImage.length; i++) {
            cityImage[i] = new Image("assets/cities/bluecities.png");
        }*/

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        org.newdawn.slick.Input input = gameContainer.getInput();
        System.out.println("X : " + input.getMouseX());
        System.out.println("Y : " + input.getMouseY());

    }

    @Override
    public void render(GameContainer gameContainer, Graphics g) throws SlickException {

        g.drawImage(image, xPos, yPos);
    }

    public void mousePressed() {

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
            cubeYellow += amount;
        } else if (color == "blue") {
            cubeBlue += amount;
        } else if (color == "red") {
            cubeRed += amount;
        } else if (color == "black") {
            cubeBlack += amount;
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
}
