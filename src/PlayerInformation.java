
import java.io.Serializable;

public class PlayerInformation implements Serializable{

	//Ready booleans for menu
    boolean playerOneReady, playerTwoReady, playerThreeReady, playerFourReady;
    
    //Player Scores
    int playerOneScore, playerTwoScore, playerThreeScore, playerFourScore;
    
    //int to decide player numbers
    int playerNumber;
    int[] houseColour = new int[54];
    int[] roadsColourDiagonal = new int[72];
    int[] roadsColourStraight = new int[72];
    int[] SOCtileIndex = new int[19];
    int[] SOCnumberIndex = new int[19];
    boolean[] SOChouseArea = new boolean[54];
    boolean[] SOCroadAreaDiagonal = new boolean[72];
    boolean[] SOCroadAreaStraight = new boolean[72];
   

    PlayerInformation(){
        playerOneReady = false;
        playerTwoReady = false;
        playerThreeReady = false;
        playerFourReady = false;
        
        
    }
}
