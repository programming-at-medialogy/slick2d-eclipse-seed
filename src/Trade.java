import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Trade {
	Controller control;
	Game game;
	Image ore, clay, wood, wool, wheat, oreBank, clayBank, woodBank, woolBank, wheatBank;
	int x;
	int y;
	int xSpacing;
	int ySpacing;
	boolean p_ore, p_clay, p_wood, p_wool, p_wheat, b_ore, b_clay, b_wood, b_wool, b_wheat;
	boolean [][] playerResource;
	
	Trade(Controller control) throws SlickException{
		this.control = control;
		ore = new Image("images/ore.png");
        clay = new Image("images/clay.png");
        wood = new Image("images/wood.png");
        wool = new Image("images/wool.png");
        wheat = new Image("images/wheat.png");
        oreBank = new Image("images/ore.png");
        clayBank = new Image("images/clay.png");
        woodBank = new Image("images/wood.png");
        woolBank = new Image("images/wool.png");
        wheatBank = new Image("images/wheat.png");
        playerResource = new boolean[5][2];
        x = 680;
        y = 635;
        xSpacing = 55;
        ySpacing = 55;
        p_ore = true;
        b_ore = true;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		if(control.tradeButtonControl == true){
		g.drawImage(oreBank, x, y);
        g.drawImage(clayBank, x+xSpacing, y);
        g.drawImage(woodBank, x+xSpacing*2, y);
        g.drawImage(woolBank, x+xSpacing*3, y);
        g.drawImage(wheatBank, x+xSpacing*4, y);
        g.drawImage(ore, x, y - ySpacing);
        g.drawImage(clay, x+xSpacing, y - ySpacing);
        g.drawImage(wood, x+xSpacing*2, y - ySpacing);
        g.drawImage(wool, x+xSpacing*3, y - ySpacing);
        g.drawImage(wheat, x+xSpacing*4, y - ySpacing);
		}
	}
	
	
	public void update(GameContainer gc, int i) throws SlickException, IOException {
		
		if(control.tradeButtonControl == true){
		for (int k = 0; k < 5; k++) {
			if (playerResource[k][0]) {
				for (int j = 0; j < 5; j++) {
					if (playerResource[j][1]) {
						if(game.client.obj.playerResource[control.playerNo - 1][k]>=4){
						game.client.obj.playerResource[control.playerNo - 1][j]++;
						game.client.obj.playerResource[control.playerNo - 1][k]-=4;
						game.client.sendData(game.client.obj);
						}
						control.deselectButtonControl = false;
						control.tradeButtonControl = false;
						playerResource[k][0] = false;
						playerResource[j][1] = false;
					}
				}
			}
		}
		}
	}

}