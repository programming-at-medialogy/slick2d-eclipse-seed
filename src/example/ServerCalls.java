package example;

import org.lwjgl.Sys;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Class that holds the Server Calls. A String is passed to the server and a value returned
 * Created by TMA on 07-11-2015.
 */
public class ServerCalls extends Thread {

    GameStateCommons gsc;
    private static Socket socket = GameClient.socket;
    private static PrintWriter out = GameClient.out;

    private int roleNo;
    public boolean player1status,player2status,player3status,player4status;


    public ServerCalls(GameStateCommons gsc) {
        this.gsc = gsc;
    }

    /**
     * Assign the player with an ID so that we always can find his position within an ArrayList of Players
     * @throws IOException
     */

    public void getPlayerID() throws IOException {
        out.println("GET_PLAYER_ID");
        out.flush();

        /*
        inputStream = in.readLine();
        System.out.println(inputStream);

        if (inputStream.equals("GET_PLAYER_ID@0") || inputStream.equals("GET_PLAYER_ID@1") || inputStream.equals("GET_PLAYER_ID@2") || inputStream.equals("GET_PLAYER_ID@3")) {
            String [] value = inputStream.split("@");
            String tmpInt = value[1];
            System.out.println("IT ACCESSES THIS IF STATEMENT AT SPLITS THE STRING INTO " +value[0]+ "AND THE EVERMORE USEFUL ID: " +value[1]);
            playerID = Integer.valueOf(tmpInt);
        }
        return playerID;*/
    }

    /**
     * @param index sets the GET function to the specific player ID
     * @return the current status of the player (if he is ready or not)
     * @throws IOException
     */
    public void getPlayerStatusOnServer(int index) throws IOException {

        out.println("GET_PLAYER_STATUS: " + index);
        out.flush();
    }

    /**
     * @param playerList arraylist of the players
     * @param playerIdNo the ID of the specific player using that specific client
     *                   Sets the status of the player on the server (if he is ready to play or not)
     */
    public void setPlayerStatusOnServer(List<Player> playerList, int playerIdNo) {

        boolean curPlayStatus;
        curPlayStatus = playerList.get(playerIdNo).getPlayerReady();
        out.println("SET_PLAYER_STATUS: " + curPlayStatus + " " + playerIdNo);
        out.flush();
    }

    public void setPlayerRole(int playerNo) {

        out.println("SET_PLAYER_ROLE: " + playerNo);
        out.flush();

    }

    public void getPlayerRole(int playerNo) throws IOException {

        out.println("GET_PLAYER_ROLE: " + playerNo);
        out.flush();
    }


    public void getAnimationStatus() throws IOException {

        out.println("GET_ANIMATION_STATUS");
        out.flush();
    }

    public void setAnimationStatusTrue() {

        out.println("SET_ANIMATION_TRUE");
        out.flush();
    }

