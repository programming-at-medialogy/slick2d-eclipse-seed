
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {

	HexMap map;
	HouseClickArea houseArea;
	RoadClickArea roadArea;
	OnScreenButton buttoms;
	OnScreenTextField textField;
	
	public Card cardHelp;
	public Card[] developmentPile = new Card[25];
	
	
	public Play(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		map = new HexMap();
		houseArea = new HouseClickArea();
		roadArea = new RoadClickArea();
		buttoms = new OnScreenButton();
		textField = new OnScreenTextField();
		textField.create(gc);
		
		cardHelp = new Card();
		cardHelp.createDevPile(developmentPile);
		Collections.shuffle(Arrays.asList(developmentPile));
		/*for(int i = 0; i < developmentPile.length; i++){
			System.out.println(developmentPile[i]);
		}*/
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		map.render(gc, g);
		roadArea.render(gc, g);
		houseArea.render(gc, g);
		buttoms.render(gc, g);
		textField.render(gc, g);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

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
				synchronized (textField) {
					try {
						textField.update(gc, delta);
					} catch (SlickException e) {
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
