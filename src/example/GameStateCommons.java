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
    private boolean enteringLobbyStateP1, enteringLobbyStateP2, enteringLobbyStateP3, enteringLobbyStateP4;

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

    public boolean isEnteringLobbyState(int playerIndex) {
        if (playerIndex == 0)
            return enteringLobbyStateP1;
        else if (playerIndex == 1)
            return enteringLobbyStateP2;
        else if (playerIndex == 2)
            return enteringLobbyStateP3;
        else
            return enteringLobbyStateP4;
    }

    public void setEnteringLobbyState(boolean enteringLobbyState, int playerIndex) {
        if (playerIndex == 0)
            this.enteringLobbyStateP1 = enteringLobbyState;
        else if (playerIndex == 1)
            this.enteringLobbyStateP2 = enteringLobbyState;
        else if (playerIndex == 2)
            this.enteringLobbyStateP3 = enteringLobbyState;
        else
            this.enteringLobbyStateP4 = enteringLobbyState;
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
