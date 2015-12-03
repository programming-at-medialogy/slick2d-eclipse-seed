import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {

	HexMap map;
	Game game;
	SettlementSpawn houseArea;
	RoadClickArea roadArea;
	Controller control;
	DieRoll die;
	OnScreenButton buttons;
	int VPWinAmount = 150;
	
	public Card cardHelp;
	public Card infoCard;
	public Card[] developmentPile = new Card[25];
	
	
	public Play(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		control = new Controller();
		map = new HexMap(control);
		die = new DieRoll(control,1000,50);
		houseArea = new SettlementSpawn(control);
		roadArea = new RoadClickArea(control);
		buttons = new OnScreenButton(control);
		
		infoCard = new Card();
		infoCard.cardType = new Image ("images/info.png");
		infoCard.x = 990;
		infoCard.y = 170;
		cardHelp = new Card();
		cardHelp.createDevPile(developmentPile);
		Collections.shuffle(Arrays.asList(developmentPile));
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		map.render(gc, g);
		roadArea.render(gc, g);
		houseArea.render(gc, g);
		die.render(gc, g);
		buttons.render(gc, g);
		infoCard.render(gc, g);
		control.resources.render(gc, g);

	}
	
	

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		try {
			control.update(gc, delta);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if(game.client.obj.roundCount == 0 || control.resources.victoryPoint >= VPWinAmount){
			sbg.enterState(2);
		}

		Thread t1 = new Thread() {
			@Override
			public void run() {
				synchronized (houseArea) {
					try {
						houseArea.update(gc, delta);
					} catch (SlickException e) {
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		};

		Thread t2 = new Thread() {
			@Override
			public void run() {
				synchronized (roadArea) {
					try {
						roadArea.update(gc, delta);
					} catch (SlickException e) {
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		Thread t3 = new Thread(){
			@Override
			public void run() {
				synchronized (die) {
					try {
						die.update(gc, delta);
					} catch (SlickException | IOException e) {
						//e.printStackTrace();
					}
				}	
			}
			
		};

		t1.start();
		t2.start();
		t3.start();

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
		}

	}

	public int getID() {
		return 1;
	}

}
