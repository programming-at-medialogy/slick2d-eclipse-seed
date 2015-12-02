package example;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.List;

/**
 * Created by Marianne on 05-11-2015.
 */
public class GameBoard extends BasicGameState {

    /**
     * Declaring variables.
     */
    private GameStateCommons gsc;
    private ServerCalls serverCalls;
    private City[] cities;
    private Image gameBoard;
    private Image actionNotAvailable;
    private Button player1Hand, player2Hand, player3Hand, player4Hand;
    private boolean showHand1, showHand2, showHand3, showHand4;
    private Infection_Marker infectionMarker;
    private Outbreak_Marker outbreakMarker;
    private ActionMenu actionMenu;

    private Button yes;
    private Button no;
    private Image confirmMenu;

    private boolean blueCure, yellowCure, blackCure, redCure;
    private Image blueCureImg, yellowCureImg, blackCureImg, redCureImg;

    private int playerNo;
    private int roleNo;

    private boolean outOfTurns;
    private boolean endTurn;

    private List<Player> players;

    int noOfResearchStationsLeft = 6;


    public GameBoard(GameStateCommons gsc, ServerCalls serverCalls, List<Player> players) {

        this.gsc = gsc;
        this.serverCalls = serverCalls;
        this.players = players;
    }


    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        gameBoard = new Image("assets/backgrounds/bg.png");
        infectionMarker = new Infection_Marker("infectionmarker", 0);
        infectionMarker.init(gc);
        outbreakMarker = new Outbreak_Marker("outbreakmarker", 0);
        outbreakMarker.init(gc);
        actionMenu = new ActionMenu("actionMenu");
        actionMenu.init(gc);
        actionNotAvailable = new Image("assets/guielements/notavailable.png");

        yes = new Button("yes", 455, 374, 30);
        no = new Button("no", 705, 374, 29);

        yes.init(gc);
        no.init(gc);
        confirmMenu = new Image("assets/guielements/confirmmenu.png");

        blueCureImg = new Image("assets/guielements/bluecure.png");
        yellowCureImg = new Image("assets/guielements/yellowcure.png");
        blackCureImg = new Image("assets/guielements/blackcure.png");
        redCureImg = new Image("assets/guielements/redcure.png");

