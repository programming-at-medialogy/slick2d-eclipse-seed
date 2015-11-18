package example;

import org.lwjgl.Sys;
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
    int cubeYellow = 0;
    //int cubesBlue
    int cubeBlue = 0;
    //int cubesRed
    int cubeRed = 0;
    //int cubesBlack
    int cubeBlack = 0;
    //bool hasResearchSt
    boolean hasResearchSt;
    //String neighborCities
    String[] neighborCities;

    int color;

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> MarianneKelstrup
    Button button;

    Image[] cityImage = new Image[48];
>>>>>>> MarianneKelstrup
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
    public void init(GameContainer gc) throws SlickException {

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> MarianneKelstrup
        if (color == 0) {
            button = new Button("cityButton", xPos, yPos, 19);
            //image = new Image("assets/cities/bluecities.png");
        } else if (color == 1) {
            //image = new Image("assets/cities/yellowcities.png");
            button = new Button("cityButton", xPos, yPos, 20);
        }
        else if (color == 2) {
            //image = new Image("assets/cities/blackcities.png");
            button = new Button("cityButton", xPos, yPos, 21);
        }
        else {
            //image = new Image("assets/cities/redcities.png");
            button = new Button("cityButton", xPos, yPos, 22);
        }

        button.init(gc);

>>>>>>> MarianneKelstrup
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        org.newdawn.slick.Input input = gc.getInput();
        System.out.println("X : " + input.getMouseX());
        System.out.println("Y : " + input.getMouseY());


    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
<<<<<<< HEAD

        //g.drawImage(image, xPos, yPos);
        button.render(gc,g);
        g.setColor(Color.white);
        g.drawString(cityName, xPos, yPos + 25);
=======

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
>>>>>>> MarianneKelstrup


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


