import java.io.IOException;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {

	HexMap map;
	Game game;

	SettlementSpawn settlementArea;
	RoadClickArea roadArea;
	Controller control;
	DieRoll die;
	OnScreenButton buttons;
	int VPWinAmount = 150;
	OnScreenTextField textField;
	Image playBackground;
	public Card infoCard;
	
	
	public Play(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		control = new Controller();
		map = new HexMap(control);
		die = new DieRoll(control,1000,50);
		settlementArea = new SettlementSpawn(control);
		roadArea = new RoadClickArea(control);
		buttons = new OnScreenButton(control);
		textField = new OnScreenTextField(control);
		textField.create(gc);

		
		playBackground = new Image("images/PlayBackground.png");
		
		infoCard = new Card(control);
		infoCard.cardType = new Image ("images/info.png");
		infoCard.x = 990;
		infoCard.y = 170;
		/*cardHelp = new Card();
		cardHelp.createDevPile(developmentPile);
		Collections.shuffle(Arrays.asList(developmentPile));*/
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		g.drawImage(playBackground, 0,0);
		

		buttons.render(gc, g);
		map.render(gc, g);
		roadArea.render(gc, g);
		settlementArea.render(gc, g);
		die.render(gc, g);
		infoCard.render(gc, g);
		control.resources.render(gc, g);
		textField.render(gc, g);

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
				synchronized (settlementArea) {
					try {
						settlementArea.update(gc, delta);
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
						//textField.update(gc, delta);
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