	import java.io.Serializable;

public class PlayerInformation implements Serializable{

	//Ready booleans for menu
    boolean playerOneReady, playerTwoReady, playerThreeReady, playerFourReady;
    
    //Player Scores
    int playerOneScore, playerTwoScore, playerThreeScore, playerFourScore;
    
    //int to decide player numbers
    int playerNumber;
    
    //stores the colours of the houses
    int[] houseColour = new int[54];
    //stores the colours of the cities
    int[] cityColour = new int[54];
    //stores the colours of the roads
    int[] roadsColourDiagonal = new int[72];
    int[] roadsColourStraight = new int[72];
    
    //stores the tile index and number index which will be shuffled.
    int[] SOCtileIndex = new int[19];
    int[] SOCnumberIndex = new int[19];
    
    //stores where the houses/cities/roads have been placed
    boolean[] SOChouseArea = new boolean[54];
    boolean[] SOCcityArea = new boolean[54];
    boolean[] SOCroadAreaDiagonal = new boolean[72];
    boolean[] SOCroadAreaStraight = new boolean[72];
    
    //stores the player number of the person who owns each house, which is used as a requirement to place a city.
    int[] areaClickedOwnership = new int[54];

    //store values of dices.
    int diceOneValue = 1;
    int diceTwoValue = 1;

    //stores the variable that controls which player has the turn
    int playerTurn = 1;
    
    //stores the amount of rounds till game ends. Optional.
    int roundCount = 30;

    //stores players resources
    int [][] playerResource = new int[4][5];
    //stores the position of each settlement, which is used to distribute resources.
    int [][] settlementPos = new int[54][3];

    //stores each players victory points.
    int[][] playerVictoryPoints = new int[4][1];


    PlayerInformation(){
    	
    	playerOneReady = false;
        playerTwoReady = false;
        playerThreeReady = false;
        playerFourReady = false;

        //sets the starting resources of each player
        for(int i = 0;i<playerResource.length;i++){
            playerResource[i][0] = 0;
            playerResource[i][1] = 2;
            playerResource[i][2] = 4;
            playerResource[i][3] = 4;
            playerResource[i][4] = 2;
        }

    }
}