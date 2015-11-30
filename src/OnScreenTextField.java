import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;

public class OnScreenTextField {

	TextField textField, textField2, textField3, textField4;
	OnScreenButton diceButton;
	Controller control;
	DieRoll dice;

	public OnScreenTextField() throws SlickException {
		diceButton = new OnScreenButton();
		control = new Controller();
		dice = new DieRoll();
	}

	public void create(GameContainer gc) {

		textField = new TextField(gc, gc.getDefaultFont(), 990,10,200,35);
		textField.setBorderColor(Color.green);
		textField.setBackgroundColor(Color.gray);
		
		textField2 = new TextField(gc, gc.getDefaultFont(), 990,50,200,35);
		textField2.setBorderColor(Color.red);
		textField2.setBackgroundColor(Color.gray);
		
		textField3 = new TextField(gc, gc.getDefaultFont(), 990,90,200,35);
		textField3.setBorderColor(Color.blue);
		textField3.setBackgroundColor(Color.gray);
		
		textField4 = new TextField(gc, gc.getDefaultFont(), 990,130,200,35);
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

	public void render(GameContainer gc, Graphics g) throws SlickException {
		textField.render(gc, g);
		textField2.render(gc, g);
		textField3.render(gc, g);
		textField4.render(gc, g);
	}

	public void update(GameContainer gc, int i) throws SlickException {
		diceButton.update(gc, i);
		int once = 0;

		if (control.isPlayerTurn == true && diceButton.buttonDiceControl == true) {
			if (once < 1) {
				writeDiceToConsole();
				once++;
				diceButton.buttonDiceControl = false;
			}
		}
		// Next lines does not currently work, since the game does not have turn based gameplay yet
		/*if(control.endPlayerTurn() == true){
			once = 0;
		}*/
	}
}