    public void run() {


        try {
            boolean running = true;
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (running) {
                String serverCommands;

                serverCommands = in.readLine();

                if (serverCommands.equals("quit")) {
                    running = false;
                    System.out.println("QUITTER");
                }

                if (    serverCommands.equals("GET_PLAYER_ID@0") ||
                        serverCommands.equals("GET_PLAYER_ID@1") ||
                        serverCommands.equals("GET_PLAYER_ID@2") ||
                        serverCommands.equals("GET_PLAYER_ID@3")) {

                    String[] value = serverCommands.split("@");
                    int tmpInt = Integer.valueOf(value[1]);
                    gsc.setPlayerNo(tmpInt);
                }

                if (       serverCommands.equals("GET_PLAYER_ROLE@0@0")
                        || serverCommands.equals("GET_PLAYER_ROLE@0@1")
                        || serverCommands.equals("GET_PLAYER_ROLE@0@2")
                        || serverCommands.equals("GET_PLAYER_ROLE@0@3")
                        || serverCommands.equals("GET_PLAYER_ROLE@0@4")
                        || serverCommands.equals("GET_PLAYER_ROLE@0@5")
                        || serverCommands.equals("GET_PLAYER_ROLE@0@6")

                        || serverCommands.equals("GET_PLAYER_ROLE@1@1")
                        || serverCommands.equals("GET_PLAYER_ROLE@1@2")
                        || serverCommands.equals("GET_PLAYER_ROLE@1@3")
                        || serverCommands.equals("GET_PLAYER_ROLE@1@4")
                        || serverCommands.equals("GET_PLAYER_ROLE@1@5")
                        || serverCommands.equals("GET_PLAYER_ROLE@1@6")

                        || serverCommands.equals("GET_PLAYER_ROLE@2@1")
                        || serverCommands.equals("GET_PLAYER_ROLE@2@2")
                        || serverCommands.equals("GET_PLAYER_ROLE@2@3")
                        || serverCommands.equals("GET_PLAYER_ROLE@2@4")
                        || serverCommands.equals("GET_PLAYER_ROLE@2@5")
                        || serverCommands.equals("GET_PLAYER_ROLE@2@6")

                        || serverCommands.equals("GET_PLAYER_ROLE@3@1")
                        || serverCommands.equals("GET_PLAYER_ROLE@3@2")
                        || serverCommands.equals("GET_PLAYER_ROLE@3@3")
                        || serverCommands.equals("GET_PLAYER_ROLE@3@4")
                        || serverCommands.equals("GET_PLAYER_ROLE@3@5")
                        || serverCommands.equals("GET_PLAYER_ROLE@3@6")) {

                    String[] value = serverCommands.split("@");
                    int playerNo = Integer.valueOf(value[1]);
                    int roleNo = Integer.valueOf(value[2]);
                    gsc.getPlayers().get(playerNo).getRole().setIndexNo(roleNo);
                }


                if (    serverCommands.equals("GET_PLAYER_STATUS@true@0") ||
                        serverCommands.equals("GET_PLAYER_STATUS@false@0") ||
                        serverCommands.equals("GET_PLAYER_STATUS@true@1") ||
                        serverCommands.equals("GET_PLAYER_STATUS@false@1") ||
                        serverCommands.equals("GET_PLAYER_STATUS@true@2") ||
                        serverCommands.equals("GET_PLAYER_STATUS@false@2") ||
                        serverCommands.equals("GET_PLAYER_STATUS@true@3") ||
                        serverCommands.equals("GET_PLAYER_STATUS@false@3")) {

                    String[] tmpValue = serverCommands.split("@");
                    int identifier = Integer.valueOf(tmpValue[2]);
                    boolean status = Boolean.valueOf(tmpValue[1]);
                    gsc.getPlayers().get(identifier).setPlayerReady(status);
                }

                if (serverCommands.equals("GET_ANIMATION_STATUS@true") || serverCommands.equals("GET_ANIMATION_STATUS@false")) {
                    String[] value = serverCommands.split("@");
                    String tmpString = value[1];
                    boolean tmpBool = Boolean.valueOf(tmpString);
                    gsc.setAnimationStatus(tmpBool);
                    System.out.println(value);
                }

            }


        } catch (IOException ioEx){
            ioEx.printStackTrace();
        }

    }

    public int getServerReturnedRole() {
        return roleNo;
    }

    private void setServerReturnedRole(int role) {
        this.roleNo = role;
    }

    public int getServerReturnedPlayerNo() {
        return 1;
    }

    public boolean isPlayer1status() {
        return player1status;
    }

    public void setPlayer1status(boolean player1status) {
        this.player1status = player1status;
    }

    public boolean isPlayer2status() {
        return player2status;
    }

    public void setPlayer2status(boolean player2status) {
        this.player2status = player2status;
    }

    public boolean isPlayer3status() {
        return player3status;
    }

    public void setPlayer3status(boolean player3status) {
        this.player3status = player3status;
    }

    public boolean isPlayer4status() {
        return player4status;
    }

    public void setPlayer4status(boolean player4status) {
        this.player4status = player4status;
    }
}
