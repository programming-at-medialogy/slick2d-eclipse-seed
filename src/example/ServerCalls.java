package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by TMA on 07-11-2015.
 */
public class ServerCalls {

    public ServerCalls() {

    }

    public static int assignPlayerID() throws IOException {
            String inputStream;
            int playerID;

            Socket s = new Socket("localhost", 1234);

            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            out.println("ASSIGN_PLAYER_ID");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            inputStream = in.readLine();
            playerID = Integer.valueOf(inputStream);
            System.out.println(inputStream);

            return playerID;
    }

    /**
     *
     * @param index sets the GET function to the specific player ID
     * @return the current status of the player (if he is ready or not)
     * @throws IOException
     */
    public static boolean getPlayerStatusOnServer(int index) throws IOException {

        boolean gO;
        String oPSO;

        Socket gP = new Socket("localhost", 1234);
        PrintWriter gPs = new PrintWriter(gP.getOutputStream(), true);
        gPs.println("GET_PLAYER_STATUS: "+index);

        BufferedReader gettingThePlayerStatus = new BufferedReader(new InputStreamReader(gP.getInputStream()));
        oPSO = gettingThePlayerStatus.readLine();
        gO = Boolean.valueOf(oPSO);
        //System.out.println("FROM GET FUNCTION : Player With ID " + index + " is active " + gO);
        return gO;
    }

}
