
import java.io.Serializable;

public class PlayerInformation implements Serializable{

    boolean playerOneReady, playerTwoReady, playerThreeReady, playerFourReady;
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
