package example;

import java.util.List;

/**
 * A class that holds commons throughout the different gamestates (ie. inputscreen, lobby, gameboard)
 * Created by TMA on 12-11-2015.
 */
public class GameStateCommons {

    private List<Player> players;
    private int playerNo;
    private boolean animationStatus;

    public GameStateCommons(List<Player> players) {

        playerNo = 0;
        this.players = players;

    }

    public int getPlayerNo() {
        return playerNo;
    }

    public void setPlayerNo(int player) {
        this.playerNo = player;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isAnimationStatus() {
        return animationStatus;
    }

    public void setAnimationStatus(boolean animationStatus) {
        this.animationStatus = animationStatus;
    }

}
