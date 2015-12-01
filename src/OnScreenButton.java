
import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class OnScreenButton {

	int screenWidth = 1200;
	int screenHeight = 700;

	Controller control;
	Game game;

	OnScreenButtonSpawn buttonPlaceHouse;
	OnScreenButtonSpawn buttonPlaceRoad;
	OnScreenButtonSpawn buttonPlaceDice;
	OnScreenButtonSpawn buttonPlaceEndturn;

	boolean buttonRoadControl = false;
	boolean buttonHouseControl = false;
	boolean buttonTurnControl = false;
	boolean buttonDiceControl = false;
	
	int buttonHeight = 56;
	int buttonWidth = 97;

	int buttonStartPosX = screenWidth - buttonWidth - 20;
	int buttonStartPosY = screenHeight - buttonHeight - 20;
	int buttonSpacing = 65;

	OnScreenButton() throws SlickException {

		control = new Controller();

		buttonPlaceHouse = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY, 1);
		buttonPlaceRoad = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY - buttonSpacing, 2);
		buttonPlaceDice = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY - (buttonSpacing * 2), 3);
		buttonPlaceEndturn = new OnScreenButtonSpawn(buttonStartPosX, buttonStartPosY - (buttonSpacing * 3), 4);
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {

		buttonPlaceHouse.render(gc, g);
		buttonPlaceRoad.render(gc, g);
		buttonPlaceDice.render(gc, g);
		buttonPlaceEndturn.render(gc, g);

	}

	public void update(GameContainer gc, int i) throws SlickException {

		int xMousePos = Mouse.getX(); // gets x position of mouse
		int yMousePos = Mouse.getY(); // gets y position of mouse

		Input input = gc.getInput();

		// ButtomHouse
		if ((xMousePos > buttonStartPosX && xMousePos < buttonStartPosX + buttonWidth)
				&& (yMousePos < screenHeight - buttonStartPosY
				&& yMousePos > screenHeight - buttonStartPosY - buttonHeight)) {
			if (input.isMouseButtonDown(0)) {
				buttonHouseControl = true;
				buttonRoadControl = false;
			}
		}

		// ButtomRoad
		if ((xMousePos > buttonStartPosX && xMousePos < buttonStartPosX + buttonWidth)
				&& (yMousePos < screenHeight - buttonStartPosY + buttonSpacing
				&& yMousePos > screenHeight - buttonStartPosY + buttonSpacing - buttonHeight)) {
			if (input.isMouseButtonDown(0)) {
				buttonRoadControl = true;
				buttonHouseControl = false;
			}
		}
		// ButtomDice
		if ((xMousePos > buttonStartPosX && xMousePos < buttonStartPosX + buttonWidth)
				&& (yMousePos < screenHeight - buttonStartPosY + (buttonSpacing * 2)
				&& yMousePos > screenHeight - buttonStartPosY + (buttonSpacing * 2) - buttonHeight)) {
			if (input.isMouseButtonDown(0)) {
				buttonDiceControl = true;
			}
		}

		// ButtomEndTurn
		if ((xMousePos > buttonStartPosX && xMousePos < buttonStartPosX + buttonWidth)
				&& (yMousePos < screenHeight - buttonStartPosY + buttonSpacing * 3
				&& yMousePos > screenHeight - buttonStartPosY + buttonSpacing * 3 - buttonHeight)) {
			if (input.isMouseButtonDown(0)) {
				

			}
		}
	}
}