        cities = new City[48];
        cities[0] = new City("pandemic", "atlanta", 230, 300, new String[]{"miami", "washington", "chicago"}, 0);
        cities[1] = new City("pandemic", "chicago", 189, 243, new String[]{"atlanta", "sanfrancisco", "montreal", "mexicocity"}, 0);
        cities[2] = new City("pandemic", "sanfrancisco", 93, 272, new String[]{"losangeles", "chicago", "tokyo", "manila"}, 0);
        cities[3] = new City("pandemic", "montreal", 280, 205, new String[]{"chicago", "washington", "newyork"}, 0);
        cities[4] = new City("pandemic", "newyork", 312, 252, new String[]{"montreal", "washington", "madrid", "london"}, 0);
        cities[5] = new City("pandemic", "washington", 291, 292, new String[]{"newyork", "miami", "atlanta", "montreal"}, 0);
        cities[6] = new City("pandemic", "london", 585, 190, new String[]{"newyork", "madrid", "paris", "essen"}, 0);
        cities[7] = new City("pandemic", "madrid", 550, 260, new String[]{"newyork", "london", "paris", "saopaulo", "algiers"}, 0);
        cities[8] = new City("pandemic", "paris", 609, 237, new String[]{"london", "madrid", "algiers", "milan", "essen"}, 0);
        cities[9] = new City("pandemic", "essen", 655, 180, new String[]{"london", "paris", "milan", "stpetersburg"}, 0);
        cities[10] = new City("pandemic", "milan", 679, 236, new String[]{"istanbul", "paris", "essen"}, 0);
        cities[11] = new City("pandemic", "stpetersburg", 738, 170, new String[]{"essen", "istanbul", "moscow"}, 0);
        cities[12] = new City("pandemic", "losangeles", 143, 332, new String[]{"sanfrancisco", "sydney", "mexicocity", "chicago"}, 1);
        cities[13] = new City("pandemic", "mexicocity", 196, 375, new String[]{"losangeles", "chicago", "miami", "bogota", "lima"}, 1);
        cities[14] = new City("pandemic", "miami", 299, 351, new String[]{"mexicocity", "atlanta", "washington", "bogota"}, 1);
        cities[15] = new City("pandemic", "bogota", 289, 423, new String[]{"mexicocity", "miami", "lima", "saopaulo", "buenosaires"}, 1);
        cities[16] = new City("pandemic", "lima", 299, 496, new String[]{"mexicocity", "bogota", "santiago"}, 1);
        cities[17] = new City("pandemic", "santiago", 311, 602, new String[]{"lima"}, 1);
        cities[18] = new City("pandemic", "buenosaires", 371, 590, new String[]{"bogota", "saopaulo"}, 1);
        cities[19] = new City("pandemic", "saopaulo", 416, 524, new String[]{"buenosaires", "bogota", "lagos", "madrid"}, 1);
        cities[20] = new City("pandemic", "lagos", 591, 386, new String[]{"saopaulo", "kinshasa", "khartoum"}, 1);
        cities[21] = new City("pandemic", "kinshasa", 667, 451, new String[]{"lagos", "johannesburg", "khartoum"}, 1);
        cities[22] = new City("pandemic", "johannesburg", 729, 540, new String[]{"kinshasa", "khartoum"}, 1);
        cities[23] = new City("pandemic", "khartoum", 730, 363, new String[]{"lagos", "kinshasa", "johannesburg", "cairo"}, 1);
        cities[24] = new City("pandemic", "algiers", 616, 292, new String[]{"istanbul", "cairo", "madrid", "paris"}, 2);
        cities[25] = new City("pandemic", "istanbul", 729, 235, new String[]{"algiers", "cairo", "milan", "baghdad", "moscow", "stpetersburg"}, 2);
        cities[26] = new City("pandemic", "cairo", 714, 313, new String[]{"algiers", "riyadh", "baghdad", "istanbul", "khartoum"}, 2);
        cities[27] = new City("pandemic", "moscow", 782, 215, new String[]{"stpetersburg", "istanbul", "tehran"}, 2);
        cities[28] = new City("pandemic", "tehran", 845, 244, new String[]{"moscow", "baghdad", "karachi", "delhi"}, 2);
        cities[29] = new City("pandemic", "baghdad", 780, 283, new String[]{"istanbul", "cairo", "riyadh", "karachi", "tehran"}, 2);
        cities[30] = new City("pandemic", "riyadh", 800, 350, new String[]{"cairo", "baghdad", "karachi"}, 2);
        cities[31] = new City("pandemic", "karachi", 878, 303, new String[]{"riyadh", "baghdad", "tehran", "delhi", "mumbai"}, 2);
        cities[32] = new City("pandemic", "mumbai", 919, 347, new String[]{"karachi", "delhi", "chennai"}, 2);
        cities[33] = new City("pandemic", "delhi", 939, 280, new String[]{"tehran", "karachi", "mumbai", "chennai", "kolkata"}, 2);
        cities[34] = new City("pandemic", "kolkata", 986, 318, new String[]{"delhi", "chennai", "bangkok", "hongkong"}, 2);
        cities[35] = new City("pandemic", "chennai", 950, 395, new String[]{"mumbai", "delhi", "kolkata", "bangkok", "jakarta"}, 2);
        cities[36] = new City("pandemic", "bangkok", 1040, 369, new String[]{"kolkata", "chennai", "jakarta", "hochiminhcity", "hongkong"}, 3);
        cities[37] = new City("pandemic", "jakarta", 1050, 460, new String[]{"chennai", "bangkok", "hochiminhcity", "sydney"}, 3);
        cities[38] = new City("pandemic", "sydney", 1251, 567, new String[]{"jakarta", "manila", "losangeles"}, 3);
        cities[39] = new City("pandemic", "manila", 1140, 375, new String[]{"hochiminhcity", "sydney", "hongkong", "taipei", "sanfrancisco"}, 3);
        cities[40] = new City("pandemic", "hochiminhcity", 1085, 420, new String[]{"bangkok", "jakarta", "manila", "hongkong"}, 3);
        cities[41] = new City("pandemic", "hongkong", 1085, 325, new String[]{"kolkata", "bangkok", "hochiminhcity", "manila", "taipei", "shanghai"}, 3);
        cities[42] = new City("pandemic", "shanghai", 1070, 270, new String[]{"hongkong", "taipei", "tokyo", "seoul", "beijing"}, 3);
        cities[43] = new City("pandemic", "beijing", 1070, 210, new String[]{"shanghai", "seoul"}, 3);
        cities[44] = new City("pandemic", "seoul", 1164, 236, new String[]{"beijing", "shanghai", "tokyo"}, 3);
        cities[45] = new City("pandemic", "tokyo", 1240, 263, new String[]{"seoul", "shanghai", "osaka", "sanfrancisco"}, 3);
        cities[46] = new City("pandemic", "osaka", 1178, 290, new String[]{"tokyo", "taipei"}, 3);
        cities[47] = new City("pandemic", "taipei", 1190, 340, new String[]{"hongkong", "shanghai", "osaka", "manila"}, 3);

