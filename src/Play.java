
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {

	HexMap map;
	HouseClickArea houseArea;
	RoadClickArea roadArea;
	OnScreenButton buttoms;

	public Play(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		map = new HexMap();
		houseArea = new HouseClickArea();
		roadArea = new RoadClickArea();
		buttoms = new OnScreenButton();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		map.render(gc, g);
		roadArea.render(gc, g);
		houseArea.render(gc, g);
		buttoms.render(gc, g);

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
					}
				}
			}
		};

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
		}

	}

	public int getID() {
		return 1;
	}

}
