//importing libs
import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
//import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
//import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;

public abstract class Button {
	//class variables 
	int x, y, width, height;
	Image image, highlight, pressed;
	String message;
	static boolean mouseDown;
	static ArrayList<Button> buttons = new ArrayList();
	BasicGameState state;
	
	static int yPos, xPos;

	//constructor
	Button(int x, int y, int width, int height, String message, BasicGameState state){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.message = message;
		try {
			this.image = new Image("resources/ButtonHover.png");
			this.highlight = new Image("resources/ButtonActive.png");
			this.pressed = new Image("resources/ButtonPressed.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		mouseDown = false;
		buttons.add(this);
		this.state = state;
	}

	//drawing the buttons
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
				Resource.buttonFont.drawString(buttons.get(i).x + buttons.get(i).width / 2 - Resource.buttonFont.getWidth(buttons.get(i).message) / 2, buttons.get(i).y + buttons.get(i).height / 2 - Resource.buttonFont.getHeight(buttons.get(i).message) / 2, buttons.get(i).message);
				//buttonFont.drawString(Windows.scWidth - buttonFont.getA buttonFont.getWidth(buttons.get(i).message) / 2, 250, buttons.get(i).message);
			}
		}
	}
	
	//updating the mouse position and lets us know whether the mouse is clicked or not
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
	
	//abstract class that lets us adjust what action should be performed when the mouse is clicked
	public abstract void isClicked();	
}
