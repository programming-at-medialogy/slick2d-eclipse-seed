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
    private boolean statusP1, statusP2,statusP3,statusP4;
    private int player1Role, player2Role, player3Role, player4Role;
    private boolean animationStatus;

    public boolean isStatusP1() {
        return statusP1;
    }

    public void setStausP1(boolean stausP1) {
        this.statusP1 = stausP1;
        System.out.println("PLAYER 1 IS NOW "+statusP1);

    }

    public boolean isStatusP2() {
        return statusP2;
    }

    public void setStatusP2(boolean statusP2) {
        this.statusP2 = statusP2;
    }

    public boolean isStatusP3() {
        return statusP3;
    }

    public void setStatusP3(boolean statusP3) {
        this.statusP3 = statusP3;
    }

    public boolean isStatusP4() {
        return statusP4;
    }

    public void setStatusP4(boolean statusP4) {
        this.statusP4 = statusP4;
    }

    public GameStateCommons(List<Player> players) {

        playerNo = 0;
        roleNo = 0;

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

    /*
    public int getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(int role) {
        this.roleNo = role;
    }*/

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

    public boolean isAnimationStatus() {
        return animationStatus;
    }

    public void setAnimationStatus(boolean animationStatus) {
        this.animationStatus = animationStatus;
    }

    public int getPlayer1Role() {
        return player1Role;
    }

    public void setPlayer1Role(int player1Role) {
        this.player1Role = player1Role;
    }

    public int getPlayer2Role() {
        return player2Role;
    }

    public void setPlayer2Role(int player2Role) {
        this.player2Role = player2Role;
    }

    public int getPlayer3Role() {
        return player3Role;
    }

    public void setPlayer3Role(int player3Role) {
        this.player3Role = player3Role;
    }

    public int getPlayer4Role() {
        return player4Role;
    }

    public void setPlayer4Role(int player4Role) {
        this.player4Role = player4Role;
    }


}
