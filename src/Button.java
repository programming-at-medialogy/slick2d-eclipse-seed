//importing libs
import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
//import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
//import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;

public abstract class Button {
	//class variables 
	int x, y, width, height, fontSize;
	static Image image, highlight, pressed;
	String message;
	static boolean mouseDown;
	static ArrayList<Button> buttons = new ArrayList();
	BasicGameState state;
	TrueTypeFont buttonFont;
	
	static int yPos, xPos;

/**
 * Constructor for the button class
 * @param x the parameter for button x position
 * @param y the parameter for button y position
 * @param width the parameter for button width
 * @param height the parameter for button height
 * @param message the parameter for the text layer onto the button
 * @param state the parameter for which state the button belongs to
 */
	Button(int x, int y, int width, int height, int fontSize, String message, BasicGameState state){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.message = message;
		//this.fontSize = fontSize;
		
		buttonFont = Resource.getFont("std", fontSize);
		
		try {
			this.image = new Image("resources/btImg_0.png");
			this.highlight = new Image("resources/btImg_1.png");
			this.pressed = new Image("resources/btImg_2.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		mouseDown = false;
		buttons.add(this);
		this.state = state;
	}

	/**
	 * Method for drawing all button as either 
	 * untouched, pressed, or highlighted for 
	 * dynamic gaming experience 
	 * @param g is the graphics component
	 * which actually draws the object
	 * @param state is whichever state the specific button
	 * should be drawn. It will only be drawn on that 
	 * specific GameState
	 */
	public static void draw(Graphics g, BasicGameState state){
		for(int i = 0; i < buttons.size(); i++){
			if (buttons.get(i).state == state){
				if((xPos > buttons.get(i).x && xPos < buttons.get(i).x + buttons.get(i).width)&& (yPos > buttons.get(i).y && yPos < buttons.get(i).y + buttons.get(i).height) && mouseDown == true){
					buttons.get(i).highlight.draw(buttons.get(i).x, buttons.get(i).y, buttons.get(i).width, buttons.get(i).height);
				}
				else if ((xPos > buttons.get(i).x && xPos < buttons.get(i).x + buttons.get(i).width)&& (yPos > buttons.get(i).y && yPos < buttons.get(i).y + buttons.get(i).height)) {
					buttons.get(i).pressed.draw(buttons.get(i).x, buttons.get(i).y, buttons.get(i).width, buttons.get(i).height);
				} else {
					buttons.get(i).image.draw(buttons.get(i).x, buttons.get(i).y, buttons.get(i).width, buttons.get(i).height);
				}
				buttons.get(i).buttonFont.drawString(buttons.get(i).x + buttons.get(i).width / 2 - buttons.get(i).buttonFont.getWidth(buttons.get(i).message) / 2, buttons.get(i).y + buttons.get(i).height / 2 - buttons.get(i).buttonFont.getHeight(buttons.get(i).message) / 2, buttons.get(i).message, new Color(100, 54, 26));
			}
		}
	}
	/**
	 * updating the mouse position and lets us know 
	 * whether the mouse is clicked or not
	 * @param state is the current state the player is in. 
	 * which lets the system know only to update 
	 * whatever is in the current state and nothing else
	 */
	public static void update(BasicGameState state){
		xPos = Mouse.getX();
		yPos = Windows.scHeight - Mouse.getY();
		for(int i = 0; i < buttons.size(); i++){
			if((xPos > buttons.get(i).x && xPos < buttons.get(i).x + buttons.get(i).width)&& (yPos > buttons.get(i).y && yPos < buttons.get(i).y + buttons.get(i).height)){
				if(mouseDown == true && Mouse.isButtonDown(0) != true) {
					if (buttons.get(i).state == state)
						buttons.get(i).isClicked();
				}
			}
		}	
		if(Mouse.isButtonDown(0))
			mouseDown = true;
		else
			mouseDown = false;
	}
	/**
	 * abstract class that lets us adjust 
	 * what action should be performed when the mouse is clicked
	 * Allows for a very flexible button interaction
	 * is build whenever a button declaration is committed
	 */
	
	public abstract void isClicked();	
}
