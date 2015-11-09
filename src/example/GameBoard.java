package example;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Marianne on 05-11-2015.
 */
public class GameBoard extends BasicGameState {

    City[] cities;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
    cities = new City[48];
        City atlanta = new City("pandemic","atlanta",215,296, new String[]{"miami","washington","chicago"});
        City chicago = new City("pandemic","chicago",189,243, new String[]{"atlanta", "sanfrancisco","montreal"});
        City sanfrancisco = new City("pandemic", "sanfrancisco",93,272, new String[]{"losangeles","chicago","tokyo","manila"});
        City montreal = new City("pandemic","montreal",264,239, new String[]{"chicago","washington","newyork"});
        City newyork = new City("pandemic","newyork",323,249, new String[]{"montreal","washington","madrid","london"});
        City washington = new City("pandemic","washington",298,296, new String[]{"newyork","miami","atlanta","montreal"});
        City london = new City("pandemic","london",463,206, new String[]{"newyork","madrid","paris","essen"});
        City madrid = new City("pandemic","madrid",453,279, new String[]{"newyork","london","paris","saopaulo","algiers"});
        City paris = new City("pandemic","paris",520,241, new String[]{"london","madrid","algiers","milan","essen"});
        City essen = new City("pandemic","essen",539,192, new String[]{"london","paris","milan","stpetersburg"});
        City milan = new City("pandemic","milan",572,227, new String[]{"istanbul","paris","essen"});
        City stpetersburg = new City("pandemic","stpetersburg",622,175, new String[]{"essen","istanbul","moscow"});
        City losangeles = new City("pandemic","losangeles",106,351, new String[]{"sanfrancisco","sydney","mexicocity","chicago"});
        City mexicocity = new City("pandemic","mexicocity",176,374, new String[]{"losangeles","chicago","miami","bogota","lima"});
        City miami = new City("pandemic","miami",265,361, new String[]{"mexicocity","atlanta","washington","bogota"});
        City bogota = new City("pandemic","bogota",260,438, new String[]{"maxicocity","miami","lima","saopaulo","buenosaires"});
        City lima = new City("pandemic","lima",231,521, new String[]{"mexicocity","bogota","santiago"});
        City santiago = new City("pandemic","santiago",241,603, new String[]{"lima"});
        City buenosaires = new City("pandemic","buenosaires",322,592, new String[]{"bogota","saopaulo"});
        City saopaulo = new City("pandemic","saopaulo",365,532, new String[]{"buenosaires","bogota","lagos","madrid"});
        City lagos = new City("pandemic","lagos",513,424, new String[]{"saopaulo","kinshasa","khartoum"});
        City kinshasa = new City("pandemic","kinshasa",560,478, new String[]{"lagos","johannesburg","khartoum"});
        City johannesburg = new City("pandemic","johannesburg",604,555, new String[]{"kinshasa","khartoum"});
        City khartoum = new City("pandemic","khartoum",610,410, new String[]{"lagos","kinshasa","johannesburg","cairo"});
        City algiers = new City("pandemic","algiers",537,321, new String[]{"istanbul","cairo","madrid","paris"});
        City istanbul = new City("pandemic","istanbul",604,272, new String[]{"algiers","cairo","milan","baghdad","moscow","stpetersburg"});
        City cairo = new City("pandemic","cairo",594,335, new String[]{"algiers","riyadh","baghdad","istanbul"});
        City moscow = new City("pandemic","moscow",665,226, new String[]{"stpetersburg","istanbul","tehran"});
        City tehran = new City("pandemic","tehran",716,263, new String[]{"moscow","baghdad","karachi","delhi"});
        City baghdad = new City("pandemic","baghdad",659,308, new String[]{"istanbul","cairo","riyadh","karachi","tehran"});
        City riyadh = new City("pandemic","riyadh",667,379, new String[]{"cairo","baghdad","karachi"});
        City karachi = new City("pandemic","karachi",731,335, new String[]{"riyadh","baghdad","tehran","delhi","mumbai"});
        City mumbai = new City("pandemic","mumbai",738,390, new String[]{"karachi","delhi","chennai"});
        City delhi = new City("pandemic","delhi",787,313, new String[]{"tehran","karachi","mumbai","chennai","kolkata"});
        City kolkata = new City("pandemic","kolkata",841,332, new String[]{"delhi","chennai","bangkok","hongkong"});
        City chennai = new City("pandemic","chennai",797,433, new String[]{"mumbai","delhi","kolkata","bangkok","jakarta"});
        City bangkok = new City("pandemic","bangkok",851,398, new String[]{"kolkata","chennai","jakarta","hochiminhcity","hongkong"});
        City jakarta = new City("pandemic","jakarta",851,497, new String[]{"chennai","bangkok","hochiminhcity","sydney"});
        City sydney = new City("pandemic","sydney",1016,602, new String[]{"jakarta","manila","losangeles"});
        City manila = new City("pandemic","manila",972,447, new String[]{"hochiminhcity","sydney","hongkong","taipei","sanfrancisco"});
        City hochiminhcity = new City("pandemic","hochiminhcity",897,451, new String[]{"bangkok","jakarta","manila","hongkong"});
        City hongkong = new City("pandemic","hongkong",895,363, new String[]{"kolkata","bangkok","hochiminhcity","manila","taipei","shanghai"});
        City shanghai = new City("pandemic","shanghai",888,296, new String[]{"hongkong","taipei","tokyo","seoul","beijing"});
        City beijing = new City("pandemic","beijing",880,247, new String[]{"shanghai","seoul"});
        City seoul = new City("pandemic","seoul",950,243, new String[]{"beijing","shanghai","tokyo"});
        City tokyo = new City("pandemic","tokyo",1004,271, new String[]{"seoul","shanghai","osaka"});
        City osaka = new City("pandemic","osaka",1011,327, new String[]{"tokyo","taipei"});
        City taipei = new City("pandemic","taipei",955,353, new String[]{"hongkong","shanghai","osaka"});


    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
