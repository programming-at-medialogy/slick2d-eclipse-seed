import java.io.Serializable;

public class PlayerInformation implements Serializable{

    boolean playerOneReady, playerTwoReady, playerThreeReady, playerFourReady;
    int playerNumber;
    int[] houseColour = new int[54];
    int[] cityColour = new int[54];
    int[] roadsColourDiagonal = new int[72];
    int[] roadsColourStraight = new int[72];
    int[] SOCtileIndex = new int[19];
    int[] SOCnumberIndex = new int[19];
    boolean[] SOChouseArea = new boolean[54];
    boolean[] SOCcityArea = new boolean[54];
    boolean[] SOCroadAreaDiagonal = new boolean[72];
    boolean[] SOCroadAreaStraight = new boolean[72];
    int[] areaClickedOwnership = new int[54];

    boolean diceClick = true;
    int diceOneValue = 1;
    int diceTwoValue = 1;
    boolean distributeResources = false;
    int testInt = 0;

    int playerTurn = 1;
    int roundCount = 1;

    int [][] playerResource = new int[4][5];
    int [][] settlementPos = new int[54][3];

    int[][] playerVictoryPoints = new int[4][1];


    PlayerInformation(){

        for(int i = 0;i<playerResource.length;i++){
            playerResource[i][0] = 0;
            playerResource[i][1] = 2;
            playerResource[i][2] = 4;
            playerResource[i][3] = 4;
            playerResource[i][4] = 2;
        }

        playerOneReady = false;
        playerTwoReady = false;
        playerThreeReady = false;
        playerFourReady = false;

    }
}
