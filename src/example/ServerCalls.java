package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Class that holds the Server Calls. A String is passed to the server and a value returned
 * Created by TMA on 07-11-2015.
 */
public class ServerCalls {

    private static final String HOST = "localhost";
    private static final int PORT = 1234;

    public ServerCalls() {

    }

    /**
     * Assign the player with an ID so that we always can find his position within an ArrayList of Players
     *
     * @return Returns the ID of the player as an integer
     * @throws IOException
     */
    public static int assignPlayerID() throws IOException {
        String inputStream;
        int playerID;

        Socket s = new Socket(HOST, PORT);

        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        out.println("ASSIGN_PLAYER_ID");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        inputStream = in.readLine();
        playerID = Integer.valueOf(inputStream);
        System.out.println(inputStream);

        return playerID;
    }

    public static int getPlayerID() throws IOException {
        String inputStream;
        int playerID;

        Socket s = new Socket(HOST, PORT);

        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        out.println("GET_PLAYER_ID");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        inputStream = in.readLine();
        playerID = Integer.valueOf(inputStream);
        System.out.println(inputStream);

        return playerID;
    }

    /**
     * @param index sets the GET function to the specific player ID
     * @return the current status of the player (if he is ready or not)
     * @throws IOException
     */
    public static boolean getPlayerStatusOnServer(int index) throws IOException {

        boolean gO;
        String oPSO;

        Socket gP = new Socket(HOST, PORT);
        PrintWriter gPs = new PrintWriter(gP.getOutputStream(), true);
        gPs.println("GET_PLAYER_STATUS: " + index);

        BufferedReader gettingThePlayerStatus = new BufferedReader(new InputStreamReader(gP.getInputStream()));
        oPSO = gettingThePlayerStatus.readLine();
        gO = Boolean.valueOf(oPSO);
        //System.out.println("FROM GET FUNCTION : Player With ID " + index + " is active " + gO);
        return gO;
    }

    /**
     * @param playerList arraylist of the players
     * @param playerIdNo the ID of the specific player using that specific client
     *                   Sets the status of the player on the server (if he is ready to play or not)
     */
    public static void setPlayerStatusOnServer(List<Player> playerList, int playerIdNo) {

        String playerStatusOut;
        boolean curPlayStatus;

        try {
            Socket s = new Socket(HOST, PORT);
            PrintWriter setPlayerStatus = new PrintWriter(s.getOutputStream(), true);
            curPlayStatus = playerList.get(playerIdNo).getPlayerReady();
            setPlayerStatus.println("SET_PLAYER_STATUS: " + curPlayStatus + " " + playerIdNo);

            BufferedReader getPlayerStatus = new BufferedReader(new InputStreamReader(s.getInputStream()));
            playerStatusOut = getPlayerStatus.readLine();
            curPlayStatus = Boolean.valueOf(playerStatusOut);
            System.out.println("FROM SET PLAY FUNCTION : Player With ID " + playerIdNo + " is now active" + curPlayStatus);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

    }

    public static int setPlayerRole(int playerNo) throws IOException {
        String playerRoleString;
        int playerRole;

        Socket s = new Socket(HOST, PORT);

        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        out.println("SET_PLAYER_ROLE: " + playerNo);

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        playerRoleString = in.readLine();
        playerRole = Integer.valueOf(playerRoleString);

        return playerRole;
    }

    public static int getPlayerRole(int playerNo) throws IOException {
        String playerRoleString;
        int playerRole;

        Socket s = new Socket(HOST, PORT);

        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        out.println("GET_PLAYER_ROLE: " + playerNo);

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        playerRoleString = in.readLine();
        playerRole = Integer.valueOf(playerRoleString);

        return playerRole;
    }

    public static boolean getAnimationStatus() throws IOException {

        String animationStatusString;
        boolean animationStatus;

        Socket s = new Socket(HOST, PORT);

        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        out.println("GET_ANIMATION_STATUS");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        animationStatusString = in.readLine();
        animationStatus = Boolean.valueOf(animationStatusString);

        return animationStatus;
    }

    public static void setAnimationStatusTrue() {

        try {
            Socket s = new Socket(HOST, PORT);

            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            out.println("SET_ANIMATION_TRUE");

        } catch (IOException IOEx) {
            IOEx.printStackTrace();
        }
    }

}
