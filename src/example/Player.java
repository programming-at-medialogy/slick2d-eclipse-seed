package example;

/**
 * The player class
 * Created by TMA on 03-11-2015.
 */
public class Player {

    String pos, role;
    boolean turn;
    int actionsLeft;
    private boolean playerReady;
    Roles[] roles;

    String gametitle;


    public Player(String gametitle) {
        this.gametitle = gametitle;
        playerReady = false;

    }

    /**
     * GETTER and SETTER Methods
     */

    public boolean getPlayerReady() {
        return playerReady;
    }

    public void setPlayerReady(boolean setPlayerReady) {

        this.playerReady = setPlayerReady;
        //System.out.println(getTitle() + " is ready?: " + playerReady);

    }




}
