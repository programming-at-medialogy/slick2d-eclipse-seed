import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class WinCondition extends BasicGameState{
	
	Game game;
	Font awtfont;
	TrueTypeFont font;
	int spacing = 60;
	Image winBG;
	Color color = new Color(67, 175, 35);
	
	WinCondition(int state){

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		 awtfont = new Font("Times New Roman", Font.BOLD, 42);
		 font = new TrueTypeFont(awtfont, false);
		 winBG = new Image("images/WinnerBanner.png");

		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawImage(winBG,0,0);
		/////////Victory points leaderboard/////////////////////////////////////////////
		g.drawString("Player one has: "+game.client.obj.playerVictoryPoints[0][0] + " Victory Points!", 450, 330);
		g.drawString("Player two has: "+game.client.obj.playerVictoryPoints[1][0] + " Victory Points!", 450, 330 + spacing);
		g.drawString("Player three has: "+game.client.obj.playerVictoryPoints[2][0] + " Victory Points!", 450, 330 + spacing * 2);
		g.drawString("Player four has: "+game.client.obj.playerVictoryPoints[3][0] + " Victory Points!", 450, 330 + spacing * 3);
		////////////////////////////////////////////////////////////////////////////////
		
		findWinner(g);

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

		g.setFont(font);
		font.drawString(400, 150, "Player " + playerNumber + " is the winner!",color);
		
	}
}

