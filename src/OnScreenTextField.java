import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;

public class OnScreenTextField {

	TextField textField, textField2, textField3, textField4;
	private int textFieldXPos = 970;
	private int textFieldYPos = 10;
	private int textFieldYOffset = 40;
	private int textFieldWidth = 220;
	private int textFieldHeight = 35;
	OnScreenButton relButton;
	Controller control;
	Game game;
	DieRoll dice;
	Card cardHelp;
	Card[] developmentPile = new Card[25];
	Card pileOutput;

	public OnScreenTextField(Controller control) throws SlickException {
		this.control = control;
		relButton = new OnScreenButton(control);
		//dice = new DieRoll();
		cardHelp = new Card(control);
		pileOutput = new Card(control);
		cardHelp.createDevPile(developmentPile);
		Collections.shuffle(Arrays.asList(developmentPile));

	}

	public void create(GameContainer gc) {

		textField = new TextField(gc, gc.getDefaultFont(), textFieldXPos, textFieldYPos, textFieldWidth,
				textFieldHeight);
		textField.setBorderColor(Color.green);
		textField.setBackgroundColor(Color.gray);

		textField2 = new TextField(gc, gc.getDefaultFont(), textFieldXPos, textFieldYPos + (textFieldYOffset),
				textFieldWidth, textFieldHeight);
		textField2.setBorderColor(Color.red);
		textField2.setBackgroundColor(Color.gray);

		textField3 = new TextField(gc, gc.getDefaultFont(), textFieldXPos, textFieldYPos + (textFieldYOffset * 2),
				textFieldWidth, textFieldHeight);
		textField3.setBorderColor(Color.blue);
		textField3.setBackgroundColor(Color.gray);

		textField4 = new TextField(gc, gc.getDefaultFont(), textFieldXPos, textFieldYPos + (textFieldYOffset * 3),
				textFieldWidth, textFieldHeight);
		textField4.setBorderColor(Color.magenta);
		textField4.setBackgroundColor(Color.gray);
	}

	public void writeBuyToConsole(Card[] input) throws SlickException {
		Card output = new Card(control);
		Random rand = new Random();

		if (control.playerNo == 1) {
			output = input[rand.nextInt(input.length)];
			textField.setConsumeEvents(true);
			textField.setText(output.effectline);
			textField.getText();
			pileOutput = output;
		} else if (control.playerNo == 2) {
			output = input[rand.nextInt(input.length)];
			textField2.setConsumeEvents(true);
			textField2.setText(output.effectline);
			textField2.getText();
			pileOutput = output;
		} else if (control.playerNo == 3) {
			output = input[rand.nextInt(input.length)];
			textField3.setConsumeEvents(true);
			textField3.setText(output.effectline);
			textField3.getText();
			pileOutput = output;
		} else if (control.playerNo == 4) {
			output = input[rand.nextInt(input.length)];
			textField4.setConsumeEvents(true);
			textField4.setText(output.effectline);
			textField4.getText();
			pileOutput = output;
		}
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		textField.render(gc, g);
		textField2.render(gc, g);
		textField3.render(gc, g);
		textField4.render(gc, g);
	}

	public void update(GameContainer gc, int i) throws SlickException, IOException {
		relButton.update(gc, i);

		if (control.devCardButtonClicked == true) {
			if (game.client.obj.playerTurn == control.playerNo) {
				if (cardHelp.checkDevCardCost() == true) {
					cardHelp.buyCard();
					writeBuyToConsole(developmentPile);
					pileOutput.effect();
					control.devCardButtonClicked = false;
					
				}
			}	
		}
	}
}