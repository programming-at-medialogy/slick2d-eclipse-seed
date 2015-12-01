
import java.io.Serializable;

public class PlayerInformation implements Serializable{

	//Ready booleans for menu
    boolean playerOneReady, playerTwoReady, playerThreeReady, playerFourReady;
    
    //Player Scores
    int playerOneScore, playerTwoScore, playerThreeScore, playerFourScore;
    
    //int to decide player numbers
    int playerNumber;
    
    int[] SOCtileIndex = new int[19];
    int[] SOCnumberIndex = new int[19];

    PlayerInformation(){
        playerOneReady = false;
        playerTwoReady = false;
        playerThreeReady = false;
        playerFourReady = false;
    }
}
