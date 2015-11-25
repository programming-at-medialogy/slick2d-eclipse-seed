package example;

import java.util.List;

/**
 * A class that holds commons throughout the different gamestates (ie. inputscreen, lobby, gameboard)
 * Created by TMA on 12-11-2015.
 */
public class GameStateCommons {

    private List<Player> players;
    private int playerNo, roleNo;
    private boolean enteringGameStateP1, enteringGameStateP2, enteringGameStateP3, enteringGameStateP4;

    public int getPlayerNo() {
        return playerNo;
    }

    public void setPlayerNo(int player) {
        this.playerNo = player;
    }

    public int getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(int role) {
        this.roleNo = role;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
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


}
