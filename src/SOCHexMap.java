import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class SOCHexMap {

    int[] tileIndex;
    int[] numberIndex;

    SOCHexMap(){

        tileIndex = new int[] {0,0,0,1,1,1,1,2,3,3,3,3,4,4,4,5,5,5,5}; //predetermined values at the indexes - the type of the tiles
        numberIndex = new int[]{2,3,3,4,4,5,5,6,6,7,8,8,9,9,10,10,11,11,12}; //predetermined values at the indexes - the numbers on the tiles
        shuffleArray(tileIndex); //shuffles the array
        shuffleArray(numberIndex); //shuffles the array

    }


 //function that goes through each value in the socket and randomizes their index value;
    public void shuffleArray(int[] ar){
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

}
