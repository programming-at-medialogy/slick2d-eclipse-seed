import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class WinCondition extends BasicGameState{
	
	Game game;
	int spacing = 20;
	
	WinCondition(int state){

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		findWinner(g);
		
		/////////Victory points leaderboard/////////////////////////////////////////////
		g.drawString("Player one has: "+game.client.obj.playerVictoryPoints[0][0] + " Victory Points!", 500, 500);
		g.drawString("Player two has: "+game.client.obj.playerVictoryPoints[1][0] + " Victory Points!", 500, 500 + spacing);
		g.drawString("Player three has: "+game.client.obj.playerVictoryPoints[2][0] + " Victory Points!", 500, 500 + spacing * 2);
		g.drawString("Player four has: "+game.client.obj.playerVictoryPoints[3][0] + " Victory Points!", 500, 500 + spacing * 3);
		////////////////////////////////////////////////////////////////////////////////
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	public void findWinner(Graphics g){
		int max = Integer.MIN_VALUE;
		int playerNumber = 0;
		for(int i = 0 ; i < 4; i++){
			if(game.client.obj.playerVictoryPoints[i][0] > max){
				max = game.client.obj.playerVictoryPoints[i][0];
				playerNumber = i+1;
			}
		}
		System.out.println(max);
		System.out.println("Player " +playerNumber + " won!");
		g.drawString("Player " + playerNumber + " is the winner!", 500, 600);
	}

}