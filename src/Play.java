import java.io.IOException;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/*
 * This is the playstate class. This is the class that takes care of the actual game
 * It acts as a "main" class in the game phase, and thus controls what classes are called during this
 * It also does multi-threading in the update to lessen usage
 */
public class Play extends BasicGameState {
	
	//Different instances of the different classses
	Game game;
	HexMap map;
	SettlementSpawn settlementArea;
	RoadClickArea roadArea;
	Controller control;
	DieRoll die;
	OnScreenButton buttons;
	OnScreenTextField textField;
	Card infoCard;
	
	//Background image
	Image playBackground;
	
	//Sets the amount of points required to win the game.
	int VPWinAmount = 10;
	
	public Play(int state) {

	}

	//Initializer
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//Initlizes all the classes
		control = new Controller();
		map = new HexMap(control);
		die = new DieRoll(control,1000,50); //1000, 50 is the placement of the dices.
		settlementArea = new SettlementSpawn(control);
		roadArea = new RoadClickArea(control);
		buttons = new OnScreenButton(control);
		textField = new OnScreenTextField(control);
		textField.create(gc);
		infoCard = new Card(control);

		//Gives the reference to the background image
		playBackground = new Image("images/PlayBackground.png");
		infoCard.cardType = new Image ("images/info.png");
		
		//X and Y for placement of the info card.
		infoCard.x = 990;
		infoCard.y = 170;
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		//Renders background image as the first thing, to make sure it is in the back.
		g.drawImage(playBackground, 0,0);

		//Calls the render methods from the different classes.
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
			control.update(gc, delta); //calls the update method from control to keep updating
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		checkWinCondition(sbg); //Updates to see if any player has x-amount of victory points

		/*
		 * Multi-threaded updates from different classes
		 * There are many for-loops etc associated with these
		 * classes and therefore these are multi-threaded
		 */
		Thread t1 = new Thread() {
			@Override
			public void run() {
				synchronized (settlementArea) {
					try {
						settlementArea.update(gc, delta);
					} catch (SlickException e) {
						e.printStackTrace();
					} catch (IOException e) {
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
		
		Thread t4 = new Thread (){
			@Override
			public void run() {
				synchronized (textField) {
					try {
						textField.update(gc, delta);
					} catch (SlickException | IOException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
				}
			}
		};
		
		//Starts all the threads
		t1.start();
		t2.start();
		t3.start();
		t4.start();

		//Joins them to sync any potential data used by the same classes.
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	//A function to check if anyone has reached the required amount of victory points (VP points)
	public void checkWinCondition(StateBasedGame sbg){
		for(int j = 0; j < game.client.obj.playerVictoryPoints.length; j++){
			if(game.client.obj.playerVictoryPoints[j][0] >= VPWinAmount){
				sbg.enterState(2);
			}
		}
	}

	//The state ID of the play class
	public int getID() {
		return 1;
	}

}