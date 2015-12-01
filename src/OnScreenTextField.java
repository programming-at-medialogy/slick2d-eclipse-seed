import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;

public class OnScreenTextField {

	private int once = 0;
	TextField textField, textField2, textField3, textField4;
	private int textFieldXPos = 970;
	private int textFieldYPos = 10;
	private int textFieldYOffset = 40;
	private int textFieldWidth = 220;
	private int textFieldHeight = 35;
	OnScreenButton buttons;
	Controller control;
	DieRoll dice;
	Card cardHelp;
	Card[] developmentPile = new Card[25];
	Card pileOutput;

	public OnScreenTextField() throws SlickException {
		buttons = new OnScreenButton(control);
		control = new Controller();
		dice = new DieRoll();
		cardHelp = new Card();
		pileOutput = new Card();
		cardHelp.createDevPile(developmentPile);
		Collections.shuffle(Arrays.asList(developmentPile));
	}

	public void create(GameContainer gc) {

		textField = new TextField(gc, gc.getDefaultFont(), textFieldXPos,textFieldYPos,textFieldWidth,textFieldHeight);
		textField.setBorderColor(Color.green);
		textField.setBackgroundColor(Color.gray);
		
		textField2 = new TextField(gc, gc.getDefaultFont(), textFieldXPos,textFieldYPos+(textFieldYOffset),textFieldWidth,textFieldHeight);
		textField2.setBorderColor(Color.red);
		textField2.setBackgroundColor(Color.gray);
		
		textField3 = new TextField(gc, gc.getDefaultFont(), textFieldXPos,textFieldYPos+(textFieldYOffset*2),textFieldWidth,textFieldHeight);
		textField3.setBorderColor(Color.blue);
		textField3.setBackgroundColor(Color.gray);
		
		textField4 = new TextField(gc, gc.getDefaultFont(), textFieldXPos,textFieldYPos+(textFieldYOffset*3),textFieldWidth,textFieldHeight);
		textField4.setBorderColor(Color.magenta);
		textField4.setBackgroundColor(Color.gray);
	}

	public void writeDiceToConsole() {
		//depending on how we recognize players, this much change accordingly
		if (control.playerNo == 1){
			textField.setConsumeEvents(true);
			textField.setText(Integer.toString(dice.rollDice()));
			textField.getText();
		}else if (control.playerNo == 2){
			textField2.setConsumeEvents(true);
			textField2.setText(Integer.toString(dice.rollDice()));
			textField2.getText();
		}else if (control.playerNo == 3){
			textField3.setConsumeEvents(true);
			textField3.setText(Integer.toString(dice.rollDice()));
			textField3.getText();
		}else if (control.playerNo == 4){
			textField4.setConsumeEvents(true);
			textField4.setText(Integer.toString(dice.rollDice()));
			textField4.getText();
		}
		
	}
	
	public Card writeBuyToConsole(Card[] input) throws SlickException{
		Card output = new Card();
		int getDice;
		
		if (control.playerNo == 1){
			getDice = dice.rollDice();
			output = input[getDice];
			textField.setConsumeEvents(true);
			textField.setText("Bought development card");
			textField.getText();
			return output;
		}else if (control.playerNo == 2){
			getDice = dice.rollDice();
			output = input[getDice];
			textField2.setConsumeEvents(true);
			textField2.setText("Bought development card");
			textField2.getText();
			return output;
		}else if (control.playerNo == 3){
			getDice = dice.rollDice();
			output = input[getDice];
			textField3.setConsumeEvents(true);
			textField3.setText("Bought development card");
			textField3.getText();
			return output;
		}else if (control.playerNo == 4){
			getDice = dice.rollDice();
			output = input[getDice];
			textField4.setConsumeEvents(true);
			textField4.setText("Bought development card");
			textField4.getText();
			return output;
		}
		
		return null;
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		textField.render(gc, g);
		textField2.render(gc, g);
		textField3.render(gc, g);
		textField4.render(gc, g);
	}

	public void update(GameContainer gc, int i) throws SlickException, IOException {
		buttons.update(gc, i);

		if (buttons.buttonDiceControl == true) {
			if (once < 1) {
				writeDiceToConsole();
				once++;
				buttons.buttonDiceControl = false;
			}
		}
		if (buttons.buttonDevCardControl == true) {
			writeBuyToConsole(developmentPile);
			pileOutput = writeBuyToConsole(developmentPile);
			buttons.buttonDiceControl = false;
		}
		// Next lines does not currently work, since the game does not have turn based gameplay yet
		/*if(control.endPlayerTurn() == true){
			once = 0;
		}*/
	}
}