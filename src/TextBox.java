import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;

public abstract class TextBox {
	
	private final int MAX_CHARS;
	private static final int PADDING = 8;
	
	private int x, y;
	private int width, height;
	int fontSize;
	
	private String content;
	
	private boolean isAlpha;
	private boolean isNumeric;
	private boolean isWhiteSpace;
	
	private static Image notActive;
	private static Image active;
	
	private boolean isActive;
	
	private BasicGameState state;
	
	private static ArrayList<TextBox> textBoxs = new ArrayList<TextBox>();
	
	TextBox(int x, int y, int width, int height, int fontSize, BasicGameState state) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.state = state;
		this.fontSize = fontSize;
		
		this.isActive = false;
		this.isAlpha = true;
		this.isNumeric = true;
		this.isWhiteSpace = true;
		this.content = "";
		
		if (notActive == null) {
			try {
				notActive = new Image("resources/TextField.png");
				active = new Image("resources/TextFieldActive.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		
		// sets a max chars based on the 
		this.MAX_CHARS = (int)(width / 20 * 1.2);
		
		textBoxs.add(this);
	}
	
	/**
	 * Draws all textboxs.
	 * @param g A slick2d graphics object
	 * @param state The state that it has to draw in
	 */
	public static void draw(Graphics g, BasicGameState state) {
		for (TextBox textBox : textBoxs) {
			if (state == textBox.state) {
				if (textBox.isActive) {
					active.draw(textBox.x, textBox.y, textBox.width, textBox.height);
				} else {
					notActive.draw(textBox.x, textBox.y, textBox.width, textBox.height);
				}
				g.setColor(new Color(255, 255, 255));
				Resource.buttonFont[textBox.fontSize].drawString(textBox.x + PADDING * 2, textBox.y + textBox.height / 2 - Resource.buttonFont[textBox.fontSize].getHeight(textBox.content) / 2, textBox.content);
			}
		}
	}
	
	/**
	 * Updates all textboxs.
	 * @param state The state that it has to update in
	 */
	public static void update(BasicGameState state) {
		int x = Mouse.getX();
		int y = Windows.scHeight - Mouse.getY();
		
		if (Mouse.isButtonDown(0)) {
			for (TextBox textBox : textBoxs) {
				if (state == textBox.state) {
					if (x > textBox.x && x < textBox.x + textBox.width && y > textBox.y && y < textBox.y + textBox.height) {
						textBox.isActive = true;
					} else {
						textBox.isActive = false;
					}
				}
			}
		}
	}
	
	/**
	 * This needs to be called in the keyPressed state for all Slick states.
	 * @param key The keycode
	 * @param c The character
	 * @param state The state to register keypresses in
	 */
	public static void keyPress(int key, char c, BasicGameState state) {
		for (TextBox textBox : textBoxs) { 
			if (state == textBox.state) {
				if (textBox.isActive) {
					if (Resource.buttonFont[textBox.fontSize].getWidth(textBox.content) < textBox.width - PADDING * 4) {
						if (Character.isAlphabetic(c) && textBox.isAlpha) {
							textBox.content += c;
						} else if ((Character.isDigit(c) || key == 52) && textBox.isNumeric) { // 52 is '.'
							textBox.content += c;
						} else if (Character.isSpaceChar(c) && textBox.isWhiteSpace) {
							textBox.content += c;
						} 
					}
					
					if (key == 14 && textBox.content.length() > 0) { // blackslash key
						textBox.content = textBox.content.substring(0, textBox.content.length() - 1);
					}
					
					if (key == 28) { // return key
						textBox.onSubmit();
					}
				}
			}
		}
	}
	
	/**
	 * Sets the allowed input on a text box.
	 * @param isAlpha Allows alphabetical characters
	 * @param isNumeric Allows numeric characters
	 * @param isWhiteSpace Allows whitespace
	 */
	public void setPermissions(boolean isAlpha, boolean isNumeric, boolean isWhiteSpace) {
		this.isAlpha = isAlpha;
		this.isNumeric = isNumeric;
		this.isWhiteSpace = isWhiteSpace;
	}
	
	public void activate() {
		isActive = true;
	}
	
	public void deactivate() {
		isActive = false;
	}
	
	/**
	 * Gets the content of the String.
	 * @return The content
	 */
	public String getContent() {
		return content;
	}
	
	public void clear() {
		content = "";
	}
	
	/**
	 * Abstract method that gets called when the enter key is pressed.
	 */
	public abstract void onSubmit();
	
	public static void remove(){
		textBoxs = new ArrayList();
	}
}