        for (int j = 0; j < players.size(); j++) {
            players.get(j).init(gc);
        }

        for (int i = 0; i < cities.length; i++) {
            cities[i].init(gc);
        }


        player1Hand = new Button("Player1", 25, 11, 12); //EVERYONE'S A MEDIC
        player2Hand = new Button("Player2", 255, 11, 12);
        player3Hand = new Button("Player3", 485, 11, 12);
        player4Hand = new Button("Player4", 715, 11, 12);

        player1Hand.init(gc);
        player2Hand.init(gc);
        player3Hand.init(gc);
        player4Hand.init(gc);

        //PLACE THE CDC IN ATLANTA
        cities[0].setHasResearchSt(true);

        //CUBE TESTS SHOULD OBVIOUSLY NOT BE HERE
        cities[1].setCubeBlue(3);
        cities[0].setCubeYellow(3);
        cities[0].setCubeBlack(3);
        cities[0].setCubeRed(1);
        cities[16].setCubeBlack(3);
        cities[28].setCubeYellow(3);
        cities[39].setCubeRed(3);

        for (int j = 0; j < players.size(); j++) {
            players.get(j).setPlayerCards(cities);
        }


        //TEST OF CARDS
        players.get(0).addCardsToHand("hochiminhcity");
        players.get(0).addCardsToHand("bangkok");
        players.get(0).addCardsToHand("sydney");
        players.get(0).addCardsToHand("jakarta");
        players.get(0).addCardsToHand("tokyo");
        players.get(0).addCardsToHand("riyadh");
        players.get(0).addCardsToHand("istanbul");
        players.get(0).addCardsToHand("paris");




        players.get(1).addCardsToHand("atlanta");
        players.get(1).addCardsToHand("manila");

        players.get(2).addCardsToHand("kolkata");

        players.get(3).addCardsToHand("essen");


    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        updateLibrary();
        moveAction();
        move(gc);
        removeCubeAction();
        removeCube(gc);
        placeResearchStationAction();
        placeResearchStation(gc);
        makeCure(gc);

        endTurn = actionMenu.getEndTurn();
        if (endTurn && players.get(playerNo).getHandLength() > 7) {
            if (playerNo == 0)
                showHand1 = true;
            else if (playerNo == 1)
                showHand2 = true;
            else if (playerNo == 2)
                showHand3 = true;
            else
                showHand4 = true;

            players.get(playerNo).discardCards(gc);
        } else if(endTurn && players.get(playerNo).getHandLength() <= 7) {
            System.out.println("SÅ SKAL DER TEGNES KNAP");
        }

        outOfTurns = players.get(playerNo).getOutOfTurns();
        actionMenu.setPlayerOutTurns(outOfTurns);


        infectionMarker.update(gc, i);
        outbreakMarker.update(gc, i);
        actionMenu.update(gc, i);

        if (player1Hand.clickWithin(gc)) {
            showHand1 = !showHand1;
        }

        if (player2Hand.clickWithin(gc)) {
            showHand2 = !showHand2;
        }

        if (player3Hand.clickWithin(gc)) {
            showHand3 = !showHand3;
        }

        if (player4Hand.clickWithin(gc)) {
            showHand4 = !showHand4;
        }


        for (int j = 0; j < players.size(); j++) {
            players.get(j).update(gc, i);
        }

        cities[0].update(gc, i);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        g.drawImage(gameBoard, 0, 0);
        infectionMarker.render(gc, g);
        outbreakMarker.render(gc, g);

        player1Hand.render(gc, g);
        player2Hand.render(gc, g);
        player3Hand.render(gc, g);
        player4Hand.render(gc, g);

        players.get(playerNo).displayTurnsLeft(g);


        //MOVE RENDER FUNCTIONALITY & REMOVE CUBE RENDER FUNCTIONALITY && PLACE RESEARCH STATION RENDER FUNCTIONALITY
        for (int i = 0; i < cities.length; i++) {
            if (((players.get(playerNo).getNeighborCityAsList().contains(cities[i].getCityName())) && actionMenu.getIsMoveActive())
                    || ((players.get(playerNo).getPlayerPosition().isHasResearchSt() && cities[i].isHasResearchSt()) && actionMenu.getIsMoveActive())
                    || ((players.get(playerNo).convertHandToStringList().contains(cities[i].getCityName())) && actionMenu.getIsMoveActive()) // TEST RIGHT NOW
                    || cities[i].getCityName().equals(players.get(playerNo).getPlayerPosition().getCityName()) && actionMenu.getIsRemoveCubeActive()
                    || cities[i].getCityName().equals(players.get(playerNo).getPlayerPosition().getCityName()) && !cities[i].isHasResearchSt() && actionMenu.getIsResearchSActive())

            {
                cities[i].activeAction(true);
                cities[i].render(gc, g);
            } else {
                cities[i].activeAction(false);
                cities[i].render(gc, g);
            }
        }

