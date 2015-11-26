package example;

import java.util.List;

/**
 * A class that holds commons throughout the different gamestates (ie. inputscreen, lobby, gameboard)
 * Created by TMA on 12-11-2015.
 */
public class GameStateCommons {

    private List<Player> players;
    private int playerNo;
    private boolean enteringGameStateP1, enteringGameStateP2, enteringGameStateP3, enteringGameStateP4;
    private boolean animationStatus;

    public GameStateCommons(List<Player> players) {

        playerNo = 0;

        enteringGameStateP1 = false;
        enteringGameStateP2 = false;
        enteringGameStateP3 = false;
        enteringGameStateP4 = false;

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

    public boolean isEnteringGameState(int playerIndex) {
        if (playerIndex == 0)
            return enteringGameStateP1;
        else if (playerIndex == 1)
            return enteringGameStateP2;
        else if (playerIndex == 2)
            return enteringGameStateP3;
        else
            return enteringGameStateP4;
    }

    public void setEnteringGameState(boolean enteringGameState, int playerIndex) {
        if (playerIndex == 0)
            this.enteringGameStateP1 = enteringGameState;
        else if (playerIndex == 1)
            this.enteringGameStateP2 = enteringGameState;
        else if (playerIndex == 2)
            this.enteringGameStateP3 = enteringGameState;
        else
            this.enteringGameStateP4 = enteringGameState;
    }

    public boolean isAnimationStatus() {
        return animationStatus;
    }

    public void setAnimationStatus(boolean animationStatus) {
        this.animationStatus = animationStatus;
    }

}
