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

    public City(String title, String cityName, int xPos, int yPos, String[] neighborCities ) {
        super(title);
        this.cityName = cityName;
        this.xPos = xPos;
        this.yPos = yPos;
        this.neighborCities = neighborCities;
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {


    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        org.newdawn.slick.Input input = gameContainer.getInput();
        System.out.println("X : " + input.getMouseX());
        System.out.println("Y : " + input.getMouseY());

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

    }

    public void mousePressed() {

    }

    //functions:
    //placeResearchSt ()
    public void placeResearchSt (){
        hasResearchSt = true;
    }
    //removeResearchSt ()
    public void removeResearchSt (){
        hasResearchSt = false;
    }
    //placeCubeY(color: string, amount: int)
    public void placeCube (String color, int amount){
        if(color == "yellow"){
            cubeYellow+=amount;
        } else if(color == "blue"){
            cubeBlue+=amount;
        } else if(color == "red"){
            cubeRed+=amount;
        } else if (color == "black"){
            cubeBlack+=amount;
        }
    }

    //removeCube(color: string, amount: int)
    public void removeCube (String color, int amount){
        if(color == "yellow"){
            cubeYellow-=amount;
        } else if(color== "blue"){
            cubeBlue-=amount;
        } else if(color == "red"){
            cubeRed-=amount;
        } else if (color == "black"){
            cubeBlack-=amount;
        }
    }
}