        //DISPLAY THE LOCATION OF THE OTHER PLAYERS WITH A LITTLE BLUE FLAG
        if (playerNo != 0)
            players.get(0).displayOthersLocation(g);
        if (playerNo != 1)
            players.get(1).displayOthersLocation(g);
        if (playerNo != 2)
            players.get(1).displayOthersLocation(g);
        if (playerNo != 3)
            players.get(1).displayOthersLocation(g);


        //DISPLAY THE LOCATION OF YOU WITH A LITTLE ORANGE FLAG
        players.get(playerNo).displayCurrentLocation(g);

        //DISPLAY WHICH CITIES HAS RESEARCH STATIONS
        for (int i = 0; i < cities.length; i++) {
            cities[i].displayResearchCenter(g);
        }

        //DISPLAY THE CITY OVERVIEW
        for (int i = 0; i < cities.length; i++) {
            cities[i].displayCityOverview(gc, g);
        }

        //DISPLAY PLAYER ICONS
        for (int i = 0; i < cities.length; i++) {
            if (players.get(0).getPlayerPosition().getCityName().equals(cities[i].getCityName()) && cities[i].getButton().hoverOver(gc)) {
                players.get(0).setPlayerID(0);
                players.get(0).render(gc, g);
            }
        }

        for (int i = 0; i < cities.length; i++) {
            if (players.get(1).getPlayerPosition().getCityName().equals(cities[i].getCityName()) && cities[i].getButton().hoverOver(gc)) {
                players.get(1).setPlayerID(1);
                players.get(1).render(gc, g);
            }
        }

        for (int i = 0; i < cities.length; i++) {
            if (players.get(2).getPlayerPosition().getCityName().equals(cities[i].getCityName()) && cities[i].getButton().hoverOver(gc)) {
                players.get(2).setPlayerID(2);
                players.get(2).render(gc, g);
            }
        }

        for (int i = 0; i < cities.length; i++) {
            if (players.get(3).getPlayerPosition().getCityName().equals(cities[i].getCityName()) && cities[i].getButton().hoverOver(gc)) {
                players.get(3).setPlayerID(3);
                players.get(3).render(gc, g);
            }
        }

        boolean isCureSelected = actionMenu.getIsCureActive();

        if (isCureSelected && (players.get(playerNo).countCardsForDisease().equals("cureblue") || (players.get(playerNo).countCardsForDisease().equals("cureyellow") || players.get(playerNo).countCardsForDisease().equals("cureblack") || players.get(playerNo).countCardsForDisease().equals("curered")))) {
            g.drawImage(confirmMenu, 446, 307);
            yes.render(gc, g);
            no.render(gc, g);
        } else if (isCureSelected) {
            g.drawImage(actionNotAvailable, 316, 311);
        }

        if (showHand1) {
            players.get(0).displayHand(gc, g, 0);
        }
        if (showHand2) {
            players.get(1).displayHand(gc, g, 1);
        }
        if (showHand3) {
            players.get(2).displayHand(gc, g, 2);
        }
        if (showHand4) {
            players.get(3).displayHand(gc, g, 3);
        }

        actionMenu.render(gc, g);

