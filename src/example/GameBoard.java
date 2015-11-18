package example;

import com.sun.xml.internal.ws.api.pipe.SyncStartForAsyncFeature;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Marianne on 05-11-2015.
 */
public class GameBoard extends BasicGameState {

    City[] cities;
    private Image gameBoard;


    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        gameBoard = new Image("assets/backgrounds/bg.png");

        cities = new City[48];
        cities[0] = new City("pandemic","atlanta",230,300, new String[]{"miami","washington","chicago"},0);
        cities[1] = new City("pandemic","chicago",189,243, new String[]{"atlanta", "sanfrancisco","montreal"},0);
        cities[2] = new City("pandemic", "sanfrancisco",93,272, new String[]{"losangeles","chicago","tokyo","manila"},0);
        cities[3] = new City("pandemic","montreal",280,205, new String[]{"chicago","washington","newyork"},0);
        cities[4] = new City("pandemic","newyork",312,252, new String[]{"montreal","washington","madrid","london"},0);
        cities[5] = new City("pandemic","washington",291,292, new String[]{"newyork","miami","atlanta","montreal"},0);
        cities[6] = new City("pandemic","london",585,190, new String[]{"newyork","madrid","paris","essen"},0);
        cities[7] = new City("pandemic","madrid",550,260, new String[]{"newyork","london","paris","saopaulo","algiers"},0);
        cities[8] = new City("pandemic","paris",609,237, new String[]{"london","madrid","algiers","milan","essen"},0);
        cities[9] = new City("pandemic","essen",655,180, new String[]{"london","paris","milan","stpetersburg"},0);
        cities[10] = new City("pandemic","milan",679,236, new String[]{"istanbul","paris","essen"},0);
        cities[11] = new City("pandemic","stpetersburg",738,170, new String[]{"essen","istanbul","moscow"},0);
        cities[12] = new City("pandemic","losangeles",143,332, new String[]{"sanfrancisco","sydney","mexicocity","chicago"},1);
        cities[13] = new City("pandemic","mexicocity",196,375, new String[]{"losangeles","chicago","miami","bogota","lima"},1);
        cities[14] = new City("pandemic","miami",299,351, new String[]{"mexicocity","atlanta","washington","bogota"},1);
        cities[15] = new City("pandemic","bogota",289,423, new String[]{"maxicocity","miami","lima","saopaulo","buenosaires"},1);
        cities[16] = new City("pandemic","lima",299,496, new String[]{"mexicocity","bogota","santiago"},1);
        cities[17] = new City("pandemic","santiago",311,602, new String[]{"lima"},1);
        cities[18] = new City("pandemic","buenosaires",371,590, new String[]{"bogota","saopaulo"},1);
        cities[19] = new City("pandemic","saopaulo",416,524, new String[]{"buenosaires","bogota","lagos","madrid"},1);
        cities[20] = new City("pandemic","lagos",591,386, new String[]{"saopaulo","kinshasa","khartoum"},1);
        cities[21] = new City("pandemic","kinshasa",667,451, new String[]{"lagos","johannesburg","khartoum"},1);
        cities[22] = new City("pandemic","johannesburg",729,540, new String[]{"kinshasa","khartoum"},1);
        cities[23] = new City("pandemic","khartoum",730,363, new String[]{"lagos","kinshasa","johannesburg","cairo"},1);
        cities[24] = new City("pandemic","algiers",616,292, new String[]{"istanbul","cairo","madrid","paris"},2);
        cities[25] = new City("pandemic","istanbul",729,235, new String[]{"algiers","cairo","milan","baghdad","moscow","stpetersburg"},2);
        cities[26] = new City("pandemic","cairo",714,313, new String[]{"algiers","riyadh","baghdad","istanbul"},2);
        cities[27] = new City("pandemic","moscow",782,215, new String[]{"stpetersburg","istanbul","tehran"},2);
        cities[28] = new City("pandemic","tehran",845,244, new String[]{"moscow","baghdad","karachi","delhi"},2);
        cities[29] = new City("pandemic","baghdad",780,283, new String[]{"istanbul","cairo","riyadh","karachi","tehran"},2);
        cities[30] = new City("pandemic","riyadh",800,350, new String[]{"cairo","baghdad","karachi"},2);
        cities[31] = new City("pandemic","karachi",878,303, new String[]{"riyadh","baghdad","tehran","delhi","mumbai"},2);
        cities[32] = new City("pandemic","mumbai",919,347, new String[]{"karachi","delhi","chennai"},2);
        cities[33] = new City("pandemic","delhi",939,280, new String[]{"tehran","karachi","mumbai","chennai","kolkata"},2);
        cities[34] = new City("pandemic","kolkata",986,318, new String[]{"delhi","chennai","bangkok","hongkong"},2);
        cities[35] = new City("pandemic","chennai",950,395, new String[]{"mumbai","delhi","kolkata","bangkok","jakarta"},2);
        cities[36] = new City("pandemic","bangkok",1040,369, new String[]{"kolkata","chennai","jakarta","hochiminhcity","hongkong"},3);
        cities[37] = new City("pandemic","jakarta",1050,460, new String[]{"chennai","bangkok","hochiminhcity","sydney"},3);
        cities[38] = new City("pandemic","sydney",1251,567, new String[]{"jakarta","manila","losangeles"},3);
        cities[39] = new City("pandemic","manila",1140,375, new String[]{"hochiminhcity","sydney","hongkong","taipei","sanfrancisco"},3);
        cities[40] = new City("pandemic","hochiminhcity",1085,420, new String[]{"bangkok","jakarta","manila","hongkong"},3);
        cities[41] = new City("pandemic","hongkong",1085,325, new String[]{"kolkata","bangkok","hochiminhcity","manila","taipei","shanghai"},3);
        cities[42] = new City("pandemic","shanghai",1070,270, new String[]{"hongkong","taipei","tokyo","seoul","beijing"},3);
        cities[43] = new City("pandemic","beijing",1070,210, new String[]{"shanghai","seoul"},3);
        cities[44] = new City("pandemic","seoul",1164,236, new String[]{"beijing","shanghai","tokyo"},3);
        cities[45] = new City("pandemic","tokyo",1240,263, new String[]{"seoul","shanghai","osaka"},3);
        cities[46] = new City("pandemic","osaka",1178,290, new String[]{"tokyo","taipei"},3);
        cities[47] = new City("pandemic","taipei",1190,340, new String[]{"hongkong","shanghai","osaka"},3);

        for (int i = 0; i < cities.length; i++) {
            cities[i].init(gc);
        }



    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        g.drawImage(gameBoard, 0,0);

        //cities[1].placeCube("blue", 3);

        for (int i = 0; i < cities.length; i++) {
            cities[i].render(gc,g);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        //cities[0].placeCube("blue",2);
        //cities[0].update(gc,i);
        cities[0].setCubeBlue(2);
        cities[4].setCubeBlack(2);
        cities[28].setCubeYellow(3);
        cities[10].setCubeRed(1);
    }
}
