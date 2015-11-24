//importing libs
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;

public abstract class Button {
	//class variables 
	int x, y, width, height;
	Image image;
	static boolean mouseDown;
	static ArrayList<Button> buttons = new ArrayList();
	BasicGameState state;

	//constructor
	Button(int x, int y, Image image, BasicGameState state){
		this.x = x;
		this.y = y;
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.image = image;
		mouseDown = false;
		buttons.add(this);
		this.state = state;
	}

	//drawing the buttons
	public static void draw(Graphics g, BasicGameState state){
		for(int i = 0; i < buttons.size(); i++){
			if (buttons.get(i).state == state)
				buttons.get(i).image.draw(buttons.get(i).x, buttons.get(i).y);
		}
	}
	
	//updating the mouse position and lets us know whether the mouse is clicked or not
	public static void update(BasicGameState state){
		int xPos = Mouse.getX();
		int yPos = Windows.scHeight - Mouse.getY();
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
