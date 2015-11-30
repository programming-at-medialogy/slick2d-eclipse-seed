package example;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.List;

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

    private String cityName;
    private int xPos;
    private int yPos;
    private int cubeYellow = 0;
    private int cubeBlue = 0;
    private int cubeRed = 0;
    private int cubeBlack = 0;
    private boolean hasResearchSt;
    private boolean moveButtonSelected;
    private boolean removeCubeButtonSelected;
    private boolean placeResearchStationSelected;
    private String[] neighborCities;

    private int color;

    private Button button;

    private boolean isActive;
    private Image nonMovableCity;
    private Image cityOverview;
    private Image cityOverviewName;
    private Image researchStation;
    private Image warning;

    Cards playerCards;
    Cards infectionCards;

    /**
     * The constructor for the city class, defining the parameters needed to create a city object
     * instance in the Gameboard class.
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
         * If statements that relate color variables to instances of the button class that take the arguements:
         * a title, an x-coordinate, a y-coordinate and an image file represented by a number.
         * Button: (String title, int, int, int picIndexNo);
         **/

        playerCards = new Cards("playerCard", 0, cityName);
        infectionCards = new Cards("infectionCard", 1, cityName);
        playerCards.init(gc);
        infectionCards.init(gc);

        if (color == 0) {
            button = new Button("cityButton", xPos, yPos, 19);

        } else if (color == 1) {

            button = new Button("cityButton", xPos, yPos, 20);
        } else if (color == 2) {

            button = new Button("cityButton", xPos, yPos, 21);
        } else {

            button = new Button("cityButton", xPos, yPos, 22);
        }

        /**
         * Initializing images for nonMovableCity, cityOverview and cityOverviewName.
         */

        nonMovableCity = new Image("assets/cities/notmovablecities.png");
        cityOverview = new Image("assets/guielements/cityoverview.png");
        cityOverviewName = new Image("assets/cities/" + cityName + ".png");
        researchStation = new Image("assets/guielements/researchstation.png");
        warning = new Image("assets/guielements/warning.png"); //REMEMBER TO CHANGE


        button.init(gc);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {



        button.render(gc, g);
        if (cubeBlack == 3 || cubeBlue == 3 || cubeRed == 3 || cubeYellow == 3)
            g.drawImage(warning, xPos + 2, yPos - button.getImageWidth() / 2 - warning.getWidth() / 2);



        /**
         * This if-statement draws an image on the cities that are not electable to move to
         * and issues a warning if more than 3 cubes of the same color are drawn on the same city.
         */
        if (moveButtonSelected || removeCubeButtonSelected || placeResearchStationSelected) {
            if (!isActive) {
                g.drawImage(nonMovableCity, xPos, yPos);
            }
        }

        //playerCards.render(gc,g);

    }


    /**
     * placeCube(color: string, amount: int)
     * Adds cubes of a given color.
     */

    //removeCube(color: string, amount: int)
    public void removeCube(List<Player> players, int playerNo) {

        if (playerOnLocation(players, playerNo)) {
            //IF YELLOW IS GREATEST
            if (cubeYellow > cubeBlack && cubeYellow > cubeBlue && cubeYellow > cubeRed) {
                cubeYellow -= 1;
            }
            //IF BLUE IS GREATEST
            else if (cubeBlue > cubeBlack && cubeYellow < cubeBlue && cubeBlue > cubeRed) {
                cubeBlue -= 1;
            }
            //IF BLACK IS GREATEST
            else if (cubeYellow < cubeBlack && cubeBlack > cubeBlue && cubeBlack > cubeRed) {
                cubeBlack -= 1;
            }
            //IF RED IS GREATEST
            else if (cubeRed > cubeBlack && cubeRed > cubeBlue && cubeYellow < cubeRed) {
                cubeRed -= 1;
            } else {

                //ELSE IF ANY CUBE IS ABOVE 0
                if (cubeBlue > 0 || cubeBlack > 0 || cubeRed > 0 || cubeYellow > 0) {
                    //REMOVE THE CITY COLOR FIRST
                    if (color == 0 && cubeBlue > 0) {
                        cubeBlue -= 1;
                    } else if (color == 1 && cubeYellow > 0) {
                        cubeYellow -= 1;
                    } else if (color == 2 && cubeBlack > 0) {
                        cubeBlack -= 0;
                    } else if (color == 3 && cubeRed > 0) {
                        cubeRed -= 1;
                    } else {
                        //OR ELSE JUST REMOVE THEM FROM BLUE TO RED
                        if (cubeBlue > 0) {
                            cubeBlue -= 1;
                        } else if (cubeYellow > 0) {
                            cubeYellow -= 1;
                        } else if (cubeBlack > 0) {
                            cubeBlack -= 1;
                        } else if (cubeRed > 0) {
                            cubeRed -= 1;
                        }
                    }
                } else {
                    System.out.println("DU KAN IKKE FJERNE HVAD DER IKKE ER TOSSE! ");
                }
            }
        } else {
            System.out.println("DU ER IKKE ENGANG I DEN BY DIN TOSSE! ");
        }

    }

    public void placeResearchStation(List<Player> players, int playerno) {

        if (playerOnLocation(players, playerno) && !hasResearchSt) {
            hasResearchSt = true;
        }

    }

    private boolean playerOnLocation(List<Player> players, int playerNo) {
        if (cityName.equals(players.get(playerNo).getPlayerPosition().getCityName())) {
            return true;
        } else {
            return false;
        }
    }

    public void displayCityOverview(GameContainer gc, Graphics g) {


        //Variables for displaying the cubes and the amount of cubes on the city toggle.

        int cubeSize = 15;
        int firstRowX = xPos - 50;
        int firstRowY = yPos + 84;
        int secondRowX = xPos + 20;
        int secondRowY = yPos + 105;
        int stringPosX = 20;

        /**
         * If statement that creates a toggle menu, using the button class and its hoverOver function.
         * If the mouse hovers over a city, a toggle board is displayed. The toggle shows the city name,
         * the colored cubes and the amount of colored cubes.
         */

        if (button.hoverOver(gc)) {
            g.drawImage(cityOverview, ((xPos - (cityOverview.getWidth() / 2)) + (button.getImageWidth() / 2) - 4), yPos + button.getImageHeight() + 5);
            g.setColor(Color.white);
            g.drawImage(cityOverviewName, firstRowX, yPos + 65);

            /**
             * The following if-statements draw the various cubes and a string that displays the amount 1, 2 or 3, for each
             * individual color.
             */

            if (cubeBlue == 0) {
                g.setColor(Color.blue);
                g.fillRect(firstRowX, firstRowY, cubeSize, cubeSize);
            } else if (cubeBlue == 1) {
                g.setColor(Color.blue);
                g.fillRect(firstRowX, firstRowY, cubeSize, cubeSize);
                g.setColor(Color.white);
                g.drawString("x 1", firstRowX + stringPosX, firstRowY);
            } else if (cubeBlue == 2) {
                g.setColor(Color.blue);
                g.fillRect(firstRowX, firstRowY, cubeSize, cubeSize);
                g.setColor(Color.white);
                g.drawString("x 2", firstRowX + stringPosX, firstRowY);
            } else if (cubeBlue == 3) {
                g.setColor(Color.blue);
                g.fillRect(firstRowX, firstRowY, cubeSize, cubeSize);
                g.setColor(Color.white);
                g.drawString("x 3", firstRowX + stringPosX, firstRowY);
            }

            if (cubeYellow == 0) {
                g.setColor(Color.yellow);
                g.fillRect(secondRowX, firstRowY, cubeSize, cubeSize);
            } else if (cubeYellow == 1) {
                g.setColor(Color.yellow);
                g.fillRect(secondRowX, firstRowY, cubeSize, cubeSize);
                g.setColor(Color.white);
                g.drawString("x 1", secondRowX + stringPosX, firstRowY);
            } else if (cubeYellow == 2) {
                g.setColor(Color.yellow);
                g.fillRect(secondRowX, firstRowY, cubeSize, cubeSize);
                g.setColor(Color.white);
                g.drawString("x 2", secondRowX + stringPosX, firstRowY);
            } else if (cubeYellow == 3) {
                g.setColor(Color.yellow);
                g.fillRect(secondRowX, firstRowY, cubeSize, cubeSize);
                g.setColor(Color.white);
                g.drawString("x 3", secondRowX + stringPosX, firstRowY);
            }

            if (cubeBlack == 0) {
                g.setColor(Color.black);
                g.fillRect(firstRowX, secondRowY, cubeSize, cubeSize);
            } else if (cubeBlack == 1) {
                g.setColor(Color.black);
                g.fillRect(firstRowX, secondRowY, cubeSize, cubeSize);
                g.setColor(Color.white);
                g.drawString("x 1", firstRowX + stringPosX, secondRowY);
            } else if (cubeBlack == 2) {
                g.setColor(Color.black);
                g.fillRect(firstRowX, secondRowY, cubeSize, cubeSize);
                g.setColor(Color.white);
                g.drawString("x 2", firstRowX + stringPosX, secondRowY);
            } else if (cubeBlack == 3) {
                g.setColor(Color.black);
                g.fillRect(firstRowX, secondRowY, cubeSize, cubeSize);
                g.setColor(Color.white);
                g.drawString("x 3", firstRowX + stringPosX, secondRowY);
            }

            if (cubeRed == 0) {
                g.setColor(Color.red);
                g.fillRect(secondRowX, secondRowY, cubeSize, cubeSize);
            } else if (cubeRed == 1) {
                g.setColor(Color.red);
                g.fillRect(secondRowX, secondRowY, cubeSize, cubeSize);
                g.setColor(Color.white);
                g.drawString("x 1", secondRowX + stringPosX, secondRowY);
            } else if (cubeRed == 2) {
                g.setColor(Color.red);
                g.fillRect(secondRowX, secondRowY, cubeSize, cubeSize);
                g.setColor(Color.white);
                g.drawString("x 2", secondRowX + stringPosX, secondRowY);
            } else if (cubeRed == 3) {
                g.setColor(Color.red);
                g.fillRect(secondRowX, secondRowY, cubeSize, cubeSize);
                g.setColor(Color.white);
                g.drawString("x 3", secondRowX + stringPosX, secondRowY);
            }
        }

    }


    public void displayResearchCenter(Graphics g) {
        if (hasResearchSt) {
            g.drawImage(researchStation, xPos + button.getImageWidth() / 2, yPos + button.getImageHeight() / 2);
        }

    }

    /**
     * Getter and setter methods.
     */

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

    //SHOULD NOT BE USED OTHER THAN SETTING THE INITIAL CITY TO TRUE. INSTEAD USE PLACERESEARCHSTATION
    public void setHasResearchSt(boolean hasResearchSt) {
        this.hasResearchSt = hasResearchSt;
    }

    public String[] getNeighborCities() {
        return neighborCities;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public void activeAction(boolean value) {
        this.isActive = value;
    }

    public boolean getActive() {
        return isActive;
    }

    public boolean isMoveButtonSelected() {
        return moveButtonSelected;
    }

    public void setMoveButtonSelected(boolean moveButtonSelected) {
        this.moveButtonSelected = moveButtonSelected;
    }

    public void setRemoveCubeButtonSelected(boolean removeCubeButtonSelected) {
        this.removeCubeButtonSelected = removeCubeButtonSelected;
    }

    public void setPlaceResearchStationSelected(boolean placeResearchStation) {
        this.placeResearchStationSelected = placeResearchStation;
    }

}