        //DRAWING THE OBTAINED CURES
        if (blueCure)
            g.drawImage(blueCureImg, 27, 698);
        if (yellowCure)
            g.drawImage(yellowCureImg, 72, 697);
        if (blackCure)
            g.drawImage(blackCureImg, 117, 698);
        if (redCure)
            g.drawImage(redCureImg, 162, 697);


    }

    public void updateLibrary() {

        playerNo = gsc.getPlayerNo();
        roleNo = gsc.getPlayers().get(playerNo).getRole().getRoleNumber();

        player1Hand.setPicIndexNo(gsc.getPlayers().get(0).getRole().getRoleNumber() + 12);
        player2Hand.setPicIndexNo(gsc.getPlayers().get(1).getRole().getRoleNumber() + 12);
        player3Hand.setPicIndexNo(gsc.getPlayers().get(2).getRole().getRoleNumber() + 12);
        player4Hand.setPicIndexNo(gsc.getPlayers().get(3).getRole().getRoleNumber() + 12);

        players.get(0).setRoleP1(gsc.getPlayers().get(0).getRole().getRoleNumber());
        players.get(1).setRoleP2(gsc.getPlayers().get(1).getRole().getRoleNumber());
        players.get(2).setRoleP3(gsc.getPlayers().get(2).getRole().getRoleNumber());
        players.get(3).setRoleP4(gsc.getPlayers().get(3).getRole().getRoleNumber());


    }

    public void moveAction() {
        boolean move = actionMenu.getIsMoveActive();
        for (int i = 0; i < cities.length; i++) {
            if (move) {
                cities[i].setMoveButtonSelected(true);
                players.get(playerNo).setMoveSelected(true);
            } else {
                cities[i].setMoveButtonSelected(false);
                players.get(playerNo).setMoveSelected(false);
            }
        }

    }

    public void move(GameContainer gc) {
        //MOVE PLAYER
        if (actionMenu.getIsMoveActive()) {
            for (int i = 0; i < cities.length; i++) {
                if (cities[i].getButton().clickWithin(gc)) {
                    players.get(playerNo).setPlayerPosition(cities[i]);
                    players.get(playerNo).turnsSpent();
                }
            }
        }
    }

    public void removeCubeAction() {
        boolean removeCube = actionMenu.getIsRemoveCubeActive();
        for (int i = 0; i < cities.length; i++) {
            if (removeCube) {
                cities[i].setRemoveCubeButtonSelected(true);
            } else {
                cities[i].setRemoveCubeButtonSelected(false);
            }
        }
    }

    //REMOVE CUBE
    public void removeCube(GameContainer gc) {
        if (actionMenu.getIsRemoveCubeActive()) {
            for (int i = 0; i < cities.length; i++) {
                if (cities[i].getButton().clickWithin(gc)) {
                    cities[i].removeCube(players, playerNo);
                    players.get(playerNo).turnsSpent();
                }
            }
        }
    }

    public void placeResearchStationAction() {
        boolean placeResearchStation = actionMenu.getIsResearchSActive();
        for (int i = 0; i < cities.length; i++) {
            if (placeResearchStation) {
                cities[i].setPlaceResearchStationSelected(true);
            } else {
                cities[i].setPlaceResearchStationSelected(false);
            }
        }

    }

    //PLACE RESEARCH STATION
    public void placeResearchStation(GameContainer gc) {

        if (actionMenu.getIsResearchSActive()) {
            for (int i = 0; i < cities.length; i++) {
                if (cities[i].getButton().clickWithin(gc) && noOfResearchStationsLeft > 0) {
                    cities[i].placeResearchStation(players, playerNo);
                    noOfResearchStationsLeft -= 1;
                    players.get(playerNo).turnsSpent();
                }
            }
        }
    }

    public void makeCure(GameContainer gc) {
        boolean makeCure = actionMenu.getIsCureActive();

        for (int i = 0; i < cities.length; i++) {
            if (makeCure && yes.clickWithin(gc)) {
                actionMenu.setCureActive(false);
                actionMenu.setEventCardActive(false);
                actionMenu.setMoveActive(false);
                actionMenu.setRemoveCubeActive(false);
                actionMenu.setShareActive(false);
                actionMenu.setResearchSActive(false);
                players.get(playerNo).setCureSelected(true);
                if (players.get(playerNo).countCardsForDisease().equals("cureblue")) {
                    players.get(playerNo).removeCardsForTheCure();
                    players.get(playerNo).turnsSpent();
                    blueCure = true;
                }
                if (players.get(playerNo).countCardsForDisease().equals("cureyellow")) {
                    players.get(playerNo).removeCardsForTheCure();
                    players.get(playerNo).turnsSpent();
                    yellowCure = true;
                }
                if (players.get(playerNo).countCardsForDisease().equals("cureblack")) {
                    players.get(playerNo).removeCardsForTheCure();
                    players.get(playerNo).turnsSpent();
                    blackCure = true;
                }
                if (players.get(playerNo).countCardsForDisease().equals("curered")) {
                    players.get(playerNo).removeCardsForTheCure();
                    players.get(playerNo).turnsSpent();
                    redCure = true;
                }
            } else {
                players.get(playerNo).setCureSelected(false);
            }
            if (no.clickWithin(gc)) {
                actionMenu.setCureActive(false);
                actionMenu.setEventCardActive(false);
                actionMenu.setMoveActive(false);
                actionMenu.setRemoveCubeActive(false);
                actionMenu.setShareActive(false);
                actionMenu.setResearchSActive(false);
            }
        }
    }


    @Override
    public int getID() {
        return 2;
    }
}
